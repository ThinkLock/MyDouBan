package com.yang.mydouban.mvp.model;

import com.yang.mydouban.mvp.ApiCompleteListener;

/**
 * Created by fengzhaoyang_i on 2017/6/13.
 */

public interface IStoryDetailModel {

    /**
     * 加载新闻详细内容
     * @param id 新闻id
     * @param listener 返回监听
     */
    void loadStoryDetailData(int id, ApiCompleteListener listener);

    /**
     * 加载新闻额外信息
     * @param id 新闻id
     * @param listener
     */
    void loadStoryExtraData(int id,ApiCompleteListener listener);

    void cancelLoading();
}
