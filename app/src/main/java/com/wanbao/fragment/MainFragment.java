package com.wanbao.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.sunfusheng.marqueeview.MarqueeView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.XuanZheCheXSJActivity;
import com.wanbao.adapter.GlideImageLoader;
import com.wanbao.base.AppContext;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.UpgradeUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.IndexItemViewHolder;
import com.wanbao.viewholder.IndexViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends PSFragment implements SwipeRefreshLayout.OnRefreshListener, AMapLocationListener {


    @BindView(R.id.lv_main)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.imageSousuo)
    ImageView imageSousuo;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    private RecyclerArrayAdapter<String> adapter;
    private View view;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    ArrayList<String> images = new ArrayList<>();
    List<String> info = new ArrayList<>();
    int carNum = 0;

    public static MainFragment newInstance() {
        MainFragment mf = new MainFragment();
        return mf;
    }

    private RecyclerArrayAdapter<Integer> hadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onRefresh() {
        getMain();
        getMyCar();
    }
    @Override
    public void onEventMainThread(BaseEvent event) {
        if (event.getAction().equals(BaseEvent.Change_Data)) {
            getMyCar();
        }
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_5), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<String>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item;
                return new IndexViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private Banner banner;
            private View viewGsc;
            private View viewWxby;
            private View viewSsfy;
            private View viewAcrj;
            private View viewPmzc;
            private View viewScsj;
            private View viewHdxx;
            private MarqueeView marqueeView;
            private MyEasyRecyclerView hrecyclerView;


            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_shouye, null);
                banner = view.findViewById(R.id.banner);
                viewGsc = view.findViewById(R.id.viewGsc);
                viewWxby = view.findViewById(R.id.viewWxby);
                viewSsfy = view.findViewById(R.id.viewSsfy);
                viewAcrj = view.findViewById(R.id.viewAcrj);
                viewPmzc = view.findViewById(R.id.viewPmzc);
                viewScsj = view.findViewById(R.id.viewScsj);
                viewHdxx = view.findViewById(R.id.viewHdxx);
                marqueeView = view.findViewById(R.id.marqueeView);
                hrecyclerView = view.findViewById(R.id.recyclerView);
                hrecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                hrecyclerView.setAdapter(hadapter = new RecyclerArrayAdapter<Integer>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_index_item;
                        return new IndexItemViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, context));
                spaceDecoration.setPaddingEdgeSide(false);
                hrecyclerView.addItemDecoration(spaceDecoration);
                hrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                hrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        hrecyclerView.setScroll(true);
                    }
                });
                hrecyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                hadapter.clear();
                hadapter.add(1);
                hadapter.add(2);
                hadapter.add(3);
                hadapter.notifyDataSetChanged();
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                //设置轮播时间
                banner.setDelayTime(3000);
                banner.start();
                marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
                viewGsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewWxby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            Intent intent = new Intent();
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            if (carNum == 0) {
                                Intent intent = new Intent();
                                intent.setClass(context, BanDingCLActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(context, WeiXiuBYActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });
                viewSsfy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewAcrj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(context, AiCheDangAnActivity.class);
                        startActivity(intent);
                    }
                });
                viewPmzc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewScsj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(context, XuanZheCheXSJActivity.class);
                        startActivity(intent);
                    }
                });
                viewHdxx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        recyclerView.setRefreshListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private List<String> mList;

    @Override
    public void fetchData() {
        LogUtils.getConfig().setLogSwitch(true);
        initRecycler();
        images.clear();
        images.add("1");
        images.add("2");
        info.clear();
        info.add("400匹全时四驱到底有多快？两小伙阿迪发动");
        info.add("500匹全时四驱到底有多快？两小伙阿迪发动");
        info.add("600匹全时四驱到底有多快？两小伙阿迪发动");

        //初始化定位
        mLocationClient = new AMapLocationClient(AppContext.getIntance());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(MainFragment.this);
        mList = new ArrayList<>();
        if (mList.size() == 0) {
            mList.add("已到底线了");
        }
        showDialog("定位中..");
        getAddressPermissions();
        onRefresh();
    }

    private void getMain() {
        HttpApi.post(context, getOkObjectSms(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                adapter.clear();
                adapter.add("底线了");
                LogUtils.getConfig().setBorderSwitch(false);
                LogUtils.e("主页", s);
            }

            @Override
            public void onError() {
                showError("网络异常");
            }

            @Override
            public void onComplete() {
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(context).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
    }

    private OkObject getOkObjectSms() {
        String url = Constant.HOST + Constant.Url.Home;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void getAddressPermissions() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            setDingw();
                        } else {
                            dismissDialog();
                            Toast.makeText(context, "拒绝权限,点击重新申请！", Toast.LENGTH_SHORT).show();
                            address.setText("定位失败，点击重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setDingw() {
        address.setText("定位中..");
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @OnClick({R.id.address, R.id.imageSousuo})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.address:
                getAddressPermissions();
//                intent.setClass(context, XuanZheCSActivity.class);
//                startActivity(intent);
                break;
            case R.id.imageSousuo:
                UpgradeUtils.checkUpgrade(context);
//                Intent intent1 = new Intent();
//                intent1.setClass(context, LoginActivity.class);
//                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
//                address.setText(amapLocation.getAddress());
                dismissDialog();
                address.setText(amapLocation.getCity().toString() + amapLocation.getDistrict().toString() + amapLocation.getStreet().toString()
                        + amapLocation.getStreetNum().toString());

            } else {
                dismissDialog();
                address.setText("定位失败，点击重试");
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
    }


    private void getMyCar() {
        HttpApi.post(context, getOkObjectMyCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("我的爱车", s);
                try {
                    User_My user_my = GsonUtils.parseJSON(s, User_My.class);
                    int status = user_my.getStatus();
                    if (status == 1) {
                        carNum = user_my.getCarNum();
                    } else {
                        ToastUtils.showShort(user_my.getInfo());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onError() {
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
