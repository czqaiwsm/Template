package com.hyl.hylbase.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.download.base.utils.ScreenUtils;
import com.download.update.UpdateMgr;
import com.hyl.hylbase.BaseApplication;
import com.hyl.hylbase.R;
import com.hyl.hylbase.fragment.BaseFragment;
import com.hyl.hylbase.fragment.Fragment1;
import com.hyl.hylbase.fragment.Fragment2;
import com.hyl.hylbase.fragment.Fragment3;


public class MainActivity extends BaseActivity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */

    // 未读消息textview
    private TextView unreadLabel;
    // 未读通讯录textview
    private TextView unreadAddressLable;

    private Button[] mTabs;
    private BaseFragment[] fragments;

    private Fragment1 homePageFragment;
    private Fragment2 msgInfosFragment;
    private Fragment3 pCenterFragment;

    private final int VIEW_COUNT = 3;
    private int index;
    // 当前fragment的index
    private int currentTabIndex;
    BaseFragment currentFragment = null;
    int i=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.getScreenSize(this);
        UpdateMgr.getInstance(this).checkUpdateInfo(null, false);
        setContentView(R.layout.main_activity);
        fragments = new BaseFragment[VIEW_COUNT];
        fragments[0] = homePageFragment =new Fragment1();
        fragments[1] = msgInfosFragment = new Fragment2();
        fragments[2] = pCenterFragment = new Fragment3();

        initView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.length;i++){
            transaction.add(R.id.fragment_container,fragments[i]).hide(fragments[i]);

        }
        currentFragment = fragments[0];
        transaction.show(currentFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        mTabs = new Button[VIEW_COUNT];
        mTabs[0] = (Button) findViewById(R.id.btn_conversation);
        mTabs[1] = (Button) findViewById(R.id.btn_address_list);
        mTabs[2] = (Button) findViewById(R.id.btn_center);
        // 把第一个tab设为选中状态
        currentTabIndex = 0;
        mTabs[currentTabIndex].setSelected(true);

        for(int i=0;i<mTabs.length;i++){
            mTabs[i].setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        int index = 0;
        switch (v.getId()){
            case R.id.btn_conversation:
                currentFragment = fragments[0];
                index = 0;
                break;
            case R.id.btn_address_list:
                currentFragment = fragments[1];
                index = 1;
                break;
            case R.id.btn_center:
                currentFragment = showSetting();
                index = 2;
                break;
        }

        if(currentTabIndex != index){
            mTabs[currentTabIndex].setSelected(false);
            mTabs[index].setSelected(true);
            hidShow(currentFragment);
            currentTabIndex = index;
            currentFragment.onrefresh();
        }
    }

    private void hidShow(Fragment newf){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.length;i++){
            transaction.hide(fragments[i]);
        }
        transaction.show(newf);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 显示购物车界面
     * 1.未登录
     * 2.已登录，购物车为空
     * 3.已登录，购物车不为空
     */
    private Fragment showShopCar(){
        return null;
    }
    
    @Override
    public void onrefresh() {
    	currentFragment.onrefresh();
    }

    /**
     * @return
     */
    private BaseFragment showSetting(){
            return pCenterFragment;
    }
    private long firstTime = 0;
    /*public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {// 如果两次按键时间间隔大于2秒，则不退出
                SmartToast.makeText(this,
                        getString(R.string.exit), Toast.LENGTH_SHORT).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            } else {
                System.gc();
                System.exit(0);// 否则退出程序
            }
        }
        return super.onKeyUp(keyCode, event);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BaseApplication.getInstance().locationUitl.stopLocation();
    }
    
    @Override
    public BaseFragment getLoginFragment() {//todo 在项目中要配置登录的界面
//    	LoginRegFramgent lf=new LoginRegFramgent();
//    	return lf;
        return null;
    }
}
