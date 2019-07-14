package com.xiao.xlixli.service;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoApiService {
    @GET("Videos")
    Observable<JSONObject> getVideos();
    @GET("getVideoDetail")
    Observable<JSONObject> getVideoDetail(@Query("id")String id);
    @GET("getOtherVideos")
    Observable<JSONObject> getOtherVideos();
}
