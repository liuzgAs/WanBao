package com.wanbao.activity;

import android.content.Intent;
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
import com.wanbao.base.view.TwoBtnDialog;
import com.wanbao.modle.Bank_CardList;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.viewholder.BankViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WoDeYHKActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textWenTi)
    TextView textWenTi;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    private RecyclerArrayAdapter<Bank_CardList.DataBean> adapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_yhk);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        type=getIntent().getIntExtra("type",0);
    }

    @Override
    protected void initViews() {
        titleText.setText("我的银行卡");
        imageRight.setVisibility(View.VISIBLE);
        initRecycler();

    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.AddBank.equals(event.getAction())){
            initData();
        }
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
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Bank_CardList.DataBean>(WoDeYHKActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_bank;
                return new BankViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (type==0){
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeBank, adapter.getItem(position)));
                    finish();
                }
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(context, "您确定要删除该银行卡？", "是", "否");
                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        twoBtnDialog.dismiss();
                        Bank_CardDel(String.valueOf(adapter.getItem(position).getId()));
                    }

                    @Override
                    public void doCancel() {
                        twoBtnDialog.dismiss();
                    }
                });
                twoBtnDialog.show();
                return false;
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @OnClick({R.id.imageRight,R.id.imageback, R.id.textWenTi})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.imageRight:
                intent=new Intent();
                intent.setClass(context,XinZengYHKActivity.class);
                startActivity(intent);
                break;
            case R.id.textWenTi:
                break;
            default:
                break;
        }
    }
    private void getCardList() {
        HttpApi.post(context, getOkObjectCardList(), new HttpApi.CallBack() {
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
                    LogUtils.e("Bank_CardList", s);
                    Bank_CardList bank_cardList = GsonUtils.parseJSON(s, Bank_CardList.class);
                    int status = bank_cardList.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(bank_cardList.getData());
                    } else {
                        ToastUtils.showShort(bank_cardList.getInfo());
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

    private OkObject getOkObjectCardList() {
        String url = Constant.HOST + Constant.Url.Bank_CardList;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void Bank_CardDel(String id) {
        HttpApi.post(context, getOkObjectBank_CardDel(id), new HttpApi.CallBack() {
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
                    LogUtils.e("Bank_CardDel", s);
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        onRefresh();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeBank,null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
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

    private OkObject getOkObjectBank_CardDel(String id) {
        String url = Constant.HOST + Constant.Url.Bank_CardDel;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        getCardList();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

}
