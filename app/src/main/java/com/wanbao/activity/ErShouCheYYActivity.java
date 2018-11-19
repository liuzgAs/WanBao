package com.wanbao.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.GridImage1Adapter;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.A2bigA;
import com.wanbao.base.util.FullyGridLayoutManager;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.City_List;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.Respond_Qntoken;
import com.wanbao.modle.Seller_Online;
import com.wanbao.modle.Seller_Online_before;
import com.wanbao.modle.Uploads_Appimgs;
import com.wanbao.modle.Usercar_Vin_zb;
import com.wanbao.modle.XinShiZZM;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ErShouCheYYActivity extends BaseActivity {

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.titleRight)
    TextView titleRight;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.editVin)
    EditText editVin;
    @BindView(R.id.textPinPaiCX)
    TextView textPinPaiCX;
    @BindView(R.id.textKanCheCS)
    TextView textKanCheCS;
    @BindView(R.id.textShangPaiSJ)
    TextView textShangPaiSJ;
    @BindView(R.id.editCheZhuBJ)
    EditText editCheZhuBJ;
    @BindView(R.id.editXinCheJG)
    EditText editXinCheJG;
    @BindView(R.id.editBiaoXianLC)
    EditText editBiaoXianLC;
    @BindView(R.id.editCheLing)
    EditText editCheLing;
    @BindView(R.id.editPaiLiang)
    EditText editPaiLiang;
    @BindView(R.id.editPaiFangBZ)
    EditText editPaiFangBZ;
    @BindView(R.id.imageKanCheSP)
    ImageView imageKanCheSP;
    @BindView(R.id.editCheLiangMS)
    EditText editCheLiangMS;
    @BindView(R.id.editDianPuXX)
    EditText editDianPuXX;
    @BindView(R.id.editDianPuDH)
    EditText editDianPuDH;
    @BindView(R.id.imageDianPuLogo)
    ImageView imageDianPuLogo;
    @BindView(R.id.editDianPuJS)
    EditText editDianPuJS;
    @BindView(R.id.btnSubmit)
    StateButton btnSubmit;
    @BindView(R.id.textVin)
    TextView textVin;
    @BindView(R.id.imageZhanShi)
    ImageView imageZhanShi;
    @BindView(R.id.textTips)
    TextView textTips;
    private GridImage1Adapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> selectList1 = new ArrayList<>();
    private List<LocalMedia> selectList2 = new ArrayList<>();

    private int themeId = R.style.picture_default_style;
    private ArrayList<String> Chushi = new ArrayList<>();
    private String cid;
    private String cityId;
    private String video;
    private String store_logo;
    private String video_img;
    public static final String IMAGES = "storage/emulated/qianche/s.jpg";
    private int video_second = 10;
    private UploadManager uploadManager;
    private XinShiZZM xinShiZZM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_che_yy);
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
        uploadManager = new UploadManager();
        titleText.setText("预约上架");
        editVin.setTransformationMethod(new A2bigA());
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImage1Adapter(context, onAddPicClickListener);
        adapter.setList(selectList);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImage1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                PictureSelector.create(ErShouCheYYActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
        editVin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textVin.setText("VIN码（" + s.length() + "/17）");
                if (s.length() == 17) {
                    textTips.setVisibility(View.GONE);
                    getVin(s.toString());
                } else {
                    textTips.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.INDEX.equals(event.getAction())) {
            int index = (int) event.getData();
            Chushi.remove(index);
        }
        if (BaseEvent.Choose_CarX.equals(event.getAction())) {
            Car_Index.DataBean dataBean = (Car_Index.DataBean) event.getData();
            if (dataBean != null) {
                textPinPaiCX.setText(dataBean.getTitle());
                cid = dataBean.getId() + "";
            }
        }
        if (BaseEvent.Choose_CS.equals(event.getAction())) {
            City_List.CityBean.ListBean listBean = (City_List.CityBean.ListBean) event.getData();
            textKanCheCS.setText(listBean.getName());
            cityId = listBean.getId() + "";
        }
        if (BaseEvent.XinShiZZM.equals(event.getAction())) {
            xinShiZZM = (XinShiZZM) event.getData();
            textShangPaiSJ.setText(xinShiZZM.getData().getCard_time());
            editCheLing.setText(xinShiZZM.getData().getAge());
            editVin.setText(xinShiZZM.getData().getVin());
        }
    }

    @Override
    protected void initData() {
        sellerOnlineBefore();
    }

    @OnClick({R.id.textPaiZhao,R.id.imageZhanShi, R.id.imageback, R.id.viewPinPaiCX, R.id.viewKanCheCS, R.id.viewShangPaiSJ, R.id.imageKanCheSP, R.id.imageDianPuLogo, R.id.btnSubmit})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.textPaiZhao:
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
            case R.id.viewPinPaiCX:
                intent = new Intent();
                intent.setClass(context, XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewKanCheCS:
                intent = new Intent(context, XuanZheCSActivity.class);
                startActivity(intent);
                break;
            case R.id.viewShangPaiSJ:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textShangPaiSJ.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;
            case R.id.imageKanCheSP:
                PictureSelector.create(ErShouCheYYActivity.this)
                        .openCamera(PictureMimeType.ofVideo())
                        .videoMaxSecond(video_second)
                        .recordVideoSecond(video_second)
                        .compress(true)
                        .forResult(PictureConfig.TYPE_VIDEO);
                break;
            case R.id.imageDianPuLogo:
                PictureSelector.create(ErShouCheYYActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isCamera(true)
                        .imageFormat(PictureMimeType.PNG)
                        .enableCrop(true)
                        .compress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(false)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .openClickSound(true)
                        .selectionMedia(selectList1)
                        .forResult(PictureConfig.SINGLE);
                break;
            case R.id.imageZhanShi:
                PictureSelector.create(ErShouCheYYActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isCamera(true)
                        .imageFormat(PictureMimeType.PNG)
                        .enableCrop(true)
                        .compress(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .withAspectRatio(1, 1)
                        .freeStyleCropEnabled(true)
                        .circleDimmedLayer(false)
                        .showCropFrame(true)
                        .showCropGrid(true)
                        .openClickSound(true)
                        .selectionMedia(selectList2)
                        .forResult(PictureConfig.MAX_COMPRESS_SIZE);
                break;
            case R.id.btnSubmit:
                if (Chushi.size() == 0) {
                    ToastUtils.showShort("请上传车辆照片！");
                    return;
                }
                if (!RegexUtils.isMatch(Constant.Cjh, editVin.getText().toString())) {
                    ToastUtils.showShort("请输入正确车架号");
                    return;
                }
                if (TextUtils.isEmpty(cid)) {
                    ToastUtils.showShort("请设置品牌车系！");
                    return;
                }
                if (TextUtils.isEmpty(cityId)) {
                    ToastUtils.showShort("请设置看车城市！");
                    return;
                }
                if (TextUtils.isEmpty(textShangPaiSJ.getText().toString())) {
                    ToastUtils.showShort("请设置上牌时间");
                    return;
                }
                if (TextUtils.isEmpty(editCheZhuBJ.getText().toString())) {
                    ToastUtils.showShort("请输入车主报价");
                    return;
                }
                if (TextUtils.isEmpty(editXinCheJG.getText().toString())) {
                    ToastUtils.showShort("请输入新车购置价");
                    return;
                }
                if (TextUtils.isEmpty(editBiaoXianLC.getText().toString())) {
                    ToastUtils.showShort("请输入表显里程");
                    return;
                }
                if (TextUtils.isEmpty(editCheLing.getText().toString())) {
                    ToastUtils.showShort("请输入车龄");
                    return;
                }
                if (TextUtils.isEmpty(editPaiLiang.getText().toString())) {
                    ToastUtils.showShort("请输入排量");
                    return;
                }
                if (TextUtils.isEmpty(editPaiFangBZ.getText().toString())) {
                    ToastUtils.showShort("请输入排放标准");
                    return;
                }
                sellerOnline();
                break;
            default:
                break;
        }
    }

    private void sellerOnlineBefore() {
        HttpApi.post(context, getOkObjectBefore(), new HttpApi.CallBack() {
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
                LogUtils.e("Seller_Online_before", s);
                try {
                    Seller_Online_before seller_online_before = GsonUtils.parseJSON(s, Seller_Online_before.class);
                    int status = seller_online_before.getStatus();
                    if (status == 1) {
                        editDianPuXX.setText(seller_online_before.getStore_name());
                        editDianPuDH.setText(seller_online_before.getStore_tel());
                        editDianPuJS.setText(seller_online_before.getStore_intro());
                        video_second = seller_online_before.getVideo_second();
                        textTips.setText(seller_online_before.getTips());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(seller_online_before.getStore_logo())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageDianPuLogo);
                    } else {
                        ToastUtils.showShort(seller_online_before.getInfo());
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

    private OkObject getOkObjectBefore() {
        String url = Constant.HOST + Constant.Url.Seller_Online_before;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void sellerOnline() {
        HttpApi.post(context, getOkObject(), new HttpApi.CallBack() {
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
                    Seller_Online seller_online = GsonUtils.parseJSON(s, Seller_Online.class);
                    int status = seller_online.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ErShouChe, null));
                        MyDialog.dialogFinish(ErShouCheYYActivity.this, seller_online.getInfo());
                    } else {
                        ToastUtils.showShort(seller_online.getInfo());
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

    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Seller_Online;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("cid", cid);
        params.put("see_city_id", cityId);
        params.put("title", textPinPaiCX.getText().toString());
        params.put("price", editCheZhuBJ.getText().toString());
        params.put("card_time", textShangPaiSJ.getText().toString());
        params.put("km", editBiaoXianLC.getText().toString());
        params.put("vin", editVin.getText().toString());
        params.put("store_name", editDianPuXX.getText().toString());
        params.put("store_tel", editDianPuDH.getText().toString());
        params.put("store_intro", editDianPuJS.getText().toString());
        params.put("age", editCheLing.getText().toString());
        params.put("purchasePrice", editXinCheJG.getText().toString());
        params.put("intro", editCheLiangMS.getText().toString());
        params.put("displacement", editPaiLiang.getText().toString());
        params.put("effluentStandard", editPaiFangBZ.getText().toString());
        params.put("imgs", Chushi.toString().replace("[", "").replace("]", ""));
        params.put("video", video);
        params.put("video_key", video);
        params.put("video_img", video_img);
        params.put("store_logo", store_logo);
        return new OkObject(params, url);
    }

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
                        getAppImgAdd(ImgToBase64.toBase64(selectList.get(i).getPath()), 0);
                    }
                    break;
                case PictureConfig.SINGLE:
                    List<LocalMedia> selectSingle = PictureSelector.obtainMultipleResult(data);
                    GlideApp.with(context)
                            .asBitmap()
                            .load(selectSingle.get(0).getCutPath())
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageDianPuLogo);
                    getAppImgAdd(ImgToBase64.toBase64(selectSingle.get(0).getPath()), 1);
                    break;
                case PictureConfig.TYPE_VIDEO:
                    List<LocalMedia> selectVideo = PictureSelector.obtainMultipleResult(data);
                    LogUtils.e("selectVideo", selectVideo.get(0).getPath() + "1");
                    LogUtils.e("selectVideoCompress", selectVideo.get(0).getCompressPath() + "2");
                    GlideApp.with(context)
                            .asBitmap()
                            .load(selectVideo.get(0).getPath())
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageKanCheSP);
                    files.clear();
                    files.add(new File(selectVideo.get(0).getPath()));
//                    getVedioId();
                    upFile();
                    break;
                case PictureConfig.MAX_COMPRESS_SIZE:
                    List<LocalMedia> imageVedio = PictureSelector.obtainMultipleResult(data);
                    GlideApp.with(context)
                            .asBitmap()
                            .load(imageVedio.get(0).getCutPath())
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageZhanShi);
                    getAppImgAdd(ImgToBase64.toBase64(imageVedio.get(0).getPath()), 2);
                    break;
                default:
                    break;
            }
        }
    }

    private void getAppImgAdd(String img, final int type) {
        if (TextUtils.isEmpty(img)) {
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
                        if (type == 0) {
                            Chushi.add(respondAppImgAdd.getImgId());
                        } else if (type == 1) {
                            store_logo = respondAppImgAdd.getImg();
                        } else if (type == 2) {
                            video_img = respondAppImgAdd.getImg();
                        }
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

    List<File> files = new ArrayList<>();
    private static ProgressDialog progressDialog;

    private void getVedioId() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在上传视频……");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.show();
        HttpApi.upFiles(context, getOkObjectUserUpload(), files, new HttpApi.UpLoadCallBack() {
            @Override
            public void onSuccess(String s) {
                Log.e("getVedioId", s);
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                try {
                    Uploads_Appimgs userUpload = GsonUtils.parseJSON(s, Uploads_Appimgs.class);
                    if (userUpload.getStatus() == 1) {
                        video = userUpload.getImg().get(0).getId();
                    } else {
                        ToastUtils.showShort(userUpload.getInfo());
                    }

                } catch (Exception e) {
                    dismissDialog();
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void uploadProgress(float progress) {
                if (progressDialog != null) {
                    progressDialog.setProgress((int) progress);
                }
            }
        });
    }


    private void upFile() {
        HttpApi.post(context, getOkObjectUp(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("正在上传视频……");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("Respond_Qntoken", s);
                try {
                    Respond_Qntoken respond_qntoken = GsonUtils.parseJSON(s, Respond_Qntoken.class);
                    int status = respond_qntoken.getStatus();
                    if (status == 1) {
                        uploadManager.put(files.get(0), files.get(0).getName(), respond_qntoken.getToken(),
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info, JSONObject res) {
                                        progressDialog.dismiss();
                                        if (info.isOK()) {
                                            try {
                                                video = res.getString("key");
                                                ToastUtils.showShort("上传成功！");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            ToastUtils.showShort("上传失败！" + info.error);
                                        }
                                    }
                                }, new UploadOptions(null, null, false,
                                        new UpProgressHandler() {
                                            @Override
                                            public void progress(String key, final double percent) {
                                                LogUtils.e("percent", percent + "");
                                                textVin.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progressDialog.setProgress((int) (percent * 100));
                                                    }
                                                });
                                            }
                                        }, null));
                    } else {
                        ToastUtils.showShort(respond_qntoken.getInfo());
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

    private OkObject getOkObjectUp() {
        String url = Constant.HOST + Constant.Url.Respond_Qntoken;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private OkObject getOkObjectUserUpload() {
        String url = Constant.HOST + Constant.Url.Uploads_Appimgs;
        HashMap<String, String> params = new HashMap<>();
        return new OkObject(params, url);
    }


    private GridImage1Adapter.onAddPicClickListener onAddPicClickListener = new GridImage1Adapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(ErShouCheYYActivity.this)
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

    private void getVin(final String img) {
        HttpApi.post(context, getOkObjectVin(img), new HttpApi.CallBack() {
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
                    Usercar_Vin_zb usercarVinZb = GsonUtils.parseJSON(s, Usercar_Vin_zb.class);
                    int status = usercarVinZb.getStatus();
                    if (status == 1) {
                        textPinPaiCX.setText(usercarVinZb.getCid_name());
                        cid = usercarVinZb.getCid();
                        editXinCheJG.setText(usercarVinZb.getPurchasePrice());
                        editPaiLiang.setText(usercarVinZb.getDisplacement());
                        editPaiFangBZ.setText(usercarVinZb.getEffluentStandard());
                    } else {
                        ToastUtils.showShort(usercarVinZb.getInfo());
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

    private OkObject getOkObjectVin(String img) {
        String url = Constant.HOST + Constant.Url.Usercar_Vin_zb;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("vin", img);
        return new OkObject(params, url);
    }

}
