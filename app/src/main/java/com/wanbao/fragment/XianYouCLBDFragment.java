package com.wanbao.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.activity.SaoMiaoActivity;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.City_List;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Query;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public class XianYouCLBDFragment extends PSFragment {
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.betterSpinner)
    BetterSpinner betterSpinner;
    @BindView(R.id.editChePai)
    EditText editChePai;
    @BindView(R.id.sbtn_chaxun)
    StateButton sbtnChaxun;
    @BindView(R.id.viewChaxun)
    LinearLayout viewChaxun;
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
    StateButton sbtnTijiaobdw;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    Unbinder unbinder;
    private View view;
    private Car_Index.DataBean dataBean;
    private City_List.CityBean.ListBean listBean;
    private static final String[] COUNTRIES = new String[]{
            "京", "津", "沪", "渝", "蒙",
            "新", "藏", "宁", "桂", "港",
            "澳", "黑", "吉", "辽", "晋",
            "冀", "青", "鲁", "豫", "苏",
            "皖", "浙", "闽", "赣", "湘",
            "鄂", "粤", "琼", "甘", "陕",
            "黔", "滇", "川"
    };
    private Usercar_Query usercar_query;
    private XinShiZZM xinShiZZM;
    private XingShiZFY xingShiZFY;

    public static XianYouCLBDFragment newInstance() {
        XianYouCLBDFragment sf = new XianYouCLBDFragment();
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_xian_you_cl, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void fetchData() {
        viewSwitcher.setDisplayedChild(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.item_sheng, COUNTRIES);
        betterSpinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
        if (timer != null) {
            timer.cancel();
        }
    }


    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_CarX.equals(event.getAction())) {
            dataBean = (Car_Index.DataBean) event.getData();
            if (dataBean != null) {
                textClxx.setText(dataBean.getTitle());
            }
        }
        if (BaseEvent.Choose_CS.equals(event.getAction())) {
            listBean = (City_List.CityBean.ListBean) event.getData();
            if (listBean != null) {
            }
        }
        if (BaseEvent.XinShiZZM.equals(event.getAction())) {
            xinShiZZM = (XinShiZZM) event.getData();
            textCph.setText(xinShiZZM.getData().getCar_no());
            textFdjh.setText(xinShiZZM.getData().getEngine());
            textCjh.setText(xinShiZZM.getData().getVin());
        }
        if (BaseEvent.XingShiZFY.equals(event.getAction())) {
            xingShiZFY = (XingShiZFY) event.getData();
            textNsdq.setText(xingShiZFY.getData().getYear_end());
            textBxdq.setText(xingShiZFY.getData().getInsurance_end());
        }
    }

    private void usercar_Query() {
        HttpApi.post(context, getOkObjectUQ(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("查询中..");
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
                    usercar_query = GsonUtils.parseJSON(s, Usercar_Query.class);
                    int status = usercar_query.getStatus();
                    if (status == 1) {
                        viewSwitcher.setDisplayedChild(1);
                        if (usercar_query.getR() == 0) {
                            textState.setText("系统未查到该车牌车辆，请自行录入");
                            imageCxxx.setVisibility(View.VISIBLE);
                            imageGcsj.setVisibility(View.VISIBLE);
                            imagexslc.setVisibility(View.VISIBLE);
                            viewSzy.setVisibility(View.VISIBLE);
                            viewSfy.setVisibility(View.VISIBLE);
                            textSjhm.setText(usercar_query.getData().getPhone_show());
                        } else {
                            imageCxxx.setVisibility(View.GONE);
                            imageGcsj.setVisibility(View.GONE);
                            imagexslc.setVisibility(View.GONE);
                            viewSzy.setVisibility(View.GONE);
                            viewSfy.setVisibility(View.GONE);
                            textState.setText("查询到以下车辆信息");
                            textClxx.setText(usercar_query.getData().getCar_name());
                            textGcsj.setText(usercar_query.getData().getBc_time());
//                            textxslc.setText(usercar_query.getData().);
                            textCph.setText(usercar_query.getData().getCar_no());
                            textFdjh.setText(usercar_query.getData().getEngine_show());
                            textCjh.setText(usercar_query.getData().getVin_show());
//                            textNsdq.setText(usercar_query.getData());
                            textSjhm.setText(usercar_query.getData().getPhone_show());

                        }
                    } else {
                        ToastUtils.showShort(usercar_query.getInfo());
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
        String url = Constant.HOST + Constant.Url.Usercar_Query;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_no", betterSpinner.getText().toString() + editChePai.getText().toString());
        return new OkObject(params, url);
    }

    private void usercar_Add_car(OkObject okObject) {
        HttpApi.post(context, okObject, new HttpApi.CallBack() {
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
                        ToastUtils.showShort("提交成功！");
                        getActivity().finish();
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
        params.put("car_name", usercar_query.getData().getCar_name());
        params.put("cid", usercar_query.getData().getCid());
        params.put("bc_time", usercar_query.getData().getBc_time());
        params.put("engine", usercar_query.getData().getEngine());
        params.put("car_no", usercar_query.getData().getCar_no());
        params.put("vin", usercar_query.getData().getVin());
        params.put("km", textxslc.getText().toString());
        params.put("phone", usercar_query.getData().getPhone());
        params.put("year_end", textNsdq.getText().toString());
        params.put("insurance_end", textBxdq.getText().toString());
        params.put("code", editYzm.getText().toString());
        params.put("address", "");
        params.put("name", "");
        return new OkObject(params, url);
    }

    private OkObject getOkObjectBD1() {
        String url = Constant.HOST + Constant.Url.Usercar_Add_car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_name", textClxx.getText().toString());
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", textGcsj.getText().toString());
        params.put("engine", xinShiZZM.getData().getEngine());
        params.put("car_no", xinShiZZM.getData().getCar_no());
        params.put("vin", xinShiZZM.getData().getVin());
        params.put("km", textxslc.getText().toString());
        params.put("phone", usercar_query.getData().getPhone());
        params.put("year_end", xingShiZFY.getData().getYear_end());
        params.put("insurance_end", xingShiZFY.getData().getInsurance_end());
        params.put("code", editYzm.getText().toString());
        params.put("address", xinShiZZM.getData().getAddress());
        params.put("name", xinShiZZM.getData().getName());
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

    @OnClick({R.id.textFs, R.id.sbtn_chaxun, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewSzy, R.id.viewSfy, R.id.sbtn_tijiaobdw})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.textFs:
                yanZM(usercar_query.getData().getPhone());
                break;
            case R.id.sbtn_chaxun:
                if (TextUtils.isEmpty(editChePai.getText())) {
                    ToastUtils.showShort("请输入车牌号！");
                    return;
                }
                usercar_Query();
                break;
            case R.id.viewCxxx:
                intent = new Intent();
                intent.setClass(getActivity(), XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGcsj:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textGcsj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog1.show();
                break;
            case R.id.viewXslc:
                final EditDialog editDialog1 = new EditDialog(context, "行驶里程（km）", "", "确认", "取消");
                editDialog1.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog1.dismiss();
                        textxslc.setText(intro);
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
                intent.setClass(getActivity(), SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSfy:
                intent = new Intent();
                intent.putExtra("type", "53");
                intent.putExtra("side", "back");
                intent.setClass(getActivity(), SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.sbtn_tijiaobdw:
                if (usercar_query.getR() == 0) {
                    if (xinShiZZM==null){
                        ToastUtils.showShort("请扫描获取身份证正页信息！");
                        return;
                    }
                    if (xingShiZFY==null){
                        ToastUtils.showShort("请扫描获取身份证副面信息！");
                        return;
                    }
                    usercar_Add_car(getOkObjectBD1());
                } else if (usercar_query.getR() == 1) {
                    usercar_Add_car(getOkObjectBD());
                }
                break;
            default:
                break;

        }
    }
}
