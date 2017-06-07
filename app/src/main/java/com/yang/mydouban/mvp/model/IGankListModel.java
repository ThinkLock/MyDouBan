package com.yang.mydouban.mvp.model;

import com.yang.mydouban.mvp.ApiCompleteListener;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public interface IGankListModel {

    void loadGankList(String type, int count, int page, ApiCompleteListener listener);

    void cancelLoading();
}
