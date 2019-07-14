package com.xiao.xlixli.videoDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiao.network.CusConsumer;
import com.xiao.network.CustomerObserver;
import com.xiao.network.RetrofitServiceManager;
import com.xiao.xlixli.R;
import com.xiao.xlixli.bean.AdImg;
import com.xiao.xlixli.bean.AdImgViewBind;
import com.xiao.xlixli.bean.EmptyValue;
import com.xiao.xlixli.bean.Line;
import com.xiao.xlixli.bean.LineViewBind;
import com.xiao.xlixli.bean.OptionType;
import com.xiao.xlixli.bean.Video;
import com.xiao.xlixli.bean.VideoOther;
import com.xiao.xlixli.bean.VideoOtherViewBind;
import com.xiao.xlixli.bean.VideoViewBind;
import com.xiao.xlixli.fragments.BaseFragment;
import com.xiao.xlixli.service.VideoApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.MultiTypeAdapter;

public class VideoBriefFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<Object> list;
    private MultiTypeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videobrie, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        loadData();
    }
    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                if (list.get(position) instanceof OptionType) {
                    return 1;//一行占5个
                }
                return 5;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(Video.class, new VideoDetailViewBind());
        adapter.register(Line.class,new LineViewBind());
        adapter.register(VideoOther.class,new VideoOtherViewBind());
//        adapter.register(OptionType.class,new VideoViewBind());
        recyclerView.setAdapter(adapter);

    }
    private void loadData(){
        final VideoApiService service = RetrofitServiceManager.create(VideoApiService.class);
        service.getVideoDetail("1")
                .compose(this.<JSONObject>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new CusConsumer<Video>() {
                    @Override
                    public void success(Video video) {
                        list = new ArrayList<>();
                        list.add(video);
                        list.add(new Line());
                        adapter.setItems(list);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<JSONObject, ObservableSource<JSONObject>>() {
                    @Override
                    public ObservableSource<JSONObject> apply(JSONObject jsonObject) throws Exception {
                        return service.getOtherVideos();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomerObserver<List<VideoOther>>() {
                    @Override
                    public void success(List<VideoOther> videos) {
                        list.addAll(videos);
                        Toast.makeText(getActivity(),videos.size()+"",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
