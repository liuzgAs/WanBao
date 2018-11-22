package com.wanbao.fragment;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.sunfusheng.marqueeview.MarqueeView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.MianFeiYCActivity;
import com.wanbao.activity.ShiMinRzActivity;
import com.wanbao.activity.TouTiaoLBActivity;
import com.wanbao.activity.WebHongBaoActivity;
import com.wanbao.activity.WebViewActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.XiaoXiActivity;
import com.wanbao.activity.XinCheZTActivity;
import com.wanbao.activity.XuanZheCheXSJActivity;
import com.wanbao.activity.YouZhiESCActivity;
import com.wanbao.adapter.GlideImageLoader;
import com.wanbao.base.AppContext;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.BaseFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ScreenUtils;
import com.wanbao.modle.Bonus_BonusDown;
import com.wanbao.modle.IndexBonusbefore;
import com.wanbao.modle.IndexBonusget;
import com.wanbao.modle.Index_Home;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
import com.wanbao.viewholder.IndexViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AMapLocationListener {


    @BindView(R.id.lv_main)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.imageSousuo)
    ImageView imageSousuo;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    @BindView(R.id.imageHongBaoDialog)
    ImageView imageHongBaoDialog;
    private RecyclerArrayAdapter<Index_Home.TeamDataBean> adapter;
    private View view;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    List<String> info = new ArrayList<>();
    int carNum = 0;
    private Index_Home indexHome;
    private int page = 1;
    private boolean isHongBaoShow = false;
    private Dialog mDialog;
    private Bonus_BonusDown bonus_bonusDown;
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
            unbinder = ButterKnife.bind(this, view);
            init();
        }
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
        if (event.getAction().equals(BaseEvent.ShowTips)) {
            if (!isHongBaoShow) {
                hongBaoQingQing();
                isHongBaoShow = true;
            }
        }
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {
        initRecycler();

        //初始化定位
        mLocationClient = new AMapLocationClient(AppContext.getIntance());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(MainFragment.this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        if (mList.size() == 0) {
            mList.add("已到底线了");
        }
        getAddressPermissions();
        onRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isHongBaoShow&&SPUtils.getInstance().getInt(Constant.SF.ShowTips, 1)>=4) {
            hongBaoQingQing();
            isHongBaoShow = true;
        }
    }

    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(0, getActivity()));
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Index_Home.TeamDataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_yangche;
                return new IndexViewHolder(parent, layout);
            }
        });
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(2));
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
                layoutParams.width = (int) (screenWidth - context.getResources().getDimension(R.dimen.dp_20));
                banner.setLayoutParams(layoutParams);
                LogUtils.e("ShouYeFragment--onCreateView", "" + (int) (480f * (float) screenWidth / 1080f));
                layoutParams.height = (int) (480f * (float) screenWidth / 1080f);
                marqueeView = view.findViewById(R.id.marqueeView);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (indexHome != null) {
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(indexHome.getBanner());
                    //banner设置方法全部调用完毕时最后调用
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    banner.start();
                    info.clear();
                    for (int i = 0; i < indexHome.getNews().size(); i++) {
                        info.add(indexHome.getNews().get(i).getTitle());
                    }
                    marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
                }
                marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Intent intent = new Intent();
                        intent.putExtra("title", indexHome.getNews().get(position).getTitle());
                        intent.putExtra("mUrl", indexHome.getNews().get(position).getUrl());
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
                        Intent intent = new Intent(context, XinCheZTActivity.class);
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
                        Intent intent = new Intent(context, TouTiaoLBActivity.class);
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
                        Intent intent = new Intent(context, TouTiaoLBActivity.class);
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
                Intent intent = new Intent(context, MianFeiYCActivity.class);
                intent.putExtra("ctid", String.valueOf(adapter.getItem(position).getId()));
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }


    private List<String> mList;


    private void getMain() {
        page = 1;
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
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        params.put("p", page + "");
        return new OkObject(params, url);
    }
    /**
     * 红包请求
     */
    private void hongBaoQingQing() {

        HttpApi.post(context, getHongBaoQQOkObject(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("主页", s);
                try {
                    bonus_bonusDown = GsonUtils.parseJSON(s, Bonus_BonusDown.class);
                    int status = bonus_bonusDown.getStatus();
                    if (status == 1) {
                        if (bonus_bonusDown.getDown() == 1) {
                            hongBaoDialog();
                            imageHongBaoDialog.setVisibility(View.VISIBLE);
                        } else {
                            imageHongBaoDialog.setVisibility(View.GONE);
                        }
                    } else {
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onError() {
            }

            @Override
            public void onComplete() {
            }

        });
    }
    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getHongBaoQQOkObject() {
        String url = Constant.HOST + Constant.Url.Bonus_BonusDown;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
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
                if (!checkGPSIsOpen()){
                    openGPSSettings();
                    return;
                }
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
                SPUtils.getInstance().put(Constant.SF.Latitude, String.valueOf(amapLocation.getLatitude()));
                SPUtils.getInstance().put(Constant.SF.Longitude, String.valueOf(amapLocation.getLongitude()));

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
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        return new OkObject(params, url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.imageHongBaoDialog)
    public void onViewClicked() {
        hongBaoQingQing();
    }
    /**
     * 红包弹窗
     */
    @SuppressLint("WrongConstant")
    private void hongBaoDialog() {
        if (mDialog == null) {
            int screenWidth = ScreenUtils.getScreenWidth(context);
            int screenHeight = ScreenUtils.getScreenHeight(context);


            View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_hongbao, null);
            RelativeLayout relaHongBao = (RelativeLayout) inflate.findViewById(R.id.relaHongBao);


            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao002);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 60), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(3000) + 2000;
                int delay = new Random().nextInt(2500);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao002);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 60), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(3000) + 2000;
                int delay = new Random().nextInt(2500);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }

            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao001);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao001);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao003);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }

            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.hongbao003);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }


            mDialog = new Dialog(context, R.style.mydialog);
            mDialog.setContentView(inflate);
            mDialog.show();
        } else {
            mDialog.show();
        }
    }
    /**
     * 抢红包
     */
    private void qiangHongBao() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
            Intent intent=new Intent();
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
            return;
        }
        HttpApi.post(context, getQiangHongBAoOkObject(), new HttpApi.CallBack() {
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
                    IndexBonusbefore indexBonusbefore = GsonUtils.parseJSON(s, IndexBonusbefore.class);
                    int status = indexBonusbefore.getStatus();
                    if (status == 1) {
                        if (indexBonusbefore.getGoRealName() == 1) {
                            new AlertDialog.Builder(context)
                                    .setTitle("提示")
                                    .setMessage(indexBonusbefore.getDes())
                                    .setNegativeButton("否", null)
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent=new Intent(context,ShiMinRzActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(context, WebHongBaoActivity.class);
                            intent.putExtra(Constant.INTENT_KEY.TITLE, "领取红包");
                            intent.putExtra(Constant.INTENT_KEY.URL, indexBonusbefore.getUrl());
                            startActivityForResult(intent, Constant.REQUEST_RESULT_CODE.HONG_BAO);
                        }
                    } else {
                        ToastUtils.showShort(indexBonusbefore.getInfo());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
            }

        });
    }
    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getQiangHongBAoOkObject() {
        String url = Constant.HOST + Constant.Url.Bonus_BonusBefore;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        return new OkObject(params, url);
    }
    /**
     * 抢到红包
     *
     * @param indexBonusget
     */
    private void showHongBaoDialog(IndexBonusget indexBonusget) {
        int screenWidth = ScreenUtils.getScreenWidth(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_hongbaolq, null);
        TextView texthongBao = (TextView) inflate.findViewById(R.id.texthongBao);

        texthongBao.setText(indexBonusget.getMoney() + "元");
        View linearhongBao = inflate.findViewById(R.id.linearhongBao);
        ViewGroup.LayoutParams layoutParams = linearhongBao.getLayoutParams();
        int width = screenWidth - (int) DpUtils.convertDpToPixel(40 * 2, context);
        layoutParams.width = width;
        layoutParams.height = (int) (width * 1.15f);
        linearhongBao.setLayoutParams(layoutParams);
        final Dialog mDialog = new Dialog(context, R.style.mydialog);
        mDialog.setContentView(inflate);
        mDialog.show();
        inflate.findViewById(R.id.viewhongBao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        Toast.makeText(context, indexBonusget.getDes(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_RESULT_CODE.HONG_BAO && resultCode == Constant.REQUEST_RESULT_CODE.HONG_BAO) {
            IndexBonusget indexBonusget = (IndexBonusget) data.getSerializableExtra(Constant.INTENT_KEY.value);
            showHongBaoDialog(indexBonusget);
        }
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        return isOpen;
    }
    private int GPS_REQUEST_CODE = 10;

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
            //没有打开则弹出对话框
            new AlertDialog.Builder(context)
                    .setTitle(R.string.notifyTitle)
                    .setMessage("请打开GPS")
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })

                    .setPositiveButton(R.string.setting,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, GPS_REQUEST_CODE);
                                }
                            })

                    .setCancelable(false)
                    .show();
    }
}
