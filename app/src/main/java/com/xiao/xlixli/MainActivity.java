package com.xiao.xlixli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.xiao.xlixli.bean.TabItem;
import com.xiao.xlixli.databinding.MenuTabItemBinding;
import com.xiao.xlixli.fragments.ChannelFragment;
import com.xiao.xlixli.fragments.DynamicFragment;
import com.xiao.xlixli.fragments.HomeFragment;
import com.xiao.xlixli.fragments.MemberFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private List<MenuTabItemBinding> binds = new ArrayList<>();
    private TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initFragment();
        setStatusBar();
    }

    private void init(){
        tab = (TabLayout)findViewById(R.id.tabMenu);
        List<TabItem> items = new ArrayList<>();
        TabItem tabItem = new TabItem();
        tabItem.setLogoNoraml(R.mipmap.img_tab_home_normal);
        tabItem.setLogoPressed(R.mipmap.img_tab_home_pressed);
        tabItem.setTitle("首页");
        tabItem.setPressed(true);
        items.add(tabItem);
        tabItem = new TabItem();
        tabItem.setLogoNoraml(R.mipmap.img_tab_home_normal);
        tabItem.setLogoPressed(R.mipmap.img_tab_home_pressed);
        tabItem.setTitle("频道");
        items.add(tabItem);
        tabItem = new TabItem();
        tabItem.setLogoNoraml(R.mipmap.img_tab_home_normal);
        tabItem.setLogoPressed(R.mipmap.img_tab_home_pressed);
        tabItem.setTitle("动态");
        tabItem.setMessage(true);
        items.add(tabItem);
        tabItem = new TabItem();
        tabItem.setLogoNoraml(R.mipmap.img_tab_home_normal);
        tabItem.setLogoPressed(R.mipmap.img_tab_home_pressed);
        tabItem.setTitle("会员购");
        items.add(tabItem);

        for (TabItem item: items) {
            MenuTabItemBinding itemBinding = MenuTabItemBinding.inflate(LayoutInflater.from(this), tab, false);
            itemBinding.setTabItem(item);
            TabLayout.Tab tabitem = tab.newTab().setCustomView(itemBinding.getRoot());
            tabitem.setTag(itemBinding);
            binds.add(itemBinding);
            tab.addTab(tabitem,item.getTitle().equals("首页"));
        }

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (MenuTabItemBinding d : binds){
                    d.getTabItem().setPressed(false);
                }

                MenuTabItemBinding binding =  (MenuTabItemBinding)tab.getTag();
                binding.getTabItem().setPressed(true);
                if(tab.getPosition() ==0){
                    showFragment(homeFragment);
                }else if(tab.getPosition() ==1){
                    showFragment(channelFragment);
                }else if(tab.getPosition() ==2){
                    showFragment(dynamicFragment);
                }else{
                    showFragment(memberFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private HomeFragment homeFragment;
    private ChannelFragment channelFragment;
    private DynamicFragment dynamicFragment;
    private MemberFragment memberFragment;
    private void initFragment(){
        homeFragment = new HomeFragment();
        channelFragment = new ChannelFragment();
        dynamicFragment = new DynamicFragment();
        memberFragment = new MemberFragment();
        showFragment(homeFragment);
    }

    private Fragment curFragment;
    private void showFragment(Fragment fragment) {
        if(fragment == curFragment)return;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if(curFragment == null){
            transaction.add(R.id.frameContent, fragment).commit();
            curFragment = fragment;
            return;
        }
        if(!fragment.isAdded()){
            transaction.hide(curFragment).add(R.id.frameContent, fragment).commit();
        }else{
            transaction.hide(curFragment).show(fragment).commit();
        }
        curFragment = fragment;
    }

}
