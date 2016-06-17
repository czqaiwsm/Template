package com.hyl.hylbase.fragment;

import com.hyl.hylbase.activity.BaseActivity;
import com.hyl.hylbase.utils.LoadUtil;
import com.hyl.hylbase.utils.NavigationTool;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class BaseFragment extends Fragment implements OnTouchListener,ResultBackListener {
	public NavigationTool navigationTool;
	public ResultBackListener backListener;
	public boolean isLoginUi=false;
	@Override
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		navigationTool = ((BaseActivity) context).getNavigation();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	
	//添加回调监听
	public void addResultBackListener(ResultBackListener listener){
		this.backListener=listener;
	}
	//发出回调响应
	public void setFragmentResult(int resCode,Bundle data){
		if(backListener!=null){
			backListener.onFragmentResult(resCode,data);
		}
	}

	@Override
	public void onFragmentResult(int resCode,Bundle data) {
		// TODO Auto-generated method stub
	};
	
	public void finish(){
		BaseFragment parent=(BaseFragment)this.getParentFragment();
		if(parent==null){
			navigationTool.removeView(this);
		}else{
			navigationTool.removeView(parent);
		}
	}
	
	public void backToViewByStep(int step){
		navigationTool.removeViewByStep(step);
	}
	
	public void startFragment(BaseFragment to, Bundle values,boolean needLogin){
		if(needLogin){
			LoadUtil.loadView(this, to, values);
		}else{
			to.setArguments(values);
			navigationTool.addView(to);
		}
	}
	
	public void startFragmentForResult(BaseFragment to, Bundle values,boolean needLogin){
		to.addResultBackListener(this);
		startFragment(to, values,needLogin);
	}
	
	public void onrefresh(){}
	
}
