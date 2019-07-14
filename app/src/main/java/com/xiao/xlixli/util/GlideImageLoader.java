package com.xiao.xlixli.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shehuan.niv.NiceImageView;
import com.xiao.xlixli.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        CornerTransform transform = new CornerTransform(context,dip2px(context,5));
        transform.setExceptCorner(false,false,false,false);
        Glide.with(context).load(path).skipMemoryCache(true).transform(transform).into(imageView);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public ImageView createImageView(Context context) {
        NiceImageView imageView = new NiceImageView(context);
        //imageView.setCornerRadius((int)context.getResources().getDimension(R.dimen.padding_s));
        return imageView;
    }
}
