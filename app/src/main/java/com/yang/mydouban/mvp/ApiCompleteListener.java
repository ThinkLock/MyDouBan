package com.yang.mydouban.mvp;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public interface ApiCompleteListener {

    void onCompleted(Object reult);

    void onFailed(String msg);
}
