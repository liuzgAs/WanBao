package com.wanbao.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
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
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.Usercar_Getinfo;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.textDabh)
    TextView textDabh;
    @BindView(R.id.viewDabh)
    LinearLayout viewDabh;
    @BindView(R.id.textHdzrs)
    TextView textHdzrs;
    @BindView(R.id.viewHdzrs)
    LinearLayout viewHdzrs;
    @BindView(R.id.textZzl)
    TextView textZzl;
    @BindView(R.id.viewZzl)
    LinearLayout viewZzl;
    @BindView(R.id.textWkcc)
    TextView textWkcc;
    @BindView(R.id.viewWkcc)
    LinearLayout viewWkcc;
    @BindView(R.id.textZbzl)
    TextView textZbzl;
    @BindView(R.id.viewZbzl)
    LinearLayout viewZbzl;
    @BindView(R.id.textWgtp)
    TextView textWgtp;
    @BindView(R.id.viewWgtp)
    LinearLayout viewWgtp;
    private String id;
    private Usercar_Getinfo usercar_getinfo;
    private XinShiZZM xinShiZZM;
    private XingShiZFY xingShiZFY;
    private Car_Index.DataBean dataBean;
    private City_List.CityBean.ListBean listBean;
    ArrayList<String> images = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    private int themeId = R.style.picture_default_style;
    private List<LocalMedia> imageList = new ArrayList<>();
    private String vinCode;
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
            textXm.setText(xinShiZZM.getData().getName());
            textCx.setText(xinShiZZM.getData().getCar_name());
            textDz.setText(xinShiZZM.getData().getAddress());
            textZcrq.setText(xinShiZZM.getData().getRegister_date());
            textGcsj.setText(xinShiZZM.getData().getRegister_date());
            textFzrq.setText(xinShiZZM.getData().getIssue_date());
            usercar_getinfo.getData().setCar_no(xinShiZZM.getData().getCar_no());
            usercar_getinfo.getData().setEngine(xinShiZZM.getData().getEngine());
            usercar_getinfo.getData().setVin(xinShiZZM.getData().getVin());

        }
        if (BaseEvent.XingShiZFY.equals(event.getAction())) {
            xingShiZFY = (XingShiZFY) event.getData();
            textNsdq.setText(xingShiZFY.getData().getYear_end());
            textBxdq.setText(xingShiZFY.getData().getInsurance_end());

            textDabh.setText(xingShiZFY.getData().getFile_no());
            textHdzrs.setText(xingShiZFY.getData().getAppproved_passenger_capacity());
            textZzl.setText(xingShiZFY.getData().getGross_mass());
            textWkcc.setText(xingShiZFY.getData().getOverall_dimension());
            textZbzl.setText(xingShiZFY.getData().getUnladen_mass());
            usercar_getinfo.getData().setYear_end(xingShiZFY.getData().getYear_end());
            usercar_getinfo.getData().setInsurance_end(xingShiZFY.getData().getInsurance_end());
        }
        if (BaseEvent.CarImage.equals(event.getAction())) {
            ArrayList<Respond_AppImgAdd> userUpload = (ArrayList<Respond_AppImgAdd>) event.getData();
            if (userUpload != null) {
                imageUrls.clear();
                images.clear();
                textWgtp.setText("已上传" + userUpload.size() + "张图片");
                for (int i = 0; i < userUpload.size(); i++) {
                    images.add(userUpload.get(i).getImgId());
                    imageUrls.add(userUpload.get(i).getImg());
                }
            }
        }
    }

    @OnClick({R.id.viewWgtp,R.id.viewDabh, R.id.viewHdzrs, R.id.viewZzl, R.id.viewWkcc, R.id.viewZbzl, R.id.imageback, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewSzy, R.id.viewSfy, R.id.textFs, R.id.sbtn_tijiaobdw,
            R.id.viewSyx, R.id.viewFzrq, R.id.viewZcrq, R.id.viewXm, R.id.viewCx, R.id.viewDz, R.id.viewCph, R.id.viewFdjh, R.id.viewCjh, R.id.viewNcdq, R.id.viewBxdq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewWgtp:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                if (imageUrls.size() == 0) {
                    intent = new Intent(context, CheShenTpActivity.class);
                    startActivity(intent);
                    return;
                }
                new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("上传车身外观图片")
                        .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                imageList.clear();
                                for (int i = 0; i < imageUrls.size(); i++) {
                                    LocalMedia localMedia = new LocalMedia();
                                    localMedia.setPath(imageUrls.get(i));
                                    imageList.add(localMedia);
                                }
                                PictureSelector.create(XiuGaiCheLiangActivity.this).themeStyle(themeId).openExternalPreview(0, imageList);
                            }
                        })
                        .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(context, CheShenTpActivity.class);
                                startActivity(intent);
                            }
                        }).show();
                break;
            case R.id.viewDabh:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialogText editDialogDanb = new EditDialogText(context, "输入档案编号", textDabh.getText().toString(), "确认", "取消");
                editDialogDanb.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogDanb.dismiss();
                        textDabh.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialogDanb.dismiss();
                    }
                });
                editDialogDanb.show();
                break;
            case R.id.viewHdzrs:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialog editDialogHdzrs = new EditDialog(context, "输入核定载人数（人）", textHdzrs.getText().toString(), "确认", "取消");
                editDialogHdzrs.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogHdzrs.dismiss();
                        textHdzrs.setText(intro + "人");
                    }

                    @Override
                    public void doCancel() {
                        editDialogHdzrs.dismiss();
                    }
                });
                editDialogHdzrs.show();
                break;
            case R.id.viewZzl:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialog editDialogZzl = new EditDialog(context, "输入总质量（kg）", textZzl.getText().toString(), "确认", "取消");
                editDialogZzl.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogZzl.dismiss();
                        textZzl.setText(intro + "kg");
                    }

                    @Override
                    public void doCancel() {
                        editDialogZzl.dismiss();
                    }
                });
                editDialogZzl.show();
                break;
            case R.id.viewWkcc:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialogText editDialogWkcc = new EditDialogText(context, "输入外廓尺寸（mm）", textWkcc.getText().toString(), "确认", "取消");
                editDialogWkcc.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogWkcc.dismiss();
                        textWkcc.setText(intro + "mm");
                    }

                    @Override
                    public void doCancel() {
                        editDialogWkcc.dismiss();
                    }
                });
                editDialogWkcc.show();
                break;
            case R.id.viewZbzl:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialog editDialogZbzl = new EditDialog(context, "输入整备质量（kg）", textZbzl.getText().toString(), "确认", "取消");
                editDialogZbzl.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialogZbzl.dismiss();
                        textZbzl.setText(intro + "kg");
                    }

                    @Override
                    public void doCancel() {
                        editDialogZbzl.dismiss();
                    }
                });
                editDialogZbzl.show();
                break;
            case R.id.viewZcrq:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar czc = Calendar.getInstance();
                DatePickerDialog datePickerDialogzc = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textZcrq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setRegister_date(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, czc.get(Calendar.YEAR), czc.get(Calendar.MONTH), czc.get(Calendar.DAY_OF_MONTH));
                datePickerDialogzc.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogzc.show();
                break;
            case R.id.viewFzrq:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar cfz = Calendar.getInstance();
                DatePickerDialog datePickerDialogfz = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textFzrq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setIssue_date(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, cfz.get(Calendar.YEAR), cfz.get(Calendar.MONTH), cfz.get(Calendar.DAY_OF_MONTH));
                datePickerDialogfz.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialogfz.show();
                break;
            case R.id.viewSyx:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar csy = Calendar.getInstance();
                DatePickerDialog datePickerDialogsy = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textSyx.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setInsurance_commerce(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, csy.get(Calendar.YEAR), csy.get(Calendar.MONTH), csy.get(Calendar.DAY_OF_MONTH));
                datePickerDialogsy.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialogsy.show();
                break;
            case R.id.viewXm:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
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
                        vinCode=intro;
                        textCjh.setText(vinCode);
                        usercar_getinfo.getData().setVin(vinCode);
                    }

                    @Override
                    public void doCancel() {
                        editDialogCjh.dismiss();
                    }
                });
                editDialogCjh.show();
                break;
            case R.id.viewNcdq:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar c0 = Calendar.getInstance();
                DatePickerDialog datePickerDialog0 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textNsdq.setText(year + "-" + (month + 1));
                        usercar_getinfo.getData().setYear_end(year + "-" + (month + 1));
                    }
                }, c0.get(Calendar.YEAR), c0.get(Calendar.MONTH), c0.get(Calendar.DAY_OF_MONTH));
                datePickerDialog0.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog0.show();
                break;
            case R.id.viewBxdq:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setInsurance_end(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewCxxx:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                intent = new Intent();
                intent.setClass(context, XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGcsj:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textGcsj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setBc_time(year + "-" + (month + 1) + "-" + dayOfMonth);

                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialog1.show();
                break;
            case R.id.viewXslc:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                final EditDialog editDialog1 = new EditDialog(context, "行驶里程（km）", "", "确认", "取消");
                editDialog1.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog1.dismiss();
                        textxslc.setText(intro);
                        usercar_getinfo.getData().setKm(String.valueOf(intro));
                    }

                    @Override
                    public void doCancel() {
                        editDialog1.dismiss();
                    }
                });
                editDialog1.show();
                break;
            case R.id.viewSzy:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "face");
                    intent.setClass(context, CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.viewSfy:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "back");
                    intent.setClass(context, CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.textFs:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                yanZM(usercar_getinfo.getData().getPhone());
                break;
            case R.id.sbtn_tijiaobdw:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                if (TextUtils.isEmpty(textGcsj.getText().toString())) {
                    ToastUtils.showShort("请设置购车时间");
                    return;
                }
                if (TextUtils.isEmpty(textxslc.getText().toString())) {
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
                if (TextUtils.isEmpty(textZcrq.getText().toString())) {
                    ToastUtils.showShort("请设置注册日期");
                    return;
                }
                if (TextUtils.isEmpty(textFzrq.getText().toString())) {
                    ToastUtils.showShort("请设置发证日期");
                    return;
                }
                if (!RegexUtils.isMatch(Constant.Cjh, vinCode)) {
                    ToastUtils.showShort("请输入正确车架号");
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
                if (TextUtils.isEmpty(textSyx.getText().toString())) {
                    ToastUtils.showShort("请设置商业险到期时间");
                    return;
                }
                if (TextUtils.isEmpty(editYzm.getText().toString())) {
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
                        vinCode=usercar_getinfo.getData().getVin();
                        textNsdq.setText(usercar_getinfo.getData().getYear_end());
                        textBxdq.setText(usercar_getinfo.getData().getInsurance_end());
                        textSjhm.setText(usercar_getinfo.getData().getPhone_show());
                        textXm.setText(usercar_getinfo.getData().getName());
                        textCx.setText(usercar_getinfo.getData().getCar_name());
                        textDz.setText(usercar_getinfo.getData().getAddress());
                        textZcrq.setText(usercar_getinfo.getData().getRegister_date());
                        textFzrq.setText(usercar_getinfo.getData().getIssue_date());
                        textSyx.setText(usercar_getinfo.getData().getInsurance_commerce());

                        textDabh.setText(usercar_getinfo.getData().getFile_no());
                        textHdzrs.setText(usercar_getinfo.getData().getAppproved_passenger_capacity());
                        textZzl.setText(usercar_getinfo.getData().getGross_mass());
                        textWkcc.setText(usercar_getinfo.getData().getOverall_dimension());
                        textZbzl.setText(usercar_getinfo.getData().getUnladen_mass());
                        if (usercar_getinfo.getImgs().size()>0){
//                            imageUrls.addAll(usercar_getinfo.getImgs());
                            textWgtp.setText("已上传" +usercar_getinfo.getImgs().size() + "张图片");
                        }
                        if (usercar_getinfo.getState()==1){
                            sbtnTijiaobdw.setText("审核中");
                        }else if (usercar_getinfo.getState()==2){
                            sbtnTijiaobdw.setText("提交审核");
                        }
                    } else {
                        MyDialog.dialogFinish(XiuGaiCheLiangActivity.this, usercar_getinfo.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(XiuGaiCheLiangActivity.this, "数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(XiuGaiCheLiangActivity.this, "网络异常");
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

        params.put("file_no", textDabh.getText().toString());
        params.put("appproved_passenger_capacity", textHdzrs.getText().toString());
        params.put("gross_mass", textZzl.getText().toString());
        params.put("overall_dimension", textWkcc.getText().toString());
        params.put("unladen_mass", textZbzl.getText().toString());
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
