package com.yang.mydouban.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseFragment;
import com.yang.mydouban.been.GankListResult;
import com.yang.mydouban.been.ZHDailyListResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.presenter.impl.GankListPresenterImpl;
import com.yang.mydouban.mvp.presenter.impl.HomeDataPresenterImpl;
import com.yang.mydouban.mvp.view.IHomeDataView;
import com.yang.mydouban.ui.adapters.TopStoriesListAdapter;
import com.yang.mydouban.ui.views.ImageSlideshow;
import com.yang.mydouban.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public class HomeFragment extends BaseFragment implements IHomeDataView,SwipeRefreshLayout.OnRefreshListener{


    private View mRootView;
    private SwipeRefreshLayout swipe;
    private ImageSlideshow mImageSlideshow;
    private RecyclerView mRcvZHDaily;
    private LinearLayoutManager layoutManager;
    private TopStoriesListAdapter mTopStoriesAdapter;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    private HomeDataPresenterImpl homeDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            mRootView = (View) inflater.inflate(R.layout.fragment_home, container, false);
            isPrepared = true;
            lazyLoad();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return mRootView;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initEvents();
        initData();
    }


    private void initEvents() {
        try {
            mImageSlideshow = (ImageSlideshow) mRootView.findViewById(R.id.sld_banner);
            swipe = (SwipeRefreshLayout) mRootView.findViewById(R.id.swp_home);
            homeDataPresenter = new HomeDataPresenterImpl(this);
            swipe.setOnRefreshListener(this);
            mImageSlideshow.setDelay(5*1000);
            mImageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRcvZHDaily = (RecyclerView) mRootView.findViewById(R.id.rec_top_stories);
            mTopStoriesAdapter = new TopStoriesListAdapter(getActivity());
            mRcvZHDaily.setLayoutManager(layoutManager);
            mRcvZHDaily.setAdapter(mTopStoriesAdapter);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    private void initData() {
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
    public void refreshData(Object result,String type) {
        if(result instanceof GankListResult){
            //判断返回的数据类型  福利  Android Ios
            switch (type){
                case Constants.FLAG_GIRLS:
                    List<GankListResult.Results> body = ((GankListResult) result).getResults();
                    if(body.size()>0){
                        for (GankListResult.Results item : body) {
                            mImageSlideshow.addImageUrl(item.getUrl());
                        }
                    }
                    mImageSlideshow.commit();
                    break;
                case Constants.FLAG_Android:
                    Log.i(TAG,result.toString());
                    break;
                case Constants.FLAG_iOS:
                    break;
            }
        }
    }

    /**
     * 刷新知乎日报数据
     * @param result
     */
    @Override
    public void refreshZhihuaDaily(Object result) {
        if(result instanceof ZHDailyListResult){
            mTopStoriesAdapter.setListData(((ZHDailyListResult) result).getTop_stories());
            mTopStoriesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        mImageSlideshow.clearUp();
        homeDataPresenter.getHomeGankData(Constants.FLAG_GIRLS,5,1);
        homeDataPresenter.getHomeGankData(Constants.FLAG_Android,10,1);
        homeDataPresenter.getHomeZHData();
    }
}
