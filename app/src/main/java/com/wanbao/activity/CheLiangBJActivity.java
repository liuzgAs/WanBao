package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
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
    @BindView(R.id.textShangChuanTP)
    TextView textShangChuanTP;
    @BindView(R.id.textBaoXianGS)
    TextView textBaoXianGS;
    @BindView(R.id.textShangYeBXGS)
    TextView textShangYeBXGS;
    private String id;
    private Usercar_Getinfo usercar_getinfo;
    private Car_Index.DataBean dataBean;
    ArrayList<String> images = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    private int themeId = R.style.picture_default_style;
    private List<LocalMedia> imageList = new ArrayList<>();
    private RecyclerArrayAdapter<String> adapter;

    private List<Usercar_Getinfo.BaseSafetyBean> base_safety = new ArrayList<>();
    private List<Usercar_Getinfo.BusinessSafetyBean> business_safety = new ArrayList<>();
    private String base_safety_id;
    private String business_safety_id;
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
                usercar_getinfo.getData().setCid(dataBean.getId());
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
                LogUtils.e("userupload", userUpload.toString());
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
                for (int i = 0; i < imageUrls.size(); i++) {
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
                        base_safety.clear();
                        business_safety.clear();
                        base_safety.addAll(usercar_getinfo.getBase_safety());
                        business_safety.addAll(usercar_getinfo.getBusiness_safety());
                        textClxx.setText(usercar_getinfo.getData().getCid_name());
                        textGcsj.setText(usercar_getinfo.getData().getBc_time());
                        editXslc.setText(usercar_getinfo.getData().getKm() + "");
                        textCph.setText(usercar_getinfo.getData().getCar_no());
                        textFdjh.setText(usercar_getinfo.getData().getEngine_show());
                        textCjh.setText(usercar_getinfo.getData().getVin_show());
                        textNsdq.setText(usercar_getinfo.getData().getYear_end());
                        textJqx.setText(usercar_getinfo.getData().getInsurance_end());
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
                        imageUrls.clear();
                        images.clear();
                        for (int i = 0; i < usercar_getinfo.getImgs().size(); i++) {
                            imageUrls.add(usercar_getinfo.getImgs().get(i).getImg_url());
                            images.add(usercar_getinfo.getImgs().get(i).getImg_id());
                        }
                        for (int i = 0; i < base_safety.size(); i++) {
                            if (base_safety.get(i).getAct()==1){
                                textBaoXianGS.setText(base_safety.get(i).getName());
                                base_safety_id=String.valueOf(base_safety.get(i).getId());
                            }
                        }
                        for (int i = 0; i < business_safety.size(); i++) {
                            if (business_safety.get(i).getAct()==1){
                                textShangYeBXGS.setText(business_safety.get(i).getName());
                                business_safety_id=String.valueOf(business_safety.get(i).getId());
                            }
                        }
                        adapter.clear();
                        adapter.addAll(imageUrls);
                        if (usercar_getinfo.getState() == 1) {
                            sbtnTijiaobdw.setText("审核中");
                            editDabh.setEnabled(false);
                            editDz.setEnabled(false);
                            editXslc.setEnabled(false);
                        } else if (usercar_getinfo.getState() == 2) {
                            sbtnTijiaobdw.setText("提交审核");
                            editDabh.setEnabled(true);
                            editDz.setEnabled(true);
                            editXslc.setEnabled(true);
                        } else {
                            sbtnTijiaobdw.setText("提交审核");
                            editDabh.setEnabled(true);
                            editDz.setEnabled(true);
                            editXslc.setEnabled(true);
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

    @OnClick({R.id.viewShangYeBXGS,R.id.viewBaoXianGS,R.id.textShangChuanTP, R.id.imageback, R.id.textClxx, R.id.textGcsj, R.id.textNsdq, R.id.textJqx, R.id.textSyx, R.id.sbtn_tijiaobdw})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewShangYeBXGS:
                ArrayList<String> saveStringsS=new ArrayList<>();
                for (int i = 0; i < business_safety.size(); i++) {
                    saveStringsS.add(business_safety.get(i).getName());
                }
                new MaterialDialog.Builder(context)
                        .title("选择商业保险公司")
                        .items(saveStringsS)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textShangYeBXGS.setText(business_safety.get(position).getName());
                                business_safety_id=String.valueOf(business_safety.get(position).getId());
                            }
                        })
                        .show();
                break;
            case R.id.viewBaoXianGS:
                ArrayList<String> saveStrings=new ArrayList<>();
                for (int i = 0; i < base_safety.size(); i++) {
                    saveStrings.add(base_safety.get(i).getName());
                }
                new MaterialDialog.Builder(context)
                        .title("选择保险公司")
                        .items(saveStrings)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textBaoXianGS.setText(base_safety.get(position).getName());
                                base_safety_id=String.valueOf(base_safety.get(position).getId());
                            }
                        })
                        .show();
                break;
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
            case R.id.sbtn_tijiaobdw:
                if (usercar_getinfo.getState() == 1) {
                    ToastUtils.showShort("审核中，不可修改");
                    return;
                }
                if (TextUtils.isEmpty(textClxx.getText().toString())) {
                    ToastUtils.showShort("请设置正确的车辆信息！");
                    return;
                }
                if (TextUtils.isEmpty(textGcsj.getText().toString())) {
                    ToastUtils.showShort("请设置正确的购车时间！");
                    return;
                }
                if (TextUtils.isEmpty(editXslc.getText().toString())) {
                    ToastUtils.showShort("请设置正确的行驶里程！");
                    return;
                }
                if (TextUtils.isEmpty(editDz.getText().toString())) {
                    ToastUtils.showShort("请设置正确的地址！");
                    return;
                }
                if (TextUtils.isEmpty(textNsdq.getText().toString())) {
                    ToastUtils.showShort("请设置正确的年审到期时间！");
                    return;
                }
                if (TextUtils.isEmpty(textJqx.getText().toString())) {
                    ToastUtils.showShort("请设置正确的交强险到期时间！");
                    return;
                }
                if (TextUtils.isEmpty(textSyx.getText().toString())) {
                    ToastUtils.showShort("请设置正确的商业险到期时间！");
                    return;
                }
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
        params.put("km", editXslc.getText().toString());
        params.put("phone", usercar_getinfo.getData().getPhone());
        params.put("year_end", usercar_getinfo.getData().getYear_end());
        params.put("insurance_end", usercar_getinfo.getData().getInsurance_end());
        params.put("Insurance_commerce", usercar_getinfo.getData().getInsurance_commerce());
        params.put("address", editDz.getText().toString());
        params.put("name", usercar_getinfo.getData().getName() + "");
        params.put("id", usercar_getinfo.getData().getId() + "");
        params.put("file_no", editDabh.getText().toString());
        params.put("appproved_passenger_capacity", textHdzrs.getText().toString());
        params.put("gross_mass", textZzl.getText().toString());
        params.put("overall_dimension", textWkcc.getText().toString());
        params.put("unladen_mass", textZbzl.getText().toString());
        params.put("imgs", images.toString().replace("[", "").replace("]", ""));
        params.put("base_safety", base_safety_id);
        params.put("business_safety", business_safety_id);
        return new OkObject(params, url);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

}
