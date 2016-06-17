package com.hyl.hylbase.pay.alipay;

public class AlipayConstants {


	public static String ALIPAY_NOTIFY_URL = ""; // 支付宝回调

	// /////////////// 支付宝相关 ///////////////////////
	/*
	 * 提示：如何获取安全校验码和合作身份者id 1.用您的签约支付宝账号登录支付宝网站(www.alipay.com) 2.点击“商家服务”(https://b.alipay.com/order/myorder.htm) 3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
	 * 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。这里签名时，只需要使用生成的RSA私钥。Note:
	 * 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
	 */
	// 商户PID，以2088开头的16位纯数字
	public static String ALIPAY_PARTNER = "2088321033642736";
	// 收款支付宝账号
	public static String ALIPAY_SELLER = "srh_004@126.com";
	// 商户私钥，自助生成
	public static String ALIPAY_RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANGSSTqdnTEulB53DjYoNMGXEQp4g9kwkZpxz7FsCWE6QxqRoLl0YJtgC42U/OkEmB1kYZvQo/jZ2iFkHlsbczl9xptrioIl7MjqdUqBgSB/wcRD1Cf5dca9IheHNs97JMy0f+JfMHN/sKzstnaz2CqDXB4BGP9T972g6lLssET9AgMBAAECgYBYe2D7pIw3tajH7BBOAU+05KlHOHbCfmUbRi4ghLK5IDYww4eOhXo8X6LBFkRpxYzL7BPiC4cPjDsymejPIpnUycnhXLyfpbpQ3tjPypvNDk5ZNFuPECbUzgA36Da6YpfmbMmCaHd9OB5X9NLD7lrCMRDv9SQVZX9Ztn/Vg71IaQJBAP1yDCTCr2nzNWSVcJ4mv7eK6o4ZfdE9x62Fb/ahYGuttK9kPGh1bzEtq+CY1ZzOJtfoeB/FBmXAwIjxmRgbd1sCQQDTrwhXUrWIM538PwKezN04F/2ZyAefWN5k4u7M5nTXAesa2b7HENJjF41G283lVOApnmHcCRnfBCqA2vQCoTyHAkEA61HjOJaTLHqnDpc6k31C8PZIxlug2JDWP6Tvyj0YO6Jza45UEGFJNo5DUixV2lwG8N1l+4mCYSPRINXB4gJOlQJBAKHL6ygzg6UzLtIapzoRhBTX4XKlPY5CMtoRyky66Rtd0AGZ5QPqi6RJ4CYE438iPKQS9uFj425elM4gjNllvAkCQQDlo9zeDhNaQGx3AaV/kBq7J2/mFp9Gu8JvtkX1AgfISQPy3bERofZ/2IpiXFWTPRBuxESYEgxgUKOi4Yy2RUUZ";






	// public static final String ZHIFUBAO_SELLER = "ahhyc001@126.com";
	// public static final String ZHIFUBAO_PARTNER = "2088811720422661";
//	 public static final String ZHIFUBAO_RSA_PRIVATE =
//	 "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN2pxeIhxH9L2vDHZdOqy2oFNHI4xW/RIF8Gc0hLbAt5C1R0MB+/jYVLnWsFyDHRiTXdN2JIzlhVunjD/V8IriH+FM/tw5M5NZwkKeY7JaDXUZs4PAFsFiSiGsBe8DcvsSByuTGsU7Y8OFC26ecjLEEbZF9Yprs54x0DU5vx1m4JAgMBAAECgYEAhhHEjXStG+1ufEmubisUo4iIYzmxOWN8t9z351cu/3V0A93OqbU7TM8iZHP31SIOYpTthbXRkJ4xjg/E9TET2H1iJWFjzqw+J60pCW5lm3EbvRQOzbwbNF3Vo6shVj9iyYA4RESf5oAuYn7uwHscq0lQPgzCjhnquvcPvkyfsE0CQQDu69uUiFj1bGEWfzsumHhQkjT/qk0XRQieXYp/amEyV3zWABHS1GvfF88ro2+1xZIeFZJhv97YvZMEPs75exGHAkEA7YIZiCtBf2xIloG2XHP8JZgwmWEvRZatosLt4lUFkCwvuYQDyFfKcaiahCTKU6hMExGec62uGlHX+aA57O6n7wJBALqaeAuQWnqExWd+w8hmyUGUj9RHPKK6Bjs6L9vISFW7QwMZVtJlmkLKI8KfibZcsvzQOF4kL3b0yYh9TcZIuq0CQFQIuCfpxh7jnTtDYdaMYyaaFnfuXt9sVLtF5Q0zr+g2+hvVjdEQ3twgH6upqLy4y1Hj5gBf7z34v0XfXx9pwZkCQBz73vU9DXDHYPjSGdu3OygAn8rOSRrOFUMGeyaTdKJKu57utz4hFbRJQ4TBHZiFxz5eaH6bjUDyiYdH791mOEE=";
	 // 生活很忙

	// 支付宝公钥
	public static String ALIPAY_RSA_PUBLIC =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	;
	// public static final String ZHIFUBAO_RSA_PUBLIC =
//	 "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	 // 生活很忙

}
