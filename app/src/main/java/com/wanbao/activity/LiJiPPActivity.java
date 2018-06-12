package com.wanbao.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hedgehog.ratingbar.RatingBar;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.TagAdapter;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.FlowTagLayout;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Comment_AddBefore;
import com.wanbao.modle.OkObject;
import com.wanbao.ui.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class LiJiPPActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageCar)
    ImageView imageCar;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.textCarNo)
    TextView textCarNo;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.imageHeader)
    CircleImageView imageHeader;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.flowTagLayout)
    FlowTagLayout flowTagLayout;
    @BindView(R.id.editPj)
    EditText editPj;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    private String id;
    private TagAdapter tagAdapter;
    private List<Comment_AddBefore.TagBean> flagBeanList;
    private int startNum = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_ji_pp);
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
        titleText.setText("立即评价");
        ratingbar.setStar(5);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        ratingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                startNum = (int) RatingCount;
            }
        });
        viewSwitcher.setDisplayedChild(0);
    }

    @Override
    protected void initData() {
        Comment();
    }

    @OnClick({R.id.imageback, R.id.sbtn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.sbtn_tijiao:
                if (TextUtils.isEmpty(editPj.getText().toString())){
                    ToastUtils.showShort("请输入评价内容！");
                    return;
                }
                if (editPj.length()<8){
                    ToastUtils.showShort("评价内容至少八个字！");
                    return;
                }
                AddSubmit();
                break;
            default:
                break;
        }
    }

    private void Comment() {
        HttpApi.post(context, getOkObjectComment(), new HttpApi.CallBack() {
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
                Log.e("Comment_AddBefore", s);
                try {
                    Comment_AddBefore data = GsonUtils.parseJSON(s, Comment_AddBefore.class);
                    if (data.getStatus() == 1) {
                        tagAdapter = new TagAdapter(context);
                        flowTagLayout.setAdapter(tagAdapter);
                        flagBeanList = data.getTag();
                        tagAdapter.clearAndAddAll(flagBeanList);
                        flowTagLayout.clearAllOption();
                        textCarName.setText(data.getGoods_name());
                        textCarNo.setText(data.getGoods_price());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(data.getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCar);
                        textDes.setText(data.getNameDes());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(data.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHeader);
                        editPj.setHint(data.getDes());
                    } else {
                        ToastUtils.showShort("数据出错");
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

    private OkObject getOkObjectComment() {
        String url = Constant.HOST + Constant.Url.Comment_AddBefore;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    private void AddSubmit() {
        HttpApi.post(context, getOkObjectAddSubmit(), new HttpApi.CallBack() {
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
                Log.e("Comment_AddBefore", s);
                try {
                    Comment data = GsonUtils.parseJSON(s, Comment.class);
                    if (data.getStatus() == 1) {
                        viewSwitcher.setDisplayedChild(1);
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_SjOrder,null));
                    } else {
                        ToastUtils.showShort("数据出错");
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

    private OkObject getOkObjectAddSubmit() {
        List<Integer> allSelect = new ArrayList<>();
        for (int i = 0; i < flowTagLayout.getAllSelect().size(); i++) {
            allSelect.add(flagBeanList.get(i).getId());
        }
        String url = Constant.HOST + Constant.Url.Comment_AddSubmit;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        params.put("star", startNum + "");
        params.put("evaluate", editPj.getText().toString());
        params.put("tag", allSelect.toString().replace("[","").replace("]","").replace(" ",""));
        params.put("imgs", "");

        return new OkObject(params, url);
    }
}
