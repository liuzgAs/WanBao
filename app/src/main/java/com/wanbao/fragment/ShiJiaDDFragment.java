package com.wanbao.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.activity.ShiJiaDDXQActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Testdrive_TestOrder;
import com.wanbao.viewholder.ShiJiaDDViewHolder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShiJiaDDFragment extends PSFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    private View view;
    private RecyclerArrayAdapter<Testdrive_TestOrder.DataBean> adapter;
    private String state;
    int page = 1;

    public static final ShiJiaDDFragment newInstance(String states) {
        ShiJiaDDFragment fragment = new ShiJiaDDFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", states);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_wei_bao_dd, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        state = getArguments().getString("state");
        initRecycler();
        return view;
    }

    @Override
    public void fetchData() {
        onRefresh();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Change_SjOrder.equals(event.getAction())) {
            getOrder();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red, R.color.deep_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Testdrive_TestOrder.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_sjdd;
                return new ShiJiaDDViewHolder(parent, layout, ShiJiaDDFragment.this);
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
                        LogUtils.e("Testdrive_TestOrder", s);
                        try {
                            page++;
                            Testdrive_TestOrder usercar_index = GsonUtils.parseJSON(s, Testdrive_TestOrder.class);
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
                Intent intent = new Intent();
                intent.putExtra("id", String.valueOf(adapter.getItem(position).getId()));
                intent.setClass(context, ShiJiaDDXQActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getOrder();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
    }

    private void getOrder() {
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
                LogUtils.e("Testdrive_TestOrder", s);
                try {
                    page++;
                    Testdrive_TestOrder usercar_index = GsonUtils.parseJSON(s, Testdrive_TestOrder.class);
                    int status = usercar_index.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(usercar_index.getData());
                        adapter.notifyDataSetChanged();
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

    private OkObject getOkObjectOrder() {
        String url = Constant.HOST + Constant.Url.User_Test_drive_order;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid,0) + "");
        params.put("p", String.valueOf(page));
        params.put("state", state);
        return new OkObject(params, url);
    }

}
