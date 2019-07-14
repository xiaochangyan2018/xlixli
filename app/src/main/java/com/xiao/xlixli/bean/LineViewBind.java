package com.xiao.xlixli.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.xiao.xlixli.R;

import org.jetbrains.annotations.NotNull;

import me.drakeet.multitype.ItemViewBinder;

public class LineViewBind extends ItemViewBinder<Line, LineViewBind.VideoHolder> {



    @NotNull
    @Override
    public VideoHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_line,viewGroup,false);
        return new VideoHolder(view);
    }
    @Override
    public void onBindViewHolder(@NotNull VideoHolder videoHolder, Line video) {

    }

    static class VideoHolder extends RecyclerView.ViewHolder{

        VideoHolder(View itemView) {
            super(itemView);

        }

    }
}
