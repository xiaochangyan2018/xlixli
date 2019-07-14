package com.xiao.xlixli;

import android.app.Application;

import com.xiao.network.OKHttpConfig;
import com.xiao.network.RetrofitServiceManager;

public class XlixliApplaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            OKHttpConfig.initOkHttp(getApplicationContext(),getExternalCacheDir());
            RetrofitServiceManager.initRetrofit(getApplicationContext());
        }catch (Exception ex){

        }
    }
}
