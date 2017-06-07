package com.yang.mydouban.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseFragment;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.mvp.presenter.impl.GankListPresenterImpl;
import com.yang.mydouban.mvp.view.IHomeDataView;
import com.yang.mydouban.ui.views.ImageSlideshow;
import com.yang.mydouban.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public class HomeFragment extends BaseFragment implements IHomeDataView,SwipeRefreshLayout.OnRefreshListener{



    private SwipeRefreshLayout swipe;
    private ImageSlideshow mImageSlideshow;

    private GankListPresenterImpl gankListPresenter;

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    protected void initEvents() {
        mImageSlideshow = (ImageSlideshow) mRootView.findViewById(R.id.sld_banner);
        swipe = (SwipeRefreshLayout)mRootView.findViewById(R.id.swp_home);
        gankListPresenter = new GankListPresenterImpl(this);
        swipe.setOnRefreshListener(this);
        mImageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    protected void initData(boolean isSavedNull) {
       onRefresh();
    }

    @Override
    public void onDestroy() {
        mImageSlideshow.releaseResource();
        super.onDestroy();
    }


    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe.setRefreshing(false);
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof GankListResult){
            List<GankListResult.Results> body = ((GankListResult) result).getResults();
            if(body.size()>0){
                for (GankListResult.Results item : body) {
                    mImageSlideshow.addImageUrl(item.getUrl());
                }
            }
            mImageSlideshow.commit();
        }
    }

    @Override
    public void onRefresh() {
        mImageSlideshow.clearUp();
        gankListPresenter.getGankList("福利",5,1);
    }
}
