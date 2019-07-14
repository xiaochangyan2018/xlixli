package com.xiao.xlixli.bean;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.xiao.xlixli.R;
import com.xiao.xlixli.util.GlideImageLoader;
import com.youth.banner.Banner;

import org.jetbrains.annotations.NotNull;

import me.drakeet.multitype.ItemViewBinder;

public class AdImgViewBind extends ItemViewBinder<AdImg, AdImgViewBind.AdImgHolder> {

    @NotNull
    @Override
    public AdImgHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_recommend_adimag,viewGroup,false);
        return new AdImgHolder(view);
    }


    @Override
    public void onBindViewHolder(@NotNull AdImgHolder adImgHolder, AdImg adImg) {
        //下载图片
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.LOLLIPOP) {
            adImgHolder.banner.setClipToOutline(true);
        }

        adImgHolder.banner.setImages(adImg.getUrls());
        adImgHolder.banner.start();
    }

    static class AdImgHolder extends RecyclerView.ViewHolder {
        Banner banner;

        AdImgHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            banner.setImageLoader(new GlideImageLoader());
        }

    }
}
