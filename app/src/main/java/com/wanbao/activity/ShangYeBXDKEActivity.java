package com.wanbao.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Calculator_Loan;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.ShangYeBXDKHolder;
import com.wanbao.viewholder.ShangYeBXRightDKHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShangYeBXDKEActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textDrawerTitle)
    TextView textDrawerTitle;
    @BindView(R.id.textDrawerRight)
    TextView textDrawerRight;
    @BindView(R.id.recyclerViewRight)
    EasyRecyclerView recyclerViewRight;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private RecyclerArrayAdapter<Calculator_Loan.Des2Bean.SBeanXX> adapter;
    private RecyclerArrayAdapter<Calculator_Loan.Des2Bean.SBeanXX.SBeanX> adapterR;
    private String down_payment;
    private String year;
    private String cid;
    private String money;
    private String third_part;
    private String iscn;
    private String body_scratches;
    private String insurance;
    private Calculator_Loan cIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_ye_bx);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        cid = getIntent().getStringExtra("cid");
        money = getIntent().getStringExtra("money");
        third_part = getIntent().getStringExtra("third_part");
        iscn = getIntent().getStringExtra("iscn");
        body_scratches = getIntent().getStringExtra("body_scratches");

    }

    @Override
    protected void initViews() {
        titleText.setText("商业保险");
        initRecycler();
        initRecyclerRight();
    }

    @Override
    protected void initData() {
        getCalculator();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Calculator_Loan.Des2Bean.SBeanXX>(ShangYeBXDKEActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shangyebx;
                return new ShangYeBXDKHolder(parent, layout);
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
                if (adapter.getItem(position).getS().size() > 0) {
                    textDrawerTitle.setText(adapter.getItem(position).getSt());
                    adapterR.clear();
                    adapterR.addAll(adapter.getItem(position).getS());
                    drawerLayout.openDrawer(GravityCompat.END);
                } else {
                    if (adapter.getItem(position).getIsc() == 1) {
                        Iterator iter = AppContext.getIntance().xcqk.iterator();
                        while (iter.hasNext()) {
                            if (iter.next().equals(adapter.getItem(position).getId())) {
                                iter.remove();
                            }
                        }
                    }else {
                        if (!AppContext.getIntance().xcqk.contains(adapter.getItem(position).getId())){
                            AppContext.getIntance().xcqk.add(adapter.getItem(position).getId());
                        }
                    }
                    initData();
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeXinQuan,null));
                }
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerRight() {
        recyclerViewRight.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewRight.addItemDecoration(itemDecoration);
        recyclerViewRight.setRefreshingColorResources(R.color.basic_color);
        recyclerViewRight.setAdapterWithProgress(adapterR = new RecyclerArrayAdapter<Calculator_Loan.Des2Bean.SBeanXX.SBeanX>(ShangYeBXDKEActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shangyebx_right;
                return new ShangYeBXRightDKHolder(parent, layout);
            }
        });
        adapterR.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapterR.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapterR.resumeMore();
            }
        });
        adapterR.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                drawerLayout.closeDrawers();
                if ("third_part".equals(adapterR.getItem(position).getK())){
                    third_part=String.valueOf(adapterR.getItem(position).getV());
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Third_Part,third_part));
                }else if ("iscn".equals(adapterR.getItem(position).getK())){
                    iscn=String.valueOf(adapterR.getItem(position).getV());
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Iscn,iscn));
                }else if ("body_scratches".equals(adapterR.getItem(position).getK())){
                    body_scratches=String.valueOf(adapterR.getItem(position).getV());
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Body_Scratches,body_scratches));
                }
                initData();
            }
        });
    }

    private void getCalculator() {
        HttpApi.post(context, getOkObjectCalculator(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("Calculator_Index", s);
                try {
                    cIndex = GsonUtils.parseJSON(s, Calculator_Loan.class);
                    if (cIndex.getStatus() == 1) {
                        adapter.clear();
                        adapter.addAll(cIndex.getDes2().getS());
                    } else {
                        ToastUtils.showShort(cIndex.getInfo());
                    }

                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectCalculator() {
        String url = Constant.HOST + Constant.Url.Calculator_Loan2;
        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);
        params.put("money",money);
        params.put("third_part",third_part);
        params.put("iscn",iscn);
        params.put("body_scratches",body_scratches);
        params.put("insurance", AppContext.getIntance().xcqk.toString().replace("[","").replace("]",""));
        params.put("down_payment",down_payment);
        params.put("year",year);
        return new OkObject(params, url);
    }

    @OnClick({R.id.imageback, R.id.textDrawerRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.textDrawerRight:
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
