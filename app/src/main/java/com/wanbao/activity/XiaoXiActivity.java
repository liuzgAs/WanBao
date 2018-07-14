package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Massage_Msg;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.XiaoXiViewHolder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XiaoXiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Massage_Msg.DataBean> adapter;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi);
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
        titleText.setText("消息");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Massage_Msg.DataBean>(XiaoXiActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xiaoxi;
                return new XiaoXiViewHolder(parent, layout);
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
                            Massage_Msg usercar_index = GsonUtils.parseJSON(s, Massage_Msg.class);
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
                if (!TextUtils.isEmpty(adapter.getItem(position).getCode())){
                    goMessage(adapter.getItem(position));
                }
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getCar();
    }

    private void getCar() {
        page = 1;
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
                    LogUtils.e("Massage_Msg", s);
                    page++;
                    Massage_Msg usercar_index = GsonUtils.parseJSON(s, Massage_Msg.class);
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

    private OkObject getOkObjectCar() {
        String url = Constant.HOST + Constant.Url.Massage_Msg;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("p", String.valueOf(page));
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
    private void goMessage(Massage_Msg.DataBean myMessage){
        Intent intent;
        switch (myMessage.getCode()) {
            case "web":
                intent=new Intent();
                intent.putExtra("title","消息");
                intent.putExtra("mUrl",myMessage.getUrl());
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
                break;
            case "app_i":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_my":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_find":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_money":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_sos":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WeiBaoDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, WBDingDanXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_to":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, ShiJiaDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_to_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, ShiJiaDDXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_user_msg":
                break;
            case "app_user_account":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WoDeJKActivity.class);
                context.startActivity(intent);
                break;
            case "app_team_order_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, MianFeiYCActivity.class);
                context.startActivity(intent);
                break;
            case "app_like_car":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, AiCheDangAnActivity.class);
                context.startActivity(intent);
                break;
            case "app_comment":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiPPActivity.class);
                context.startActivity(intent);
                break;
            case "app_money_recom_log":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, TuiJianJLActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
