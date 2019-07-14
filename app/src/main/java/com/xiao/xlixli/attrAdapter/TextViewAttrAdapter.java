package com.xiao.xlixli.attrAdapter;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class TextViewAttrAdapter {


    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, int resId) {
        view.setTextColor(view.getContext().getResources().getColor(resId));
    }


}
