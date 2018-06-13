package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanbao.R;
import com.wanbao.base.fragment.PSFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TuiJianHYGCFragment extends PSFragment {
    private View view;
    List<String> list = new ArrayList<>();


    public TuiJianHYGCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_tui_jian_hygc, container, false);
        }
        return view;
    }

    @Override
    public void fetchData() {
    }


}
