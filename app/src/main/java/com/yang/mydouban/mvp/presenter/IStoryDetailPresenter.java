package com.yang.mydouban.mvp.presenter;

/**
 * Created by fengzhaoyang_i on 2017/6/13.
 */

public interface IStoryDetailPresenter {

    void loadStoryDetail(int id);

    void loadStoryExtra(int id);

    void cancelLoading();
}

