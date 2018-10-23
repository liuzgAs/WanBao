package com.wanbao.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.CameraActivity;
import com.wanbao.activity.CheShenTpActivity;
import com.wanbao.activity.DrivingLicenseActivity;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.adapter.GridImage1Adapter;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.A2bigA;
import com.wanbao.base.util.FullyGridLayoutManager;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.base.view.EditDialogText;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.City_List;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Login_RegSms;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.Usercar_Query;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.imageZy)
    ImageView imageZy;
    @BindView(R.id.imageFy)
    ImageView imageFy;
    @BindView(R.id.imageZM)
    ImageView imageZM;
    @BindView(R.id.imageBM)
    ImageView imageBM;
    @BindView(R.id.recycler)
    RecyclerView recycler;
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
    ArrayList<String> images = new ArrayList<>();
    List<String> imageUrls = new ArrayList<>();
    private int themeId = R.style.picture_default_style;
    private List<LocalMedia> imageList = new ArrayList<>();
    private String img_id;
    private String cid;
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
        textFdjh.setTransformationMethod(new A2bigA());
        textCjh.setTransformationMethod(new A2bigA());
        return view;
    }
    private GridImage1Adapter adapter;
    @Override
    public void fetchData() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
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
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.item_sheng, COUNTRIES);
        betterSpinner.setAdapter(adapter1);

        adapter = new GridImage1Adapter(getActivity(), onAddPicClickListener);
        adapter.setList(selectList);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImage1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                PictureSelector.create(XianYouCLBDFragment.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
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

    private ArrayList<String> Chushi = new ArrayList<>();

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.INDEX.equals(event.getAction())){
            int index=(int)event.getData();
            Chushi.remove(index);
        }
        if (BaseEvent.Choose_CarX.equals(event.getAction())) {
            dataBean = (Car_Index.DataBean) event.getData();
            if (dataBean != null) {
                textClxx.setText(dataBean.getTitle());
                cid=dataBean.getId()+"";
            }
        }
        if (BaseEvent.Choose_CS.equals(event.getAction())) {
            listBean = (City_List.CityBean.ListBean) event.getData();
            if (listBean != null) {
            }
        }
        if (BaseEvent.ImageZJ.equals(event.getAction())) {
            String imageString=(String) event.getData();
            if (imageType==0){
                GlideApp.with(getContext())
                        .asBitmap()
                        .load(imageString)
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageZM);
            }else {
                GlideApp.with(getContext())
                        .asBitmap()
                        .load(imageString)
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageBM);
            }
        }
        if (BaseEvent.XinShiZZM.equals(event.getAction())) {
            xinShiZZM = (XinShiZZM) event.getData();
            img_id = xinShiZZM.getImg_id();
            imageZy.setVisibility(View.VISIBLE);
            cid=xinShiZZM.getData().getCid();
            textClxx.setText(xinShiZZM.getData().getCid_name());
            textCph.setText(xinShiZZM.getData().getCar_no());
            textFdjh.setText(xinShiZZM.getData().getEngine());
            textCjh.setText(xinShiZZM.getData().getVin());
            textXm.setText(xinShiZZM.getData().getName());
            textCx.setText(xinShiZZM.getData().getCar_name());
            textDz.setText(xinShiZZM.getData().getAddress());
            textZcrq.setText(xinShiZZM.getData().getRegister_date());
            textGcsj.setText(xinShiZZM.getData().getRegister_date());
            textFzrq.setText(xinShiZZM.getData().getIssue_date());
            face_img = xinShiZZM.getImg_id();
        }
        if (BaseEvent.XingShiZFY.equals(event.getAction())) {
            xingShiZFY = (XingShiZFY) event.getData();
            imageFy.setVisibility(View.VISIBLE);
            textNsdq.setText(xingShiZFY.getData().getYear_end());
            textBxdq.setText(xingShiZFY.getData().getInsurance_end());
            textDabh.setText(xingShiZFY.getData().getFile_no());
            textHdzrs.setText(xingShiZFY.getData().getAppproved_passenger_capacity());
            textZzl.setText(xingShiZFY.getData().getGross_mass());
            textWkcc.setText(xingShiZFY.getData().getOverall_dimension());
            textZbzl.setText(xingShiZFY.getData().getUnladen_mass());
            back_img = xingShiZFY.getImg_id();
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
                            textCph.setText(betterSpinner.getText().toString() + editChePai.getText().toString());
                            textSjhm.setText(usercar_query.getData().getPhone_show());
                        } else {
                            imageCxxx.setVisibility(View.VISIBLE);
                            imageGcsj.setVisibility(View.VISIBLE);
                            imagexslc.setVisibility(View.VISIBLE);
                            viewSzy.setVisibility(View.VISIBLE);
                            viewSfy.setVisibility(View.VISIBLE);
                            textState.setText("查询到以下车辆信息");
                            textClxx.setText(usercar_query.getData().getCid_name());
                            textCx.setText(usercar_query.getData().getCar_name());
                            textGcsj.setText(usercar_query.getData().getBc_time());
                            textxslc.setText(usercar_query.getData().getKm() + "");
                            textCph.setText(usercar_query.getData().getCar_no());
                            textXm.setText(usercar_query.getData().getName());
                            textDz.setText(usercar_query.getData().getAddress());
                            textFdjh.setText(usercar_query.getData().getEngine_show());
                            textCjh.setText(usercar_query.getData().getVin_show());
//                            textNsdq.setText(usercar_query.getData());
                            textSjhm.setText(usercar_query.getData().getPhone_show());
                            textZcrq.setText(usercar_query.getData().getRegister_date());
                            textFzrq.setText(usercar_query.getData().getIssue_date());
                            textSyx.setText(usercar_query.getData().getInsurance_commerce());
                            textNsdq.setText(usercar_query.getData().getYear_end());
                            textBxdq.setText(usercar_query.getData().getInsurance_end());
                            textDabh.setText(usercar_query.getData().getFile_no());
                            textHdzrs.setText(usercar_query.getData().getAppproved_passenger_capacity());
                            textZzl.setText(usercar_query.getData().getGross_mass());
                            textWkcc.setText(usercar_query.getData().getOverall_dimension());
                            textZbzl.setText(usercar_query.getData().getUnladen_mass());
                            imageUrls.clear();
                            images.clear();
                            textWgtp.setText("已上传" + usercar_query.getImgs().size() + "张图片");
                            for (int i = 0; i < usercar_query.getImgs().size(); i++) {
                                images.add(usercar_query.getImgs().get(i).getImg_id());
                                imageUrls.add(usercar_query.getImgs().get(i).getImg_url());
                            }
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
        params.put("car_no", betterSpinner.getText().toString() + editChePai.getText().toString().toUpperCase());
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
        params.put("car_name", textCx.getText().toString());
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", textGcsj.getText().toString());
        params.put("engine", textFdjh.getText().toString().trim().toUpperCase());
        params.put("car_no", textCph.getText().toString().toUpperCase());
        params.put("vin", textCjh.getText().toString().toUpperCase());
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
        params.put("file_no", textDabh.getText().toString());
        params.put("appproved_passenger_capacity", textHdzrs.getText().toString());
        params.put("gross_mass", textZzl.getText().toString());
        params.put("overall_dimension", textWkcc.getText().toString());
        params.put("unladen_mass", textZbzl.getText().toString());
        params.put("imgs", Chushi.toString().replace("[", "").replace("]", ""));

        return new OkObject(params, url);
    }

    private OkObject getOkObjectBD1() {
        String url = Constant.HOST + Constant.Url.Usercar_Add_car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("car_name", textCx.getText().toString());
        params.put("cid", dataBean.getId() + "");
        params.put("bc_time", textGcsj.getText().toString());
        params.put("engine", textFdjh.getText().toString().trim().toUpperCase());
        params.put("car_no", textCph.getText().toString().toUpperCase());
        params.put("vin", textCjh.getText().toString().toUpperCase());
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

        params.put("file_no", textDabh.getText().toString());
        params.put("appproved_passenger_capacity", textHdzrs.getText().toString());
        params.put("gross_mass", textZzl.getText().toString());
        params.put("overall_dimension", textWkcc.getText().toString());
        params.put("unladen_mass", textZbzl.getText().toString());
        params.put("imgs", Chushi.toString().replace("[", "").replace("]", ""));

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
    int imageType=0;
    @OnClick({R.id.imageBM,R.id.imageZM,R.id.imageZy, R.id.imageFy, R.id.viewWgtp, R.id.viewDabh, R.id.viewHdzrs, R.id.viewZzl, R.id.viewWkcc, R.id.viewZbzl, R.id.textFs, R.id.sbtn_chaxun, R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewSzy, R.id.viewSfy, R.id.sbtn_tijiaobdw,
            R.id.viewSyx, R.id.viewFzrq, R.id.viewZcrq, R.id.viewXm, R.id.viewCx, R.id.viewDz, R.id.viewCph, R.id.viewFdjh, R.id.viewCjh, R.id.viewNcdq, R.id.viewBxdq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageBM:
                imageType=1;
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "back");
                    intent.putExtra("img_id", img_id);
                    intent.setClass(getActivity(), CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.imageZM:
                imageType=0;
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "face");
                    intent.setClass(getActivity(), CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.imageZy:
                intent = new Intent(context, DrivingLicenseActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("zy", xinShiZZM);
                startActivity(intent);
                break;
            case R.id.imageFy:
                intent = new Intent(context, DrivingLicenseActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("fy", xingShiZFY);
                startActivity(intent);
                break;
            case R.id.viewWgtp:
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
                                PictureSelector.create(context).themeStyle(themeId).openExternalPreview(0, imageList);
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
                Calendar csy = Calendar.getInstance();
                DatePickerDialog datePickerDialogsy = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textSyx.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, csy.get(Calendar.YEAR), csy.get(Calendar.MONTH), csy.get(Calendar.DAY_OF_MONTH));
                datePickerDialogsy.show();
                break;
            case R.id.viewXm:
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
//                if (usercar_query.getR() != 0) {
//                    return;
//                }
//                String stringCph = "";
//                if (xinShiZZM != null) {
//                    stringCph = xinShiZZM.getData().getAddress();
//                }
//                final EditDialogText editDialogCph = new EditDialogText(context, "输入车牌号", stringCph, "确认", "取消");
//                editDialogCph.setClicklistener(new EditDialogText.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm(String intro) {
//                        editDialogCph.dismiss();
//                        textCph.setText(intro);
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        editDialogCph.dismiss();
//                    }
//                });
//                editDialogCph.show();
                break;
            case R.id.viewFdjh:
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
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textBxdq.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
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
                        xslc = intro;
                        textxslc.setText(intro + "km");
                    }

                    @Override
                    public void doCancel() {
                        editDialog1.dismiss();
                    }
                });
                editDialog1.show();
                break;
            case R.id.viewSzy:
                imageType=0;
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "face");
//                    intent.setClass(getActivity(), SaoMiaoActivity.class);
                    intent.setClass(getActivity(), CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.viewSfy:
                imageType=1;
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            0);

                } else {
                    intent = new Intent();
                    intent.putExtra("type", "53");
                    intent.putExtra("side", "back");
                    intent.putExtra("img_id", img_id);
                    intent.setClass(getActivity(), CameraActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.sbtn_tijiaobdw:
                if (TextUtils.isEmpty(cid)) {
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
                    if (TextUtils.isEmpty(usercar_query.getAdd_tips())) {
                        usercar_Add_car(getOkObjectBD1());
                    } else {
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage(usercar_query.getAdd_tips())
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        usercar_Add_car(getOkObjectBD1());

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                } else if (usercar_query.getR() == 1) {
                    if (TextUtils.isEmpty(editYzm.getText().toString())) {
                        ToastUtils.showShort("短信验证码不能为空！");
                        return;
                    }
                    if (TextUtils.isEmpty(usercar_query.getAdd_tips())) {
                        usercar_Add_car(getOkObjectBD1());
                    } else {
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage(usercar_query.getAdd_tips())
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        usercar_Add_car(getOkObjectBD());

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
                break;
            default:
                break;

        }
    }
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImage1Adapter.onAddPicClickListener onAddPicClickListener = new GridImage1Adapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(XianYouCLBDFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(9)
                        .minSelectNum(1)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .previewImage(true)
                        .isCamera(true)
                        .imageFormat(PictureMimeType.PNG)
                        .enableCrop(false)
                        .compress(false)
                        .glideOverride(100, 100)
                        .previewEggs(true)
                        .openClickSound(true)
                        .selectionMedia(selectList)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < selectList.size(); i++) {
                        getAppImgAdd(ImgToBase64.toBase64(selectList.get(i).getPath()));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void getAppImgAdd(String img) {
        if (TextUtils.isEmpty(img)){
            return;
        }
        HttpApi.post(context, getOkObjectAppImgAdd(img), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("上传中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                LogUtils.e("getAppImgAdd", s);
                try {
                    Respond_AppImgAdd respondAppImgAdd = GsonUtils.parseJSON(s, Respond_AppImgAdd.class);
                    int status = respondAppImgAdd.getStatus();
                    if (status == 1) {
                        Chushi.add(respondAppImgAdd.getImgId());
                    } else {
                        ToastUtils.showShort(respondAppImgAdd.getInfo());
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

    private OkObject getOkObjectAppImgAdd(String img) {
        String url = Constant.HOST + Constant.Url.Respond_AppImgAdd;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("code", "");
        params.put("img", img);
        params.put("type", "png");
        return new OkObject(params, url);
    }
}
