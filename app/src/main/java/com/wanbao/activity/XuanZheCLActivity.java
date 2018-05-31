package com.wanbao.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.SideLetterBarCX;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Car_CarParam;
import com.wanbao.modle.Car_CarStyle;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.CarCarParamHolder;
import com.wanbao.viewholder.ReMenCheHolder;
import com.wanbao.viewholder.XzCarCarParamDHolder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XuanZheCLActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.imageCheXi)
    ImageView imageCheXi;
    @BindView(R.id.textCheMing)
    TextView textCheMing;
    @BindView(R.id.recyclerViewCheXi)
    EasyRecyclerView recyclerViewCheXi;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.side_letter_bar)
    SideLetterBarCX sideLetterBar;
    @BindView(R.id.tv_letter_overlay)
    TextView tvLetterOverlay;
    private RecyclerArrayAdapter<Car_CarParam.HotbrandBean> hadapter;
    private RecyclerArrayAdapter<Car_CarParam.BrandBean> adapter;
    private RecyclerArrayAdapter<Car_CarStyle.DataBean> adaptercx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zhe_cl);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("选择品牌");
        sideLetterBar.setOverlay(tvLetterOverlay);
        initRecycler();
        initRecyclercx();
        sideLetterBar.setOnLetterChangedListener(new SideLetterBarCX.OnLetterChangedListener() {

            @Override
            public void onLetterChanged(String letter, int position) {
                recyclerView.scrollToPosition(position);
            }
        });

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.background), (int) getResources().getDimension(R.dimen.dp_1), (int) getResources().getDimension(R.dimen.dp_55), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Car_CarParam.BrandBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chexi;
                return new CarCarParamHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private EasyRecyclerView hrecyclerView;


            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_xuanzhepp, null);
                hrecyclerView = view.findViewById(R.id.recyclerViewHead);
                hrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                hrecyclerView.setAdapter(hadapter = new RecyclerArrayAdapter<Car_CarParam.HotbrandBean>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_remenche;
                        return new ReMenCheHolder(parent, layout);
                    }
                });
                hadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        getCheXid(String.valueOf(hadapter.getItem(position).getId()));
                        GlideApp.with(context)
                                .asBitmap()
                                .load(hadapter.getItem(position).getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCheXi);
                        textCheMing.setText(hadapter.getItem(position).getName());
                        drawerLayout.openDrawer(GravityCompat.END);
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        // StickyHeader
        recyclerView.setRefreshListener(this);
    }

    private void initRecyclercx() {
        recyclerViewCheXi.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCheXi.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.background), (int) getResources().getDimension(R.dimen.dp_1), (int) getResources().getDimension(R.dimen.dp_55), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewCheXi.addItemDecoration(itemDecoration);
        recyclerViewCheXi.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCheXi.setAdapter(adaptercx = new RecyclerArrayAdapter<Car_CarStyle.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xuanzhechexi;
                return new XzCarCarParamDHolder(parent, layout);
            }
        });
        adaptercx.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        getCheXi();
    }

    private void getCheXi() {
        HttpApi.post(context, getOkObjectCX(), new HttpApi.CallBack() {
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
                    Car_CarParam car_carParam = GsonUtils.parseJSON(s, Car_CarParam.class);
                    int status = car_carParam.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        hadapter.clear();
                        adapter.addAll(car_carParam.getBrand());
                        adapter.notifyDataSetChanged();
                        hadapter.addAll(car_carParam.getHotbrand());
                    } else {
                        ToastUtils.showShort(car_carParam.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
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

    private OkObject getOkObjectCX() {
        String url = Constant.HOST + Constant.Url.Car_CarParam;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Car_Id.equals(event.getAction())){
            Car_CarParam.BrandBean.ListBean listBean=(Car_CarParam.BrandBean.ListBean)event.getData();
            if (listBean!=null){
                getCheXid(String.valueOf(listBean.getId()));
                GlideApp.with(context)
                        .asBitmap()
                        .load(listBean.getImg())
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageCheXi);
                textCheMing.setText(listBean.getName());
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }
    }

    private void getCheXid(String id) {
        HttpApi.post(context, getOkObjectCXd(id), new HttpApi.CallBack() {
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
                    Car_CarStyle car_carStyle = GsonUtils.parseJSON(s, Car_CarStyle.class);
                    int status = car_carStyle.getStatus();
                    if (status == 1) {
                        adaptercx.clear();
                        adaptercx.addAll(car_carStyle.getData());
                    } else {
                        ToastUtils.showShort(car_carStyle.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
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

    private OkObject getOkObjectCXd(String id) {
        String url = Constant.HOST + Constant.Url.Car_CarStyle;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);

        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
