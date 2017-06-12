package com.yang.mydouban.api.services;

import com.yang.mydouban.been.ZHStoryDetailResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by fengzhaoyang_i on 2017/6/12.
 */

public interface IZHStoryDetailService {

    @GET("{id}")
    Observable<ZHStoryDetailResult> getStoryDetail(@Path("id") int id);
}
