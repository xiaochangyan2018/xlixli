package com.xiao.xlixli.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.trello.rxlifecycle3.RxLifecycle;
import com.xiao.network.CustomerObserver;
import com.xiao.network.NetWorkService;
import com.xiao.network.RetrofitServiceManager;
import com.xiao.network.WorkResultListener;
import com.xiao.xlixli.R;
import com.xiao.xlixli.bean.AdImg;
import com.xiao.xlixli.bean.AdImgViewBind;
import com.xiao.xlixli.bean.EmptyValue;
import com.xiao.xlixli.bean.Video;
import com.xiao.xlixli.bean.VideoViewBind;
import com.xiao.xlixli.fragments.BaseFragment;
import com.xiao.xlixli.service.VideoApiService;

import org.json.JSONException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.MultiTypeAdapter;

public class RecommendFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<Object> list;
    private MultiTypeAdapter adapter;
    private MaterialRefreshLayout pullRefress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initPullRefresh();
    }
    private void initPullRefresh(){
        pullRefress = (MaterialRefreshLayout)findViewById(R.id.pull_refresh);
        MaterialRefreshListener materialRefreshListener = new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                onHeaderRefresh(materialRefreshLayout);
            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

            }
        };
        pullRefress.setMaterialRefreshListener(materialRefreshListener);
        pullRefress.autoRefresh();
    }
    private void onHeaderRefresh(MaterialRefreshLayout view) {

        RetrofitServiceManager.create(VideoApiService.class)
                .getVideos()
                .compose(this.<JSONObject>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomerObserver<JSONObject>() {
                    @Override
                    public void success(JSONObject json) {
                        list = new ArrayList<>();
                        AdImg img = json.getObject("adImgs",AdImg.class);
                        list.add(img);
                        List<Video> videos = JSON.parseArray(json.getString("videos"),Video.class);
                        list.addAll(videos);
                        adapter.setItems(list);
                        adapter.notifyDataSetChanged();
                        pullRefress.finishRefresh();
                    }
                });
    }
    private  void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //recyclerView.addItemDecoration( new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (list.get(position) instanceof AdImg) {
                    return 2;//一行占1个
                }
                if (list.get(position) instanceof Video) {
                    return 1;//一行占5个
                }
                if (list.get(position) instanceof EmptyValue) {
                    return 2;//一行占2个
                }
                return 2;//默认一行占1个
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(AdImg.class, new AdImgViewBind());
        adapter.register(Video.class,new VideoViewBind());
        recyclerView.setAdapter(adapter);

    }




}
