package com.xiao.network;



import android.app.Application;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DefaultObserver;

public abstract class CustomerObserver<T>  extends DefaultObserver<JSONObject> {
    //数据格式为{"success": true,"data:{},"message":""}
    @Override
    public void onNext(JSONObject json) {
        if(json.getBoolean("success")){
            Type[] types =  ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
            Object obj = JSON.parse(json.getString("data"));
            if(obj instanceof JSONObject){
                success((T)json.getObject("data",types[0]));
            }else if(obj instanceof JSONArray){
                Type[] types2 = ((ParameterizedType)types[0]).getActualTypeArguments();
                Class clas2 = (Class)types2[0];
                T t= (T)JSON.parseArray(((JSONArray)obj).toJSONString(),clas2);
                success(t);
            }

        }else{
            Toast.makeText(RetrofitServiceManager.getContext(),json.getString("message"),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("xlixli",throwable.getLocalizedMessage());
    }

    @Override
    public void onComplete() {

    }
    public abstract void success(T t);
}
