package com.yang.mydouban.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseFragment;
import com.yang.mydouban.ui.views.ImageSlideshow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fengzhaoyang_i on 2017/6/6.
 */

public class HomeFragment extends BaseFragment {

    ImageSlideshow mImageSlideshow;

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home,container,false);
        mImageSlideshow = (ImageSlideshow) mRootView.findViewById(R.id.sld_banner);
    }

    @Override
    protected void initEvents() {
        try {
            String[] imageUrls = {"https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg",
                    "https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg",
                    "https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg",
                    "https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg",
                    "http://pic2.zhimg.com/a62f9985cae17fe535a99901db18eba9.jpg"};
            for (int i = 0; i < 5; i++) {
                mImageSlideshow.addImageUrl(imageUrls[i]);
            }
            // 为ImageSlideshow设置数据
            mImageSlideshow.setDotSpace(12);
            mImageSlideshow.setDotSize(12);
            mImageSlideshow.setDelay(3000);
            mImageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.i(TAG,"on click");
                }
            });
            mImageSlideshow.commit();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initData(boolean isSavedNull) {

    }

    @Override
    public void onDestroy() {
        mImageSlideshow.releaseResource();
        super.onDestroy();
    }
}
