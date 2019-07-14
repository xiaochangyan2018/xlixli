package com.xiao.network;

import android.app.Application;
import android.content.Context;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class RetrofitServiceManager {

    private RetrofitServiceManager(Context context){
        mRetrofit = getRetrofit(context);
        this.context = context;
    }
    private static RetrofitServiceManager retrofitServiceManager;
    public static RetrofitServiceManager initRetrofit(Context context){
        if(retrofitServiceManager == null){
            synchronized (RetrofitServiceManager.class){
                if(retrofitServiceManager == null){
                    retrofitServiceManager = new RetrofitServiceManager(context);
                }
            }
        }
        return retrofitServiceManager;
    }
    public static final long DEFAULT_TIME_OUT = 15;
    private Retrofit mRetrofit;
    private Context context;
    public static Context getContext(){
        return retrofitServiceManager.context;
    }

    private  OkHttpClient.Builder getOkHttpClientBuilder(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接 超时时间
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作 超时时间
        builder.retryOnConnectionFailure(true);//错误重连
        File cacheFile = new File(context.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        builder.cache(cache);

        //项目中设置头信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
                requestBuilder.addHeader("Authorization", "Bearer " + "");//添加请求头信息，服务器进行token有效性验证
                Request request = requestBuilder.build();
                request.url().newBuilder().addQueryParameter("kkk", "");
                return chain.proceed(request);
            }
        };

        builder.addInterceptor(headerInterceptor);

        return builder;
    }

    private  Retrofit getRetrofit(Context context) {
        OkHttpClient.Builder builder = getOkHttpClientBuilder(context);
        // 创建Retrofit
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl("https://www.easy-mock.com/mock/5d21aedbeb1b8910ca396c78/example/")
                .build();
        return mRetrofit;
    }

    public static  <T> T create(Class<T> service) {
        return retrofitServiceManager.mRetrofit.create(service);
    }
}
