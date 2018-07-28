package com.wanbao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.R;
import com.wanbao.adapter.CarImageAdapter;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.util.FullyGridLayoutManager;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class CheShenTpActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.titleRight)
    TextView titleRight;
    private int themeId;

    private CarImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    ArrayList<Respond_AppImgAdd> respond_appImgAdds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_shen_tp);
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
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("保存");
        titleText.setText("车身图片");
        themeId = R.style.picture_default_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new CarImageAdapter(context, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CarImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                PictureSelector.create(CheShenTpActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.DeleteImg.equals(event.getAction())) {
            int position = (Integer) event.getData();
            selectList.remove(position);
            adapter.setList(selectList);
            adapter.notifyDataSetChanged();
//            files.clear();
//            for (int i = 0; i < selectList.size(); i++) {
//                files.add(new File(selectList.get(i).getPath()));
//            }
        }
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
                    break;
                default:
                    break;
            }
        }
    }

    private void getAppImgAdd(String img) {
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
                LogUtils.e("getAppImgAdd", s);
                try {
                    Respond_AppImgAdd respondAppImgAdd = GsonUtils.parseJSON(s, Respond_AppImgAdd.class);
                    int status = respondAppImgAdd.getStatus();
                    if (status == 1) {
                        respond_appImgAdds.add(respondAppImgAdd);
                        if (pos == selectList.size() - 1) {
                            dismissDialog();
                            ToastUtils.showShort("上传成功");
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.CarImage, respond_appImgAdds));
                            finish();
                        }else {
                            pos=pos+1;
                            getAppImgAdd(ImgToBase64.toBase64(selectList.get(pos).getPath()));
                        }
                    } else {
                        dismissDialog();
                        ToastUtils.showShort(respondAppImgAdd.getInfo());
                    }
                } catch (Exception e) {
                    dismissDialog();
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

    private CarImageAdapter.onAddPicClickListener onAddPicClickListener = new CarImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(CheShenTpActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(maxSelectNum)
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
    int pos = 0;

    @OnClick({R.id.imageback, R.id.titleRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.titleRight:
                if (selectList == null) {
                    return;
                }
                if (selectList.size() == 0) {
                    ToastUtils.showShort("请选择图片！");
                    return;
                }
                pos=0;
                respond_appImgAdds.clear();
                getAppImgAdd(ImgToBase64.toBase64(selectList.get(pos).getPath()));
                break;
            default:
                break;
        }
    }
}
