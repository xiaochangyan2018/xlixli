package com.xiao.xlixli.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.xiao.xlixli.BR;

public class Video extends BaseObservable {
    private String title;
    private String type;
    private String url;
    private String PlayNums;
    private  int followNums;
    private String playTimes;
    //描述
    private String description;
    //发布时间
    private String relaseTime;
    //账号
    private String account;
    private String name;
    private String fins;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getPlayNums() {
        return PlayNums;
    }

    public void setPlayNums(String playNums) {
        PlayNums = playNums;
        notifyPropertyChanged(BR.playNums);
    }

    @Bindable
    public int getFollowNums() {
        return followNums;
    }

    public void setFollowNums(int followNums) {
        this.followNums = followNums;
        notifyPropertyChanged(BR.followNums);
    }

    @Bindable
    public String getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(String playTimes) {
        this.playTimes = playTimes;
        notifyPropertyChanged(BR.playTimes);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getRelaseTime() {
        return relaseTime;
    }

    public void setRelaseTime(String relaseTime) {
        this.relaseTime = relaseTime;
        notifyPropertyChanged(BR.relaseTime);
    }

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getFins() {
        return fins;
    }

    public void setFins(String fins) {
        this.fins = fins;
        notifyPropertyChanged(BR.fins);
    }
}
