package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanbao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiXiuJLFragment extends Fragment {
    private View view;


    public WeiXiuJLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_wei_xiu_jl, container, false);
        }
        return view;
    }

}