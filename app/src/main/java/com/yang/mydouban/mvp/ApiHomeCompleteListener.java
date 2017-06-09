package com.yang.mydouban.mvp;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public interface ApiHomeCompleteListener {
    void onCompleted(Object reult,String type);

    void onFailed(String msg);
}
