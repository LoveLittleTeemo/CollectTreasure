package com.zoesap.collecttreasure.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zoesap.collecttreasure.R;

/**
 * Created by Dubei on 2016/9/19.
 * 邮箱：370318201@qq.com
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    protected static final int PICTURE_FROM_CAMERA=2;
    protected Activity activity;
    protected Context context;
    protected LayoutInflater mInflater;
    protected long mExitTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mInflater = getLayoutInflater();
        init(savedInstanceState);
    }

    /**
     * get resId
     * @return
     */
    protected int getLayoutId(){
        return 0;
    }

    protected void init(Bundle savedInstanceState){
        initData();
        initView();
        setData();
    }

    protected void initData(){
        context=BaseActivity.this;
        activity=BaseActivity.this;
    }

    protected void initView(){

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

    /**
     * close this pager
     */
    protected void close(){
        this.finish();
        overridePendingTransition(R.anim.zoom, R.anim.zoomout);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * set view invisible
     * @param view
     */
    protected void setInVisible(View view){
        if(view==null)
            return;
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * set view visible
     * @param view
     */
    protected void setVisible(View view){
        if(view==null)
            return;
        view.setVisibility(View.VISIBLE);
    }

    /**
     * set view gone
     * @param view
     */
    protected void setGone(View view){
        if(view==null)
            return;
        view.setVisibility(View.GONE);
    }

    /**
     * set textview with resId
     * @param view
     * @param resId
     */
    protected void setTitle(TextView view,int resId){
        if(view==null)
            return;
        view.setText(resId);
    }

    /**
     * set textview with charsequence
     * @param view
     * @param text
     */
    protected void setTitle(TextView view,CharSequence text){
        if(view==null)
            return;
        view.setText(text);
    }

    /**
     * click back is exit
     * @return
     */
    protected boolean isTwiceBack(){
        return false;
    }

    /**
     * exit app you must click twice in set-time.
     * @param keyCode
     * @param event
     * @return
     */
    // 监听手机上的BACK键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(isTwiceBack()){
                // 判断菜单是否关闭
                // 判断两次点击的时间间隔（默认设置为2秒）
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    // 杀死本应用的进程
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
                return true;
            }else{
                close();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    protected String getDefaultPath(String name){
//        return Environment.getExternalStorageDirectory()+"/zoesap_watermark_temp_pic.jpg";
        return Environment.getExternalStorageDirectory()+"/"+name;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    private MyReceiver myReceiver;

    /**
     * 广播注册
     * @param Action
     */
    public void filter(String Action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(Action);
        myReceiver=new MyReceiver();
        registerReceiver(myReceiver,filter);
    }

    /**
     * 发送广播
     * @param Action
     */
    public void sendBroad(String Action){
        Intent intent=new Intent();
        intent.setAction(Action);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            receiver(intent);
        }
    }

    /**
     * 获取广播数据
     * @param intent
     */
    public void receiver(Intent intent){

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

    /**
     * alert确认按钮点击事件
     */
    public void setPositiveClick(){

    }
}
