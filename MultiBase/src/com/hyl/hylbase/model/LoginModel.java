package com.hyl.hylbase.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.hyl.hylbase.utils.HttpUtil;
import com.hyl.hylbase.utils.Urls;
import com.hyl.hylbase.utils.HttpUtil.CallBack;

import android.content.Context;

public class LoginModel {
	Context mContext;
	HttpUtil http;
	public LoginModel(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext=context;
		http=new HttpUtil(context);
		http.setShowDialog(true);
	}
	
	public void login(String name,String password,final LoginCallBack callBack ){
		JSONObject params=new JSONObject();
		try {
			params.put("name", name);
			params.put("pass", password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		http.getJson(Urls.loginUrl, params, new CallBack() {
			
			@Override
			public void success(JSONObject result) {
				// TODO Auto-generated method stub
				//处理逻辑后回调
				callBack.onLoginSuccess();
			}
			
			@Override
			public void error(JSONObject result) {
				// TODO Auto-generated method stub
				//处理逻辑后回调
				callBack.onLoginFailed(null);
			}
		});
	}
	public interface LoginCallBack {

		public void onLoginSuccess();

		public void onLoginFailed(String msg);

	}
}
