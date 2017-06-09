package com.yang.mydouban.mvp.view;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public interface IGankListView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);
}
