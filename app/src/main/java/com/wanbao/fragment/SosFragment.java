package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wanbao.R;
import com.wanbao.activity.WangJiMiMaActivity;
import com.wanbao.base.fragment.PSFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SosFragment extends PSFragment {


    @BindView(R.id.qdsg)
    LinearLayout qdsg;
    Unbinder unbinder;
    private View view;

    public static SosFragment newInstance() {
        SosFragment sf = new SosFragment();
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_sos, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.qdsg)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(context, WangJiMiMaActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchData() {

    }
}
