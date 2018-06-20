package com.wanbao.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Bank_CardAddbefore;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.OkObject;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XinZengYHKActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editCard)
    EditText editCard;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.viewBank)
    LinearLayout viewBank;
    @BindView(R.id.editBankCard)
    EditText editBankCard;
    @BindView(R.id.sBtnTiJiao)
    StateButton sBtnTiJiao;
    @BindView(R.id.editYzm)
    EditText editYzm;
    @BindView(R.id.textFs)
    TextView textFs;
    private ArrayList<String> maintainString = new ArrayList<>();
    private Bank_CardAddbefore addbefore;
    private String bankId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_zeng_yhk);
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
        titleText.setText("新增银行卡");
    }

    @Override
    protected void initData() {
        CardAddbefore();
    }

    @OnClick({R.id.textFs,R.id.imageback, R.id.viewBank, R.id.sBtnTiJiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textFs:
                if (!RegexUtils.isMobileSimple(editPhone.getText())){
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                yanZM(editPhone.getText().toString());
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewBank:
                maintainString.clear();
                for (int i = 0; i < addbefore.getData().size(); i++) {
                    maintainString.add(addbefore.getData().get(i).getName());
                }
                new MaterialDialog.Builder(context)
                        .title("选择银行卡")
                        .items(maintainString)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textBankName.setText(addbefore.getData().get(position).getName());
                                bankId = String.valueOf(addbefore.getData().get(position).getId());
                            }
                        })
                        .show();
                break;
            case R.id.sBtnTiJiao:
                if (TextUtils.isEmpty(editName.getText().toString())) {
                    ToastUtils.showShort("请设置你的姓名");
                    return;
                }
                if (TextUtils.isEmpty(editCard.getText().toString())) {
                    ToastUtils.showShort("请设置你的身份证号");
                    return;
                }
                if (!RegexUtils.isMobileSimple(editPhone.getText())){
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(editYzm.getText().toString())) {
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(bankId)) {
                    ToastUtils.showShort("请选择银行信息");
                    return;
                }
                if (TextUtils.isEmpty(editBankCard.getText().toString())) {
                    ToastUtils.showShort("请设置你的银行卡号");
                    return;
                }
                CardAdd();
                break;
            default:
                break;
        }
    }

    private void CardAddbefore() {
        HttpApi.post(context, getOkObjectCardAddbefore(), new HttpApi.CallBack() {
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
                Log.e("Bank_CardAddbefore", s);
                try {
                    addbefore = GsonUtils.parseJSON(s, Bank_CardAddbefore.class);
                    if (addbefore.getStatus() == 1) {
                    } else {
                        MyDialog.dialogFinish(XinZengYHKActivity.this, addbefore.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(XinZengYHKActivity.this, "数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(XinZengYHKActivity.this, "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectCardAddbefore() {
        String url = Constant.HOST + Constant.Url.Bank_CardAddbefore;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void CardAdd() {
        HttpApi.post(context, getOkObjectCardAdd(), new HttpApi.CallBack() {
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
                Log.e("CardAdd", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        ToastUtils.showShort("添加成功！");
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.AddBank, null));
                        finish();
                    } else {
                        MyDialog.dialogFinish(XinZengYHKActivity.this, comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");
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

    private OkObject getOkObjectCardAdd() {
        String url = Constant.HOST + Constant.Url.Bank_CardAdd;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("bank", bankId);
        params.put("card", editCard.getText().toString());
        params.put("code", editYzm.getText().toString());
        params.put("bankCard", editBankCard.getText().toString());
        params.put("name", editName.getText().toString());
        params.put("phone", editPhone.getText().toString());
        return new OkObject(params, url);
    }
    private void yanZM(String phone) {
        HttpApi.post(context, getOkObjectYZM(phone), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("发送中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("fasong", s);
                try {
                    Login_RegSms login_regSms = GsonUtils.parseJSON(s, Login_RegSms.class);
                    if (login_regSms.getStatus() == 1) {
                        timer.start();
                        ToastUtils.showShort("发送成功！");
                    } else {
                        ToastUtils.showShort(login_regSms.getInfo());
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

    private OkObject getOkObjectYZM(String phone) {
        String url = Constant.HOST + Constant.Url.Login_BindCarSms;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", phone);
        return new OkObject(params, url);
    }
    /**
     * 倒计时60秒，一次1秒
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            textFs.setEnabled(false);
            textFs.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            textFs.setEnabled(true);
            textFs.setText("重新发送");
        }
    };
}
