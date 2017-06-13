package com.yang.mydouban.mvp.presenter.impl;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.R;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.model.IGankListModel;
import com.yang.mydouban.mvp.model.impl.GankListModelImpl;
import com.yang.mydouban.mvp.presenter.IGankListPresenter;
import com.yang.mydouban.mvp.view.IGankListView;
import com.yang.mydouban.mvp.view.IHomeDataView;
import com.yang.mydouban.utils.NetworkUtils;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public class GankListPresenterImpl implements IGankListPresenter,ApiCompleteListener{
    private IGankListModel mGankListModel;
    private IGankListView mHomeDataView;

    public GankListPresenterImpl(IGankListView view){
        mHomeDataView = view;
        mGankListModel = new GankListModelImpl();
    }

    @Override
    public void onCompleted(Object result) {
        if(result instanceof GankListResult){
            mHomeDataView.refreshData(result);
        }
        mHomeDataView.hideProgress();
    }

    @Override
    public void onFailed(String msg) {
        mHomeDataView.hideProgress();
        if(msg == null){
            return;
        }
        mHomeDataView.showMessage(msg);
    }

    @Override
    public void getGankList(String type, int count, int page) {
        if(!NetworkUtils.isConnected(MyApplication.getApplication())){
            mHomeDataView.showMessage(MyApplication.getApplication().getString(R.string.poor_network));
            mHomeDataView.hideProgress();
        }
        mHomeDataView.showProgress();
        mGankListModel.loadGankList(type,count,page,this);
    }

    @Override
    public void cancelLoading() {
        mGankListModel.cancelLoading();
    }
}
