package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Bank_CardList;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Withdraw_AddBefore;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TiXianActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewAddCard)
    RelativeLayout viewAddCard;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.textBankId)
    TextView textBankId;
    @BindView(R.id.viewChangeCard)
    RelativeLayout viewChangeCard;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.textJinEr)
    EditText textJinEr;
    @BindView(R.id.textKtx)
    TextView textKtx;
    @BindView(R.id.textAll)
    TextView textAll;
    @BindView(R.id.sBtnTiXian)
    StateButton sBtnTiXian;
    @BindView(R.id.imageBank)
    ImageView imageBank;
    @BindView(R.id.textDes)
    TextView textDes;
    private Withdraw_AddBefore wAddBefore;
    private String bankId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian);
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
        titleText.setText("提现");
        viewSwitcher.setDisplayedChild(0);
        textJinEr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)){
                    sBtnTiXian.setEnabled(true);
                }else {
                    sBtnTiXian.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ChangeBank.equals(event.getAction())){
            Bank_CardList.DataBean dataBean=(Bank_CardList.DataBean) event.getData();
            if (dataBean!=null){
                viewSwitcher.setDisplayedChild(1);
                GlideApp.with(context)
                        .asBitmap()
                        .load(dataBean.getImg())
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageBank);
                textBankName.setText(dataBean.getBank());
                textBankId.setText("尾号"+dataBean.getBankCard());
                bankId=String.valueOf(dataBean.getId());
            }else {
                initData();
            }
        }
    }

    @Override
    protected void initData() {
        AddBefore();
    }

    @OnClick({R.id.imageback, R.id.viewAddCard, R.id.viewChangeCard, R.id.textAll, R.id.sBtnTiXian})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewAddCard:
                intent=new Intent();
                intent.setClass(context,WoDeYHKActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChangeCard:
                intent=new Intent();
                intent.setClass(context,WoDeYHKActivity.class);
                startActivity(intent);
                break;
            case R.id.textAll:
                if (wAddBefore!=null){
                    textJinEr.setText(wAddBefore.getMoney()+"");
                }
                break;
            case R.id.sBtnTiXian:
                if (bankId==null){
                    ToastUtils.showShort("请选择银行卡");
                    return;
                }
                if (TextUtils.isEmpty(textJinEr.getText().toString())){
                    ToastUtils.showShort("提现金额不能为空");
                    return;
                }
                if (Double.valueOf(textJinEr.getText().toString())>wAddBefore.getMoney()){
                    ToastUtils.showShort("余额不足");
                    return;
                }
                AddDone();
                break;
            default:
                break;
        }
    }

    private void AddBefore() {
        HttpApi.post(context, getOkObjectAddBefore(), new HttpApi.CallBack() {
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
                Log.e("Withdraw_AddBefore", s);
                try {
                     wAddBefore = GsonUtils.parseJSON(s, Withdraw_AddBefore.class);
                    if (wAddBefore.getStatus() == 1) {
                        textKtx.setText(wAddBefore.getMoneyDes());
                        textDes.setText(wAddBefore.getDes());
                        if (wAddBefore.getBankShow()==1){
                            viewSwitcher.setDisplayedChild(1);
                            GlideApp.with(context)
                                    .asBitmap()
                                    .load(wAddBefore.getBank().getImg())
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(imageBank);
                            textBankName.setText(wAddBefore.getBank().getBank());
                            textBankId.setText("尾号"+wAddBefore.getBank().getBankCard());
                            bankId=String.valueOf(wAddBefore.getBank().getId());
                        }else {
                            viewSwitcher.setDisplayedChild(0);
                        }
                    } else {
                        MyDialog.dialogFinish(TiXianActivity.this, wAddBefore.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(TiXianActivity.this, "数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(TiXianActivity.this, "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectAddBefore() {
        String url = Constant.HOST + Constant.Url.Withdraw_AddBefore;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void AddDone() {
        HttpApi.post(context, getOkObjectAddDone(), new HttpApi.CallBack() {
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
                Log.e("AddDone", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        ToastUtils.showShort("提现申请成功！");
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.TiXian,null));
                        finish();
                    } else {
                        MyDialog.dialogFinish(TiXianActivity.this, comment.getInfo());
                    }
                } catch (Exception e) {
                   ToastUtils.showShort("数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort( "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectAddDone() {
        String url = Constant.HOST + Constant.Url.Withdraw_AddDone;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("money",textJinEr.getText().toString());
        params.put("bank",bankId);
        params.put("pay_id","0");
        return new OkObject(params, url);
    }
}
