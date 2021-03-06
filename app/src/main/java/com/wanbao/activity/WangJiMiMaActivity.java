package com.wanbao.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.MD5Util;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.Login_register;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WangJiMiMaActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editAccount)
    EditText editAccount;
    @BindView(R.id.editCode)
    EditText editCode;
    @BindView(R.id.btnYzm)
    Button btnYzm;
    @BindView(R.id.editPwd)
    EditText editPwd;
    @BindView(R.id.editPwdAg)
    EditText editPwdAg;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_wang_ji_mi_ma);
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
        titleText.setText("重置密码");

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.btnYzm, R.id.sbtn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btnYzm:
                if (TextUtils.isEmpty(editAccount.getText())){
                    ToastUtils.showShort("手机号不能为空");
                    return;
                }
                yanZM();
                break;
            case R.id.sbtn_tijiao:
                if (TextUtils.isEmpty(editAccount.getText())){
                    ToastUtils.showShort("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(editCode.getText())){
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(editPwd.getText())||TextUtils.isEmpty(editPwdAg.getText())){
                    ToastUtils.showShort("密码不能为空");
                    return;
                }else {
                    if (!editPwd.getText().toString().equals(editPwdAg.getText().toString())){
                        ToastUtils.showShort("密码不一致");
                        return;
                    }
                }
                if (!RegexUtils.isMatch(Constant.reg,editPwd.getText().toString())){
                    ToastUtils.showShort("密码至少六位字母和数字组合");
                    return;
                }
                XiuGai();
                break;
            default:
                break;
        }
    }

    private void yanZM() {
        HttpApi.post(context, getOkObjectYZM(), new HttpApi.CallBack() {
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

    private OkObject getOkObjectYZM() {
        String url = Constant.HOST + Constant.Url.Login_ForgetSms;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", editAccount.getText().toString());
        return new OkObject(params, url);
    }


    private void XiuGai() {
        HttpApi.post(context, getOkObjectXG(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("提交中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("zuce", s);
                try {
                    Login_register login_register = GsonUtils.parseJSON(s, Login_register.class);
                    if (login_register.getStatus() == 1) {
                        ToastUtils.showShort("修改成功！");
                        finish();
                    } else {
                        ToastUtils.showShort(login_register.getInfo());
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

    private OkObject getOkObjectXG() {
        String url = Constant.HOST + Constant.Url.Login_Forget;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", editAccount.getText().toString());
        params.put("userPwd", MD5Util.getMD5(MD5Util.getMD5(editPwd.getText().toString().trim())+ "ad"));
        params.put("code", editCode.getText().toString());
//        params.put("type", "0");
        return new OkObject(params, url);
    }
    /**
     * 倒计时60秒，一次1秒
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            btnYzm.setEnabled(false);
            btnYzm.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            btnYzm.setEnabled(true);
            btnYzm.setText("重新发送");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
        if (timer != null) {
            timer.cancel();
        }
    }
}
