package com.hyl.hylbase.activity;

import com.hyl.hylbase.fragment.BaseFragment;
import com.hyl.hylbase.utils.AppManager;
import com.hyl.hylbase.utils.NavigationTool;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import com.toast.ToasetUtil;

public class BaseActivity extends FragmentActivity {
	private NavigationTool navigation;
	public ToasetUtil toasetUtil = null;

	public NavigationTool getNavigation() {
		return navigation;
	}

	public void setNavigation(NavigationTool navigation) {
		this.navigation = navigation;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		navigation = new NavigationTool(android.R.id.content, this);
		toasetUtil  = new ToasetUtil(this);
		AppManager.getAppManager().addActivity(this);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (getNavigation().removeView()) {
				return true;
			}
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private long clickTime = 0; // 记录第一次点击的时间

	private void exit() {
		if ((System.currentTimeMillis() - clickTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
					Toast.LENGTH_SHORT).show();
			clickTime = System.currentTimeMillis();
		} else {
			Log.e("MainActivity", "exit application");
			this.finish();
			 System.exit(0);
		}
	}
	
	public BaseFragment getLoginFragment(){
		return null;
	};
	public void onrefresh(){
	}

	@Override
	protected void onDestroy() {

		//exit app
		AppManager.getAppManager().finishActivity(this);
		super.onDestroy();
	}
}
