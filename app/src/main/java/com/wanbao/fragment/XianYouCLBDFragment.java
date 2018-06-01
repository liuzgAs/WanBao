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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.activity.XuanZheCSActivity;
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
    @BindView(R.id.betterSpinner)
    BetterSpinner betterSpinner;
    @BindView(R.id.editChePai)
    EditText editChePai;
    @BindView(R.id.sbtn_chaxun)
    StateButton sbtnChaxun;
    Unbinder unbinder;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.textCxxx)
    TextView textCxxx;
    @BindView(R.id.textGcsj)
    TextView textGcsj;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.textCjh)
    TextView textCjh;
    @BindView(R.id.textCs)
    TextView textCs;
    @BindView(R.id.textsj)
    TextView textsj;
    @BindView(R.id.editYzm)
    EditText editYzm;
    @BindView(R.id.sbtnYzm)
    StateButton sbtnYzm;
    @BindView(R.id.sbtn_tijiaobd)
    StateButton sbtnTijiaobd;
    @BindView(R.id.textChePai)
    TextView textChePai;
    @BindView(R.id.editFdjh)
    EditText editFdjh;
    @BindView(R.id.editCjh)
    EditText editCjh;
    @BindView(R.id.editSjhw)
    EditText editSjhw;
    @BindView(R.id.editYzmw)
    EditText editYzmw;
    @BindView(R.id.sbtnYzmw)
    StateButton sbtnYzmw;
    @BindView(R.id.sbtn_tijiaobdw)
    StateButton sbtnTijiaobdw;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.viewChaxun)
    LinearLayout viewChaxun;
    @BindView(R.id.textCp)
    TextView textCp;
    @BindView(R.id.textxslc)
    TextView textxslc;
    @BindView(R.id.textNsdq)
    TextView textNsdq;
    @BindView(R.id.textBxdq)
    TextView textBxdq;
    @BindView(R.id.textBydq)
    TextView textBydq;
    @BindView(R.id.textClxx)
    TextView textClxx;
    @BindView(R.id.viewCxxx)
    LinearLayout viewCxxx;
    @BindView(R.id.textGcsjw)
    TextView textGcsjw;
    @BindView(R.id.viewGcsj)
    LinearLayout viewGcsj;
    @BindView(R.id.textxslcw)
    TextView textxslcw;
    @BindView(R.id.viewXslc)
    LinearLayout viewXslc;
    @BindView(R.id.textCsw)
    TextView textCsw;
    @BindView(R.id.viewCs)
    LinearLayout viewCs;
    @BindView(R.id.textNsdqw)
    TextView textNsdqw;
    @BindView(R.id.viewNsdq)
    LinearLayout viewNsdq;
    @BindView(R.id.textBxdqw)
    TextView textBxdqw;
    @BindView(R.id.viewBxdq)
    LinearLayout viewBxdq;
    @BindView(R.id.textBydqw)
    TextView textBydqw;
    @BindView(R.id.viewBydq)
    LinearLayout viewBydq;
    @BindView(R.id.viewXslcw)
    LinearLayout viewXslcw;
    @BindView(R.id.viewNsdqw)
    LinearLayout viewNsdqw;
    @BindView(R.id.viewBxdqw)
    LinearLayout viewBxdqw;
    @BindView(R.id.viewBydqw)
    LinearLayout viewBydqw;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.item_sheng, COUNTRIES);
        betterSpinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
    }


    @OnClick({R.id.viewXslcw, R.id.viewNsdqw, R.id.viewBxdqw, R.id.viewBydqw,R.id.sbtn_chaxun, R.id.sbtnYzm, R.id.sbtn_tijiaobd, R.id.sbtnYzmw, R.id.sbtn_tijiaobdw, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewCs, R.id.viewNsdq, R.id.viewBxdq, R.id.viewBydq})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewXslcw:
                final EditDialog editDialog1 = new EditDialog(context, "行驶里程（km）", "0", "确认", "取消");
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
            case R.id.viewNsdqw:
                Calendar c00 = Calendar.getInstance();
                DatePickerDialog datePickerDialog00 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textNsdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c00.get(Calendar.YEAR), c00.get(Calendar.MONTH), c00.get(Calendar.DAY_OF_MONTH));
                datePickerDialog00.show();
                break;
            case R.id.viewBxdqw:
                Calendar c12 = Calendar.getInstance();
                DatePickerDialog datePickerDialog12 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c12.get(Calendar.YEAR), c12.get(Calendar.MONTH), c12.get(Calendar.DAY_OF_MONTH));
                datePickerDialog12.show();
                break;
            case R.id.viewBydqw:
                Calendar c13 = Calendar.getInstance();
                DatePickerDialog datePickerDialog13 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBydq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c13.get(Calendar.YEAR), c13.get(Calendar.MONTH), c13.get(Calendar.DAY_OF_MONTH));
                datePickerDialog13.show();
                break;
            case R.id.viewCxxx:
                intent.setClass(getActivity(), XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGcsj:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textGcsjw.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog1.show();
                break;
            case R.id.viewXslc:
                final EditDialog editDialog = new EditDialog(context, "行驶里程（km）", "0", "确认", "取消");
                editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog.dismiss();
                        textxslcw.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialog.dismiss();
                    }
                });
                editDialog.show();
                break;
            case R.id.viewCs:
                intent.setClass(getActivity(), XuanZheCSActivity.class);
                startActivity(intent);
                break;
            case R.id.viewNsdq:
                Calendar c0 = Calendar.getInstance();
                DatePickerDialog datePickerDialog0 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textNsdqw.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c0.get(Calendar.YEAR), c0.get(Calendar.MONTH), c0.get(Calendar.DAY_OF_MONTH));
                datePickerDialog0.show();
                break;
            case R.id.viewBxdq:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdqw.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.viewBydq:
                Calendar c11 = Calendar.getInstance();
                DatePickerDialog datePickerDialog11 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBydqw.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c11.get(Calendar.YEAR), c11.get(Calendar.MONTH), c11.get(Calendar.DAY_OF_MONTH));
                datePickerDialog11.show();
                break;
            case R.id.sbtnYzm:
                yanZM(textsj.getText().toString());
                break;
            case R.id.sbtn_tijiaobd:
                usercar_Add_car(getOkObjectBD());
                break;
            case R.id.sbtnYzmw:
                yanZM(editSjhw.getText().toString());
                break;
            case R.id.sbtn_tijiaobdw:
                usercar_Add_car(getOkObjectBD1());
                break;
            case R.id.sbtn_chaxun:
                if (TextUtils.isEmpty(editChePai.getText())) {
                    ToastUtils.showShort("请输入车牌号！");
                    return;
                }
                usercar_Query();
                break;
            default:
                break;
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
                textCsw.setText(listBean.getName());
            }
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
                        if (usercar_query.getR() == 0) {
                            textState.setText("系统未查到该车牌车辆，请自行录入");
                            viewChaxun.setVisibility(View.GONE);
                            viewSwitcher.setVisibility(View.VISIBLE);
                            viewSwitcher.setDisplayedChild(1);
                            textChePai.setText(betterSpinner.getText().toString() + editChePai.getText().toString());
                        } else {
                            textState.setText("查询到以下车辆信息");
                            viewChaxun.setVisibility(View.GONE);
                            viewSwitcher.setVisibility(View.VISIBLE);
                            viewSwitcher.setDisplayedChild(0);
                            textCp.setText(usercar_query.getData().getCar_no());
                            textCxxx.setText(usercar_query.getData().getCar_name());
                            textGcsj.setText(usercar_query.getData().getBc_time());
                            textFdjh.setText(usercar_query.getData().getEngine_show());
                            textCjh.setText(usercar_query.getData().getVin_show());
                            textCs.setText(usercar_query.getData().getCity());
                            textsj.setText(usercar_query.getData().getPhone_show());
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
        params.put("city", usercar_query.getData().getCity());
        params.put("phone", usercar_query.getData().getPhone());
        params.put("year_end", textNsdq.getText().toString());
        params.put("insurance_end", textBxdq.getText().toString());
        params.put("maintain_end", textBydq.getText().toString());
        params.put("code", editYzm.getText().toString());
        return new OkObject(params, url);
    }

    private OkObject getOkObjectBD1() {
        String url = Constant.HOST + Constant.Url.Usercar_Add_car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_name", textClxx.getText().toString());
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", textGcsjw.getText().toString());
        params.put("engine", editFdjh.getText().toString());
        params.put("car_no", textChePai.getText().toString());
        params.put("vin", editCjh.getText().toString());
        params.put("km", textxslcw.getText().toString());
        params.put("city", textCsw.getText().toString());
        params.put("phone", editSjhw.getText().toString());
        params.put("year_end", textNsdqw.getText().toString());
        params.put("insurance_end", textBxdqw.getText().toString());
        params.put("maintain_end", textBydqw.getText().toString());
        params.put("code", editYzmw.getText().toString());
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
            if (usercar_query.getR()==0){
                sbtnYzm.setEnabled(false);
                sbtnYzm.setText(millisUntilFinished / 1000 + "秒后重发");
            }else {
                sbtnYzmw.setEnabled(false);
                sbtnYzmw.setText(millisUntilFinished / 1000 + "秒后重发");
            }
        }

        @Override
        public void onFinish() {
            if (usercar_query.getR()==0){
                sbtnYzm.setEnabled(true);
                sbtnYzm.setText("重新发送");
            }else {
                sbtnYzmw.setEnabled(true);
                sbtnYzmw.setText("重新发送");
            }
        }
    };

}
