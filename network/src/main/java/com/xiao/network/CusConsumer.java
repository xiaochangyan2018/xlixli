package com.xiao.network;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.functions.Consumer;

public abstract class CusConsumer<T> implements Consumer<JSONObject> {
    @Override
    public void accept(JSONObject json) throws Exception {
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
    public abstract void success(T t);
}
