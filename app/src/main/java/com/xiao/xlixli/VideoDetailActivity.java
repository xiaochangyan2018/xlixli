package com.xiao.xlixli;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.xiao.xlixli.adapter.BaseFragmentAdapter;
import com.xiao.xlixli.video.SampleControlVideo;
import com.xiao.xlixli.videoDetail.VideoBriefFragment;
import com.xiao.xlixli.videoDetail.VideoCommentFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {
    private TabLayout tab;
    private ViewPager viewPager;

    private List<Fragment> fragments;

    StandardGSYVideoPlayer detailPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        setStatusBar();
        init();
        initmVideo();
    }
    private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    private void initmVideo(){
        detailPlayer = (StandardGSYVideoPlayer)findViewById(R.id.txtVideo);
        resolveNormalVideoUI();
        initVideoBuilderMode();
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(true)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }
    private void resolveNormalVideoUI() {
        //增加title
//        detailPlayer.setUpLazy(url, true, null, null, "这是title");
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }
    private void init(){
        tab = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new VideoBriefFragment());
        fragments.add(new VideoCommentFragment());
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        for (int i=0;i<adapter.getCount();i++){
            TabLayout.Tab tabitem = tab.getTabAt(i);
            if(i==0){
                tabitem.setText("简介");
            }else{
                tabitem.setCustomView(R.layout.item_tab_comment);
            }
        }
        viewPager.setCurrentItem(0);
        tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == tab.parent.getTabAt(0)){
                    return;
                }
                TextView textTitle =  tab.getCustomView().findViewById(R.id.txtTitle);
                textTitle.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                TextView textCommentNum = (TextView)tab.getCustomView().findViewById(R.id.txtCommentNum);
                textCommentNum.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab == tab.parent.getTabAt(0)){
                    return;
                }
                TextView textTitle =  tab.getCustomView().findViewById(R.id.txtTitle);
                textTitle.setTextColor(getResources().getColor(R.color.colorTextPrimaryLight));
                TextView textCommentNum = (TextView)tab.getCustomView().findViewById(R.id.txtCommentNum);
                textCommentNum.setTextColor(getResources().getColor(R.color.colorContentDarkHight));
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void loadCover(ImageView imageView, String url) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.img_videodefault);

        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000000)
                                .centerCrop()
                                )
                .load(url)
                .into(imageView);
        //.error(R.mipmap.ic_launcher_round)
        //                                .placeholder(R.mipmap.xxx1)
    }
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

    }

}
