package com.yang.mydouban.ui.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseActivity;
import com.yang.mydouban.been.ZHStoryDetailResult;
import com.yang.mydouban.been.ZHStoryExtraResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.presenter.impl.StoryDetailPresenterImpl;
import com.yang.mydouban.mvp.view.IStoryDetailView;
import com.yang.mydouban.utils.ToastUtils;


/**
 * Created by fengzhaoyang_i on 2017/6/12.
 */

public class StoryDetailActivity extends BaseActivity implements IStoryDetailView,View.OnClickListener{

    private int mStoryID;

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvImageSource;
    private ProgressBar mPsbLoading;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private StoryDetailPresenterImpl storyDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        initView();
        initData();
    }


    private void initView(){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mIvImage = (ImageView) findViewById(R.id.iv_detail_top);
        mTvTitle = (TextView)findViewById(R.id.tv_detail_title);
        mTvImageSource = (TextView)findViewById(R.id.tv_imgSource);
        mPsbLoading = (ProgressBar)findViewById(R.id.psb_loading);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ViewGroup mContentContainer = (ViewGroup) findViewById(R.id.content_container);
        mContentContainer.setOnClickListener(this);
        collapsingToolbarLayout.setTitleEnabled(false);
        storyDetailPresenter = new StoryDetailPresenterImpl(this);
    }

    private void initData(){
        mStoryID = getIntent().getIntExtra(Constants.INTENT_SOTRY_ID,0);
        Log.i(TAG,mStoryID+"");
        storyDetailPresenter.loadStoryDetail(mStoryID);
        storyDetailPresenter.loadStoryExtra(mStoryID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_story_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_share) {
            ToastUtils.showShort("share");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showProgress() {
        mPsbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mPsbLoading.setVisibility(View.GONE);
    }

    @Override
    public void loadStoryDetailInfo(Object result) {
        if(result instanceof ZHStoryDetailResult){
            mTvTitle.setText(((ZHStoryDetailResult) result).getTitle());
            mTvImageSource.setText(((ZHStoryDetailResult) result).getImage_source());
            Glide.with(this)
                    .load(((ZHStoryDetailResult) result).getImage())
                    .fitCenter()
                    .centerCrop()
                    .into(mIvImage);
        }
    }

    @Override
    public void loadStoryExtraInfo(Object result) {
        if(result instanceof ZHStoryExtraResult){
            Log.i(TAG,((ZHStoryExtraResult) result).getComments()+"");
        }
    }
}
