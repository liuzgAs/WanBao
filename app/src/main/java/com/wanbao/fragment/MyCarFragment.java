package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.CheShouZiZhuanActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.SheZhiActivity;
import com.wanbao.activity.ShiJiaDDActivity;
import com.wanbao.activity.TiYanZhongXinActivity;
import com.wanbao.activity.WeiBaoDDActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
import com.wanbao.ui.CircleImageView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarFragment extends PSFragment {


    @BindView(R.id.aichetiyan)
    LinearLayout aichetiyan;
    Unbinder unbinder;
    @BindView(R.id.imageViewTouX)
    CircleImageView imageViewTouX;
    @BindView(R.id.imageViewXX)
    ImageView imageViewXX;
    @BindView(R.id.imageViewSheZ)
    ImageView imageViewSheZ;
    @BindView(R.id.viewQBDD)
    LinearLayout viewQBDD;
    @BindView(R.id.viewDZF)
    LinearLayout viewDZF;
    @BindView(R.id.viewDQR)
    LinearLayout viewDQR;
    @BindView(R.id.viewDPJ)
    LinearLayout viewDPJ;
    @BindView(R.id.viewWXBY)
    LinearLayout viewWXBY;
    @BindView(R.id.viewYZESC)
    LinearLayout viewYZESC;
    @BindView(R.id.viewSCSJ)
    LinearLayout viewSCSJ;
    @BindView(R.id.viewPTGC)
    LinearLayout viewPTGC;
    @BindView(R.id.viewGDFW)
    LinearLayout viewGDFW;
    @BindView(R.id.viewACDA)
    LinearLayout viewACDA;
    @BindView(R.id.viewWDCD)
    LinearLayout viewWDCD;
    @BindView(R.id.cardViewHuiYuan)
    CardView cardViewHuiYuan;
    @BindView(R.id.textCheShouZZ)
    TextView textCheShouZZ;
    @BindView(R.id.btnBangD)
    Button btnBangD;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.imageCar)
    ImageView imageCar;
    private View view;

    public static MyCarFragment newInstance() {
        MyCarFragment mf = new MyCarFragment();
        return mf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_my_car, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void fetchData() {
        getMyCar();
    }

    @OnClick({R.id.btnBangD, R.id.textCheShouZZ, R.id.aichetiyan, R.id.imageViewTouX, R.id.imageViewXX, R.id.imageViewSheZ, R.id.viewQBDD, R.id.viewDZF, R.id.viewDQR, R.id.viewDPJ, R.id.viewWXBY, R.id.viewYZESC, R.id.viewSCSJ, R.id.viewPTGC, R.id.viewGDFW, R.id.viewACDA, R.id.viewWDCD, R.id.cardViewHuiYuan})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btnBangD:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), BanDingCLActivity.class);
                startActivity(intent);
                break;
            case R.id.textCheShouZZ:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), CheShouZiZhuanActivity.class);
                startActivity(intent);
                break;
            case R.id.aichetiyan:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), TiYanZhongXinActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewTouX:
                break;
            case R.id.imageViewXX:
                break;
            case R.id.imageViewSheZ:
                intent.setClass(getActivity(), SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewQBDD:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDZF:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDQR:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDPJ:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWXBY:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiXiuBYActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYZESC:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSCSJ:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), ShiJiaDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewPTGC:
                break;
            case R.id.viewGDFW:
                break;
            case R.id.viewACDA:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWDCD:
                break;
            case R.id.cardViewHuiYuan:
                break;
            default:
                break;
        }
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (event.getAction().equals(BaseEvent.Change_Data)) {
            getMyCar();
        }
    }

    private void getMyCar() {
        HttpApi.post(context, getOkObjectMyCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("我的爱车", s);
                dismissDialog();
                try {
                    User_My user_my = GsonUtils.parseJSON(s, User_My.class);
                    int status = user_my.getStatus();
                    if (status == 1) {
                        if (user_my.getCarNum() == 0) {
                            viewSwitcher.setDisplayedChild(0);
                        } else {
                            viewSwitcher.setDisplayedChild(1);
                            if (user_my.getData()!=null){
                                textCarName.setText(user_my.getData().getCar_name());
                                textDes.setText(user_my.getData().getCar_no());
                                GlideApp.with(getContext())
                                        .asBitmap()
                                        .load(user_my.getData().getImg())
                                        .placeholder(R.mipmap.ic_empty)
                                        .into(imageCar);
                            }
                        }
                        textName.setText(user_my.getNickname());
                        GlideApp.with(getContext())
                                .asBitmap()
                                .load(user_my.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageViewTouX);
                    } else {
                        ToastUtils.showShort(user_my.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
//                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectMyCar() {
        String url = Constant.HOST + Constant.Url.User_My;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }
}
