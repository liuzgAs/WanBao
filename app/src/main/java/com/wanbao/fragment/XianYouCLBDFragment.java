package com.wanbao.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
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
import com.wanbao.base.util.A2bigA;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.base.view.EditDialogText;
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
    @BindView(R.id.textXm)
    TextView textXm;
    @BindView(R.id.textCx)
    TextView textCx;
    @BindView(R.id.textDz)
    TextView textDz;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.viewXm)
    LinearLayout viewXm;
    @BindView(R.id.viewCx)
    LinearLayout viewCx;
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
    @BindView(R.id.textZcrq)
    TextView textZcrq;
    @BindView(R.id.viewZcrq)
    LinearLayout viewZcrq;
    @BindView(R.id.textFzrq)
    TextView textFzrq;
    @BindView(R.id.viewFzrq)
    LinearLayout viewFzrq;
    @BindView(R.id.textSyx)
    TextView textSyx;
    @BindView(R.id.viewSyx)
    LinearLayout viewSyx;
    private View view;
    private Car_Index.DataBean dataBean;
    private City_List.CityBean.ListBean listBean;
    private static final String[] COUNTRIES = new String[]{
            "京", "津", "沪", "闽", "粤", "渝", "蒙",
            "新", "藏", "宁", "桂", "港",
            "澳", "黑", "吉", "辽", "晋",
            "冀", "青", "鲁", "豫", "苏",
            "皖", "浙", "赣", "湘",
            "鄂", "琼", "甘", "陕",
            "黔", "滇", "川"
    };
    private Usercar_Query usercar_query;
    private XinShiZZM xinShiZZM;
    private XingShiZFY xingShiZFY;
    private String face_img;
    private String back_img;
    private String xslc;

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
        editChePai.setTransformationMethod(new A2bigA());
        textCph.setTransformationMethod(new A2bigA());
        return view;
    }

    @Override
    public void fetchData() {
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
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
            textXm.setText(xinShiZZM.getData().getName());
            textCx.setText(xinShiZZM.getData().getCar_name());
            textDz.setText(xinShiZZM.getData().getAddress());
            textZcrq.setText(xinShiZZM.getData().getRegister_date());
            textFzrq.setText(xinShiZZM.getData().getIssue_date());
            face_img = xinShiZZM.getImg_id();
        }
        if (BaseEvent.XingShiZFY.equals(event.getAction())) {
            xingShiZFY = (XingShiZFY) event.getData();
            textNsdq.setText(xingShiZFY.getData().getYear_end());
            textBxdq.setText(xingShiZFY.getData().getInsurance_end());
            back_img = xingShiZFY.getImg_id();
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
                            textCph.setText(editChePai.getText().toString());
                            textSjhm.setText(usercar_query.getData().getPhone_show());
                        } else {
                            imageCxxx.setVisibility(View.VISIBLE);
                            imageGcsj.setVisibility(View.GONE);
                            imagexslc.setVisibility(View.GONE);
                            viewSzy.setVisibility(View.GONE);
                            viewSfy.setVisibility(View.GONE);
                            textState.setText("查询到以下车辆信息");
                            textCx.setText(usercar_query.getData().getCar_name());
                            textGcsj.setText(usercar_query.getData().getBc_time());
//                            textxslc.setText(usercar_query.getData().);
                            textCph.setText(usercar_query.getData().getCar_no());
                            textFdjh.setText(usercar_query.getData().getEngine_show());
                            textCjh.setText(usercar_query.getData().getVin_show());
//                            textNsdq.setText(usercar_query.getData());
                            textSjhm.setText(usercar_query.getData().getPhone_show());
                            textZcrq.setText(usercar_query.getData().getRegister_date());
                            textFzrq.setText(usercar_query.getData().getIssue_date());
                            textSyx.setText(usercar_query.getData().getInsurance_commerce());
                        }
                        scrollView.scrollTo(0, 0);
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
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", usercar_query.getData().getBc_time());
        params.put("engine", usercar_query.getData().getEngine());
        params.put("car_no", usercar_query.getData().getCar_no());
        params.put("vin", usercar_query.getData().getVin());
        params.put("km", xslc);
        params.put("phone", usercar_query.getData().getPhone());
        params.put("year_end", textNsdq.getText().toString());
        params.put("insurance_end", textBxdq.getText().toString());
        params.put("code", editYzm.getText().toString());
        params.put("address", textDz.getText().toString());
        params.put("name", textXm.getText().toString());
        params.put("register_date", textZcrq.getText().toString());
        params.put("issue_date", textFzrq.getText().toString());
        params.put("insurance_commerce", textSyx.getText().toString());
        params.put("face_img", face_img);
        params.put("back_img", back_img);
        return new OkObject(params, url);
    }

    private OkObject getOkObjectBD1() {
        String url = Constant.HOST + Constant.Url.Usercar_Add_car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_name", textCx.getText().toString());
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", textGcsj.getText().toString());
        params.put("engine", textFdjh.getText().toString());
        params.put("car_no", textCph.getText().toString());
        params.put("vin", textCjh.getText().toString());
        params.put("km", xslc);
        params.put("phone", usercar_query.getData().getPhone());
        params.put("year_end", textNsdq.getText().toString());
        params.put("insurance_end", textBxdq.getText().toString());
        params.put("code", editYzm.getText().toString());
        params.put("address", textDz.getText().toString());
        params.put("name", textXm.getText().toString());
        params.put("face_img", face_img);
        params.put("back_img", back_img);
        params.put("register_date", textZcrq.getText().toString());
        params.put("issue_date", textFzrq.getText().toString());
        params.put("Insurance_commerce", textSyx.getText().toString());

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

    @OnClick({R.id.textFs, R.id.sbtn_chaxun, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewSzy, R.id.viewSfy, R.id.sbtn_tijiaobdw,
            R.id.viewSyx,R.id.viewFzrq,R.id.viewZcrq,R.id.viewXm, R.id.viewCx, R.id.viewDz, R.id.viewCph, R.id.viewFdjh, R.id.viewCjh, R.id.viewNcdq, R.id.viewBxdq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewZcrq:
                if (usercar_query.getR() != 0) {
                    return;
                }
                Calendar czc = Calendar.getInstance();
                DatePickerDialog datePickerDialogzc = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textZcrq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, czc.get(Calendar.YEAR), czc.get(Calendar.MONTH), czc.get(Calendar.DAY_OF_MONTH));
                datePickerDialogzc.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogzc.show();
                break;
            case R.id.viewFzrq:
                if (usercar_query.getR() != 0) {
                    return;
                }
                Calendar cfz = Calendar.getInstance();
                DatePickerDialog datePickerDialogfz = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textFzrq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, cfz.get(Calendar.YEAR), cfz.get(Calendar.MONTH), cfz.get(Calendar.DAY_OF_MONTH));
                datePickerDialogfz.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogfz.show();
                break;
            case R.id.viewSyx:
                if (usercar_query.getR() != 0) {
                    return;
                }
                Calendar csy = Calendar.getInstance();
                DatePickerDialog datePickerDialogsy = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textSyx.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, csy.get(Calendar.YEAR), csy.get(Calendar.MONTH), csy.get(Calendar.DAY_OF_MONTH));
                datePickerDialogsy.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialogsy.show();
                break;
            case R.id.viewXm:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringXm = "";
                if (xinShiZZM != null) {
                    stringXm = xinShiZZM.getData().getName();
                }
                final EditDialogText editDialogXm = new EditDialogText(context, "输入你的姓名", stringXm, "确认", "取消");
                editDialogXm.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogXm.dismiss();
                        textXm.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogXm.dismiss();
                    }
                });
                editDialogXm.show();
                break;
            case R.id.viewCx:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringCx = "";
                if (xinShiZZM != null) {
                    stringCx = xinShiZZM.getData().getCar_name();
                }
                final EditDialogText editDialogCx = new EditDialogText(context, "输入车型", stringCx, "确认", "取消");
                editDialogCx.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCx.dismiss();
                        textCx.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCx.dismiss();
                    }
                });
                editDialogCx.show();
                break;
            case R.id.viewDz:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringDz = "";
                if (xinShiZZM != null) {
                    stringDz = xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogDz = new EditDialogText(context, "输入地址", stringDz, "确认", "取消");
                editDialogDz.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogDz.dismiss();
                        textDz.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogDz.dismiss();
                    }
                });
                editDialogDz.show();
                break;
            case R.id.viewCph:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringCph = "";
                if (xinShiZZM != null) {
                    stringCph = xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogCph = new EditDialogText(context, "输入车牌号", stringCph, "确认", "取消");
                editDialogCph.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCph.dismiss();
                        textCph.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCph.dismiss();
                    }
                });
                editDialogCph.show();
                break;
            case R.id.viewFdjh:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringFdjh = "";
                if (xinShiZZM != null) {
                    stringFdjh = xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogFdjh = new EditDialogText(context, "输入发动机号", stringFdjh, "确认", "取消");
                editDialogFdjh.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogFdjh.dismiss();
                        textFdjh.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogFdjh.dismiss();
                    }
                });
                editDialogFdjh.show();
                break;
            case R.id.viewCjh:
                if (usercar_query.getR() != 0) {
                    return;
                }
                String stringCjh = "";
                if (xinShiZZM != null) {
                    stringCjh = xinShiZZM.getData().getAddress();
                }
                final EditDialogText editDialogCjh = new EditDialogText(context, "输入车架号", stringCjh, "确认", "取消");
                editDialogCjh.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogCjh.dismiss();
                        textCjh.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCjh.dismiss();
                    }
                });
                editDialogCjh.show();
                break;
            case R.id.viewNcdq:
                if (usercar_query.getR() != 0) {
                    return;
                }
                Calendar c0 = Calendar.getInstance();
                DatePickerDialog datePickerDialog0 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textNsdq.setText(year + "-" + (month + 1));
                    }
                }, c0.get(Calendar.YEAR), c0.get(Calendar.MONTH), c0.get(Calendar.DAY_OF_MONTH));
                datePickerDialog0.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog0.show();
                break;
            case R.id.viewBxdq:
                if (usercar_query.getR() != 0) {
                    return;
                }
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;
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
                datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialog1.show();
                break;
            case R.id.viewXslc:
                final EditDialog editDialog1 = new EditDialog(context, "行驶里程（km）", "", "确认", "取消");
                editDialog1.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog1.dismiss();
                        xslc=intro;
                        textxslc.setText(intro+"km");
                    }

                    @Override
                    public void doCancel() {
                        editDialog1.dismiss();
                    }
                });
                editDialog1.show();
                break;
            case R.id.viewSzy:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            0);

                }else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "face");
                    intent.setClass(getActivity(), SaoMiaoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.viewSfy:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            0);

                }else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "back");
                    intent.setClass(getActivity(), SaoMiaoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.sbtn_tijiaobdw:
                if (dataBean == null) {
                    ToastUtils.showShort("请选择车型信息！");
                    return;
                }
                if (usercar_query.getR() == 0) {
                    if (TextUtils.isEmpty(textGcsj.getText().toString())) {
                        ToastUtils.showShort("请设置购车时间");
                        return;
                    }
                    if (TextUtils.isEmpty(xslc)) {
                        ToastUtils.showShort("请设置行驶里程");
                        return;
                    }
                    if (TextUtils.isEmpty(textXm.getText().toString())) {
                        ToastUtils.showShort("请设置姓名");
                        return;
                    }
                    if (TextUtils.isEmpty(textCx.getText().toString())) {
                        ToastUtils.showShort("请设置车型");
                        return;
                    }
                    if (TextUtils.isEmpty(textDz.getText().toString())) {
                        ToastUtils.showShort("请设置地址");
                        return;
                    }
                    if (TextUtils.isEmpty(textCph.getText().toString())) {
                        ToastUtils.showShort("请设置车牌号");
                        return;
                    }
                    if (TextUtils.isEmpty(textFdjh.getText().toString())) {
                        ToastUtils.showShort("请设置发动机号");
                        return;
                    }
                    if (!RegexUtils.isMatch(Constant.Cjh, textCjh.getText().toString())) {
                        ToastUtils.showShort("请输入正确车架号");
                        return;
                    }
                    if (TextUtils.isEmpty(textZcrq.getText().toString())) {
                        ToastUtils.showShort("请设置注册日期");
                        return;
                    }
                    if (TextUtils.isEmpty(textFzrq.getText().toString())) {
                        ToastUtils.showShort("请设置发证日期");
                        return;
                    }
                    if (TextUtils.isEmpty(textNsdq.getText().toString())) {
                        ToastUtils.showShort("请设置年审到期时间");
                        return;
                    }
                    if (TextUtils.isEmpty(textBxdq.getText().toString())) {
                        ToastUtils.showShort("请设置交强险到期时间");
                        return;
                    }
                    if (TextUtils.isEmpty(textBxdq.getText().toString())) {
                        ToastUtils.showShort("请设置商业险到期时间");
                        return;
                    }
                    if (TextUtils.isEmpty(editYzm.getText().toString())) {
                        ToastUtils.showShort("短信验证码不能为空！");
                        return;
                    }
                    usercar_Add_car(getOkObjectBD1());
                } else if (usercar_query.getR() == 1) {
                    if (TextUtils.isEmpty(editYzm.getText().toString())) {
                        ToastUtils.showShort("短信验证码不能为空！");
                        return;
                    }
                    usercar_Add_car(getOkObjectBD());
                }
                break;
            default:
                break;

        }
    }

}
