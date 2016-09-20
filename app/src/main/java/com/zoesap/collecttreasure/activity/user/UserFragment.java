package com.zoesap.collecttreasure.activity.user;

import android.view.LayoutInflater;
import android.view.View;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.zoesap.collecttreasure.AppContext;
import com.zoesap.collecttreasure.R;
import com.zoesap.collecttreasure.base.BaseFragment;

/**
 * 个人
 * 作者：Dubei on 2016/9/20 10:04
 * 邮箱：370318201@qq.com
 */
public class UserFragment extends BaseFragment{

    private PullToZoomScrollViewEx scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        scrollView=(PullToZoomScrollViewEx)view.findViewById(R.id.scroll_view);
    }

    @Override
    protected void setData() {
        super.setData();
        initScroll();
    }

    public void initScroll(){
        View headView = LayoutInflater.from(activity).inflate(R.layout.layout_user_detail, null, false);
        View zoomView = LayoutInflater.from(activity).inflate(R.layout.layout_user_pic, null, false);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_user_content, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
        scrollView.setHeaderViewSize(AppContext.getApp().dp2px(250));
    }

    @Override
    protected void lazyLoad() {

    }
}
