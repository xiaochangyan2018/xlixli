package com.xiao.xlixli.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xiao.xlixli.R;
import com.xiao.xlixli.recommend.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private  TabLayout tab;
    private ViewPager viewPager;
    private List<String> titles;
    private List<Fragment> fragments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTab();
    }

    private void initTab(){
        tab = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        titles = new ArrayList<String>();
        titles.add("直播");
        titles.add("推荐");
        titles.add("热门");
        titles.add("追番");
        titles.add("影视");
        titles.add("70年");
        fragments = new ArrayList<Fragment>();
        for (int i=0;i<6;i++){
            if(i == 1){
                RecommendFragment recommendFragment = new RecommendFragment();
                fragments.add(recommendFragment);

            }else{
                ChannelFragment fragment = new ChannelFragment();
                fragments.add(fragment);
            }

        }
        viewPager.setAdapter(new MyFragmentPageAdapter(getActivity().getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }
    // 定义一个适配器给ViewPager
    class MyFragmentPageAdapter extends FragmentPagerAdapter {

        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return fragments.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            super.destroyItem(container, position, object);
        }

    }
}
