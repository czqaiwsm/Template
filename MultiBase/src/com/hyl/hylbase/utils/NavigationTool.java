package com.hyl.hylbase.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.hyl.hylbase.R;
import com.hyl.hylbase.activity.BaseActivity;
import com.hyl.hylbase.fragment.BaseFragment;
import com.hyl.hylbase.fragment.LoginFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * @author zl 导航
 * 
 */
public class NavigationTool {
	List<BaseFragment> fragments;
	BaseActivity context;
	int animIn=-1;
	int animOut=-1;
	int view;

	public NavigationTool(int view, BaseActivity context) {
		this.context = context;
		this.view = view;
		fragments = new ArrayList<BaseFragment>();
	}

	/**
	 * 设置界面切换动画
	 * @param animIn
	 * @param animOut
	 */
	public void setAnimations(int animIn,int animOut){
		this.animIn=animIn;
		this.animOut=animOut;
	}
	
	public void addView(BaseFragment fragment) {
		// for(BaseFragment base:fragments){
		// base.getView().setVisibility(View.GONE);
		// }
		BaseFragment lastfragment = null;
		if (fragments.size() > 0) {
			lastfragment = fragments.get(fragments.size()-1);
		}
		if (lastfragment != null) {
//			lastfragment.getView().setVisibility(View.GONE);
		}
		//
		fragments.add(fragment);
		FragmentManager fm = context.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		if(animIn!=-1){
			transaction.setCustomAnimations(animIn,animOut,animIn,animOut);
		}else if(fragment.isLoginUi){
			transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom,R.anim.slide_in_bottom, R.anim.slide_out_bottom);
		}else{
			transaction.setCustomAnimations(R.anim.push_left_in,
					R.anim.push_right_out, R.anim.push_left_in,
					R.anim.push_right_out);

		}
		transaction.add(view, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
	}

	public boolean removeView() {
		if (fragments.size() == 0) {
			return false;
		}
		return removeView(fragments.get(fragments.size()-1));
	}
	
	public boolean removeView(BaseFragment fragment) {
		if (fragments.size() == 0) {
			return false;
		}

		FragmentManager fm = context.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		
		if(animIn!=-1){
				transaction.setCustomAnimations(animIn,animOut,animIn,animOut);
			
		}else if(fragments.get(fragments.size()-1).isLoginUi){
		}else if(step>1){
			
		}else{
			transaction.setCustomAnimations(R.anim.push_left_in,
					R.anim.push_right_out, R.anim.push_left_in,
					R.anim.push_right_out);
		}
		transaction.remove(fragment);
		fragments.remove(fragment);
		transaction.commit();
		if(step==1){
			if (fragments.size() > 0) {
				fragments.get(fragments.size()-1).getView().setVisibility(View.VISIBLE);
				fragments.get(fragments.size()-1).onrefresh();
			}else{
				context.onrefresh();
			}
		}
		
		//初始化
		animIn=-1;
		animOut=-1;
		if(step>1){
			step--;
		}
		
		return true;
	}
	int step=1;

	public void removeViewByStep(int step) {
		this.step=step;
		if (step>fragments.size()) {
			return;
		}
		for(int i=step;i>0;i--){
			BaseFragment fragment=fragments.get(fragments.size()-i);
			removeView(fragment);
		}

	}

}
