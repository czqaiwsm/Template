package com.hyl.hylbase.fragment;

import com.hyl.hylbase.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class Fragment2 extends BaseFragment {
	  private View view;
		private ViewPager mViewPager;
		private RadioGroup rg;
		private TabFragmentPagerAdapter mAdapter;
		public boolean isscroll=true,ischeck=true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){  
			view=inflater.inflate(R.layout.fragment2, null);  
        }else{
        	return view;
        }
		mViewPager=(ViewPager)view.findViewById(R.id.mViewPager);
		rg=(RadioGroup)view.findViewById(R.id.rg);
		mAdapter = new TabFragmentPagerAdapter(this.getActivity().getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		rg.getChildAt(0).setId(0);
		rg.getChildAt(1).setId(1);
		rg.getChildAt(2).setId(2);
		((RadioButton)rg.getChildAt(0)).setChecked(true);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				// TODO Auto-generated method stub
				if(!ischeck){
					ischeck=true;
				}else{
						isscroll=false;
						mViewPager.setCurrentItem(checkedId);
				}
				
			}
		});
	
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				if(!isscroll){
					isscroll=true;
				}else{
						ischeck=false;
						((RadioButton)rg.getChildAt(position)).setChecked(true);
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
        return view;  
	}
	
	
	public static class TabFragmentPagerAdapter extends FragmentPagerAdapter{

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new LaunchUIFragment();
				break;
			case 1:
				ft = new CommonUIFragment();
				Bundle b=new Bundle();
				b.putString("q", "1");
				ft.setArguments(b);
				break;
			default:
				ft = new CommonUIFragment();
				Bundle b1=new Bundle();
				b1.putString("q", "2");
				ft.setArguments(b1);
				break;
			}
			return ft;
		}

		@Override
		public int getCount() {
			
			return 3;
		}
		
	}


}