package com.yang.mydouban.mvp.model.impl;

import com.yang.mydouban.api.ServiceFactory;
import com.yang.mydouban.api.services.IGankListService;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.model.IGankListModel;

import java.net.UnknownHostException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public class GankListModelImpl implements IGankListModel{

    @Override
    public void loadGankList(String type, int count, int page, final ApiCompleteListener listener) {
        IGankListService iGankListService = ServiceFactory.createService(Constants.GANK_URL,IGankListService.class);
        iGankListService.getGankList(type,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankListResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof UnknownHostException){
                            listener.onFailed(e.getMessage());
                            return;
                        }
                        listener.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(GankListResult result) {
                        if(result.isSuccessed()){
                            listener.onCompleted(result);
                        }else {
                            listener.onFailed("error");
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
