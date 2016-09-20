package com.zoesap.collecttreasure.activity;


import com.zoesap.collecttreasure.R;
import com.zoesap.collecttreasure.activity.connect.ConnectFragment;
import com.zoesap.collecttreasure.activity.district.DistrictFragment;
import com.zoesap.collecttreasure.activity.home.HomeFragment;
import com.zoesap.collecttreasure.activity.user.UserFragment;

/**
 * Created by Dubei on 2016/9/20.
 * 邮箱：370318201@qq.com
 */
public enum MainTab {

    Home(0, R.string.tab_main_home ,R.drawable.tab_icon_home, HomeFragment.class),
    Connection(1, R.string.tab_main_connection ,R.drawable.tab_icon_connect, ConnectFragment.class),
    District(2, R.string.tab_main_district,R.drawable.tab_icon_district, DistrictFragment.class),
    User(3, R.string.tab_main_user ,R.drawable.tab_icon_user, UserFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx,int resName,int resIcon,Class<?> clz){
        this.idx=idx;
        this.resName=resName;
        this.resIcon=resIcon;
        this.clz=clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
