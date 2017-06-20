package com.yang.mydouban.mvp.view;

/**
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public interface IHomeDataView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result,String type);

    void refreshZhihuaDaily(Object result);

    void refreshZhiHuThemes(Object result);
}
