package com.wanbao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Profile;
import com.wanbao.ui.CircleImageView;

import java.util.HashMap;

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
    private HashMap<String,String> keys=new HashMap<>();
    private HashMap<String,String> values=new HashMap<>();

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
    }

    @Override
    protected void initData() {
        Profile();
    }

    @OnClick({R.id.imageback, R.id.viewTx, R.id.viewNc, R.id.viewDh, R.id.viewXb, R.id.viewNl, R.id.viewZy, R.id.viewGzdw, R.id.viewXqbq, R.id.viewSmrz, R.id.btnTiJiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewTx:
                break;
            case R.id.viewNc:
                break;
            case R.id.viewDh:
                break;
            case R.id.viewXb:
                break;
            case R.id.viewNl:
                break;
            case R.id.viewZy:
                break;
            case R.id.viewGzdw:
                break;
            case R.id.viewXqbq:
                break;
            case R.id.viewSmrz:
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
                        textXqbq.setText(uProfile.getInterest().toString());
                        textRz.setText(uProfile.getPassDes());
                        if (uProfile.getPass() == 1) {
                            imageRz.setImageResource(R.mipmap.icon_yrzxx);
                        } else {
                            imageRz.setImageResource(R.mipmap.icon_wzrxx);
                        }
                        GlideApp.with(context)
                                .asBitmap()
                                .load(uProfile.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHeader);
                    } else {
                        ToastUtils.showShort(uProfile.getInfo());
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
}
