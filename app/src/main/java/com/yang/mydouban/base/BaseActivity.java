package com.yang.mydouban.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.utils.ActivityManagerUtils;

/**
 * activity 基类
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ((MyApplication) ActivityManagerUtils.getContext()).addActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }


    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) ActivityManagerUtils.getContext()).removeActivity(this);
    }
}
