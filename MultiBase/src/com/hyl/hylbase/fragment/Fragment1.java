package com.hyl.hylbase.fragment;

import java.util.ArrayList;

import com.android.volley.VolleyError;
import com.hyl.hylbase.R;
import com.hyl.hylbase.adapter.BannerViewAdapter;
import com.hyl.hylbase.adapter.ViewPagerScroller;
import com.hyl.hylbase.bean.BannerImgInfo;
import com.hyl.hylbase.utils.L;
import com.hyl.hylbase.utils.LoadUtil;
import com.hyl.hylbase.utils.imgUpload.CustomHeadPortrait;
import com.hyl.hylbase.utils.imgUpload.Download;
import com.hyl.hylbase.utils.imgUpload.ResponseListener;
import com.hyl.hylbase.utils.imgUpload.UploadApi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends BaseFragment implements OnClickListener {
	  private View view;
	  private ImageView img;
	  CustomHeadPortrait chp;
	  ViewPager id_guide_viewpager;//banner
	  private ArrayList<BannerImgInfo> bannerImgInfos = new ArrayList<BannerImgInfo>();
	  private BannerViewAdapter guideAdapter = null;
	  LayoutInflater inflater;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater=inflater;
		if(view==null){  
			view=inflater.inflate(R.layout.fragment1, null);  
        }  
		view.findViewById(R.id.tohome).setOnClickListener(this);
		
		img=(ImageView)view.findViewById(R.id.imgview);
		 id_guide_viewpager = (ViewPager)view.findViewById(R.id.id_guide_viewpager);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//图片 选择 拍照，裁剪
				chp=new CustomHeadPortrait(Fragment1.this);
//				chp.outputX=200;
//				chp.outputY=100;
				chp.show();
			}
		});
        return view;  
	}
	
	   @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        initGuidBanner();
	    }
	
	 private void initGuidBanner() {
	        if (view == null) {
	            return;
	        }
	        BannerImgInfo img1=new BannerImgInfo();
	        bannerImgInfos.add(img1);
	        BannerImgInfo img2=new BannerImgInfo();
	        img2.setUrl("http://img2.3lian.com/2014/f2/181/119.jpg");
	        bannerImgInfos.add(img2);
	        BannerImgInfo img3=new BannerImgInfo();
	        img3.setUrl("http://img1.3lian.com/2015/w7/68/108.jpg");
	        bannerImgInfos.add(img3);
	        BannerImgInfo img4=new BannerImgInfo();
	        img4.setUrl("http://img2.3lian.com/2014/f2/181/119.jpg");
	        bannerImgInfos.add(img4);
	        BannerImgInfo img5=new BannerImgInfo();
	        img5.setUrl("http://img1.3lian.com/2015/w7/68/108.jpg");
	        bannerImgInfos.add(img5);
	        guideAdapter = new BannerViewAdapter(bannerImgInfos, getActivity(),null,false);

	        guideAdapter.setDotAlignBottom(10);
	        guideAdapter.setAutoPlay(id_guide_viewpager, true);
	        
	     // pageCount设置红缓存的页面数     
	        if(bannerImgInfos.size()>4){
	        	 id_guide_viewpager.setOffscreenPageLimit(2);    
	        }
	       
	        // 设置2张图之前的间距。    
	        id_guide_viewpager.setPageMargin(30);  
	        id_guide_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	            @Override
	            public void onPageSelected(int position) {
//	                int pos = position % ids.length;
	                guideAdapter.moveCursorTo(position);// 点的移动
	            }

	            @Override
	            public void onPageScrolled(int position, float positionOffset,
	                                       int positionOffsetPixels) {
	            }
	           
	            @Override
	            public void onPageScrollStateChanged(int state) {
	            }
	        });

	        id_guide_viewpager.setAdapter(guideAdapter);
	        if (bannerImgInfos != null && bannerImgInfos.size() > 1) {
	            id_guide_viewpager.setCurrentItem(bannerImgInfos.size()*10);
	        }
	        ViewPagerScroller scroller = new ViewPagerScroller(getContext());
	        scroller.initViewPagerScroll(id_guide_viewpager);//这个是设置切换过渡
	    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
//		MyFragment my = new MyFragment();
//		Bundle data=new Bundle();
//		data.putString("req", "from main");
//		startFragmentForResult(my, data,true);
//		LoginFragment loginView = new LoginFragment();
//		startViewForResult(loginView, null);
		
		Fragment2 f=new Fragment2();
		startFragment(f, null, false);
	}
	
	@Override
	public void onFragmentResult(int resCode,Bundle data) {
		// TODO Auto-generated method stub
//		L.e("login back");
		L.e(data.getString("res"));
		((TextView)getView().findViewById(R.id.text)).setText("hello "+ data.getString("res"));
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 图片回调结果处理
//		super.onActivityResult(requestCode, resultCode, data);
		chp.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == CustomHeadPortrait.PHOTO_REQUEST_CUT) {
			try {
				Bitmap bitmap = data.getParcelableExtra("data");
				img.setImageBitmap(bitmap);
			//  在回调里处理 1、保存图片到本地 2、上传头像
				Download.saveHeadFile(bitmap,"head.jpg"); 
				chp.uploadImg("http://chuantu.biz/upload.php",bitmap,new ResponseListener<String>() {
		            @Override
		            public void onErrorResponse(VolleyError error) {
		                L.v("hyl","===========VolleyError========="+error) ;
		            }
		            @Override
		            public void onResponse(String response) {
		                L.v("hyl","===========onResponse========="+response) ;
		            }
		        });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
