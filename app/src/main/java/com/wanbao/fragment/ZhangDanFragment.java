package com.wanbao.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.DateTransforam;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Account_Accountlog;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.ZhangDanMXViewHolder;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ZhangDanFragment extends PSFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.textKssj)
    TextView textKssj;
    @BindView(R.id.viewDateBegin)
    LinearLayout viewDateBegin;
    @BindView(R.id.textJssj)
    TextView textJssj;
    @BindView(R.id.viewDateEnd)
    LinearLayout viewDateEnd;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    private View view;
    private String state;
    private RecyclerArrayAdapter<Account_Accountlog.DataBean> adapter;
    private int page = 1;
    private String date_begin;
    private String date_end;

    public static final ZhangDanFragment newInstance(String states) {
        ZhangDanFragment fragment = new ZhangDanFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", states);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getString("state");
    }

    @Override
    public void fetchData() {
        onRefresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_zhang_dan, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        final DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Account_Accountlog.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_txmx;
                return new ZhangDanMXViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.post(context, getOkObjectAccountlog(), new HttpApi.CallBack() {
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
                            Account_Accountlog usercar_index = GsonUtils.parseJSON(s, Account_Accountlog.class);
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
            }
        });
        recyclerView.setRefreshListener(this);
    }

    private void getAccountlog() {
        page = 1;
        HttpApi.post(context, getOkObjectAccountlog(), new HttpApi.CallBack() {
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
                    LogUtils.e("Account_Accountlog", s);
                    page++;
                    Account_Accountlog usercar_index = GsonUtils.parseJSON(s, Account_Accountlog.class);
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

    private OkObject getOkObjectAccountlog() {
        String url = Constant.HOST + Constant.Url.Account_Accountlog;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("p", String.valueOf(page));
        params.put("date_begin", textKssj.getText().toString());
        params.put("date_end", textJssj.getText().toString());
        params.put("type", state+"");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        getAccountlog();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose();
    }

    @OnClick({R.id.viewDateBegin, R.id.viewDateEnd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewDateBegin:
                Calendar c0 = Calendar.getInstance();
                DatePickerDialog datePickerDialog0 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textKssj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        try {
                            date_begin = DateTransforam.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        onRefresh();
                    }
                }, c0.get(Calendar.YEAR), c0.get(Calendar.MONTH), c0.get(Calendar.DAY_OF_MONTH));
                if (!TextUtils.isEmpty(date_end)) {
                    datePickerDialog0.getDatePicker().setMaxDate(Long.parseLong(date_end)*1000);
                } else {
                    datePickerDialog0.getDatePicker().setMaxDate(System.currentTimeMillis());
                }
                datePickerDialog0.show();
                break;
            case R.id.viewDateEnd:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        try {
                            date_end = DateTransforam.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        textJssj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        onRefresh();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                if (!TextUtils.isEmpty(date_begin)) {
                    datePickerDialog.getDatePicker().setMinDate(Long.parseLong(date_begin)*1000);
                }
                datePickerDialog.show();
                break;
            default:
                break;
        }
    }
}
