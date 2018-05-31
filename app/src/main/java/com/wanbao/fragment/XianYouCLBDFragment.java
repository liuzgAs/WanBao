package com.wanbao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Query;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public class XianYouCLBDFragment extends PSFragment {
    @BindView(R.id.betterSpinner)
    BetterSpinner betterSpinner;
    @BindView(R.id.editChePai)
    EditText editChePai;
    @BindView(R.id.sbtn_chaxun)
    StateButton sbtnChaxun;
    Unbinder unbinder;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.textCxxx)
    TextView textCxxx;
    @BindView(R.id.textGcsj)
    TextView textGcsj;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.textCjh)
    TextView textCjh;
    @BindView(R.id.textCs)
    TextView textCs;
    @BindView(R.id.textsj)
    TextView textsj;
    @BindView(R.id.editYzm)
    EditText editYzm;
    @BindView(R.id.sbtnYzm)
    StateButton sbtnYzm;
    @BindView(R.id.sbtn_tijiaobd)
    StateButton sbtnTijiaobd;
    @BindView(R.id.textChePai)
    TextView textChePai;
    @BindView(R.id.editFdjh)
    EditText editFdjh;
    @BindView(R.id.editCjh)
    EditText editCjh;
    @BindView(R.id.editSjhw)
    EditText editSjhw;
    @BindView(R.id.editYzmw)
    EditText editYzmw;
    @BindView(R.id.sbtnYzmw)
    StateButton sbtnYzmw;
    @BindView(R.id.sbtn_tijiaobdw)
    StateButton sbtnTijiaobdw;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.viewChaxun)
    LinearLayout viewChaxun;
    @BindView(R.id.textCp)
    TextView textCp;
    @BindView(R.id.textxslc)
    TextView textxslc;
    @BindView(R.id.textNsdq)
    TextView textNsdq;
    @BindView(R.id.textBxdq)
    TextView textBxdq;
    @BindView(R.id.textBydq)
    TextView textBydq;
    @BindView(R.id.textClxx)
    TextView textClxx;
    @BindView(R.id.viewCxxx)
    LinearLayout viewCxxx;
    @BindView(R.id.textGcsjw)
    TextView textGcsjw;
    @BindView(R.id.viewGcsj)
    LinearLayout viewGcsj;
    @BindView(R.id.textxslcw)
    TextView textxslcw;
    @BindView(R.id.viewXslc)
    LinearLayout viewXslc;
    @BindView(R.id.textCsw)
    TextView textCsw;
    @BindView(R.id.viewCs)
    LinearLayout viewCs;
    @BindView(R.id.textNsdqw)
    TextView textNsdqw;
    @BindView(R.id.viewNsdq)
    LinearLayout viewNsdq;
    @BindView(R.id.textBxdqw)
    TextView textBxdqw;
    @BindView(R.id.viewBxdq)
    LinearLayout viewBxdq;
    @BindView(R.id.textBydqw)
    TextView textBydqw;
    @BindView(R.id.viewBydq)
    LinearLayout viewBydq;
    private View view;
    private static final String[] COUNTRIES = new String[]{
            "京", "津", "沪", "渝", "蒙",
            "新", "藏", "宁", "桂", "港",
            "澳", "黑", "吉", "辽", "晋",
            "冀", "青", "鲁", "豫", "苏",
            "皖", "浙", "闽", "赣", "湘",
            "鄂", "粤", "琼", "甘", "陕",
            "黔", "滇", "川"
    };

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
        return view;
    }

    @Override
    public void fetchData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.item_sheng, COUNTRIES);
        betterSpinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
    }


    @OnClick({R.id.sbtn_chaxun, R.id.sbtnYzm, R.id.sbtn_tijiaobd, R.id.sbtnYzmw, R.id.sbtn_tijiaobdw,R.id.viewCxxx, R.id.viewGcsj, R.id.viewXslc, R.id.viewCs, R.id.viewBxdq, R.id.viewBydq})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewCxxx:
                intent.setClass(getActivity(), XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGcsj:
                break;
            case R.id.viewXslc:
                break;
            case R.id.viewCs:
                break;
            case R.id.viewBxdq:
                break;
            case R.id.viewBydq:
                break;
            case R.id.sbtnYzm:
                break;
            case R.id.sbtn_tijiaobd:
                break;
            case R.id.sbtnYzmw:
                break;
            case R.id.sbtn_tijiaobdw:
                break;
            case R.id.sbtn_chaxun:
                if (TextUtils.isEmpty(editChePai.getText())) {
                    ToastUtils.showShort("请输入车牌号！");
                    return;
                }
                usercar_Query();
                break;
            default:
                break;
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
                    Usercar_Query usercar_query = GsonUtils.parseJSON(s, Usercar_Query.class);
                    int status = usercar_query.getStatus();
                    if (status == 1) {
                        if (usercar_query.getR() == 0) {
                            textState.setText("系统未查到该车牌车辆，请自行录入");
                            viewChaxun.setVisibility(View.GONE);
                            viewSwitcher.setVisibility(View.VISIBLE);
                            viewSwitcher.setDisplayedChild(1);
                        } else {
                            textState.setText("查询到以下车辆信息");
                            viewChaxun.setVisibility(View.GONE);
                            viewSwitcher.setVisibility(View.VISIBLE);
                            viewSwitcher.setDisplayedChild(0);
                        }
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
        params.put("car_no", betterSpinner.getText().toString() + editChePai.getText().toString());
        return new OkObject(params, url);
    }

}
