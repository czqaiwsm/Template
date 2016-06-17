package com.base.template;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.base.template.utils.AppManager;

public class BaseActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		//exit app
		//AppManager.getAppManager().finishActivity(this);
		super.onDestroy();
	}
}
