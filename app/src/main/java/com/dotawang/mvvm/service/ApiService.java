package com.dotawang.mvvm.service;

import com.dotawang.mvvm.entity.DemoEntity;
import com.dotawang.mvvm.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 接口管理
 * Created by Dota.Wang on 2018/10/18.
 */

public interface ApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoPost(@Field("token") String token);
}
