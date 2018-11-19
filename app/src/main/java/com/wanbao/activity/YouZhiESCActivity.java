package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.luck.picture.lib.tools.ScreenUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.GridView4ScrollView;
import com.wanbao.modle.Buyer;
import com.wanbao.modle.CarGetsearchdata;
import com.wanbao.modle.Car_CarStyle;
import com.wanbao.modle.City_List;
import com.wanbao.modle.MaiChe;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.ShouYeViewHolder;
import com.wanbao.viewholder.SortViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class YouZhiESCActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.recyclerViewShaiXuan00)
    EasyRecyclerView recyclerViewShaiXuan00;
    @BindView(R.id.gridPrice)
    GridView4ScrollView gridPrice;
    @BindView(R.id.textZidingYiJaiGe)
    TextView textZidingYiJaiGe;
    @BindView(R.id.textRange)
    TextView textRange;
    @BindView(R.id.rangeSeekbar)
    CrystalRangeSeekbar rangeSeekbar;
    @BindView(R.id.btnPrice)
    Button btnPrice;
    @BindView(R.id.viewShaiXuan01)
    LinearLayout viewShaiXuan01;
    @BindView(R.id.gridAge)
    GridView4ScrollView gridAge;
    @BindView(R.id.textZidingYiJaiGe1)
    TextView textZidingYiJaiGe1;
    @BindView(R.id.textRange1)
    TextView textRange1;
    @BindView(R.id.rangeSeekbar1)
    CrystalRangeSeekbar rangeSeekbar1;
    @BindView(R.id.btnAge)
    Button btnAge;
    @BindView(R.id.viewShaiXuan02)
    LinearLayout viewShaiXuan02;
    @BindView(R.id.viewDismiss)
    RelativeLayout viewDismiss;
    @BindView(R.id.viewShaiXuan)
    LinearLayout viewShaiXuan;
    @BindView(R.id.imageZuJi)
    ImageView imageZuJi;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.viewSearch)
    RelativeLayout viewSearch;
    @BindView(R.id.imageView34)
    ImageView imageView34;
    @BindView(R.id.textLocation)
    TextView textLocation;
    @BindView(R.id.viewLocation)
    RelativeLayout viewLocation;
    @BindView(R.id.relaTitleStatue)
    RelativeLayout relaTitleStatue;
    @BindView(R.id.textSort)
    TextView textSort;
    @BindView(R.id.viewShaiXuan0000)
    RelativeLayout viewShaiXuan0000;
    @BindView(R.id.textView57)
    TextView textView57;
    @BindView(R.id.viewShaiXuan0001)
    RelativeLayout viewShaiXuan0001;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.viewShaiXuan0002)
    RelativeLayout viewShaiXuan0002;
    @BindView(R.id.textAll)
    TextView textAll;
    @BindView(R.id.viewAll)
    RelativeLayout viewAll;
    private RecyclerArrayAdapter<Buyer.DataBean> adapter;
    private RecyclerArrayAdapter<CarGetsearchdata.DataBean.SortIdBean> adapterSort;
    private int page = 1;
    private int shaiXuanVisible = -1;
    private PriceAdapter priceAdapter;
    private PriceAdapter ageAdapter;
    private List<CarGetsearchdata.DataBean.SortIdBean> sortIdBeanList = new ArrayList<>();
    private List<CarGetsearchdata.AgePriceBean> zPriceBeanList = new ArrayList<>();
    private List<CarGetsearchdata.AgePriceBean> zAgeBeanList = new ArrayList<>();
    public boolean isSearch = false;
    public boolean isPinPaiXC = false;
    public boolean isJiaGEXC = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_zhi_esc);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {
        city_id = Integer.valueOf(SPUtils.getInstance().getString(Constant.SF.CityId));
        textLocation.setText(SPUtils.getInstance().getString(Constant.SF.City));
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = relaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.dp_45) + ScreenUtils.getStatusBarHeight(context));
        relaTitleStatue.setLayoutParams(layoutParams);
        relaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(context), 0, 0);
        initRecycle();
        initSortRecycler();
        priceAdapter = new PriceAdapter(zPriceBeanList);
        gridPrice.setAdapter(priceAdapter);
        ageAdapter = new PriceAdapter(zAgeBeanList);
        gridAge.setAdapter(ageAdapter);
        setListeners();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_Car.equals(event.getAction())) {
            Car_CarStyle.DataBean dataBean = (Car_CarStyle.DataBean) event.getData();
            bid = dataBean.getId();
//            bsid = data.getIntExtra(Constant.IntentKey.BSID, 0);
            String name = dataBean.getName();
            textAll.setText(name);
            onRefresh();
        }
        if (BaseEvent.Choose_CS.equals(event.getAction())) {
            City_List.CityBean.ListBean cityBean = (City_List.CityBean.ListBean) event.getData();
            city_id = cityBean.getId();
            textLocation.setText(cityBean.getName());
            onRefresh();
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSearchOkObject() {
        String url = Constant.HOST + Constant.Url.Carold_GetSearchData;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        HttpApi.post(context, getSearchOkObject(), new HttpApi.CallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("MaiCheFragment--获取价格、车龄范围数组", s + "");
                try {
                    CarGetsearchdata carGetsearchdata = GsonUtils.parseJSON(s, CarGetsearchdata.class);
                    if (carGetsearchdata.getStatus() == 1) {
                        sortIdBeanList.clear();
                        sortIdBeanList.addAll(carGetsearchdata.getData().getSort_id());
                        for (int i = 0; i < sortIdBeanList.size(); i++) {
                            sortIdBeanList.get(i).setSelect(false);
                        }
                        sortIdBeanList.get(0).setSelect(true);
                        adapterSort.clear();
                        adapterSort.addAll(sortIdBeanList);
                        zPriceBeanList = carGetsearchdata.getData().getZ_price();
                        for (int i = 0; i < zPriceBeanList.size(); i++) {
                            zPriceBeanList.get(i).setSelect(false);
                        }
                        zPriceBeanList.get(0).setSelect(true);
                        zAgeBeanList = carGetsearchdata.getData().getZ_age();
                        for (int i = 0; i < zAgeBeanList.size(); i++) {
                            zAgeBeanList.get(i).setSelect(false);
                        }
                        zAgeBeanList.get(0).setSelect(true);
                        priceAdapter.setList(zPriceBeanList);
                        ageAdapter.setList(zAgeBeanList);
                        priceAdapter.notifyDataSetChanged();
                        ageAdapter.notifyDataSetChanged();
                        onRefresh();
                    } else {
                        Toast.makeText(context, carGetsearchdata.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("请求失败");
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


    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, context), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Buyer.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shou_ye;
                return new ShouYeViewHolder(parent, layout, 1);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.postJson(context, url, getOkObject(), new HttpApi.NCallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                            int status = buyer.getStatus();
                            if (status == 1) {
                                List<Buyer.DataBean> dataBeanList = buyer.getData();
                                adapter.addAll(dataBeanList);
                            } else {
                                adapter.pauseMore();
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
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
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                intent.setClass(context, CheLiangXQActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initSortRecycler() {
        recyclerViewShaiXuan00.setLayoutManager(new LinearLayoutManager(context));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewShaiXuan00.addItemDecoration(itemDecoration);
        recyclerViewShaiXuan00.setRefreshingColorResources(R.color.light_red);
        recyclerViewShaiXuan00.setAdapterWithProgress(adapterSort = new RecyclerArrayAdapter<CarGetsearchdata.DataBean.SortIdBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_sort;
                return new SortViewHolder(parent, layout);
            }
        });
        adapterSort.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < adapterSort.getAllData().size(); i++) {
                    adapterSort.getItem(i).setSelect(false);
                }
                adapterSort.getItem(position).setSelect(true);
                adapterSort.notifyDataSetChanged();
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                textSort.setText(adapterSort.getItem(position).getTitle());
                sort_id = adapterSort.getItem(position).getValue();
                onRefresh();
            }
        });
    }

    private void setListeners() {
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue.intValue() == 0 && maxValue.intValue() == 60) {
                    textRange.setText("不限");
                } else {
                    if (maxValue.intValue() == 60) {
                        textRange.setText(minValue + "万以上");
                    } else {
                        textRange.setText(minValue + "万至" + maxValue + "万");
                    }
                }
            }
        });
        rangeSeekbar1.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue.intValue() == 0 && maxValue.intValue() == 12) {
                    textRange1.setText("不限");
                } else {
                    if (maxValue.intValue() == 12) {
                        textRange1.setText(minValue + "年以上");
                    } else {
                        textRange1.setText(minValue + "年至" + maxValue + "年");
                    }
                }
            }
        });
        gridPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < zPriceBeanList.size(); j++) {
                    zPriceBeanList.get(j).setSelect(false);
                }
                zPriceBeanList.get(i).setSelect(true);
                priceAdapter.notifyDataSetChanged();
                z_price = zPriceBeanList.get(i).getValue();
                if (z_price.size() > 0) {
                    rangeSeekbar.setMinStartValue(z_price.get(0)).setMaxStartValue(z_price.get(1)).apply();
                } else {
                    rangeSeekbar.setMinStartValue(0).setMaxStartValue(60).apply();
                }
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                onRefresh();
            }
        });
        gridAge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < zAgeBeanList.size(); j++) {
                    zAgeBeanList.get(j).setSelect(false);
                }
                zAgeBeanList.get(i).setSelect(true);
                ageAdapter.notifyDataSetChanged();
                z_age = zAgeBeanList.get(i).getValue();
                if (z_age.size() > 0) {
                    rangeSeekbar1.setMinStartValue(z_age.get(0)).setMaxStartValue(z_age.get(1)).apply();
                } else {
                    rangeSeekbar1.setMinStartValue(0).setMaxStartValue(12).apply();
                }
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                onRefresh();
            }
        });
    }
    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        MaiChe maiChe;
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) != 0) {
            maiChe = new MaiChe(1, "android", String.valueOf(SPUtils.getInstance().getInt(Constant.SF.Uid)), System.currentTimeMillis() + "", page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        } else {
            maiChe = new MaiChe(1, "android", page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        }
        return GsonUtils.parseObject(maiChe);
    }

    String url = Constant.HOST + Constant.Url.Carold_Index;
    private int bid = 0;
    private int bsid = 0;
    private int sort_id = 0;
    private int hotcat_id = 0;
    private int city_id = 0;
    private List<Integer> z_price = new ArrayList<>();
    private List<Integer> z_age = new ArrayList<>();
    private String title = "";

    @OnClick({R.id.viewDismiss,R.id.btnPrice,R.id.viewSearch,R.id.viewShaiXuan01, R.id.btnAge, R.id.viewShaiXuan02, R.id.viewShaiXuan, R.id.imageZuJi, R.id.viewLocation, R.id.viewShaiXuan0000, R.id.viewShaiXuan0001, R.id.viewShaiXuan0002, R.id.viewAll})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btnPrice:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_price.clear();
                z_price.add(rangeSeekbar.getSelectedMinValue().intValue());
                z_price.add(rangeSeekbar.getSelectedMaxValue().intValue());
                for (int i = 0; i < zPriceBeanList.size(); i++) {
                    zPriceBeanList.get(i).setSelect(false);
                }
                priceAdapter.notifyDataSetChanged();
                onRefresh();
                break;
            case R.id.viewSearch:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                MyDialog.showSearchDialog(context, title);
                MyDialog.setOnSearchDoneListener(new MyDialog.OnSearchDoneListener() {
                    @Override
                    public void searchDone(String key) {
                        title = key;
                        onRefresh();
                    }
                });
                break;
            case R.id.viewShaiXuan01:
                break;
            case R.id.btnAge:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_age.clear();
                z_age.add(rangeSeekbar1.getSelectedMinValue().intValue());
                z_age.add(rangeSeekbar1.getSelectedMaxValue().intValue());
                for (int i = 0; i < zAgeBeanList.size(); i++) {
                    zAgeBeanList.get(i).setSelect(false);
                }
                ageAdapter.notifyDataSetChanged();
                onRefresh();
                break;
            case R.id.viewShaiXuan02:
                break;
            case R.id.viewShaiXuan:
                break;
            case R.id.imageZuJi:
                intent.setClass(context,ErShouCheGLActivity.class);
                startActivity(intent);
                break;
            case R.id.viewLocation:
                intent.setClass(context,XuanZheCSActivity.class);
                startActivity(intent);
                break;
            case R.id.viewShaiXuan0000:
                if (shaiXuanVisible != 0) {
                    viewShaiXuan.setVisibility(View.VISIBLE);
                    shaiXuanVisible = 0;
                    recyclerViewShaiXuan00.setVisibility(View.VISIBLE);
                    viewShaiXuan01.setVisibility(View.GONE);
                    viewShaiXuan02.setVisibility(View.GONE);
//                    for (int j = 0; j < viewShaiXuanArr.length; j++) {
//                        viewShaiXuanArr[j].setVisibility(View.GONE);
//                    }
//                    viewShaiXuanArr[finalI].setVisibility(View.VISIBLE);
                } else {
                    viewShaiXuan.setVisibility(View.GONE);
                    shaiXuanVisible = -1;
                }
                break;
            case R.id.viewShaiXuan0001:
                if (shaiXuanVisible != 1) {
                    viewShaiXuan.setVisibility(View.VISIBLE);
                    shaiXuanVisible = 1;
                    recyclerViewShaiXuan00.setVisibility(View.GONE);
                    viewShaiXuan01.setVisibility(View.VISIBLE);
                    viewShaiXuan02.setVisibility(View.GONE);
//                    for (int j = 0; j < viewShaiXuanArr.length; j++) {
//                        viewShaiXuanArr[j].setVisibility(View.GONE);
//                    }
//                    viewShaiXuanArr[finalI].setVisibility(View.VISIBLE);
                } else {
                    viewShaiXuan.setVisibility(View.GONE);
                    shaiXuanVisible = -1;
                }
                break;
            case R.id.viewShaiXuan0002:
                if (shaiXuanVisible != 2) {
                    viewShaiXuan.setVisibility(View.VISIBLE);
                    shaiXuanVisible = 2;
                    recyclerViewShaiXuan00.setVisibility(View.GONE);
                    viewShaiXuan01.setVisibility(View.GONE);
                    viewShaiXuan02.setVisibility(View.VISIBLE);
//                    for (int j = 0; j < viewShaiXuanArr.length; j++) {
//                        viewShaiXuanArr[j].setVisibility(View.GONE);
//                    }
//                    viewShaiXuanArr[finalI].setVisibility(View.VISIBLE);
                } else {
                    viewShaiXuan.setVisibility(View.GONE);
                    shaiXuanVisible = -1;
                }
                break;
            case R.id.viewAll:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                intent.setClass(context, XuanZheCLActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDismiss:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                break;
            default:
                break;
        }
    }

    class PriceAdapter extends BaseAdapter {
        List<CarGetsearchdata.AgePriceBean> list = new ArrayList<>();

        public PriceAdapter(List<CarGetsearchdata.AgePriceBean> list) {
            this.list = list;
        }

        public List<CarGetsearchdata.AgePriceBean> getList() {
            return list;
        }

        public void setList(List<CarGetsearchdata.AgePriceBean> list) {
            this.list = list;
        }

        class ViewHolder {
            public TextView textName;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_price_age, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(list.get(position).getTitle());
            if (list.get(position).isSelect()) {
                holder.textName.setTextColor(ContextCompat.getColor(context, R.color.light_red));
                holder.textName.setBackgroundResource(R.drawable.shape_gold1dp_priceage3dp);
            } else {
                holder.textName.setTextColor(ContextCompat.getColor(context, R.color.light_black));
                holder.textName.setBackgroundResource(R.drawable.shape_linegray1dp_3dp);
            }
            return convertView;
        }
    }

    @Override
    public void onRefresh() {
        textSearch.setText(title);
        page = 1;
        recyclerView.showProgress();
        HttpApi.postJson(context, url, getOkObject(), new HttpApi.NCallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtils.e("Buyer",s);
                try {
                    page++;
                    Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                    if (buyer.getStatus() == 1) {
                        List<Buyer.DataBean> dataBeanList = buyer.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else {
                        showError(buyer.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
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

    /**
     * 初始化筛选
     */
    private void initShaiXuan() {
        bid = 0;
        bsid = 0;
        sort_id = 0;
        z_price.clear();
        z_age.clear();
        title = "";
        textSort.setText("默认排序");
        textAll.setText("全部品牌");
        for (int i = 0; i < sortIdBeanList.size(); i++) {
            sortIdBeanList.get(i).setSelect(false);
        }
        if (sortIdBeanList.size() > 0) {
            sortIdBeanList.get(0).setSelect(true);
        }
        if (zPriceBeanList.size() > 0) {
            for (int i = 0; i < zPriceBeanList.size(); i++) {
                zPriceBeanList.get(i).setSelect(false);
            }
            zPriceBeanList.get(0).setSelect(true);
        }
        rangeSeekbar.setMinStartValue(0).setMaxStartValue(60).apply();
        if (zAgeBeanList.size() > 0) {
            for (int i = 0; i < zAgeBeanList.size(); i++) {
                zAgeBeanList.get(i).setSelect(false);
            }
            zAgeBeanList.get(0).setSelect(true);
        }
        rangeSeekbar1.setMinStartValue(0).setMaxStartValue(12).apply();
        priceAdapter.notifyDataSetChanged();
        ageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isSearch) {
            initShaiXuan();
            shaiXuanVisible = -1;
            viewShaiXuan.setVisibility(View.GONE);
            MyDialog.showSearchDialog(context, title);
            MyDialog.setOnSearchDoneListener(new MyDialog.OnSearchDoneListener() {
                @Override
                public void searchDone(String key) {
                    title = key;
                    onRefresh();
                }
            });
            isSearch = false;
        }
        if (isPinPaiXC) {
            initShaiXuan();
            viewShaiXuan.setVisibility(View.GONE);
            shaiXuanVisible = -1;
            Intent intent = new Intent();
            intent.setClass(context, XuanZheCLActivity.class);
            startActivity(intent);
            isPinPaiXC = false;
        }
        if (isJiaGEXC) {
            initShaiXuan();
            viewShaiXuan.setVisibility(View.VISIBLE);
            shaiXuanVisible = 1;
            recyclerViewShaiXuan00.setVisibility(View.GONE);
            viewShaiXuan01.setVisibility(View.VISIBLE);
            viewShaiXuan02.setVisibility(View.GONE);
            isJiaGEXC = false;
        }
    }

//    @Override
//    public boolean onBackPressed() {
//        if (shaiXuanVisible != -1) {
//            shaiXuanVisible = -1;
//            viewShaiXuan.setVisibility(View.GONE);
//            return true;
//        } else {
//            return super.onBackPressed();
//        }
//    }


    @Override
    public void onBackPressed() {
        if (shaiXuanVisible != -1) {
            shaiXuanVisible = -1;
            viewShaiXuan.setVisibility(View.GONE);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
