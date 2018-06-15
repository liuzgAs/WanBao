package com.wanbao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Withdraw_AddBefore;

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
    }

    @Override
    protected void initData() {
        AddBefore();
    }

    @OnClick({R.id.imageback, R.id.viewAddCard, R.id.viewChangeCard, R.id.textAll, R.id.sBtnTiXian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewAddCard:
                break;
            case R.id.viewChangeCard:
                break;
            case R.id.textAll:
                break;
            case R.id.sBtnTiXian:
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
                    Withdraw_AddBefore wAddBefore = GsonUtils.parseJSON(s, Withdraw_AddBefore.class);
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
                            textBankId.setText(wAddBefore.getBank().getBankCard());
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
}
