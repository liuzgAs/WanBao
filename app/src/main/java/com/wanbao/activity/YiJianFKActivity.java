package com.wanbao.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class YiJianFKActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editContent)
    EditText editContent;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian_fk);
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
    titleText.setText("意见反馈");
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
                if (TextUtils.isEmpty(editEmail.getText().toString())){
                    ToastUtils.showShort("请输入邮箱地址");
                    return;
                }
                if (TextUtils.isEmpty(editContent.getText().toString())){
                    ToastUtils.showShort("请输入你的反馈");
                    return;
                }
                getFeedback();
                break;
            default:
                break;
        }
    }

    private void getFeedback() {
        HttpApi.post(context, getOkObFeedback(), new HttpApi.CallBack() {
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
                LogUtils.e("User_Interest", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        finish();
                        ToastUtils.showShort("反馈成功！");
                    } else {
                        ToastUtils.showShort(String.valueOf(comment.getInfo()));
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
            }


        });
    }

    private OkObject getOkObFeedback() {
        String url = Constant.HOST + Constant.Url.User_Feedback;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("login_type", "1");
        params.put("email", editEmail.getText().toString());
        params.put("content", editContent.getText().toString());

        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
