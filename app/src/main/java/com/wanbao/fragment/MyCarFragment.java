package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.CheShouZiZhuanActivity;
import com.wanbao.activity.GengDuoFWActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.SheZhiActivity;
import com.wanbao.activity.WeiBaoDDActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.WoDeCheDuiActivity;
import com.wanbao.activity.WoDeJKActivity;
import com.wanbao.activity.XiaoXiActivity;
import com.wanbao.activity.XinCheZTActivity;
import com.wanbao.activity.YouZhiESCActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
import com.wanbao.ui.CircleImageView;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.CheDuiImgViewHolder;
import com.wanbao.viewholder.MyCarBQViewHolder;

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
    @BindView(R.id.recyclerView)
    MyEasyRecyclerView recyclerView;
    @BindView(R.id.viewSCSJ0)
    LinearLayout viewSCSJ0;
    @BindView(R.id.viewPTGC0)
    LinearLayout viewPTGC0;
    @BindView(R.id.viewYZESC0)
    LinearLayout viewYZESC0;
    @BindView(R.id.viewSwitcher0)
    ViewSwitcher viewSwitcher0;
    @BindView(R.id.recyclerViewcd)
    MyEasyRecyclerView recyclerViewcd;
    @BindView(R.id.textMoreNum)
    TextView textMoreNum;
    private View view;
    private RecyclerArrayAdapter<User_My.InterestBean> adapter;
    private RecyclerArrayAdapter<String> adaptercd;

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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void fetchData() {
        initRecycler();
        initRecyclercd();
        getMyCar();
    }

    @OnClick({R.id.viewSCSJ0, R.id.viewPTGC0, R.id.viewYZESC0, R.id.btnBangD, R.id.textCheShouZZ, R.id.aichetiyan, R.id.imageViewTouX, R.id.imageViewXX, R.id.imageViewSheZ, R.id.viewQBDD, R.id.viewDZF, R.id.viewDQR, R.id.viewDPJ, R.id.viewWXBY, R.id.viewYZESC, R.id.viewSCSJ, R.id.viewPTGC, R.id.viewGDFW, R.id.viewACDA, R.id.viewWDCD, R.id.cardViewHuiYuan})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewSCSJ0:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), YouZhiESCActivity.class);
                startActivity(intent);
                break;
            case R.id.viewPTGC0:
                intent = new Intent();
                intent.setClass(getActivity(), GengDuoFWActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
//                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
//                    intent = new Intent();
//                    intent.setClass(context, LoginActivity.class);
//                    startActivity(intent);
//                    return;
//                }
//                intent = new Intent();
//                intent.setClass(getActivity(), WoDeJKActivity.class);
//                startActivity(intent);
                break;
            case R.id.viewYZESC0:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), XinCheZTActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBangD:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), BanDingCLActivity.class);
                startActivity(intent);
                break;
            case R.id.textCheShouZZ:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), CheShouZiZhuanActivity.class);
                startActivity(intent);
                break;
            case R.id.aichetiyan:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewTouX:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), CheShouZiZhuanActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewXX:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), XiaoXiActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewSheZ:
                intent = new Intent();
                intent.setClass(getActivity(), SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewQBDD:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.putExtra("currentItem", 0);
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDZF:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.putExtra("currentItem", 1);
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDQR:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.putExtra("currentItem", 2);
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDPJ:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.putExtra("currentItem", 3);
                intent.setClass(getActivity(), WeiBaoDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWXBY:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WeiXiuBYActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYZESC:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), XinCheZTActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSCSJ:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), YouZhiESCActivity.class);
                startActivity(intent);
                break;
            case R.id.viewPTGC:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), WoDeJKActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGDFW:
                intent = new Intent();
                intent.setClass(getActivity(), GengDuoFWActivity.class);
                startActivity(intent);
                break;
            case R.id.viewACDA:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWDCD:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(getActivity(), WoDeCheDuiActivity.class);
                startActivity(intent);
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
            fetchData();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<User_My.InterestBean>(context) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_bq;
                return new MyCarBQViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(5, context));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);
                recyclerView.setScroll(true);
            }
        });
        recyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
            @Override
            public void daoDiLe() {
            }
        });
    }
    /**
     * 初始化recyclerview
     */
    private void initRecyclercd() {
        recyclerViewcd.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewcd.setAdapter(adaptercd = new RecyclerArrayAdapter<String>(context) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image_cd;
                return new CheDuiImgViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(5, context));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerViewcd.addItemDecoration(spaceDecoration);
        recyclerViewcd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerViewcd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);
                recyclerViewcd.setScroll(true);
            }
        });
        recyclerViewcd.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
            @Override
            public void daoDiLe() {
            }
        });
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
                LogUtils.e("User_My", s);
                dismissDialog();
                try {
                    User_My user_my = GsonUtils.parseJSON(s, User_My.class);
                    int status = user_my.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(user_my.getInterest());
                        if (user_my.getCarNum() == 0) {
                            viewSwitcher.setDisplayedChild(0);
                            viewSwitcher0.setDisplayedChild(0);
                        } else {
                            viewSwitcher.setDisplayedChild(1);
                            viewSwitcher0.setDisplayedChild(1);
                            if (user_my.getData() != null) {
                                textCarName.setText(user_my.getData().getCar_name());
                                textDes.setText(user_my.getData().getCar_no());
                                GlideApp.with(getContext())
                                        .asBitmap()
                                        .load(user_my.getData().getImg())
                                        .placeholder(R.mipmap.ic_empty)
                                        .into(imageCar);
                            }
                            if (user_my.getCar_team().getIs_show()==1){
                                viewWDCD.setVisibility(View.VISIBLE);
                                adaptercd.clear();
                                adaptercd.addAll(user_my.getCar_team().getHeadimg());
                                if (user_my.getCar_team().getHeadimg().size()>0){
                                    textMoreNum.setText(user_my.getCar_team().getMore_num());
                                }
                            }else {
                                viewWDCD.setVisibility(View.GONE);
                            }
                        }
                        if (!TextUtils.isEmpty(user_my.getNickname())) {
                            textName.setText(user_my.getNickname());
                        } else {
                            textName.setText("");
                        }
                        GlideApp.with(getContext())
                                .asBitmap()
                                .load(user_my.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageViewTouX);
                    } else {
                        ToastUtils.showShort(user_my.getInfo());
                    }
                } catch (Exception e) {
                    if (viewSwitcher != null && viewSwitcher0 != null) {
                        viewSwitcher.setDisplayedChild(0);
                        viewSwitcher0.setDisplayedChild(0);
                    }
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                if (viewSwitcher != null && viewSwitcher0 != null) {
                    viewSwitcher.setDisplayedChild(0);
                    viewSwitcher0.setDisplayedChild(0);
                }
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
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid,0) + "");
        return new OkObject(params, url);
    }

}
