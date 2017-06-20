package com.yang.mydouban.mvp.presenter.impl;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.R;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.been.ZHDailyListResult;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.ApiHomeCompleteListener;
import com.yang.mydouban.mvp.model.impl.HomeDataModelImpl;
import com.yang.mydouban.mvp.presenter.IHomeDataPresenter;
import com.yang.mydouban.mvp.view.IHomeDataView;
import com.yang.mydouban.utils.NetworkUtils;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public class HomeDataPresenterImpl implements IHomeDataPresenter,ApiHomeCompleteListener,ApiCompleteListener{
    HomeDataModelImpl homeDataModel;
    IHomeDataView iHomeDataView;

    public HomeDataPresenterImpl(IHomeDataView view){
        homeDataModel = new HomeDataModelImpl();
        iHomeDataView = view;
    }


    @Override
    public void onCompleted(Object reult, String type) {
        iHomeDataView.hideProgress();
        if(reult instanceof GankListResult){
            iHomeDataView.refreshData(reult,type);
        }
    }

    @Override
    public void onCompleted(Object reult) {
        iHomeDataView.hideProgress();
        if(reult instanceof ZHDailyListResult){
            iHomeDataView.refreshZhihuaDaily(reult);
        }
    }

    @Override
    public void onFailed(String msg) {
        iHomeDataView.hideProgress();
        iHomeDataView.showMessage(msg);
    }

    @Override
    public void getHomeGankData(String type, int count, int page) {
        //TODO 抽象省去这部分代码的重复实现
        if(!NetworkUtils.isConnected(MyApplication.getApplication())){
            iHomeDataView.hideProgress();
            iHomeDataView.showMessage(MyApplication.getApplication().getString(R.string.poor_network));
        }
        iHomeDataView.showProgress();
        homeDataModel.loadHomeGankData(type,count,page,this);
    }

    @Override
    public void getHomeZHData() {
        if(!NetworkUtils.isConnected(MyApplication.getApplication())){
            iHomeDataView.hideProgress();
            iHomeDataView.showMessage(MyApplication.getApplication().getString(R.string.poor_network));
        }
        iHomeDataView.showProgress();
        homeDataModel.loadHomeZHData(this);
    }

    @Override
    public void getHomeZHThemeData() {

    }

    @Override
    public void cancelLoading() {
        homeDataModel.cancelLoading();
    }
}
