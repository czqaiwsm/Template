package com.hyl.hylbase.fragment;

import com.hyl.hylbase.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends BaseFragment {
	  private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){  
			view=inflater.inflate(R.layout.fragment3, null);  
        }  
        return view;  
	}
}