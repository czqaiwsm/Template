package com.hyl.hylbase.pay.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.hyl.hylbase.R;
import com.hyl.hylbase.pay.alipay.utils.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class PaymentImpl {

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;

	private static final String PAY_SUCCESS = "9000";// 支付成功
	private static final String PAY_IN_TREATMENT = "8000";// 正在处理中
	private static final String PAY_FAIL = "4000";// 支付失败
	private static final String PAY_CANCEL = "6001";// 用户中途取消
	private static final String PAY_NETWORK_ERROR = "6002";// 网络链接出错

	private Context mContext;
	private String mOrderId; // 订单ID
	private String mOrderPrice; // 订单金额
	private String mOrderName; // 订单名称
	private String mOrderDesc; // 订单描述
	private String mNotifyUrl; // 支付宝回调地址

	private IAliPayCallBack mIPayCallBack;


	/**
	 * @param orderId 订单ID
	 * @param orderPrice 订单金额
	 * @param orderName 商品名称
	 * @param orderDesc 商品描述
	 * @param notifyUrl 支付宝回调地址
	 * @param iPayCallBack 支付宝完成后的回调接口
	 */
	public PaymentImpl(Context context, String orderId, String orderName, String orderDesc, String orderPrice, String notifyUrl,
			IAliPayCallBack iPayCallBack) {
		mContext = context;
		mOrderId = orderId;
//		mOrderId = getOutTradeNo();
		mOrderPrice = orderPrice;
		mOrderName = orderName;
		mOrderDesc = orderDesc;
		mNotifyUrl = notifyUrl;
		mIPayCallBack = iPayCallBack;
	}


	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 *
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);

				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();

				String resultStatus = payResult.getResultStatus();
				Log.i("payInfo:","resultStatus:"+resultStatus+"  resultInfo:"+resultInfo);
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, PAY_SUCCESS)) {
					Toast.makeText(mContext, R.string.alipay_success, Toast.LENGTH_SHORT).show();
					mIPayCallBack.onSuccess();
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, PAY_IN_TREATMENT)) {
						Toast.makeText(mContext, R.string.alipay_confirmation, Toast.LENGTH_SHORT).show();
					} else if (TextUtils.equals(resultStatus, PAY_CANCEL)) {
						Toast.makeText(mContext, R.string.alipay_cancel, Toast.LENGTH_SHORT).show();
						// mIPayCallBack.onCancel();
					} else if (TextUtils.equals(resultStatus, PAY_NETWORK_ERROR)) {
						Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_SHORT).show();
						// mIPayCallBack.onError(mContext.getResources().getString(R.string.network_error));
					} else {
						Toast.makeText(mContext, R.string.alipay_fail, Toast.LENGTH_SHORT).show();
						// mIPayCallBack.onError(mContext.getResources().getString(R.string.alipay_fail));
					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(mContext, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay() {
		// 订单
		String orderInfo = getOrderInfo();
		Log.i(">>>>","orderInfo="+orderInfo);
		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
		Log.i(">>>>","sign="+orderInfo);

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask((Activity) mContext);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo,true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * check whether the device has authentication alipay account. 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check(View v) {
		Runnable checkRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask((Activity) mContext);
				// 调用查询接口，获取查询结果
//				boolean isExist = payTask.checkAccountIfExist();

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
//				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();

	}

	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask((Activity) mContext);
		String version = payTask.getVersion();
		Toast.makeText(mContext, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo() {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConstants.ALIPAY_PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + AlipayConstants.ALIPAY_SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + mOrderId + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + mOrderName + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + mOrderDesc + "\"";

		// 商品金额
//		orderInfo += "&total_fee=" + "\"" + mOrderPrice + "\"";
		orderInfo += "&total_fee=" + "\"" + 0.01 + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + mNotifyUrl + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content 待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, AlipayConstants.ALIPAY_RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
