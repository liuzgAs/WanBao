package com.wanbao.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialogText;
import com.wanbao.modle.Comment;
import com.wanbao.modle.JiaShiZ;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.ShenFenB;
import com.wanbao.modle.ShenFenZ;
import com.wanbao.modle.User_Card_before;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShiMinRzActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.viewSfzzy)
    LinearLayout viewSfzzy;
    @BindView(R.id.textXm)
    TextView textXm;
    @BindView(R.id.textXb)
    TextView textXb;
    @BindView(R.id.textZjlx)
    TextView textZjlx;
    @BindView(R.id.textZjhm)
    TextView textZjhm;
    @BindView(R.id.viewJszzy)
    LinearLayout viewJszzy;
    @BindView(R.id.textXmj)
    TextView textXmj;
    @BindView(R.id.textXbj)
    TextView textXbj;
    @BindView(R.id.textCclzrq)
    TextView textCclzrq;
    @BindView(R.id.textZjcx)
    TextView textZjcx;
    @BindView(R.id.textYxq)
    TextView textYxq;
    @BindView(R.id.textQueren)
    TextView textQueren;
    @BindView(R.id.viewXm)
    LinearLayout viewXm;
    @BindView(R.id.viewXb)
    LinearLayout viewXb;
    @BindView(R.id.viewZjhm)
    LinearLayout viewZjhm;
    @BindView(R.id.viewSfzby)
    LinearLayout viewSfzby;
    @BindView(R.id.textYxqq)
    TextView textYxqq;
    @BindView(R.id.viewYxqq)
    LinearLayout viewYxqq;
    @BindView(R.id.textYxqz)
    TextView textYxqz;
    @BindView(R.id.viewYxqz)
    LinearLayout viewYxqz;
    @BindView(R.id.viewXmj)
    LinearLayout viewXmj;
    @BindView(R.id.viewXbj)
    LinearLayout viewXbj;
    @BindView(R.id.viewCclzrq)
    LinearLayout viewCclzrq;
    @BindView(R.id.viewZjcx)
    LinearLayout viewZjcx;
    @BindView(R.id.viewYxq)
    LinearLayout viewYxq;
    private User_Card_before uCBefore;
    private ShenFenZ shenFenZ;
    private JiaShiZ jiaShiZ;
    private String card_img;
    private String driver_img;
    private ShenFenB shenFenB;
    private String driver_start_date;
    private String driver_end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_min_rz);
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
        titleText.setText("实名认证");
    }

    @Override
    protected void initData() {
        usercar_Query();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ShenFenZ.equals(event.getAction())) {
            shenFenZ = (ShenFenZ) event.getData();
            if (shenFenZ != null) {
                card_img = shenFenZ.getImg_id();
                textXm.setText(shenFenZ.getData().getName());
                textXb.setText(shenFenZ.getData().getGender());
                textZjhm.setText(shenFenZ.getData().getCard_no());
            }

        }
        if (BaseEvent.ShenFenB.equals(event.getAction())) {
            shenFenB = (ShenFenB) event.getData();
            if (shenFenB != null) {
                textYxqq.setText(shenFenB.getData().getStart_date());
                textYxqz.setText(shenFenB.getData().getEnd_date());
            }

        }
        if (BaseEvent.JiaShiZ.equals(event.getAction())) {
            jiaShiZ = (JiaShiZ) event.getData();
            if (jiaShiZ != null) {
                driver_img = jiaShiZ.getImg_id();
                textXmj.setText(jiaShiZ.getData().getDriver_name());
                textXbj.setText(jiaShiZ.getData().getDriver_gender());
                textCclzrq.setText(jiaShiZ.getData().getDriver_reg());
                textZjcx.setText(jiaShiZ.getData().getDriver_type());
                textYxq.setText(jiaShiZ.getData().getValidity());

            }

        }
    }


    private void usercar_Query() {
        HttpApi.post(context, getOkObjectUQ(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(String s) {
                LogUtils.e("User_Card_before", s);
                dismissDialog();
                try {
                    uCBefore = GsonUtils.parseJSON(s, User_Card_before.class);
                    int status = uCBefore.getStatus();
                    if (status == 1) {
                        textXm.setText(uCBefore.getData().getName());
                        textXb.setText(uCBefore.getData().getDriver_gender());
                        textXmj.setText(uCBefore.getData().getDriver_name());
                        textXbj.setText(uCBefore.getData().getDriver_gender());
                        textZjlx.setText("身份证");
                        textZjhm.setText(uCBefore.getData().getCard_no());
                        textCclzrq.setText(uCBefore.getData().getDriver_reg());
                        textZjcx.setText(uCBefore.getData().getDriver_type());
                        textYxq.setText(uCBefore.getData().getDriver_start_date()+"至"+uCBefore.getData().getDriver_end_date());
                        textState.setText(uCBefore.getStateDes());
                        driver_start_date=uCBefore.getData().getDriver_start_date();
                        driver_end_date=uCBefore.getData().getDriver_end_date();
                        textYxqq.setText(uCBefore.getData().getStart_date());
                        textYxqz.setText(uCBefore.getData().getEnd_date());
                        if (uCBefore.getState() == 0) {
                            textQueren.setText("提交认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.light_red));
                        } else if (uCBefore.getState() == 1) {
                            textQueren.setText("审核中");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.new_bule));
                        } else if (uCBefore.getState() == 2) {
                            textQueren.setText("重新申请认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.new_important_text));
                        } else if (uCBefore.getState() == 3) {
                            textQueren.setText("已认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.light_red));
                        }
                    } else {
                        ToastUtils.showShort(uCBefore.getInfo());
                    }
                } catch (Exception e) {
                    if (uCBefore == null) {
                        uCBefore = new User_Card_before();
                    }
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

    private OkObject getOkObjectUQ() {
        String url = Constant.HOST + Constant.Url.User_Card_before;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void useradd() {
        HttpApi.post(context, getOkObadd(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(String s) {
                LogUtils.e("User_Card_before", s);
                dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeXx, null));
                        finish();
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
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

    private OkObject getOkObadd() {
        String url = Constant.HOST + Constant.Url.User_Card_add;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("name", textXm.getText().toString());
        params.put("gender", textXb.getText().toString());
        params.put("card_no", textZjhm.getText().toString());
        params.put("driver_name", textXmj.getText().toString());
        params.put("driver_gender", textXbj.getText().toString());
        params.put("driver_reg", textCclzrq.getText().toString());
        params.put("driver_type", textZjcx.getText().toString());
        params.put("validity", textYxq.getText().toString());
        params.put("card_img", card_img);
        params.put("driver_img", driver_img);
        params.put("start_date", textYxqq.getText().toString());
        params.put("end_date", textYxqz.getText().toString());
        params.put("driver_start_date", driver_start_date);
        params.put("driver_end_date",driver_end_date);
        return new OkObject(params, url);
    }

    @OnClick({R.id.imageback, R.id.viewSfzzy, R.id.viewJszzy, R.id.textQueren, R.id.viewXm, R.id.viewXb, R.id.viewZjhm, R.id.viewSfzby, R.id.viewYxqq, R.id.viewYxqz, R.id.viewXmj, R.id.viewXbj, R.id.viewCclzrq, R.id.viewZjcx, R.id.viewYxq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewSfzzy:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            0);

                }else {
                    intent = new Intent();
                    intent.putExtra("type", "51");
                    intent.putExtra("side", "face");
                    intent.setClass(context, SaoMiaoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.viewJszzy:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            0);

                }else {
                    intent = new Intent();
                    intent.putExtra("type", "52");
                    intent.putExtra("side", "face");
                    intent.setClass(context, SaoMiaoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.textQueren:
                if (uCBefore.getState() == 1 || uCBefore.getState() == 3) {
                    return;
                }
                if (TextUtils.isEmpty(textXm.getText().toString())){
                    ToastUtils.showShort("请扫描身份证正面");
                    return;
                }
                if (TextUtils.isEmpty(textXb.getText().toString())){
                    ToastUtils.showShort("请扫描身份证正面");
                    return;
                }
                if (TextUtils.isEmpty(textZjhm.getText().toString())){
                    ToastUtils.showShort("请扫描身份证正面");
                    return;
                }
                if (TextUtils.isEmpty(textYxqq.getText().toString())){
                    ToastUtils.showShort("请扫描身份证背面");
                    return;
                }
                if (TextUtils.isEmpty(textYxqz.getText().toString())){
                    ToastUtils.showShort("请扫描身份证背面");
                    return;
                }
                if (TextUtils.isEmpty(textXmj.getText().toString())){
                    ToastUtils.showShort("请扫描驾驶证正页");
                    return;
                }
                if (TextUtils.isEmpty(textXbj.getText().toString())){
                    ToastUtils.showShort("请扫描驾驶证正页");
                    return;
                }
                if (TextUtils.isEmpty(textCclzrq.getText().toString())){
                    ToastUtils.showShort("请扫描驾驶证正页");
                    return;
                }
                if (TextUtils.isEmpty(textZjcx.getText().toString())){
                    ToastUtils.showShort("请扫描驾驶证正页");
                    return;
                }
                if (TextUtils.isEmpty(textYxq.getText().toString())){
                    ToastUtils.showShort("请扫描驾驶证正页");
                    return;
                }
                useradd();
                break;
            case R.id.viewXm:
                final EditDialogText editDialogDanb = new EditDialogText(context, "输入身份证姓名", textXm.getText().toString(), "确认", "取消");
                editDialogDanb.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogDanb.dismiss();
                        textXm.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogDanb.dismiss();
                    }
                });
                editDialogDanb.show();
                break;
            case R.id.viewXb:
                final String[] sexs={"男","女"};
                new MaterialDialog.Builder(context)
                        .title("选择你的性别")
                        .items(sexs)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textXb.setText(sexs[position]);
                            }
                        })
                        .show();
                break;
            case R.id.viewZjhm:
                final EditDialogText editDialogZjhm = new EditDialogText(context, "输入身份证号码", textZjhm.getText().toString(), "确认", "取消");
                editDialogZjhm.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogZjhm.dismiss();
                        textZjhm.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogZjhm.dismiss();
                    }
                });
                editDialogZjhm.show();
                break;
            case R.id.viewSfzby:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            0);

                }else {
                    intent = new Intent();
                    intent.putExtra("type", "51");
                    intent.putExtra("side", "back");
                    intent.setClass(context, SaoMiaoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.viewYxqq:
                Calendar czc = Calendar.getInstance();
                DatePickerDialog datePickerDialogzc = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textYxqq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, czc.get(Calendar.YEAR), czc.get(Calendar.MONTH), czc.get(Calendar.DAY_OF_MONTH));
                datePickerDialogzc.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogzc.show();
                break;
            case R.id.viewYxqz:
                Calendar cfz = Calendar.getInstance();
                DatePickerDialog datePickerDialogfz = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textYxqz.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, cfz.get(Calendar.YEAR), cfz.get(Calendar.MONTH), cfz.get(Calendar.DAY_OF_MONTH));
                datePickerDialogfz.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialogfz.show();
                break;
            case R.id.viewXmj:
                final EditDialogText editDialogXmj = new EditDialogText(context, "输入驾驶证姓名", textXmj.getText().toString(), "确认", "取消");
                editDialogXmj.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogXmj.dismiss();
                        textXmj.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogXmj.dismiss();
                    }
                });
                editDialogXmj.show();
                break;
            case R.id.viewXbj:
                final String[] sexsj={"男","女"};
                new MaterialDialog.Builder(context)
                        .title("选择你的性别")
                        .items(sexsj)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textXbj.setText(sexsj[position]);
                            }
                        })
                        .show();
                break;
            case R.id.viewCclzrq:
                Calendar Cclzrq = Calendar.getInstance();
                DatePickerDialog datePickerDialogCclzrq = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textCclzrq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, Cclzrq.get(Calendar.YEAR), Cclzrq.get(Calendar.MONTH), Cclzrq.get(Calendar.DAY_OF_MONTH));
                datePickerDialogCclzrq.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogCclzrq.show();
                break;
            case R.id.viewZjcx:
                final EditDialogText editDialogZjcx = new EditDialogText(context, "输入准驾车型", textZjcx.getText().toString(), "确认", "取消");
                editDialogZjcx.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogZjcx.dismiss();
                        textZjcx.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogZjcx.dismiss();
                    }
                });
                editDialogZjcx.show();
                break;
            case R.id.viewYxq:

                break;
            default:
                break;
        }
    }
}
