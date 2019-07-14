package com.xiao.xlixli.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.xiao.xlixli.BR;
import com.xiao.xlixli.R;

public class TabItem  extends BaseObservable {

    private String title;
    private int logoNoraml;
    private int logoPressed;
    private boolean isMessage;
    private boolean isPressed;
    private int titleColorNormal;
    private int titleColorPressed;
    public TabItem(){
        titleColorNormal = R.color.colorTextPrimary;
        titleColorPressed = R.color.colorPrimaryDark;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }


    @Bindable
    public int getLogoNoraml() {
        return logoNoraml;
    }

    public void setLogoNoraml(int logoNoraml) {
        this.logoNoraml = logoNoraml;

    }

    @Bindable
    public int getLogoPressed() {
        return logoPressed;
    }

    public void setLogoPressed(int logoPressed) {
        this.logoPressed = logoPressed;
    }
@Bindable
    public boolean isMessage() {
        return isMessage;
    }

    public void setMessage(boolean message) {
        isMessage = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
        notifyPropertyChanged(BR.pressed);
    }
    @Bindable
    public int getTitleColorNormal() {
        return titleColorNormal;
    }

    public void setTitleColorNormal(int titleColorNormal) {
        this.titleColorNormal = titleColorNormal;
    }
    @Bindable
    public int getTitleColorPressed() {
        return titleColorPressed;
    }

    public void setTitleColorPressed(int titleColorPressed) {
        this.titleColorPressed = titleColorPressed;
    }
}
