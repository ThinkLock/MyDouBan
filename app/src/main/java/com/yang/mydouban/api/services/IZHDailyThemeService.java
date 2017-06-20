package com.yang.mydouban.api.services;

import com.yang.mydouban.been.ZHDailyThemeResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by fengzhaoyang_i on 2017/6/20.
 */

public interface IZHDailyThemeService {
    @GET("themes")
    Observable<ZHDailyThemeResult> getThemesList();
}
