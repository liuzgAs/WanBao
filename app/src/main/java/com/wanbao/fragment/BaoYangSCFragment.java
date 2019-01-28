package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.adapter.CustomAdapter;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zhouchaoyuan.excelpanel.ExcelPanel;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaoYangSCFragment extends PSFragment {
    Unbinder unbinder;
    @BindView(R.id.content_container)
    ExcelPanel contentContainer;
    @BindView(R.id.textDes)
    TextView textDes;
    private View view;
    private String id;
    private CustomAdapter adapter;

    public BaoYangSCFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BaoYangSCFragment newInstance(String id) {
        BaoYangSCFragment fragment = new BaoYangSCFragment();
        Bundle args = new Bundle();
        args.putSerializable("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = (String) getArguments().getSerializable("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_bao_yang_sc, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void getStore() {
        HttpApi.post(context, getOkObjectStore(), new HttpApi.CallBack() {
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
                LogUtils.e("Store_Index", s);
                try {
                    Usercar_Manual uManual = GsonUtils.parseJSON(s, Usercar_Manual.class);
                    int status = uManual.getStatus();
                    if (status == 1) {
                        StringBuffer stringBuffer=new StringBuffer();
                        for (int j=0;j<uManual.getDes().size();j++){
                            if (j==0){
                                stringBuffer.append(uManual.getDes().get(j).getV());
                            }else {
                                stringBuffer.append("\n"+uManual.getDes().get(j).getV());
                            }
                        }
                        textDes.setText(stringBuffer.toString());
                        adapter = new CustomAdapter(context, uManual.getData(),new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                            }
                        });
                        contentContainer.setAdapter(adapter);
                        ArrayList<String> major=new ArrayList<>();
                        for (int i=0;i<uManual.getCm21km().size();i++){
                            major.add("");
                        }
                        List<List<String>> majorData = new ArrayList<>();
                        for (int i = 0; i < uManual.getData().size(); i++) {
                            if (uManual.getData().get(i).getV0().size()==0){
                                majorData.add(major);
                            }else {
                                majorData.add(uManual.getData().get(i).getV0());
                            }
                        }
                        LogUtils.e("majorData", majorData.toString());
                        adapter.setAllData(uManual.getData(), uManual.getCm21km(), majorData);
//                        adapter.enableFooter();//load more
//                        adapter.enableHeader();//load history
                    } else {
                        ToastUtils.showShort(uManual.getInfo());
                    }
                } catch (Exception e) {
//                    MyDialog.dialogFinish(getActivity(), "数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(getActivity(), "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();

            }

        });
    }

    private OkObject getOkObjectStore() {
        String url = Constant.HOST + Constant.Url.Usercar_Maintenance_Manual;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void fetchData() {
        getStore();
    }

}
