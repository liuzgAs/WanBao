package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TuiJianHYGCFragment extends PSFragment {
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.viewAddCard)
    LinearLayout viewAddCard;
    @BindView(R.id.textMoney)
    TextView textMoney;
    @BindView(R.id.viewYs)
    LinearLayout viewYs;
    @BindView(R.id.textCity)
    EditText textCity;
    @BindView(R.id.textZhiYe)
    EditText textZhiYe;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;
    Unbinder unbinder;
    private View view;
    private String carId;


    public TuiJianHYGCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tui_jian_hygc, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void fetchData() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_CarX.equals(event.getAction())){
            Car_Index.DataBean dataBean=(Car_Index.DataBean)event.getData();
            textCarName.setText(dataBean.getTitle());
            carId=String.valueOf(dataBean.getId());
        }
    }

    @OnClick({R.id.viewAddCard, R.id.viewYs, R.id.sbtn_tijiao})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewAddCard:
                intent=new Intent();
                intent.setClass(context, XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYs:
                final EditDialog editDialog0 = new EditDialog(context, "请输入购车预算（元）", "", "确认", "取消");
                editDialog0.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog0.dismiss();
                        textMoney.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialog0.dismiss();
                    }
                });
                editDialog0.show();
                break;
            case R.id.sbtn_tijiao:
                if (TextUtils.isEmpty(editPhone.getText().toString())){
                    ToastUtils.showShort("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(editName.getText().toString())){
                    ToastUtils.showShort("姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(carId)){
                    ToastUtils.showShort("请选择意向车型");
                    return;
                }
                if (TextUtils.isEmpty(textMoney.getText().toString())){
                    ToastUtils.showShort("购车预算不能为空");
                    return;
                }
                Recomadd();
                break;
            default:
                break;
        }
    }

    private void Recomadd() {
        HttpApi.post(context, getOkObjectRecomadd(), new HttpApi.CallBack() {
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
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        ToastUtils.showShort("推荐成功！");
                        editName.setText("");
                        editPhone.setText("");
                        textCarName.setText("");
                        textMoney.setText("");
                        textCity.setText("");
                        textZhiYe.setText("");
                        carId="";
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

    private OkObject getOkObjectRecomadd() {
        String url = Constant.HOST + Constant.Url.Money_Recomadd;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("name", editName.getText().toString());
        params.put("phone", editPhone.getText().toString());
        params.put("car", carId);
        params.put("budget", textMoney.getText().toString());
        params.put("works", textZhiYe.getText().toString());
        params.put("city", textCity.getText().toString());
        return new OkObject(params, url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
