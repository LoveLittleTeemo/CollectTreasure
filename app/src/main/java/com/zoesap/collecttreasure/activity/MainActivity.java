package com.zoesap.collecttreasure.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zoesap.collecttreasure.R;
import com.zoesap.collecttreasure.base.BaseActivity;

/**
 * 作者：Dubei on 2016/9/19 18:00
 * 邮箱：370318201@qq.com
 */
public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener, IMainTab {

    private FragmentTabHost mTabHost;

    @Override
    protected int getLayoutId() {
        return R.layout.acty_main;
    }

    @Override
    protected void initData() {
        super.initData();
        //在5.0系统以上设置内容布局全屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void initView() {
        super.initView();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initTabs();
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.frame_content);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));

            View indicator = inflateView(R.layout.main_tab_indicator);
            ImageView icon = (ImageView) indicator.findViewById(R.id.tab_icon);
            icon.setImageResource(mainTab.getResIcon());

            TextView title = (TextView) indicator.findViewById(R.id.tab_titile);
            title.setText(mainTab.getResName());
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });

            mTabHost.addTab(tab, mainTab.getClz(), null);
//            if (mainTab.equals(MainTab.Topic)) {
//                View con = indicator.findViewById(R.id.container);
//                // con.setBackgroundColor(Color.parseColor("#00ff00"));
//                mBvTweet = new BadgeView(this, con);
//                mBvTweet.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//                mBvTweet.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//                mBvTweet.setBackgroundResource(R.drawable.tab_notification_bg);
//            }
        }

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    @Override
    public int getCurrentTab() {
        return mTabHost.getCurrentTab();
    }

    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.findViewById(R.id.tab_icon).setSelected(true);
                v.findViewById(R.id.tab_titile).setSelected(true);
            } else {
                v.findViewById(R.id.tab_icon).setSelected(false);
                v.findViewById(R.id.tab_titile).setSelected(false);
            }
        }
//        supportInvalidateOptionsMenu();
    }

    public void setData() {
        super.setData();
    }
}
