package com.zoesap.collecttreasure.activity;

import android.os.Handler;

import com.zoesap.collecttreasure.R;
import com.zoesap.collecttreasure.base.BaseActivity;
import com.zoesap.collecttreasure.util.ActivityUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acty_splash;
    }

    @Override
    protected void setData() {
        super.setData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtil.switchTo(activity,MainActivity.class);
                close();
            }
        },2000);
    }
}
