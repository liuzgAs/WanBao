package com.wanbao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.base.view.EditDialogText;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.User_Profile;
import com.wanbao.ui.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Demo class
 *
 * @author liuzg
 * @date 2018/04/10
 */
public class CheShouZiZhuanActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageHeader)
    CircleImageView imageHeader;
    @BindView(R.id.viewTx)
    LinearLayout viewTx;
    @BindView(R.id.textNc)
    TextView textNc;
    @BindView(R.id.viewNc)
    LinearLayout viewNc;
    @BindView(R.id.textDh)
    TextView textDh;
    @BindView(R.id.viewDh)
    LinearLayout viewDh;
    @BindView(R.id.textXb)
    TextView textXb;
    @BindView(R.id.viewXb)
    LinearLayout viewXb;
    @BindView(R.id.textNl)
    TextView textNl;
    @BindView(R.id.viewNl)
    LinearLayout viewNl;
    @BindView(R.id.textZy)
    TextView textZy;
    @BindView(R.id.viewZy)
    LinearLayout viewZy;
    @BindView(R.id.textGzdw)
    TextView textGzdw;
    @BindView(R.id.viewGzdw)
    LinearLayout viewGzdw;
    @BindView(R.id.textXqbq)
    TextView textXqbq;
    @BindView(R.id.viewXqbq)
    LinearLayout viewXqbq;
    @BindView(R.id.imageRz)
    ImageView imageRz;
    @BindView(R.id.textRz)
    TextView textRz;
    @BindView(R.id.viewSmrz)
    LinearLayout viewSmrz;
    @BindView(R.id.btnTiJiao)
    Button btnTiJiao;
    private HashMap<String,String> infos=new HashMap<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 1;
    private int themeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_shou_zi_zhuan);
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
        titleText.setText("车手自传");
        themeId = R.style.picture_default_style;

    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ChangeXx.equals(event.getAction())){
            initData();
        }
    }

    @Override
    protected void initData() {
        Profile();
    }

    @OnClick({R.id.imageback, R.id.viewTx, R.id.viewNc, R.id.viewDh, R.id.viewXb, R.id.viewNl, R.id.viewZy, R.id.viewGzdw, R.id.viewXqbq, R.id.viewSmrz, R.id.btnTiJiao})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewTx:
                goPhoto();
                break;
            case R.id.viewNc:
                MyDialog.twoButton(context, "请输入昵称", "请输入昵称", new MyDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        infos.put("key","nickname");
                        infos.put("value",intro);
                        getSvaeInfo(infos);
                    }

                    @Override
                    public void doCancel() {

                    }
                });
//                final EditDialogText editDialog = new EditDialogText(context, "请输入昵称", "", "确认", "取消");
//                editDialog.setClicklistener(new EditDialogText.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm(String intro) {
//                        editDialog.dismiss();
//                        infos.put("key","nickname");
//                        infos.put("value",intro);
//                        getSvaeInfo(infos);
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        editDialog.dismiss();
//                    }
//                });
//                editDialog.show();
                break;
            case R.id.viewDh:
                break;
            case R.id.viewXb:
                String[] sexs={"男","女"};
                new MaterialDialog.Builder(context)
                        .title("选择你的性别")
                        .items(sexs)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                infos.put("key","gender");
                                infos.put("value",(position+1)+"");
                                getSvaeInfo(infos);
                            }
                        })
                        .show();
                break;
            case R.id.viewNl:
                final EditDialog editDialog0 = new EditDialog(context, "请输入你的年龄", "", "确认", "取消");
                editDialog0.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog0.dismiss();
                        infos.put("key","age");
                        infos.put("value",intro);
                        getSvaeInfo(infos);
                    }

                    @Override
                    public void doCancel() {
                        editDialog0.dismiss();
                    }
                });
                editDialog0.show();
                break;
            case R.id.viewZy:
                final EditDialogText editDialog1 = new EditDialogText(context, "请输入你的职业", "", "确认", "取消");
                editDialog1.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog1.dismiss();
                        infos.put("key","profession");
                        infos.put("value",intro);
                        getSvaeInfo(infos);
                    }

                    @Override
                    public void doCancel() {
                        editDialog1.dismiss();
                    }
                });
                editDialog1.show();
                break;
            case R.id.viewGzdw:
                final EditDialogText editDialog2 = new EditDialogText(context, "请输入你的工作单位", "", "确认", "取消");
                editDialog2.setClicklistener(new EditDialogText.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog2.dismiss();
                        infos.put("key","company");
                        infos.put("value",intro);
                        getSvaeInfo(infos);
                    }

                    @Override
                    public void doCancel() {
                        editDialog2.dismiss();
                    }
                });
                editDialog2.show();
                break;
            case R.id.viewXqbq:
                intent=new Intent();
                intent.setClass(context,XingQuBQActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSmrz:
                intent=new Intent();
                intent.setClass(context,ShiMinRzActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTiJiao:
                break;
            default:
                break;
        }
    }

    private void Profile() {
        HttpApi.post(context, getOkObjectProfile(), new HttpApi.CallBack() {
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
                Log.e("User_Profile", s);
                try {
                    User_Profile uProfile = GsonUtils.parseJSON(s, User_Profile.class);
                    if (uProfile.getStatus() == 1) {
                        textNc.setText(uProfile.getNickname());
                        textDh.setText(uProfile.getMobile());
                        if (uProfile.getGender() == 1) {
                            textXb.setText("男");
                        } else if (uProfile.getGender() == 2) {
                            textXb.setText("女");
                        } else {
                            textXb.setText("保密");
                        }
                        textNl.setText(uProfile.getAge()+"");
                        textZy.setText(uProfile.getProfession());
                        textGzdw.setText(uProfile.getCompany());
                        textRz.setText(uProfile.getPassDes());
                        if (uProfile.getPass() == 1) {
                            imageRz.setImageResource(R.mipmap.icon_yrzxx);
                        } else {
                            imageRz.setImageResource(R.mipmap.icon_wzrxx);
                        }
                        ArrayList<String> interest=new ArrayList<>();
                        interest.clear();
                        for (int i=0;i<uProfile.getInterest().size();i++){
                            interest.add(uProfile.getInterest().get(i).getName());
                        }
                        textXqbq.setText(interest.toString().replace("[","").replace("]",""));
                        GlideApp.with(context)
                                .asBitmap()
                                .load(uProfile.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHeader);
                    } else {
                        MyDialog.dialogFinish(CheShouZiZhuanActivity.this,uProfile.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(CheShouZiZhuanActivity.this,"数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(CheShouZiZhuanActivity.this,"网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectProfile() {
        String url = Constant.HOST + Constant.Url.User_Profile;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList!=null){
                        getAppImgAdd(ImgToBase64.toBase64(selectList.get(0).getCutPath()));
                    }

                    break;
                default:
                    break;
            }
        }
    }

    private void goPhoto(){
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(CheShouZiZhuanActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxSelectNum)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
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
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
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
                dismissDialog();
                LogUtils.e("getAppImgAdd", s);
                try {
                    Respond_AppImgAdd respondAppImgAdd = GsonUtils.parseJSON(s, Respond_AppImgAdd.class);
                    int status = respondAppImgAdd.getStatus();
                    if (status == 1) {
                        infos.put("key","headimg");
                        infos.put("value",String.valueOf(respondAppImgAdd.getImgId()));
                        getSvaeInfo(infos);
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
    private void getSvaeInfo(HashMap<String,String> infos) {
        HttpApi.post(context, getOkObjectSvaeInfo(infos), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("修改中...");
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
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_Data,null));
                        initData();
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

    private OkObject getOkObjectSvaeInfo(HashMap<String,String> info) {
        String url = Constant.HOST + Constant.Url.User_SvaeInfo;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("key", info.get("key"));
        params.put("value", info.get("value"));
        return new OkObject(params, url);
    }
}
