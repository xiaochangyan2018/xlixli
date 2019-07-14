package com.xiao.xlixli.bean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiao.xlixli.R;
import com.xiao.xlixli.VideoDetailActivity;
import com.xiao.xlixli.databinding.ItemVideoBinding;
import com.xiao.xlixli.databinding.ItemVideoOtherBinding;
import com.xiao.xlixli.databinding.ItemVideodetailBinding;
import com.xiao.xlixli.util.CornerTransform;
import com.xiao.xlixli.util.GlideImageLoader;

import org.jetbrains.annotations.NotNull;

import me.drakeet.multitype.ItemViewBinder;

public class VideoOtherViewBind extends ItemViewBinder<VideoOther, VideoOtherViewBind.VideoHolder> {

    private ItemVideoOtherBinding binding;

    @NotNull
    @Override
    public VideoHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_video_other,viewGroup,false);
        binding = ItemVideoOtherBinding.bind(view);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull VideoHolder videoHolder, VideoOther video) {

        binding.setVideo(video);
        Context context = videoHolder.itemView.getContext();
        CornerTransform transform = new CornerTransform(context, GlideImageLoader.dip2px(context,5));
        transform.setExceptCorner(false,false,true,true);
        Glide.with(context).load(video.getUrl()).skipMemoryCache(true).transform(transform).into(videoHolder.imgPoster);
    }

    static class VideoHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        VideoHolder(View itemView) {
            super(itemView);
            imgPoster = (ImageView) itemView.findViewById(R.id.imgPoster);

        }

    }
}
