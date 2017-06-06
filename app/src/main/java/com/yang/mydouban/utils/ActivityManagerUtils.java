package com.yang.mydouban.utils;

import android.content.Context;

import com.yang.mydouban.MyApplication;

/**
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public class ActivityManagerUtils {

    public static Context getContext() {
        return MyApplication.getApplication();
    }
}
