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
public class MakeMoneyFragment extends PSFragment {


    public static MakeMoneyFragment newInstance() {
        MakeMoneyFragment mf = new MakeMoneyFragment();
        return mf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_money, container, false);
    }

    @Override
    public void fetchData() {

    }
}
