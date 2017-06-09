package com.yang.mydouban.mvp.model;

import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.ApiHomeCompleteListener;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public interface IHomeDataModel {

    void loadHomeGankData(String type, int count, int page, ApiHomeCompleteListener listener);

    void loadHomeZHData(ApiCompleteListener listener);

    void cancelLoading();
}
