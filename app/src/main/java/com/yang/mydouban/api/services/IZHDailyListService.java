package com.yang.mydouban.api.services;

import com.yang.mydouban.been.ZHDailyListResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public interface IZHDailyListService {

    @GET("news/latest")
    Observable<ZHDailyListResult> getZHDailyList();
}
