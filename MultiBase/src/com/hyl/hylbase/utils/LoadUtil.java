package com.hyl.hylbase.utils;

import android.content.Intent;
import android.os.Bundle;
import com.hyl.hylbase.activity.BaseActivity;
import com.hyl.hylbase.fragment.BaseFragment;

public class LoadUtil {
	public static boolean isLogin = false;
	static BaseActivity fromview;
	static BaseFragment toView;

	public static void loadView(BaseFragment from, BaseFragment to, Bundle values) {
		to.setArguments(values);
		fromview = (BaseActivity)from.getActivity();
		toView = to;
		
		if(to.isLoginUi){
			from.navigationTool.addView(toView);
			return;
		}
		
		if (!isLogin) {
			from.navigationTool.addView(fromview.getLoginFragment());
		} else {
			toNext();
		}

	}

	public static void toNext() {
		if(toView==null||toView.isLoginUi){
			return;
		}
		fromview.getNavigation().addView(toView);
	}

	public static void startActivity(BaseFragment from, Class<?> cls, Bundle values) {
		Intent in = new Intent(from.getContext(), cls);
		in.putExtras(values);
		from.startActivity(in);
	}
	public static void startActivityForResult(BaseFragment from, Class<?> cls, Bundle values,int requestCode) {
		Intent in = new Intent(from.getContext(), cls);
		in.putExtras(values);
		from.startActivityForResult(in,requestCode);
	}
}
