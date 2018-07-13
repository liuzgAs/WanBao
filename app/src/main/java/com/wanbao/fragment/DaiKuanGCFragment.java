package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.activity.BiYaoHFDKActivity;
import com.wanbao.activity.ShangYeBXDKActivity;
import com.wanbao.activity.XuanZheCheXActivity;
import com.wanbao.base.AppContext;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Calculator_Loan;
import com.wanbao.modle.Car_Index;
import com.wanbao.modle.OkObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaiKuanGCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaiKuanGCFragment extends PSFragment {
    private static final String ARG_PARAM1 = "cid";
    @BindView(R.id.textBookTitle)
    TextView textBookTitle;
    @BindView(R.id.textBookVal)
    TextView textBookVal;
    @BindView(R.id.textbookDesN0)
    TextView textbookDesN0;
    @BindView(R.id.textbookDesV0)
    TextView textbookDesV0;
    @BindView(R.id.textbookDesN1)
    TextView textbookDesN1;
    @BindView(R.id.textbookDesV1)
    TextView textbookDesV1;
    @BindView(R.id.textbookDesN2)
    TextView textbookDesN2;
    @BindView(R.id.textbookDesV2)
    TextView textbookDesV2;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.viewCar)
    LinearLayout viewCar;
    @BindView(R.id.editMoney)
    EditText editMoney;
    @BindView(R.id.textDex1N)
    TextView textDex1N;
    @BindView(R.id.textDex1V)
    TextView textDex1V;
    @BindView(R.id.viewDes1)
    RelativeLayout viewDes1;
    @BindView(R.id.textDex2N)
    TextView textDex2N;
    @BindView(R.id.textDex2V)
    TextView textDex2V;
    @BindView(R.id.viewDes2)
    RelativeLayout viewDes2;
    @BindView(R.id.textDex10N)
    TextView textDex10N;
    @BindView(R.id.textDex10V)
    TextView textDex10V;
    @BindView(R.id.viewDes10)
    RelativeLayout viewDes10;
    @BindView(R.id.textDex20N)
    TextView textDex20N;
    @BindView(R.id.textDex20V)
    TextView textDex20V;
    @BindView(R.id.viewDes20)
    RelativeLayout viewDes20;
    @BindView(R.id.textNotice)
    TextView textNotice;
    Unbinder unbinder;
    private View view;

    private String cid;
    private String money;
    private String third_part;
    private String iscn;
    private String body_scratches;
    private String insurance;
    private String down_payment;
    private String year;
    ArrayList<String> viewDes10s=new ArrayList<>();
    ArrayList<String> viewDes20s=new ArrayList<>();

    private Calculator_Loan cIndex;
    public DaiKuanGCFragment() {
    }

    public static DaiKuanGCFragment newInstance(String param1) {
        DaiKuanGCFragment fragment = new DaiKuanGCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cid = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_dai_kuan_gc, container, false);
            unbinder = ButterKnife.bind(this, view);
            getCalculator();
        }
        return view;
    }

    @Override
    public void fetchData() {
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_CarX.equals(event.getAction())) {
            Car_Index.DataBean dataBean = (Car_Index.DataBean) event.getData();
            if (dataBean != null) {
//                money=String.valueOf(Double.valueOf(dataBean.getPrice())*10000);
                cid=String.valueOf(dataBean.getId());
                getCalculator();
            }
        }
        if (BaseEvent.ChangeXinQuan.equals(event.getAction())) {
            getCalculator();
        }
        if (BaseEvent.Third_Part.equals(event.getAction())) {
            third_part=(String) event.getData();
            getCalculator();
        }
        if (BaseEvent.Iscn.equals(event.getAction())) {
            iscn=(String) event.getData();
            getCalculator();
        }
        if (BaseEvent.Body_Scratches.equals(event.getAction())) {
            body_scratches=(String) event.getData();
            getCalculator();
        }
    }

    private void getCalculator() {
        HttpApi.post(context, getOkObjectCalculator(), new HttpApi.CallBack() {
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
                Log.e("Calculator_Index", s);
                try {
                    cIndex = GsonUtils.parseJSON(s, Calculator_Loan.class);
                    if (cIndex.getStatus() == 1) {
                        AppContext.getIntance().xcqk.clear();
                        textBookTitle.setText(cIndex.getBookTitle());
                        textBookVal.setText(cIndex.getBookVal());
                        textCarName.setText(cIndex.getCar_name());
                        editMoney.setText(cIndex.getMoney());
                        textDex1N.setText(cIndex.getDes1().getN());
                        textDex1V.setText(cIndex.getDes1().getV());
                        textDex2N.setText(cIndex.getDes2().getN());
                        textDex2V.setText(cIndex.getDes2().getV());
                        textNotice.setText(cIndex.getNotice());
                        textbookDesN0.setText(cIndex.getBookDes().get(0).getN());
                        textbookDesV0.setText(cIndex.getBookDes().get(0).getV());
                        textbookDesN1.setText(cIndex.getBookDes().get(1).getN());
                        textbookDesV1.setText(cIndex.getBookDes().get(1).getV());
                        textbookDesN2.setText(cIndex.getBookDes().get(2).getN());
                        textbookDesV2.setText(cIndex.getBookDes().get(2).getV());
                        textDex10N.setText(cIndex.getDes01().getN());
                        textDex10V.setText(cIndex.getDes01().getV());
                        textDex20N.setText(cIndex.getDes02().getN());
                        textDex20V.setText(cIndex.getDes02().getV());
                        viewDes10s.clear();
                        for (int i=0;i<cIndex.getDes01().getS().size();i++){
                            viewDes10s.add(cIndex.getDes01().getS().get(i).getN());
                        }
                        viewDes20s.clear();
                        for (int i=0;i<cIndex.getDes02().getS().size();i++){
                            viewDes20s.add(cIndex.getDes02().getS().get(i).getN());
                        }
                        for (int i=0;i<cIndex.getDes2().getS().size();i++){
                            if (cIndex.getDes2().getS().get(i).getIsc()==1){
                                AppContext.getIntance().xcqk.add(cIndex.getDes2().getS().get(i).getId());
                            }
                        }
                    } else {
                        ToastUtils.showShort(cIndex.getInfo());
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

    private OkObject getOkObjectCalculator() {
        String url = Constant.HOST + Constant.Url.Calculator_Loan;
        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);
        params.put("money",money);
        params.put("third_part",third_part);
        params.put("iscn",iscn);
        params.put("body_scratches",body_scratches);
        params.put("insurance", AppContext.getIntance().xcqk.toString().replace("[","").replace("]",""));
        params.put("down_payment",down_payment);
        params.put("year",year);
        return new OkObject(params, url);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
    }

    @OnClick({R.id.viewCar, R.id.viewDes1, R.id.viewDes2, R.id.viewDes10, R.id.viewDes20})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewCar:
                intent = new Intent();
                intent.setClass(getActivity(), XuanZheCheXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDes1:
                intent = new Intent();
                intent.putExtra("des1Bean",cIndex.getDes1());
                intent.setClass(getActivity(), BiYaoHFDKActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDes2:
                intent = new Intent();
                intent.putExtra("cid",cid);
                intent.putExtra("money",money);
                intent.putExtra("third_part",third_part);
                intent.putExtra("iscn",iscn);
                intent.putExtra("body_scratches",body_scratches);
                intent.setClass(getActivity(), ShangYeBXDKActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDes10:
                new MaterialDialog.Builder(context)
                        .title(cIndex.getDes01().getSt())
                        .items(viewDes10s)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                down_payment=cIndex.getDes01().getS().get(position).getV()+"";
                                getCalculator();
                            }
                        })
                        .show();
                break;
            case R.id.viewDes20:
                new MaterialDialog.Builder(context)
                        .title(cIndex.getDes02().getSt())
                        .items(viewDes20s)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                year=cIndex.getDes02().getS().get(position).getV()+"";
                                getCalculator();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }
}
