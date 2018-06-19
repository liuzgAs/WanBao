package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Money_Recomlog;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.TuiJianJLViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TuiJianJLActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textSumNum)
    TextView textSumNum;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private String type;
    List<String> list = new ArrayList<>();
    private int currentItem;
    private RecyclerArrayAdapter<Money_Recomlog.DataBean> adapter;
    private int page = 1;
    private Money_Recomlog mRecomlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian_jl);
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
        titleText.setText("推荐记录");
        getRecomlog();
        initRecycler();
    }

    @Override
    protected void initData() {
        onRefresh();
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mRecomlog!=null){
                    type=String.valueOf(mRecomlog.getType().get(tab.getPosition()).getId());
                    onRefresh();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Money_Recomlog.DataBean>(TuiJianJLActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_tjjl;
                return new TuiJianJLViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
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
                            Money_Recomlog usercar_index = GsonUtils.parseJSON(s, Money_Recomlog.class);
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
                Intent intent=new Intent();
                intent.putExtra("id",adapter.getItem(position).getId());
                intent.setClass(context,TuiJianMXActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    private void getAmount() {
        page = 1;
        HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
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
                    LogUtils.e("Withdraw_Balance", s);
                    page++;
                    Money_Recomlog usercar_index = GsonUtils.parseJSON(s, Money_Recomlog.class);
                    int status = usercar_index.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(usercar_index.getData());
                    } else {
                        ToastUtils.showShort(usercar_index.getInfo());
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
    @Override
    public void onRefresh() {
        getAmount();
    }
    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
    private void getRecomlog() {
        HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
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
                try {
                    LogUtils.e("Money_Recomlog", s);
                     mRecomlog = GsonUtils.parseJSON(s, Money_Recomlog.class);
                    if (mRecomlog.getStatus() == 1) {
                        textSumNum.setText(mRecomlog.getSum_num()+"");
                        if (mRecomlog.getType().size()>0){
                            for (int i=0;i<mRecomlog.getType().size();i++){
                                list.add(mRecomlog.getType().get(i).getName());
                            }
                        }
                        tablayout.removeAllTabs();
                        for (int i = 0; i < list.size(); i++) {
                            View view = LayoutInflater.from(context).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = view.findViewById(R.id.textTitle);
                            textTitle.setText(list.get(i));
                            if (i == currentItem) {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
                            } else {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
                            }
                        }
                    } else {
                        ToastUtils.showShort(mRecomlog.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(TuiJianJLActivity.this,"数据异常");
                    dismissDialog();
                }
            }

            @Override
            public void onError() {
                MyDialog.dialogFinish(TuiJianJLActivity.this,"网络异常");
                dismissDialog();
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectOrder() {
        String url = Constant.HOST + Constant.Url.Money_Recomlog;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("type", type);
        params.put("p", page+"");
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
