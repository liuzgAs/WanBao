package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanbao.R;
import com.wanbao.base.fragment.PSFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiBaoDDFragment extends PSFragment {

    private View view;

    public WeiBaoDDFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_wei_bao_dd, container, false);
        }
        return view;
    }

    @Override
    public void fetchData() {

    }
}