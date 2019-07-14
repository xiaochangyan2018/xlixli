package com.xiao.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.json.JSONException;


public interface WorkResultListener {
	public void success(JSONObject json) throws JSONException;
	public void error(String message);
}
