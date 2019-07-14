package com.xiao.xlixli.videoDetail;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.xiao.xlixli.R;
import com.xiao.xlixli.VideoDetailActivity;
import com.xiao.xlixli.bean.Video;
import com.xiao.xlixli.bean.VideoViewBind;
import com.xiao.xlixli.databinding.ItemVideoBinding;
import com.xiao.xlixli.databinding.ItemVideodetailBinding;

import org.jetbrains.annotations.NotNull;

import me.drakeet.multitype.ItemViewBinder;

public class VideoDetailViewBind extends ItemViewBinder<Video, VideoDetailViewBind.VideoDetailHolder> {
    private ItemVideodetailBinding binding;
    @NotNull
    @Override
    public VideoDetailHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_videodetail,viewGroup,false);
        binding = ItemVideodetailBinding.bind(view);
        return new VideoDetailViewBind.VideoDetailHolder(view);
    }
    @Override
    public void onBindViewHolder(@NotNull VideoDetailHolder videoDetailHolder, Video video) {
        binding.setVideo(video);
    }

    static class VideoDetailHolder extends RecyclerView.ViewHolder{


        VideoDetailHolder(View itemView) {
            super(itemView);

        }

    }
}
