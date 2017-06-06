package com.yang.mydouban.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseFragment;

/**
 * Created by fengzhaoyang_i on 2017/6/5.
 */

public class MovieFragment extends BaseFragment{

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
