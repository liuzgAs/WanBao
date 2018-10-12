package com.wanbao.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.sunfusheng.marqueeview.MarqueeView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.MianFeiYCActivity;
import com.wanbao.activity.TouTiaoLBActivity;
import com.wanbao.activity.WebViewActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.XiaoXiActivity;
import com.wanbao.activity.XuanZheCheXSJActivity;
import com.wanbao.activity.YangCheLBActivity;
import com.wanbao.activity.YouZhiESCActivity;
import com.wanbao.adapter.GlideImageLoader;
import com.wanbao.base.AppContext;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ScreenUtils;
import com.wanbao.modle.Index_Home;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
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
    private RecyclerArrayAdapter<Index_Home.TeamDataBean> adapter;
    private View view;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    List<String> info = new ArrayList<>();
    int carNum = 0;
    private Index_Home indexHome;
    private int page=1;
    public static MainFragment newInstance() {
        MainFragment mf = new MainFragment();
        return mf;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.dp_45) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
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
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, 0,0 , 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Index_Home.TeamDataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_yangche;
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
                int screenWidth = ScreenUtils.getScreenWidth(context);
                ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                layoutParams.width = (int) (screenWidth-context.getResources().getDimension(R.dimen.dp_20));
                banner.setLayoutParams(layoutParams);
                LogUtils.e("ShouYeFragment--onCreateView", ""+(int) (480f * (float) screenWidth / 1080f));
                layoutParams.height = (int) (480f * (float) screenWidth / 1080f);
                marqueeView = view.findViewById(R.id.marqueeView);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (indexHome!=null){
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(indexHome.getBanner());
                    //banner设置方法全部调用完毕时最后调用
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    banner.start();
                    info.clear();
                    for (int i=0;i<indexHome.getNews().size();i++){
                        info.add(indexHome.getNews().get(i).getTitle());
                    }
                    marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
                }
                marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Intent intent=new Intent();
                        intent.putExtra("title",indexHome.getNews().get(position).getTitle());
                        intent.putExtra("mUrl",indexHome.getNews().get(position).getUrl());
                        intent.setClass(context, WebViewActivity.class);
                        startActivity(intent);
                    }
                });
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
                viewGsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context,YangCheLBActivity.class);
                        startActivity(intent);
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
                        Intent intent=new Intent(context,TouTiaoLBActivity.class);
                        startActivity(intent);
                    }
                });
                viewAcrj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            Intent intent = new Intent();
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setClass(context, AiCheDangAnActivity.class);
                        startActivity(intent);
                    }
                });
                viewPmzc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), YouZhiESCActivity.class);
                        startActivity(intent);
                    }
                });
                viewScsj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            Intent intent = new Intent();
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setClass(context, XuanZheCheXSJActivity.class);
                        startActivity(intent);
                    }
                });
                viewHdxx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context,TouTiaoLBActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
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
                        try {
                            page++;
                            Index_Home usercar_index = GsonUtils.parseJSON(s, Index_Home.class);
                            int status = usercar_index.getStatus();
                            if (status == 1) {
                                adapter.addAll(usercar_index.getTeamData());
                            } else {
                                ToastUtils.showShort(usercar_index.getInfo());
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
                    }

                    @Override
                    public void onComplete() {
                    }

                });
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(context,MianFeiYCActivity.class);
                intent.putExtra("ctid",String.valueOf(adapter.getItem(position).getId()));
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }



    private List<String> mList;

    @Override
    public void fetchData() {
        LogUtils.getConfig().setLogSwitch(true);
        initRecycler();

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
        page=1;
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
                LogUtils.getConfig().setBorderSwitch(false);
                LogUtils.e("主页", s);
                try {
                    page++;
                    indexHome = GsonUtils.parseJSON(s, Index_Home.class);
                    int status = indexHome.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(indexHome.getTeamData());
                        adapter.notifyDataSetChanged();
                    } else {
                    }
                } catch (Exception e) {
                }
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
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid,0) + "");
        params.put("p", page+ "");
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
        Intent intent;
        switch (view.getId()) {
            case R.id.address:
                getAddressPermissions();
//                intent.setClass(context, XuanZheCSActivity.class);
//                startActivity(intent);
                break;
            case R.id.imageSousuo:
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
                address.setText(amapLocation.getCity().toString() + amapLocation.getDistrict().toString() + amapLocation.getStreet().toString()
                        + amapLocation.getStreetNum().toString());
                SPUtils.getInstance().put(Constant.SF.Latitude,String.valueOf(amapLocation.getLatitude()));
                SPUtils.getInstance().put(Constant.SF.Longitude,String.valueOf(amapLocation.getLongitude()));

                dismissDialog();

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
        unbinder.unbind();
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
