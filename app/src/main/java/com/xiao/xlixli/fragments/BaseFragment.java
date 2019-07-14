package com.xiao.xlixli.fragments;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle3.components.support.RxFragment;


public class BaseFragment extends RxFragment {
   public View findViewById(int id){
       return getView().findViewById(id);
   }
}
