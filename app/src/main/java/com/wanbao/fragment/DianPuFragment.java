package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.modle.Store_Index;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DianPuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DianPuFragment extends PSFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
    private Store_Index.DataBean dataBean;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DianPuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DianPuFragment newInstance(Store_Index.DataBean param1) {
        DianPuFragment fragment = new DianPuFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBean = (Store_Index.DataBean) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public void fetchData() {
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
        EventBus.getDefault().post(new BaseEvent(BaseEvent.LatLng,latLng));
    }
}
