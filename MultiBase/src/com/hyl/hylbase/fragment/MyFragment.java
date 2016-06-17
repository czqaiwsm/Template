package com.hyl.hylbase.fragment;


import com.hyl.hylbase.R;
import com.hyl.hylbase.utils.L;
import com.hyl.hylbase.utils.T;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class MyFragment extends BaseFragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.my,null);
		view.findViewById(R.id.back).setOnClickListener(this);
		L.e(getArguments().getString("req"));
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Bundle data=new Bundle();
		data.putString("res", "my back");
		setFragmentResult(1,data);
		finish();
	}

}
