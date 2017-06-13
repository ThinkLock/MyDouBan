package com.yang.mydouban.mvp.model.impl;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.R;
import com.yang.mydouban.api.ServiceFactory;
import com.yang.mydouban.api.services.IZHStoryDetailService;
import com.yang.mydouban.api.services.IZHStoryExtraService;
import com.yang.mydouban.been.ZHStoryDetailResult;
import com.yang.mydouban.been.ZHStoryExtraResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.model.IStoryDetailModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengzhaoyang_i on 2017/6/13.
 */

public class StoryDetailModelImpl implements IStoryDetailModel {


    @Override
    public void loadStoryDetailData(int id, final ApiCompleteListener listener) {
        IZHStoryDetailService izhStoryDetailService = ServiceFactory.createService(Constants.ZH_URL,IZHStoryDetailService.class);
        izhStoryDetailService.getStoryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZHStoryDetailResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onNext(ZHStoryDetailResult zhStoryDetailResult) {
                        if(zhStoryDetailResult != null){
                           listener.onCompleted(zhStoryDetailResult);
                        }else{
                            listener.onFailed(MyApplication.getApplication().getString(R.string.object_is_null));
                        }
                    }
                });
    }

    @Override
    public void loadStoryExtraData(int id, final ApiCompleteListener listener) {
        IZHStoryExtraService izhStoryExtraService = ServiceFactory.createService(Constants.ZH_URL,IZHStoryExtraService.class);
        izhStoryExtraService.getZHStoryExtraData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZHStoryExtraResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onNext(ZHStoryExtraResult zhStoryExtraResult) {
                        if(zhStoryExtraResult!=null){
                            listener.onCompleted(zhStoryExtraResult);
                        }else {
                            listener.onFailed(MyApplication.getApplication().getString(R.string.object_is_null));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
