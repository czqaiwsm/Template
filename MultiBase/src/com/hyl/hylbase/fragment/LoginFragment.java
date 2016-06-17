package com.hyl.hylbase.fragment;

import com.hyl.hylbase.R;
import com.hyl.hylbase.model.LoginModel;
import com.hyl.hylbase.model.LoginModel.LoginCallBack;
import com.hyl.hylbase.utils.LoadUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class LoginFragment extends BaseFragment implements OnClickListener {
	public LoginFragment(){
		isLoginUi=true;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		LoginModel model=new LoginModel(getContext());
		
		model.login("", "", new LoginCallBack() {
			
			@Override
			public void onLoginSuccess() {
				// TODO Auto-generated method stub
				finish();
				LoadUtil.isLogin=true;
				Bundle data=new Bundle();
				data.putString("res", "login back");
				setFragmentResult(1,data);
				LoadUtil.toNext();
				
			}
			
			@Override
			public void onLoginFailed(String msg) {
				// TODO Auto-generated method stub
				finish();
				LoadUtil.isLogin=true;
				Bundle data=new Bundle();
				data.putString("res", "login back");
				setFragmentResult(1,data);
				LoadUtil.toNext();
				
			}
		});
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.login, null);

		view.findViewById(R.id.login).setOnClickListener(this);

		return view;
//		return super.getView();
	}

}
