package com.yang.mydouban.mvp.presenter.impl;

import com.yang.mydouban.MyApplication;
import com.yang.mydouban.R;
import com.yang.mydouban.been.ZHStoryDetailResult;
import com.yang.mydouban.been.ZHStoryExtraResult;
import com.yang.mydouban.mvp.ApiCompleteListener;
import com.yang.mydouban.mvp.model.impl.StoryDetailModelImpl;
import com.yang.mydouban.mvp.presenter.IStoryDetailPresenter;
import com.yang.mydouban.mvp.view.IStoryDetailView;
import com.yang.mydouban.utils.NetworkUtils;

/**
 * Created by fengzhaoyang_i on 2017/6/13.
 */

public class StoryDetailPresenterImpl implements IStoryDetailPresenter,ApiCompleteListener{

    StoryDetailModelImpl mModelImpl;
    IStoryDetailView iStoryDetailView;


    public StoryDetailPresenterImpl(IStoryDetailView view){
       mModelImpl = new StoryDetailModelImpl();
        iStoryDetailView = view;
    }

    @Override
    public void onCompleted(Object result) {
        if(result instanceof ZHStoryDetailResult){
            iStoryDetailView.loadStoryDetailInfo(result);
        }else if(result instanceof ZHStoryExtraResult){
            iStoryDetailView.loadStoryExtraInfo(result);
        }
        iStoryDetailView.hideProgress();
    }

    @Override
    public void onFailed(String msg) {
        iStoryDetailView.hideProgress();
        iStoryDetailView.showMessage(msg);
    }

    @Override
    public void loadStoryDetail(int id) {
        if(!NetworkUtils.isConnected(MyApplication.getApplication())){
            iStoryDetailView.hideProgress();
            iStoryDetailView.showMessage(MyApplication.getApplication().getString(R.string.poor_network));
        }
        iStoryDetailView.showProgress();
        mModelImpl.loadStoryDetailData(id,this);
    }

    @Override
    public void loadStoryExtra(int id) {
        if(!NetworkUtils.isConnected(MyApplication.getApplication())){
            iStoryDetailView.hideProgress();
            iStoryDetailView.showMessage(MyApplication.getApplication().getString(R.string.poor_network));
        }
        iStoryDetailView.showProgress();
        mModelImpl.loadStoryExtraData(id,this);
    }

    @Override
    public void cancelLoading() {
        mModelImpl.cancelLoading();
    }
}
