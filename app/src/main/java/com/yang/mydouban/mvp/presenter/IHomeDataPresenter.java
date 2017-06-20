package com.yang.mydouban.mvp.presenter;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public interface IHomeDataPresenter {

    void getHomeGankData(String type,int count,int page);

    void getHomeZHData();

    void getHomeZHThemeData();

    void cancelLoading();

}
