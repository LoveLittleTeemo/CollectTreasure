package com.zoesap.collecttreasure;

import android.app.Application;
import android.util.DisplayMetrics;

/**
 * Created by Dubei on 2016/9/19.
 * 邮箱：370318201@qq.com
 */
public class AppContext extends Application {

    protected static AppContext mInstance;

    private DisplayMetrics displayMetrics = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=AppContext.this;
    }

    public static AppContext getApp() {
        if (mInstance != null && mInstance instanceof AppContext) {
            return (AppContext) mInstance;
        } else {
            mInstance = new AppContext();
            mInstance.onCreate();
            return (AppContext) mInstance;
        }
    }

    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }

    public int getScreenHeight() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.heightPixels;
    }

    public int getScreenWidth() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.widthPixels;
    }

    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }

    public int dp2px(float f)
    {
        return (int)(0.5F + f * getScreenDensity());
    }

    public  int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }
}
