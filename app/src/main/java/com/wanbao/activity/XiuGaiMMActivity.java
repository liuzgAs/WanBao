package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.MD5Util;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XiuGaiMMActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editOldPw)
    EditText editOldPw;
    @BindView(R.id.editNewPw)
    EditText editNewPw;
    @BindView(R.id.editNewPwa)
    EditText editNewPwa;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_mm);
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
        titleText.setText("修改密码");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.sbtn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.sbtn_tijiao:
                if (TextUtils.isEmpty(editOldPw.getText().toString())){
                    ToastUtils.showShort("旧密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(editNewPw.getText().toString())){
                    ToastUtils.showShort("新密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(editNewPwa.getText().toString())){
                    ToastUtils.showShort("确认新密码不能为空");
                    return;
                }
                if (!editNewPwa.getText().toString().equals(editNewPw.getText().toString())){
                    ToastUtils.showShort("新密码不一致");
                    return;
                }
                PwdSave();
                break;
            default:
                break;
        }
    }

    private void PwdSave() {
        HttpApi.post(context, getOkObjectPwdSave(), new HttpApi.CallBack() {
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
                Log.e("PwdSave", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        ToastUtils.showShort("修改成功！");
                        SPUtils.getInstance().clear();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangePw,null));
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_Data,null));
                        Intent intent=new Intent();
                        intent.setClass(context,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtils.showShort(comment.getInfo());
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

    private OkObject getOkObjectPwdSave() {
        String url = Constant.HOST + Constant.Url.User_PwdSave;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("userPwd", MD5Util.getMD5(MD5Util.getMD5(editOldPw.getText().toString().trim())+ "ad"));
        params.put("newUserPwd", MD5Util.getMD5(MD5Util.getMD5(editNewPw.getText().toString().trim())+ "ad"));
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
