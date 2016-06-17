package com.base.template;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.base.template.utils.AppManager;
import com.base.template.viewpagerAdapter.Wz_myViewPagerAdapter;

public class MyActivity extends BaseActivity {
    /**
     * Called when the activity is first created.
     */

    private ViewPager m_obj_viewPager = null;
    private PagerAdapter m_obj_adapter = null;
    private View m_obj_Layoutview = null;
    private int ids[] = { R.drawable.guide_01, R.drawable.guide_02,
            R.drawable.guide_03 , R.drawable.guide_02};
    private int TO_THE_END = 0;// 到达最后一张图片
    private int LEAVE_FROM_END = 1;// 离开最后一张图片


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        m_obj_adapter = new Wz_myViewPagerAdapter(ids, findViewById(R.id.banner),
               this);
        m_obj_viewPager = (ViewPager)findViewById(R.id.id_guide_viewpager);
        m_obj_viewPager.setAdapter(m_obj_adapter);
        ((Wz_myViewPagerAdapter)m_obj_adapter).setDotAlignBottom(8);
        ((Wz_myViewPagerAdapter)m_obj_adapter).setAutoPlay(m_obj_viewPager, true);
        m_obj_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int pos = position % ids.length;
                ((Wz_myViewPagerAdapter) m_obj_adapter).moveCursorTo(pos);// 点的移动
//                if (pos == ids.length - 1) {
//                    ((Wz_myViewPagerAdapter)m_obj_adapter).setAutoPlay(m_obj_viewPager,false);
//
//                    handler.sendEmptyMessageDelayed(TO_THE_END, 1000);// 延时3s调到主界面
//
//                } else {
//                    handler.sendEmptyMessage(LEAVE_FROM_END);
//                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
      /*  Log.e(">>>>>>>>","width:"+imageView.getWidth());
        imageView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        Log.e(">>>>>>>>","WIDTH:"+imageView.getWidth());
                        return false;
                    }
                }
        );*/
    }


    private long firstTime = 0;
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {// 如果两次按键时间间隔大于2秒，则不退出
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;// 更新firstTime
        } else {//否则退出程序
            AppManager.getAppManager().finishActivity(this);
            System.gc();
            System.exit(0);
        }
    }
}
