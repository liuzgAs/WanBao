package com.wanbao.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.util.AmapUtil;
import com.wanbao.modle.MapApps;
import com.wanbao.modle.Store_Map;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DianPuXFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DianPuXFragment extends PSFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.imageDp)
    ImageView imageDp;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textPhone)
    TextView textPhone;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.textAddress)
    TextView textAddress;
    @BindView(R.id.textKm)
    TextView textKm;
    Unbinder unbinder;
    @BindView(R.id.viewMove)
    LinearLayout viewMove;
    private View view;
    // TODO: Rename and change types of parameters
    private Store_Map.DataBean dataBean;
    private ArrayList<MapApps> mapApps;
    public static DianPuXFragment newInstance(Store_Map.DataBean param1) {
        DianPuXFragment fragment = new DianPuXFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBean = (Store_Map.DataBean) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public void fetchData() {
        getAmp();
        if (dataBean != null) {
            textName.setText(dataBean.getTitle());
            textPhone.setText(dataBean.getPhone());
            textDes.setText(dataBean.getDes());
            textAddress.setText(dataBean.getAddress());
            textKm.setText(dataBean.getDistance());
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(dataBean.getImg())
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageDp);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_dian_pu, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.viewMove)
    public void onViewClicked() {
        LatLng latLng = new LatLng(Double.valueOf(dataBean.getLat()), Double.valueOf(dataBean.getLng()));
        EventBus.getDefault().post(new BaseEvent(BaseEvent.LatLng, latLng));
        if (mapApps == null) {
            return;
        }
        if (mapApps.size() == 0) {
            Toast.makeText(context, "未安装任何地图APP", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<String> stringsmap = new ArrayList<>();
        for (int i = 0; i < mapApps.size(); i++) {
            stringsmap.add(mapApps.get(i).getName());
        }
        final String[] strings = (String[]) stringsmap.toArray(new String[stringsmap.size()]);
        if (strings == null) {
            return;
        }
        new AlertDialog.Builder(context)
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mapApps.get(i).getId() == 1) {
                            AmapUtil.getInstance().openBaiduNavi(context, String.valueOf(AmapUtil.getInstance().gcj02tobd09(Double.valueOf(dataBean.getLng()),Double.valueOf(dataBean.getLat()))[1]), String.valueOf(AmapUtil.getInstance().gcj02tobd09(Double.valueOf(dataBean.getLng()),Double.valueOf(dataBean.getLat()))[0]));
                        } else if (mapApps.get(i).getId() == 2) {
                            AmapUtil.getInstance().goToGaodeNaviActivity2(context, "牵车", "我的位置", dataBean.getLat(),
                                    dataBean.getLng(), dataBean.getAddress(), "0", "", "0", "高德导航");
                        }
                    }
                })
                .show();
    }

    private void getAmp() {
        mapApps = new ArrayList<>();
        if (AmapUtil.isInstallByRead("com.baidu.BaiduMap")) {
            mapApps.add(new MapApps(1, "百度地图", "com.baidu.BaiduMap"));
        }
        if (AmapUtil.isInstallByRead("com.autonavi.minimap")) {
            mapApps.add(new MapApps(2, "高德地图", "com.autonavi.minimap"));
        }
    }
}
