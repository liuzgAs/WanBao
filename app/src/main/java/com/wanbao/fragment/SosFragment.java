package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.activity.CheLiangGZActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;

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
    @BindView(R.id.clbt)
    LinearLayout clbt;
    @BindView(R.id.myl)
    LinearLayout myl;
    @BindView(R.id.jtsg)
    LinearLayout jtsg;
    private View view;

    public static SosFragment newInstance() {
        SosFragment sf = new SosFragment();
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
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
        intent.setClass(context, CheLiangGZActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchData() {

    }

    @OnClick({R.id.clbt, R.id.myl, R.id.jtsg})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.clbt:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, CheLiangGZActivity.class);
                startActivity(intent);
                break;
            case R.id.myl:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, CheLiangGZActivity.class);
                startActivity(intent);
                break;
            case R.id.jtsg:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, CheLiangGZActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
