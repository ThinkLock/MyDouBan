package com.yang.mydouban.utils;

import android.widget.Toast;
import com.yang.mydouban.MyApplication;

public class ToastUtils {
    public static void showShort(String msg) {
        Toast.makeText(MyApplication.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(MyApplication.getApplication(), msg, Toast.LENGTH_LONG).show();
    }
}
