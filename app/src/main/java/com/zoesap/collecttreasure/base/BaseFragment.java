package com.zoesap.collecttreasure.base;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

/**
 * Created by Dubei on 2016/9/19.
 * 邮箱：370318201@qq.com
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected static final int PICTURE_FROM_CAMERA=2;
    private View v;
    protected LayoutInflater mInflater;
    public Activity activity;

    /** 标志位，标志已经初始化完成 */
    public boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    public boolean mHasLoadedOnce;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initData();
    }

    protected void initData(){
        mInflater = getActivity().getLayoutInflater();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(getLayoutId(),container,false);
            initView(v);
            setData();
        }

        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    protected abstract int getLayoutId();


    protected void initView(View view){

    }

    protected void setData(){

    }

    /**
     * resId true to View
     * @param resId
     * @return
     */
    protected View inflateView(int resId){
        return mInflater.inflate(resId, null);
    }

    protected void close(){
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {

    }

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    protected String getDefaultPath(){
        return Environment.getExternalStorageDirectory()+"/zoesap_watermark_temp_pic.jpg";
    }

    /**
     * 系统拍照
     * @param path
     */
    protected void camera(String path){
        try{
            Intent take_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            take_photo.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
            activity.startActivityForResult(take_photo, PICTURE_FROM_CAMERA);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    public void alert(String title,String positiveText,String negativText){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPositiveClick();
                    }
                })
                .setNegativeButton(negativText,null)
                .create().show();
    }

    public void setPositiveClick(){

    }
}
