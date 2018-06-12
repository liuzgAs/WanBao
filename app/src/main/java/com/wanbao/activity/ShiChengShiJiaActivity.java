package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Index_Store;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Testdrive_Details;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShiChengShiJiaActivity extends BaseActivity {
    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageCar)
    ImageView imageCar;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.textDp)
    TextView textDp;
    @BindView(R.id.viewDp)
    CardView viewDp;
    @BindView(R.id.textSj)
    TextView textSj;
    @BindView(R.id.viewSj)
    CardView viewSj;
    @BindView(R.id.textSjj)
    TextView textSjj;
    @BindView(R.id.textYxj)
    TextView textYxj;
    @BindView(R.id.textSjj0)
    TextView textSjj0;
    @BindView(R.id.btnLjyy)
    Button btnLjyy;
    @BindView(R.id.textsjxy)
    TextView textsjxy;
    private String id;
    private String book_time;
    private String store_id;
    private Index_Store.DataBean index_store;
    private HashMap<String, String> messages = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_cheng_shi_jia);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initViews() {
        titleText.setText("试乘试驾");
    }

    @Override
    protected void initData() {
        testdrive();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_Dp.equals(event.getAction())) {
            index_store = (Index_Store.DataBean) event.getData();
            if (index_store != null) {
                textDp.setText(index_store.getTitle());
                store_id = String.valueOf(index_store.getId());
            }
        }
    }

    @OnClick({R.id.imageback, R.id.viewDp, R.id.viewSj, R.id.btnLjyy,R.id.textsjxy})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.textsjxy:
                intent = new Intent();
                intent.putExtra("title","试驾协议");
                intent.putExtra("mUrl",Constant.Url.ShiJiaXY);
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewDp:
                intent = new Intent();
                intent.putExtra("cid", id);
                intent.setClass(context, YuYueDpActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSj:
                final Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ShiChengShiJiaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {


                        TimePickerDialog dialog = new TimePickerDialog(ShiChengShiJiaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                textSj.setText(year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute);
                                book_time = year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute;
                            }
                        }, c1.get(Calendar.HOUR_OF_DAY), c1.get(Calendar.MINUTE), true);
                        dialog.show();

                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
                break;
            case R.id.btnLjyy:
                if (TextUtils.isEmpty(store_id)) {
                    ToastUtils.showShort("请选择预约店铺！");
                    return;
                }
                if (TextUtils.isEmpty(book_time)) {
                    ToastUtils.showShort("请选择预约时间！");
                    return;
                }
                messages.clear();
                messages.put("sid", store_id);
                messages.put("item_id", id);
                messages.put("book_time", book_time);
                messages.put("type", "2");
                messages.put("online_pay", "1");

                intent = new Intent();
                intent.putExtra("messages", (Serializable) messages);
                intent.setClass(context, LuRuXXActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void testdrive() {
        HttpApi.post(context, getOkObjectTestdrive(), new HttpApi.CallBack() {
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
                Log.e("testdrive", s);
                try {
                    Testdrive_Details data = GsonUtils.parseJSON(s, Testdrive_Details.class);
                    if (data.getStatus() == 1) {
                        textTitle.setText(data.getData().getInfo().getTitle());
                        textDes.setText(data.getData().getInfo().getType() + "|" + data.getData().getInfo().getSeat() + "座|指导价" + data.getData().getInfo().getPrice() + "万");
                        textSjj.setText("¥" + data.getData().getInfo().getDriveprice());
                        textSjj0.setText("¥" + data.getData().getInfo().getDriveprice());
                        textYxj.setText("¥" + data.getData().getInfo().getDeposit());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(data.getData().getInfo().getThumb())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCar);
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

    private OkObject getOkObjectTestdrive() {
        String url = Constant.HOST + Constant.Url.Testdrive_Details;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

}
