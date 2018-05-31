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
public class XinCheBDFragment extends Fragment {


    private View view;

    public static XinCheBDFragment newInstance() {
        XinCheBDFragment sf = new XinCheBDFragment();
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_xin_che_bd, container, false);
        }
        return view;
    }

}
