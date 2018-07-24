package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.ListDropDownAdapter;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Car_CarParam;
import com.wanbao.modle.Car_CarStyle;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.OkObject;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.HotCarHolder;
import com.wanbao.viewholder.XuanZheCXViewHolder;
import com.wanbao.viewholder.XzCarCarParamDHolder;
import com.wanbao.viewholder.YearViewHolder;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XuanZheCheXActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private RecyclerArrayAdapter<Car_Index.DataBean> adapter;
    private RecyclerArrayAdapter<Car_Index.YearBean> adapterN;
    private RecyclerArrayAdapter<Car_CarParam.HotbrandBean> hadapter;
    private RecyclerArrayAdapter<Car_CarStyle.DataBean> adaptercx;
    private String bsid = "";
    ArrayList<Car_Index.DataBean> dataBeans = new ArrayList<>();
    private String headers[] = {"综合排序", "价格"};
    private int page=1;
    private List<View> popupViews = new ArrayList<>();
    private EasyRecyclerView recyclerView;
    private ImageView imageCheXi;
    private TextView textCheMing;
    private EasyRecyclerView recyclerViewCheXi;
    private DrawerLayout drawerLayout;
    private Car_Index testDriveList;
    private ListView paixulist ;
    private ListView jiagelist;
    private String price;
    private String sort;
    private String year;
    private ListDropDownAdapter paixuAdapter;
    private  ListDropDownAdapter jiageAdapter;
    private ArrayList<String> paixu;
    private ArrayList<String> jiage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zhe_che_x);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {
        paixulist = new ListView(context);
        jiagelist = new ListView(context);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("选择车型");
        getCar();
        paixulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paixuAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : paixu.get(position));
                dropDownMenu.closeMenu();
                sort=testDriveList.getSort().get(position).getV();
                onRefresh();
            }
        });
        jiagelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jiageAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : jiage.get(position));
                dropDownMenu.closeMenu();
                price=testDriveList.getFilter().get(position).getV();
                onRefresh();
            }
        });
    }

    @Override
    protected void initData() {
    }
    private void getCar() {
        HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
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
                dismissDialog();
                LogUtils.e("getCar", s);
                try {
                    testDriveList = GsonUtils.parseJSON(s, Car_Index.class);
                    int status = testDriveList.getStatus();
                    if (status == 1) {
                        paixu=new ArrayList<>();
                        for(int i=0;i<testDriveList.getSort().size();i++){
                            paixu.add(testDriveList.getSort().get(i).getName());
                        }
                        jiage=new ArrayList<>();
                        for(int i=0;i<testDriveList.getFilter().size();i++){
                            jiage.add(testDriveList.getFilter().get(i).getName());
                        }
                        //init age menu
                        paixulist.setDividerHeight(0);
                        paixuAdapter = new ListDropDownAdapter(context, paixu);
                        paixulist.setAdapter(paixuAdapter);

                        //init age menu
                        jiagelist.setDividerHeight(0);
                        jiageAdapter = new ListDropDownAdapter(context, jiage);
                        jiagelist.setAdapter(jiageAdapter);

                        popupViews.add(paixulist);
                        popupViews.add(jiagelist);
                        View view = LayoutInflater.from(context).inflate(R.layout.view_xuanzhecx, null);
                        recyclerView = view.findViewById(R.id.recyclerView);
                        drawerLayout = view.findViewById(R.id.drawerLayout);
                        imageCheXi = view.findViewById(R.id.imageCheXi);
                        textCheMing = view.findViewById(R.id.textCheMing);
                        recyclerViewCheXi = view.findViewById(R.id.recyclerViewCheXi);
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
                        initRecycler();
                        initRecyclercx();
                        onRefresh();
                    } else {
                        MyDialog.dialogFinish(XuanZheCheXActivity.this,testDriveList.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(XuanZheCheXActivity.this,"数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(XuanZheCheXActivity.this,"网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }


        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_2), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Car_Index.DataBean>(XuanZheCheXActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xuanzhecxn;
                return new XuanZheCXViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private EasyRecyclerView hrecyclerView;
            private MyEasyRecyclerView recyclerViewN;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_xuanzheppx, null);
                hrecyclerView = view.findViewById(R.id.recyclerViewHead);
                recyclerViewN = view.findViewById(R.id.recyclerViewN);

                hrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                hrecyclerView.setAdapter(hadapter = new RecyclerArrayAdapter<Car_CarParam.HotbrandBean>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_hotcar;
                        return new HotCarHolder(parent, layout);
                    }
                });
                hadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if ("更多品牌".equals(hadapter.getItem(position).getName())) {
                            Intent intent = new Intent();
                            intent.setClass(context, XuanZheCLActivity.class);
                            startActivity(intent);
                        } else {
                            getCheXid(String.valueOf(hadapter.getItem(position).getId()));
                            GlideApp.with(context)
                                    .asBitmap()
                                    .load(hadapter.getItem(position).getImg())
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(imageCheXi);
                            textCheMing.setText(hadapter.getItem(position).getName());
                            drawerLayout.openDrawer(GravityCompat.END);
                        }
                    }
                });
                recyclerViewN.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewN.setAdapter(adapterN = new RecyclerArrayAdapter<Car_Index.YearBean>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_year;
                        return new YearViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, context));
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerViewN.addItemDecoration(spaceDecoration);
                recyclerViewN.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                recyclerViewN.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        recyclerViewN.setScroll(true);
                    }
                });
                recyclerViewN.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
                adapterN.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        year=adapterN.getItem(position).getV()+"";
                        onRefresh();
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
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
                            Car_Index usercar_index = GsonUtils.parseJSON(s, Car_Index.class);
                            int status = usercar_index.getStatus();
                            if (status == 1) {
                                adapter.addAll(usercar_index.getData());
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
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new BaseEvent(BaseEvent.Choose_CarX,adapter.getItem(position)));
                finish();
            }
        });
        recyclerView.setRefreshListener(this);
    }

    private void initRecyclercx() {
        recyclerViewCheXi.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCheXi.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.background), (int) getResources().getDimension(R.dimen.dp_1), (int) getResources().getDimension(R.dimen.dp_55), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewCheXi.addItemDecoration(itemDecoration);
        recyclerViewCheXi.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCheXi.setAdapterWithProgress(adaptercx = new RecyclerArrayAdapter<Car_CarStyle.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xuanzhechexi;
                return new XzCarCarParamDHolder(parent, layout);
            }
        });
        adaptercx.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                bsid = String.valueOf(adaptercx.getItem(position).getId());
                if (drawerLayout != null) {
                    drawerLayout.closeDrawers();
                }
                onRefresh();
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                adaptercx.clear();
                adaptercx.notifyDataSetChanged();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    @Override
    public void onRefresh() {
        getCheXi();
        getCarlist();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_Car.equals(event.getAction())) {
            Car_CarStyle.DataBean dataBean = (Car_CarStyle.DataBean) event.getData();
            bsid = String.valueOf(dataBean.getId());
            onRefresh();
        }
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
                        hadapter.clear();
                        hadapter.addAll(car_carParam.getHotbrand());
                        hadapter.add(new Car_CarParam.HotbrandBean(0, "更多品牌", ""));
                    } else {
                        ToastUtils.showShort(car_carParam.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
            }

        });
    }


    private OkObject getOkObjectCX() {
        String url = Constant.HOST + Constant.Url.Car_CarParam;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }


    private void getCarlist() {
        page=1;
        HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("getCar", s);
                try {
                    page++;
                    Car_Index car_index = GsonUtils.parseJSON(s, Car_Index.class);
                    int status = car_index.getStatus();
                    if (status == 1) {
                        adapterN.clear();
                        adapterN.addAll(car_index.getYear());
                        adapter.clear();
                        adapter.addAll(car_index.getData());
                    } else {
                        adapter.clear();
                        adapter.addAll(dataBeans);
                    }
                } catch (Exception e) {
                    if (adapter==null){
                        return;
                    }
                    adapter.clear();
                    adapter.addAll(dataBeans);
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


    private OkObject getOkObjectCar() {
        String url = Constant.HOST + Constant.Url.Car_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("bsid", bsid);
        params.put("p", String.valueOf(page));
        params.put("price", price);
        params.put("sort", sort);
        params.put("year", year);
        return new OkObject(params, url);
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

    @OnClick({R.id.imageback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            default:
                break;
        }
    }
}
