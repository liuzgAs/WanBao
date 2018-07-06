package com.wanbao.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.util.EventReminderUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PaySucessActivity extends BaseNoLeftActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textChaKanDD)
    TextView textChaKanDD;
    private int paytype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_sucess);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        paytype = getIntent().getIntExtra("paytype", 0);
    }

    @Override
    protected void initViews() {
        titleText.setText("预约成功");
    }

    @Override
    protected void initData() {
        EventBus.getDefault().post(new BaseEvent(BaseEvent.Pay_Sucess, null));
        getAddressPermissions();
    }

    private void getAddressPermissions() {
        RxPermissions rxPermissions = new RxPermissions(PaySucessActivity.this);
        rxPermissions
                .request(Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (paytype == 1) {
                                EventReminderUtils.addCalendarEvent(PaySucessActivity.this, AppContext.getIntance().dates,"新的试驾信息");
                            } else {
                                EventReminderUtils.addCalendarEvent(PaySucessActivity.this, AppContext.getIntance().dates,"新的维保信息");
                            }
                            AppContext.getIntance().dates="";
                        } else {
                            dismissDialog();
                            Toast.makeText(context, "加入日程失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.imageback, R.id.textChaKanDD})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.textChaKanDD:
                Intent intent = new Intent();
                if (paytype == 1) {
                    intent.putExtra("currentItem", 2);
                    intent.setClass(context, ShiJiaDDActivity.class);
                } else {
                    intent.putExtra("currentItem", 2);
                    intent.setClass(context, WeiBaoDDActivity.class);
                }
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
