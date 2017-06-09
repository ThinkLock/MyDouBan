package com.yang.mydouban.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by fengzhaoyang_i on 2017/6/5.
 */

public class MovieFragment extends BaseFragment{

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (View) inflater.inflate(R.layout.fragment_movie, container, false);
        isPrepared = true;
        lazyLoad();
        return mRootView;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }
}
