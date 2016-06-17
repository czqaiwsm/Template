package com.hyl.hylbase;

import android.app.Application;
import com.hyl.hylbase.utils.Constant;
import com.hyl.hylbase.utils.ScreenUtils;

/**
 * @desc 请用一句话描述此文件
 * @creator caozhiqing
 * @data 2016/5/11
 */
public class BaseApplication extends Application {

    protected static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Constant.SCREENW = ScreenUtils.getScreenWidth(this);
        Constant.SCREENH = ScreenUtils.getScreenHeight(this);
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
