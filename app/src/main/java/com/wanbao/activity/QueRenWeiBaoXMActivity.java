package com.wanbao.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.wanbao.modle.Comment;
import com.wanbao.modle.Maintain_Confirm;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.QrwbViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class QueRenWeiBaoXMActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.textDesn)
    TextView textDesn;
    @BindView(R.id.textDesv)
    TextView textDesv;
    @BindView(R.id.textJg)
    TextView textJg;
    private String id;
    private RecyclerArrayAdapter<Maintain_Confirm.DesBean> adapter;
    private Maintain_Confirm maintain_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_wei_bao_xm);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id = (String) getIntent().getStringExtra("id");
    }

    @Override
    protected void initViews() {
        titleText.setText("确认维保订单");
        initRecycler();

    }

    @Override
    protected void initData() {
        getTc();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.background), (int) getResources().getDimension(R.dimen.dp_1), (int) getResources().getDimension(R.dimen.dp_10), (int) getResources().getDimension(R.dimen.dp_10));
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Maintain_Confirm.DesBean>(QueRenWeiBaoXMActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_qrwb;
                return new QrwbViewHolder(parent, layout);
            }
        });
    }

    @OnClick({R.id.imageback, R.id.btnSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btnSure:
                setState(BaseEvent.Is_Confirm,id);
                break;
            default:
                break;
        }
    }

    private void getTc() {
        HttpApi.post(context, getOkObjectTc(), new HttpApi.CallBack() {
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
                    LogUtils.e("保养套餐", s);
                    maintain_confirm = GsonUtils.parseJSON(s, Maintain_Confirm.class);
                    if (maintain_confirm.getStatus() == 1) {
                        textDesn.setText(maintain_confirm.getDesn());
                        textDesv.setText(maintain_confirm.getDesv());
                        textPrice.setText(maintain_confirm.getSum());
                        textJg.setText(maintain_confirm.getSumDes());
                        adapter.clear();
                        adapter.addAll(maintain_confirm.getDes());
                    } else {
                        ToastUtils.showShort(maintain_confirm.getInfo());
                    }
                } catch (Exception e) {
                    dismissDialog();
                    ToastUtils.showShort("数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }
    private OkObject getOkObjectTc() {
        String url = Constant.HOST + Constant.Url.Maintain_Confirm;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id",id);
        return new OkObject(params, url);
    }

    private void setState(final String even, String id) {
        HttpApi.post(context, getOkObjectState(even, id), new HttpApi.CallBack() {
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
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeWbOrder, null));
                        finish();
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectState(String even, String id) {
        String url = "";
        if (even.equals(BaseEvent.Cancle_order)) {
            url = Constant.HOST + Constant.Url.User_CancelOrder;
        } else if (even.equals(BaseEvent.Del_Order)) {
            url = Constant.HOST + Constant.Url.User_DelOrder;
        } else if (even.equals(BaseEvent.Is_Confirm)) {
            url = Constant.HOST + Constant.Url.User_ConfirmOrder;
        }else if (even.equals(BaseEvent.IsRefund)) {
            url = Constant.HOST + Constant.Url.User_Refund_order;
        }else if (even.equals(BaseEvent.IsAuth)) {
            url = Constant.HOST + Constant.Url.User_ConfirmAuth;
        }else if (even.equals(BaseEvent.IsAccepting)) {
            url = Constant.HOST + Constant.Url.User_ConfirmAccepting;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

}
