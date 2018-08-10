package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.LastInputEditText;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.Usercar_Getinfo;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.CarImageViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class CheLiangBJActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textCph)
    TextView textCph;
    @BindView(R.id.textClxx)
    TextView textClxx;
    @BindView(R.id.textGcsj)
    TextView textGcsj;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.textCjh)
    TextView textCjh;
    @BindView(R.id.textXm)
    TextView textXm;
    @BindView(R.id.textCx)
    TextView textCx;
    @BindView(R.id.recyclerView)
    MyEasyRecyclerView recyclerView;
    @BindView(R.id.sbtn_tijiaobdw)
    Button sbtnTijiaobdw;
    @BindView(R.id.textZcrq)
    TextView textZcrq;
    @BindView(R.id.textFzrq)
    TextView textFzrq;
    @BindView(R.id.textHdzrs)
    TextView textHdzrs;
    @BindView(R.id.textZzl)
    TextView textZzl;
    @BindView(R.id.textWkcc)
    TextView textWkcc;
    @BindView(R.id.textZbzl)
    TextView textZbzl;
    @BindView(R.id.editXslc)
    LastInputEditText editXslc;
    @BindView(R.id.editDz)
    LastInputEditText editDz;
    @BindView(R.id.textNsdq)
    TextView textNsdq;
    @BindView(R.id.textJqx)
    TextView textJqx;
    @BindView(R.id.textSyx)
    TextView textSyx;
    @BindView(R.id.editDabh)
    LastInputEditText editDabh;
    @BindView(R.id.textSjhm)
    TextView textSjhm;
    @BindView(R.id.editYzm)
    EditText editYzm;
    @BindView(R.id.textFs)
    TextView textFs;
    @BindView(R.id.textShangChuanTP)
    TextView textShangChuanTP;
    private String id;
    private Usercar_Getinfo usercar_getinfo;
    private Car_Index.DataBean dataBean;
    ArrayList<String> images = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    private int themeId = R.style.picture_default_style;
    private List<LocalMedia> imageList = new ArrayList<>();
    private RecyclerArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_bj);
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
        initRecycler();
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
        if (BaseEvent.CarImage.equals(event.getAction())) {
            ArrayList<Respond_AppImgAdd> userUpload = (ArrayList<Respond_AppImgAdd>) event.getData();
            if (userUpload != null) {
                imageUrls.clear();
                images.clear();
                for (int i = 0; i < userUpload.size(); i++) {
                    images.add(userUpload.get(i).getImgId());
                    imageUrls.add(userUpload.get(i).getImg());
                }
                LogUtils.e("userupload",userUpload.toString());
                adapter.clear();
                adapter.addAll(imageUrls);
                adapter.notifyDataSetChanged();
            }
        }
    }
    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<String>(context) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_car_img;
                return new CarImageViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, context));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);
                recyclerView.setScroll(true);
            }
        });
        recyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
            @Override
            public void daoDiLe() {
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                imageList.clear();
                for (int i=0;i<imageUrls.size();i++){
                    LocalMedia media = new LocalMedia();
                    media.setPath(imageUrls.get(i));
                    imageList.add(media);
                }
                PictureSelector.create(CheLiangBJActivity.this).themeStyle(themeId).openExternalPreview(position, imageList);
            }
        });
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
                        editXslc.setText(usercar_getinfo.getData().getKm() + "");
                        textCph.setText(usercar_getinfo.getData().getCar_no());
                        textFdjh.setText(usercar_getinfo.getData().getEngine_show());
                        textCjh.setText(usercar_getinfo.getData().getVin_show());
                        textNsdq.setText(usercar_getinfo.getData().getYear_end());
                        textJqx.setText(usercar_getinfo.getData().getInsurance_end());
                        textSjhm.setText(usercar_getinfo.getData().getPhone_show());
                        textXm.setText(usercar_getinfo.getData().getName());
                        textCx.setText(usercar_getinfo.getData().getCar_name());
                        editDz.setText(usercar_getinfo.getData().getAddress());
                        textZcrq.setText(usercar_getinfo.getData().getRegister_date());
                        textFzrq.setText(usercar_getinfo.getData().getIssue_date());
                        textSyx.setText(usercar_getinfo.getData().getInsurance_commerce());

                        editDabh.setText(usercar_getinfo.getData().getFile_no());
                        textHdzrs.setText(usercar_getinfo.getData().getAppproved_passenger_capacity());
                        textZzl.setText(usercar_getinfo.getData().getGross_mass());
                        textWkcc.setText(usercar_getinfo.getData().getOverall_dimension());
                        textZbzl.setText(usercar_getinfo.getData().getUnladen_mass());
                        adapter.clear();
                        adapter.addAll(usercar_getinfo.getImgs());
                        if (usercar_getinfo.getImgs().size() > 0) {
                            imageUrls.addAll(usercar_getinfo.getImgs());
                        }
                        if (usercar_getinfo.getState() == 1) {
                            sbtnTijiaobdw.setText("审核中");
                        } else if (usercar_getinfo.getState() == 2) {
                            sbtnTijiaobdw.setText("提交审核");
                        }
                    } else {
                        MyDialog.dialogFinish(CheLiangBJActivity.this, usercar_getinfo.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(CheLiangBJActivity.this, "数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(CheLiangBJActivity.this, "网络异常");
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

    @OnClick({R.id.textShangChuanTP, R.id.imageback, R.id.textClxx, R.id.textGcsj, R.id.textNsdq, R.id.textJqx, R.id.textSyx, R.id.textFs, R.id.sbtn_tijiaobdw})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.textShangChuanTP:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                intent = new Intent(context, CheShenTpActivity.class);
                startActivity(intent);
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.textClxx:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                intent = new Intent();
                intent.setClass(context, XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.textGcsj:
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
            case R.id.textNsdq:
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
            case R.id.textJqx:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textJqx.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        usercar_getinfo.getData().setInsurance_end(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;
            case R.id.textSyx:
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
            case R.id.textFs:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                yanZM(usercar_getinfo.getData().getPhone());
                break;
            case R.id.sbtn_tijiaobdw:
                usercar_Add_car();
                break;
            default:
                break;
        }
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

        params.put("file_no", editDabh.getText().toString());
        params.put("appproved_passenger_capacity", textHdzrs.getText().toString());
        params.put("gross_mass", textZzl.getText().toString());
        params.put("overall_dimension", textWkcc.getText().toString());
        params.put("unladen_mass", textZbzl.getText().toString());
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
