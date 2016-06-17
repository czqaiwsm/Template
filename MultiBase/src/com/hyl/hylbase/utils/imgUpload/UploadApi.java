package com.hyl.hylbase.utils.imgUpload;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;


public class UploadApi {

	public static String token;
	public static String mid;
	 private  RequestQueue mRequestQueue ;

    public UploadApi(Context context){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context) ;
        }
        mRequestQueue.start();
    }
    /**
     * 上传图片接口
     * @param bitmap 需要上传的图片
     * @param listener 请求回调
     */
    public void uploadImg(String uploadUrl,Bitmap bitmap,ResponseListener listener){
        List<FormImage> imageList = new ArrayList<FormImage>() ;
        imageList.add(new FormImage(bitmap)) ;
        Request request = new PostUploadRequest(uploadUrl,imageList,listener) ;
        mRequestQueue.add(request);
    }
}
