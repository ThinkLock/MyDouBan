package com.yang.mydouban.api.services;

import com.yang.mydouban.been.GankListResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 获取gank list
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public interface IGankListService {

    @GET("data/{type}/{count}/{page}")
    Observable<GankListResult> getGankList(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
