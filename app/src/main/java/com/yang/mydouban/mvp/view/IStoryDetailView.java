package com.yang.mydouban.mvp.view;

/**
 * Created by fengzhaoyang_i on 2017/6/12.
 */

public interface IStoryDetailView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void loadStoryDetailInfo(Object result);

    void loadStoryExtraInfo(Object result);

}
