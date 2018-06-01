package com.wanbao.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Index_Store;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.DpViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class YuYueDpActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Index_Store.DataBean> adapter;
    private int page = 1;
    private String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yue_dp);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        cid=getIntent().getStringExtra("cid");
    }

    @Override
    protected void initViews() {
        titleText.setText("预约4S店铺");
        initRecycler();
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Index_Store.DataBean>(YuYueDpActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_dp;
                return new DpViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.post(context, getOkObjectStore(), new HttpApi.CallBack() {
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
                            Index_Store index_store = GsonUtils.parseJSON(s, Index_Store.class);
                            int status = index_store.getStatus();
                            if (status == 1) {
                                adapter.addAll(index_store.getData());
                            } else {
                                ToastUtils.showShort(index_store.getInfo());
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
                EventBus.getDefault().post(new BaseEvent(BaseEvent.Choose_Dp,adapter.getItem(position)));
                finish();
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        getStore();

    }

    private void getStore() {
        page = 1;
        HttpApi.post(context, getOkObjectStore(), new HttpApi.CallBack() {
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
                    LogUtils.e("爱车",s);
                    page++;
                    Index_Store index_store = GsonUtils.parseJSON(s, Index_Store.class);
                    int status = index_store.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(index_store.getData());
                    } else {
                        ToastUtils.showShort(index_store.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据异常！");
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
    private OkObject getOkObjectStore() {
        String url = Constant.HOST + Constant.Url.Index_Store;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("p", String.valueOf(page));
        params.put("cid", cid);
        return new OkObject(params, url);
    }
}
