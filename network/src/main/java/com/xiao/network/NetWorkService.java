package com.xiao.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;



import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

public class NetWorkService {
    private WorkResultListener wrLinstener;
    private String url;
    private Map<String, String> params;
    private Context context;

    private boolean isprogress = false;
    private boolean isChace = true;
    private NetWorkService(Context context) {
        this.context = context;
    }
    public static NetWorkService create(Context context){
        NetWorkService netWorkService = new NetWorkService(context);
        return netWorkService;
    }
    public NetWorkService set(String url,Map<String,String> params,WorkResultListener wrLinstener){
        this.url = url;
        this.params = params;
        this.wrLinstener = wrLinstener;
        return this;
    }


    private void logUrl(){
        String urll = url+"?";
        for (String key : params.keySet()){
            urll+=key+"="+params.get(key)+"&";
        }
        Log.d("NetWorkService",urll);
    }

    public void work() {
        if(!NetUtil.isNetworkAvailable(context)){
            Toast.makeText(context, "当前没有网络!", Toast.LENGTH_SHORT).show();
            error("当前没有网络");
            return;
        }
        logUrl();
        post();
    }
    private JSON AdapterResult(String response){
        try{
            JSONObject json = JSONObject.parseObject(response);
            //暂定格式为{data:{},success:true,message:''}
            if(json.getBoolean("success")){
                success(json.getJSONObject("data"));
            }else{
                Toast.makeText(context, json.getString("message"), Toast.LENGTH_SHORT).show();
            }
            return json;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    private StringCallback stringCallback = new StringCallback() {
        @Override
        public void onBefore(Request request, int id) {
            if(!NetUtil.isNetworkAvailable(context)){
                Toast.makeText(context, "当前没有网络!", Toast.LENGTH_SHORT).show();
                request.cacheControl();
            }

        }

        @Override
        public void onAfter(int id) {

        }
        @Override
        public void onError(Call call, Exception e, int id) {
            error("访问失败");

        }

        @Override
        public void onResponse(String response, int id) {
            try{
                Log.d("NetWorkService",response);
                AdapterResult(response);
            }catch (Exception ex){
                ex.printStackTrace();
                error("返回数据出错");
            }
        }
    };
    public void post(){
        OkHttpUtils.post().url(url).params(params).build().execute(stringCallback);
    }
    public void get(){
        OkHttpUtils.get().url(url).params(params).build().execute(stringCallback);
    }
    public void postString() {
        String json = JSON.toJSONString(params);
        OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json).build().execute(stringCallback);
    }
    public void put(int id){
        String json =JSON.toJSONString(params);
        OkHttpUtils.put().url(url).id(id).build().execute(stringCallback);
    }
    public void delete(int id){

        OkHttpUtils.put().url(url).id(id).build().execute(stringCallback);
    }

    private void success(final JSONObject json) {
        if (wrLinstener != null) {
            try{
                wrLinstener.success(json);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void error(final String message) {
        if (wrLinstener != null) {
            wrLinstener.error(message);
        }
    }

    public WorkResultListener getWrLinstener() {
        return wrLinstener;
    }

    public void setWrLinstener(WorkResultListener wrLinstener) {
        this.wrLinstener = wrLinstener;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    //上传文件图片等
    public void uploadFiles(String message){
        if(!NetUtil.isNetworkAvailable(context)){
            Toast.makeText(context, "当前没有网络!", Toast.LENGTH_SHORT).show();
            return;
        }

        String urll = url+"?";
        for (String key : params.keySet()){
            urll+=key+"="+params.get(key)+"&";
        }
        System.out.println(urll);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Disposition", "form-data;filename=enctype");
        PostFormBuilder builder = OkHttpUtils.post();
        builder.url(url);
        for(String key : params.keySet()){
            File file1 = new File(params.get(key));
            if (!file1.exists()) {
                continue;
            }
            File file = compressToFile(new File(params.get(key)));
            String filename = file.getName();
            builder.addFile(key, filename, file);
        }
        builder.params(params)
                .headers(headers)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try{
                            Log.d("shortbarge","访问成功");
                            Log.d("shortbarge",response);
                            System.out.println("得到的结果："+response);
                            JSONObject json;
                            if(response.indexOf("{")==0) {
                                json = new JSONObject().getJSONObject(response);
                            }else{
                                json = new JSONObject();
                                json.put("result", response);
                            }
                            if(json.containsKey("result") && !json.getBoolean("result")){
                                Toast.makeText(context, json.getString("reason"), Toast.LENGTH_SHORT).show();
                            }else{
                                success(json);
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                            error("返回数据出错");
                        }

                    }
                });
    }


    private File compressToFile(File oldFile){
//        File newFile = new CompressHelper.Builder(context)
//                .setMaxWidth(720)  // 默认最大宽度为720
//                .setMaxHeight(960) // 默认最大高度为960
//                .setQuality(80)    // 默认压缩质量为80
//                .setFileName(DateUtil.getDateTime()+oldFile.getName()) // 设置你需要修改的文件名
//                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
//                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                .build()
//                .compressToFile(oldFile);
        return oldFile;
    }
}
