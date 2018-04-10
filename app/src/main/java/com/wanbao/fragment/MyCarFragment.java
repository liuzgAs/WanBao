package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wanbao.R;
import com.wanbao.activity.TiYanZhongXinActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarFragment extends Fragment {


    @BindView(R.id.aichetiyan)
    LinearLayout aichetiyan;
    Unbinder unbinder;

    public static MyCarFragment newInstance() {
        MyCarFragment mf = new MyCarFragment();
        return mf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.aichetiyan)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.setClass(getActivity(),TiYanZhongXinActivity.class);
        startActivity(intent);
    }
}
