package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
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
import com.wanbao.modle.Order_NewOrder;
import com.wanbao.modle.TestDrive_AddTuserBefore;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class LuRuXXActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editCard)
    EditText editCard;
    @BindView(R.id.editNameJj)
    EditText editNameJj;
    @BindView(R.id.editPhoneJj)
    EditText editPhoneJj;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;
    private HashMap<String,String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lu_ru_xx);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        messages=(HashMap) getIntent().getSerializableExtra("messages");
    }

    @Override
    protected void initViews() {
        titleText.setText("录入信息");
    }

    @Override
    protected void initData() {
        AddTuserBefore();
    }

    @OnClick({R.id.imageback, R.id.sbtn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.sbtn_tijiao:
                if (TextUtils.isEmpty(editName.getText().toString())){
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if (!RegexUtils.isMobileSimple(editPhone.getText().toString())){
                    ToastUtils.showShort("请正确的手机号");
                    return;
                }
                if (!RegexUtils.isIDCard15(editCard.getText().toString())&&!RegexUtils.isIDCard18(editCard.getText().toString())){
                    ToastUtils.showShort("请正确身份证号");
                    return;
                }
                if (TextUtils.isEmpty(editNameJj.getText().toString())){
                    ToastUtils.showShort("请输入紧急联系人姓名");
                    return;
                }
                if (!RegexUtils.isMobileSimple(editPhoneJj.getText().toString())){
                    ToastUtils.showShort("请正确的手机号");
                    return;
                }
                AddTuser();
                break;
            default:
                break;
        }
    }

    private void AddTuserBefore() {
        HttpApi.post(context, getOkObjectAddTuserBefore(), new HttpApi.CallBack() {
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
                Log.e("AddTuserBefore", s);
                try {
                    TestDrive_AddTuserBefore data = GsonUtils.parseJSON(s, TestDrive_AddTuserBefore.class);
                    if (data.getStatus() == 1) {
                        editName.setText(data.getData().getName());
                        editPhone.setText(data.getData().getPhone());
                        editCard.setText(data.getData().getIdcard());
                        editNameJj.setText(data.getData().getEmergency());
                        editPhoneJj.setText(data.getData().getEphone());
                    } else {
                        ToastUtils.showShort("数据出错");
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

    private OkObject getOkObjectAddTuserBefore() {
        String url = Constant.HOST + Constant.Url.TestDrive_AddTuserBefore;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void AddTuser() {
        HttpApi.post(context, getOkObjectAddTuser(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("录入中..");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("AddTuser", s);
                try {
                    Comment data = GsonUtils.parseJSON(s, Comment.class);
                    if (data.getStatus() == 1) {
                        getOrder();
                    } else {
                        ToastUtils.showShort("数据出错");
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

    private OkObject getOkObjectAddTuser() {
        String url = Constant.HOST + Constant.Url.TestDrive_AddTuser;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("name", editName.getText().toString());
        params.put("phone", editPhone.getText().toString());
        params.put("idcard", editCard.getText().toString());
        params.put("emergency", editNameJj.getText().toString());
        params.put("ephone", editPhoneJj.getText().toString());
        return new OkObject(params, url);
    }

    private void getOrder() {
        HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
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
                    Order_NewOrder order_newOrder = GsonUtils.parseJSON(s, Order_NewOrder.class);
                    if (order_newOrder.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.putExtra("Oid", order_newOrder.getOid());
                        intent.putExtra("paytype",1);
                        intent.setClass(context, LiJiZhiFuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtils.showShort(order_newOrder.getInfo());
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

    private OkObject getOkObjectOrder() {
        String url = Constant.HOST + Constant.Url.Order_NewOrder;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("sid", messages.get("sid"));
        params.put("book_time", messages.get("book_time"));
        params.put("item_id", messages.get("item_id"));
        params.put("type", "2");
        params.put("online_pay","1");
        return new OkObject(params, url);
    }
}
