package com.yang.mydouban.api.services;

import com.yang.mydouban.been.ZHStoryExtraResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by fengzhaoyang_i on 2017/6/13.
 */

public interface IZHStoryExtraService {

    @GET("story-extra/{id}")
    Observable<ZHStoryExtraResult> getZHStoryExtraData(@Path("id") int id);
}
