package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DeviceUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Login_Index;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.imageBack)
    ImageView imageBack;
    @BindView(R.id.editAccount)
    EditText editAccount;
    @BindView(R.id.editPwd)
    EditText editPwd;
    @BindView(R.id.viewDengLu)
    LinearLayout viewDengLu;
    @BindView(R.id.btnWJMM)
    Button btnWJMM;
    @BindView(R.id.btnLJZC)
    Button btnLJZC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DeviceUtils.setFullScreenTran(this);
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

    }

    @Override
    protected void initData() {

    }
    private void dengLu(){
        HttpApi.post(context, getOkObjectDL(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("登录中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("denglu",s);
                try {
                    Login_Index login_index = GsonUtils.parseJSON(s, Login_Index.class);
                    if (login_index.getStatus()==1){
                        loginSud(login_index);
                    }else {
                        ToastUtils.showShort(login_index.getInfo());
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
    private OkObject getOkObjectDL() {
        String url = Constant.HOST+Constant.Url.Login_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName",editAccount.getText().toString());
        params.put("userPwd", EncryptUtils.encryptMD5ToString(EncryptUtils.encryptMD5ToString(editPwd.getText().toString())));
        return new OkObject(params, url);
    }

    private void loginSud(Login_Index login_index){
        SPUtils.getInstance().put(Constant.SF.Uid,login_index.getUid());
        SPUtils.getInstance().put(Constant.SF.UserName,login_index.getUserName());
        SPUtils.getInstance().put(Constant.SF.Nickname,login_index.getNickname());
        SPUtils.getInstance().put(Constant.SF.HeadImg,login_index.getHeadImg());
        ToastUtils.showShort("登录成功！");
        finish();
    }

    @OnClick({R.id.imageBack, R.id.viewDengLu, R.id.btnWJMM, R.id.btnLJZC})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            case R.id.viewDengLu:
                if (TextUtils.isEmpty(editAccount.getText())){
                    ToastUtils.showShort("请输入账号！");
                    return;
                }
                if (TextUtils.isEmpty(editPwd.getText())){
                    ToastUtils.showShort("请输入密码！");
                    return;
                }
                dengLu();
                break;
            case R.id.btnWJMM:
                intent=new Intent();
                intent.setClass(context,WangJiMiMaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLJZC:
                intent=new Intent();
                intent.setClass(context,ZhuCeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
