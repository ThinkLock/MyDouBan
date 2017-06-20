package com.yang.mydouban.mvp.model.impl;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.R;
import com.yang.mydouban.api.ServiceFactory;
import com.yang.mydouban.api.services.IGankListService;
import com.yang.mydouban.api.services.IZHDailyListService;
import com.yang.mydouban.api.services.IZHDailyThemeService;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.been.ZHDailyListResult;
import com.yang.mydouban.been.ZHDailyThemeResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.ApiHomeCompleteListener;
import com.yang.mydouban.mvp.model.IHomeDataModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public class HomeDataModelImpl implements IHomeDataModel{
    @Override
    public void loadHomeGankData(final String type, int count, int page, final ApiHomeCompleteListener listener) {
        IGankListService iGankListService = ServiceFactory.createService(Constants.GANK_URL,IGankListService.class);
        iGankListService.getGankList(type,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankListResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onNext(GankListResult result) {
                        if(result.isSuccessed()){
                            listener.onCompleted(result,type);
                        }else {
                            listener.onFailed("error");
                        }
                    }
                });
    }

    @Override
    public void loadHomeZHData(final ApiCompleteListener listener) {
        IZHDailyListService mZHDailyListService = ServiceFactory.createService(Constants.ZH_URL,IZHDailyListService.class);
        mZHDailyListService.getZHDailyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZHDailyListResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onNext(ZHDailyListResult zhDailyListResult) {
                        if(zhDailyListResult!=null){
                            listener.onCompleted(zhDailyListResult);
                        }else{
                            listener.onFailed(MyApplication.getApplication().getString(R.string.object_is_null));
                        }
                    }
                });

    }

    @Override
    public void loadHomeZHTheme(ApiCompleteListener listener) {
        IZHDailyThemeService izhDailyThemeService = ServiceFactory.createService(Constants.ZH_URL,IZHDailyThemeService.class);
        izhDailyThemeService.getThemesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZHDailyThemeResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(ZHDailyThemeResult zhDailyThemeResult) {

                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
