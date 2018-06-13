package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.base.view.EditDialogText;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.City_List;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Getinfo;
import com.wanbao.modle.Usercar_Query;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XiuGaiCheLiangActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textClxx)
    TextView textClxx;
    @BindView(R.id.imageCxxx)
    ImageView imageCxxx;
    @BindView(R.id.viewCxxx)
    LinearLayout viewCxxx;
    @BindView(R.id.textGcsj)
    TextView textGcsj;
    @BindView(R.id.imageGcsj)
    ImageView imageGcsj;
    @BindView(R.id.viewGcsj)
    LinearLayout viewGcsj;
    @BindView(R.id.textxslc)
    TextView textxslc;
    @BindView(R.id.imagexslc)
    ImageView imagexslc;
    @BindView(R.id.viewXslc)
    LinearLayout viewXslc;
    @BindView(R.id.viewSzy)
    LinearLayout viewSzy;
    @BindView(R.id.textCph)
    TextView textCph;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.textCjh)
    TextView textCjh;
    @BindView(R.id.viewSfy)
    LinearLayout viewSfy;
    @BindView(R.id.textNsdq)
    TextView textNsdq;
    @BindView(R.id.textBxdq)
    TextView textBxdq;
    @BindView(R.id.textSjhm)
    TextView textSjhm;
    @BindView(R.id.editYzm)
    EditText editYzm;
    @BindView(R.id.textFs)
    TextView textFs;
    @BindView(R.id.sbtn_tijiaobdw)
    Button sbtnTijiaobdw;
    @BindView(R.id.textXm)
    TextView textXm;
    @BindView(R.id.viewXm)
    LinearLayout viewXm;
    @BindView(R.id.textCx)
    TextView textCx;
    @BindView(R.id.viewCx)
    LinearLayout viewCx;
    @BindView(R.id.textDz)
    TextView textDz;
    @BindView(R.id.viewDz)
    LinearLayout viewDz;
    @BindView(R.id.viewCph)
    LinearLayout viewCph;
    @BindView(R.id.viewFdjh)
    LinearLayout viewFdjh;
    @BindView(R.id.viewCjh)
    LinearLayout viewCjh;
    @BindView(R.id.viewNcdq)
    LinearLayout viewNcdq;
    @BindView(R.id.viewBxdq)
    LinearLayout viewBxdq;
    private String id;
    private Usercar_Getinfo usercar_getinfo;
    private Usercar_Query usercar_query;
    private XinShiZZM xinShiZZM;
    private XingShiZFY xingShiZFY;
    private Car_Index.DataBean dataBean;
    private City_List.CityBean.ListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_che_liang);
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
        titleText.setText("编辑车辆");
    }

    @Override
    protected void initData() {
        usercar_Query();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_CarX.equals(event.getAction())) {
            dataBean = (Car_Index.DataBean) event.getData();
            if (dataBean != null) {
                textClxx.setText(dataBean.getTitle());
                usercar_getinfo.getData().setCid_name(dataBean.getTitle());
            }
        }
        if (BaseEvent.XinShiZZM.equals(event.getAction())) {
            xinShiZZM = (XinShiZZM) event.getData();
            textCph.setText(xinShiZZM.getData().getCar_no());
            textFdjh.setText(xinShiZZM.getData().getEngine());
            textCjh.setText(xinShiZZM.getData().getVin());
            usercar_getinfo.getData().setCar_no(xinShiZZM.getData().getCar_no());
            usercar_getinfo.getData().setEngine(xinShiZZM.getData().getEngine());
            usercar_getinfo.getData().setVin(xinShiZZM.getData().getVin());

        }
        if (BaseEvent.XingShiZFY.equals(event.getAction())) {
            xingShiZFY = (XingShiZFY) event.getData();
            textNsdq.setText(xingShiZFY.getData().getYear_end());
            textBxdq.setText(xingShiZFY.getData().getInsurance_end());
            usercar_getinfo.getData().setYear_end(xingShiZFY.getData().getYear_end());
            usercar_getinfo.getData().setInsurance_end(xingShiZFY.getData().getInsurance_end());

        }
    }

    @OnClick({R.id.imageback, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewSzy, R.id.viewSfy, R.id.textFs, R.id.sbtn_tijiaobdw,
            R.id.viewXm, R.id.viewCx, R.id.viewDz, R.id.viewCph, R.id.viewFdjh, R.id.viewCjh, R.id.viewNcdq, R.id.viewBxdq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewXm:
                String stringXm="";
                if(xinShiZZM!=null){
                    stringXm=xinShiZZM.getData().getName();
                }
                final EditDialogText editDialogXm = new EditDialogText(context, "输入你的姓名", stringXm, "确认", "取消");
                editDialogXm.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogXm.dismiss();
                        textXm.setText(intro);
                        usercar_getinfo.getData().setName(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogXm.dismiss();
                    }
                });
                editDialogXm.show();
                break;
            case R.id.viewCx:
                String stringCx="";
                if(xinShiZZM!=null){
                    stringCx=xinShiZZM.getData().getCar_name();
                }
                final EditDialogText editDialogCx = new EditDialogText(context, "输入车型", stringCx, "确认", "取消");
                editDialogCx.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCx.dismiss();
                        textCx.setText(intro);
                        usercar_getinfo.getData().setCar_name(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCx.dismiss();
                    }
                });
                editDialogCx.show();
                break;
            case R.id.viewDz:
                String stringDz="";
                if(xinShiZZM!=null){
                    stringDz=xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogDz = new EditDialogText(context, "输入地址", stringDz, "确认", "取消");
                editDialogDz.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogDz.dismiss();
                        textDz.setText(intro);
                        usercar_getinfo.getData().setAddress(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogDz.dismiss();
                    }
                });
                editDialogDz.show();
                break;
            case R.id.viewCph:
                String stringCph="";
                if(xinShiZZM!=null){
                    stringCph=xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogCph = new EditDialogText(context, "输入车牌号", stringCph, "确认", "取消");
                editDialogCph.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCph.dismiss();
                        textCph.setText(intro);
                        usercar_getinfo.getData().setCar_no(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCph.dismiss();
                    }
                });
                editDialogCph.show();
                break;
            case R.id.viewFdjh:
                String stringFdjh="";
                if(xinShiZZM!=null){
                    stringFdjh=xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogFdjh = new EditDialogText(context, "输入发动机号", stringFdjh, "确认", "取消");
                editDialogFdjh.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogFdjh.dismiss();
                        textFdjh.setText(intro);
                        usercar_getinfo.getData().setEngine(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogFdjh.dismiss();
                    }
                });
                editDialogFdjh.show();
                break;
            case R.id.viewCjh:
                String stringCjh="";
                if(xinShiZZM!=null){
                    stringCjh=xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogCjh = new EditDialogText(context, "输入车架号", stringCjh, "确认", "取消");
                editDialogCjh.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCjh.dismiss();
                        textCjh.setText(intro);
                        usercar_getinfo.getData().setVin(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCjh.dismiss();
                    }
                });
                editDialogCjh.show();
                break;
            case R.id.viewNcdq:
                Calendar c0 = Calendar.getInstance();
                DatePickerDialog datePickerDialog0 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textNsdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setYear_end(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c0.get(Calendar.YEAR), c0.get(Calendar.MONTH), c0.get(Calendar.DAY_OF_MONTH));
                datePickerDialog0.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog0.show();
                break;
            case R.id.viewBxdq:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setInsurance_end(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewCxxx:
                intent = new Intent();
                intent.setClass(context, XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGcsj:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textGcsj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setBc_time(year + "-" + (month + 1) + "-" + dayOfMonth);

                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog1.show();
                break;
            case R.id.viewXslc:
                final EditDialog editDialog1 = new EditDialog(context, "行驶里程（km）", "", "确认", "取消");
                editDialog1.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog1.dismiss();
                        textxslc.setText(intro);
                        usercar_getinfo.getData().setKm(Integer.valueOf(intro));
                    }

                    @Override
                    public void doCancel() {
                        editDialog1.dismiss();
                    }
                });
                editDialog1.show();
                break;
            case R.id.viewSzy:
                intent = new Intent();
                intent.putExtra("type", "53");
                intent.putExtra("side", "face");
                intent.setClass(context, SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSfy:
                intent = new Intent();
                intent.putExtra("type", "53");
                intent.putExtra("side", "back");
                intent.setClass(context, SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.textFs:
                yanZM(usercar_getinfo.getData().getPhone());
                break;
            case R.id.sbtn_tijiaobdw:
                if (TextUtils.isEmpty(textGcsj.getText().toString())){
                    ToastUtils.showShort("请设置购车时间");
                    return;
                }
                if (TextUtils.isEmpty(textxslc.getText().toString())){
                    ToastUtils.showShort("请设置行驶里程");
                    return;
                }
                if (TextUtils.isEmpty(textXm.getText().toString())){
                    ToastUtils.showShort("请设置姓名");
                    return;
                }
                if (TextUtils.isEmpty(textCx.getText().toString())){
                    ToastUtils.showShort("请设置车型");
                    return;
                }
                if (TextUtils.isEmpty(textDz.getText().toString())){
                    ToastUtils.showShort("请设置地址");
                    return;
                }
                if (TextUtils.isEmpty(textCph.getText().toString())){
                    ToastUtils.showShort("请设置车牌号");
                    return;
                }
                if (TextUtils.isEmpty(textFdjh.getText().toString())){
                    ToastUtils.showShort("请设置发动机号");
                    return;
                }
                if (!RegexUtils.isMatch(Constant.Cjh,textCjh.getText().toString())){
                    ToastUtils.showShort("请输入正确车架号");
                    return;
                }
                if (TextUtils.isEmpty(textNsdq.getText().toString())){
                    ToastUtils.showShort("请设置年审到期时间");
                    return;
                }
                if (TextUtils.isEmpty(textBxdq.getText().toString())){
                    ToastUtils.showShort("请设置保险到期时间");
                    return;
                }
                if (TextUtils.isEmpty(editYzm.getText().toString())){
                    ToastUtils.showShort("短信验证码不能为空！");
                    return;
                }
                usercar_Add_car();
                break;
            default:
                break;
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

            @Override
            public void onSuccess(String s) {
                LogUtils.e("是否有车", s);
                dismissDialog();
                try {
                    usercar_getinfo = GsonUtils.parseJSON(s, Usercar_Getinfo.class);
                    int status = usercar_getinfo.getStatus();
                    if (status == 1) {
                        textClxx.setText(usercar_getinfo.getData().getCid_name());
                        textGcsj.setText(usercar_getinfo.getData().getBc_time());
                        textxslc.setText(usercar_getinfo.getData().getKm() + "");
                        textCph.setText(usercar_getinfo.getData().getCar_no());
                        textFdjh.setText(usercar_getinfo.getData().getEngine_show());
                        textCjh.setText(usercar_getinfo.getData().getVin_show());
                        textNsdq.setText(usercar_getinfo.getData().getYear_end());
                        textBxdq.setText(usercar_getinfo.getData().getInsurance_end());
                        textSjhm.setText(usercar_getinfo.getData().getPhone_show());
                        textXm.setText(usercar_getinfo.getData().getName());
                        textCx.setText(usercar_getinfo.getData().getCar_name());
                        textDz.setText(usercar_getinfo.getData().getAddress());
                    } else {
                        ToastUtils.showShort(usercar_getinfo.getInfo());
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

    private OkObject getOkObjectUQ() {
        String url = Constant.HOST + Constant.Url.Usercar_Getinfo;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
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


    private void usercar_Add_car() {
        HttpApi.post(context, getOkObjectBD(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("绑定中..");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("是否有车", s);
                dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        ToastUtils.showShort("修改成功！");
                        finish();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_Data, null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
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

    private OkObject getOkObjectBD() {
        String url = Constant.HOST + Constant.Url.Usercar_Add_car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_name", usercar_getinfo.getData().getCar_name());
        params.put("cid", usercar_getinfo.getData().getCid() + "");
        params.put("bc_time", usercar_getinfo.getData().getBc_time());
        params.put("engine", usercar_getinfo.getData().getEngine());
        params.put("car_no", usercar_getinfo.getData().getCar_no());
        params.put("vin", usercar_getinfo.getData().getVin());
        params.put("km", usercar_getinfo.getData().getKm() + "");
        params.put("phone", usercar_getinfo.getData().getPhone());
        params.put("year_end", usercar_getinfo.getData().getYear_end());
        params.put("insurance_end", usercar_getinfo.getData().getInsurance_end());
        params.put("code", editYzm.getText().toString());
        params.put("address", usercar_getinfo.getData().getAddress());
        params.put("name", usercar_getinfo.getData().getName() + "");
        params.put("id", usercar_getinfo.getData().getId() + "");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
