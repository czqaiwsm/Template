package com.hyl.hylbase.pay.alipay;

public interface IAliPayCallBack {

	void onCancel();

	void onSuccess();

	void onFail(String message);

	void onError(String message);

}
