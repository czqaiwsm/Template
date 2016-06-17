package com.hyl.hylbase.utils;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
public class HttpUtil {
	private Context mContext;
	private RequestQueue mRequestQueue;
	
	
	private boolean showDialog=true;

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public HttpUtil(Context mContext) {
		this.mContext = mContext;
		mRequestQueue = Volley.newRequestQueue(mContext);
	}

	public void getJson(String url, JSONObject data, final CallBack callback) {

		// 开始请求loading
		if(showDialog){
		}
		
		JsonObjectRequest mJsonObjectRequest;
		mJsonObjectRequest = new JsonObjectRequest(Method.POST, url, data,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// 结束loading
						if(showDialog){
						closeDialog();
						}
						System.out.println("请求结果:" + response.toString());
						// 统一错误处理
						systemError(1);
						// 自处理回调
						callback.success(response);
						callback.error(response);

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// 结束loading
						
						if(showDialog){
							closeDialog();
						}
						System.out.println("请求错误:" + error.toString());
						callback.error(null);
					}
				});
		mJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(mJsonObjectRequest);
	}

	public interface CallBack {
		public void success(JSONObject result);

		public void error(JSONObject result);
	}

	// 系统错误统一处理
	private void systemError(int resCode) {
		switch (resCode) {
		case 1:

			break;

		default:
			break;
		}
	}
	
	private void closeDialog(){
	}
}
