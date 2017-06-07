package com.yang.mydouban.mvp.presenter;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public interface IGankListPresenter {

    void getGankList(String type,int count,int page);

    void cancelLoading();
}
