package com.wanbao.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.ui.StateButton;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianZHActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.editAccount)
    EditText editAccount;
    @BindView(R.id.btnSure)
    StateButton btnSure;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian_zh);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        type=getIntent().getStringExtra("type");
    }

    @Override
    protected void initViews() {
        editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())){
                    btnSure.setEnabled(false);
                }else {
                    btnSure.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void initData() {
        if (TextUtils.equals("0",type)){
            titleText.setText("微信提现");
            editAccount.setHint("请输入微信账号");
        }else {
            titleText.setText("支付宝提现");
            editAccount.setHint("请输入支付宝账号");
        }
    }

    @OnClick({R.id.imageback, R.id.btnSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btnSure:
                HashMap<String,String> message=new HashMap<>();
                message.put("type",type);
                message.put("account",editAccount.getText().toString());
                EventBus.getDefault().post(new BaseEvent(BaseEvent.AddAccount,message));
                finish();
                break;
            default:
                break;
        }
    }
}
