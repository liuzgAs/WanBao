package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.wanbao.R;
import com.wanbao.adapter.BaoYangSCNAdapter;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.fragment.BaseFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Manual;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaoYangSCNFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaoYangSCNFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "id";
    Unbinder unbinder;
    @BindView(R.id.scrollable_panel)
    ScrollablePanel scrollablePanel;

    private String id;
    private View view;
    BaoYangSCNAdapter adapter;
    public BaoYangSCNFragment() {
        // Required empty public constructor
    }

    public static BaoYangSCNFragment newInstance(String id) {
        BaoYangSCNFragment fragment = new BaoYangSCNFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_bao_yang_scn, container, false);
            unbinder = ButterKnife.bind(this, view);
            init();
        }
        return view;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {
        adapter=new BaoYangSCNAdapter();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        getStore();
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
                        adapter.setRoomInfoList(uManual.getCm21km());
                        adapter.setDateInfoList(uManual.getData());
                        adapter.setOrdersList(uManual.getData());
                        scrollablePanel.setPanelAdapter(adapter);
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

}
