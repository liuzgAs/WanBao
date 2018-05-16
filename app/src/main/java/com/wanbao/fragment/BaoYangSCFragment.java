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
public class BaoYangSCFragment extends Fragment {
    private View view;


    public BaoYangSCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_bao_yang_sc, container, false);
        }
        return view;
    }

}
