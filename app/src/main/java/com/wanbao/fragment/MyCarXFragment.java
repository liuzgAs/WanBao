package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.AiCheDangAnActivity;
import com.wanbao.activity.BanDingCLActivity;
import com.wanbao.activity.CheShouZiZhuanActivity;
import com.wanbao.activity.GengDuoFWActivity;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.SheZhiActivity;
import com.wanbao.activity.WeiBaoDDActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.activity.WoDeCheDuiActivity;
import com.wanbao.activity.WoDeJKActivity;
import com.wanbao.activity.XiaoXiActivity;
import com.wanbao.activity.XinCheZTActivity;
import com.wanbao.activity.YouZhiESCActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ScreenUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_My;
import com.wanbao.modle.WoDe;
import com.wanbao.ui.CircleImageView;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.CheDuiImgViewHolder;
import com.wanbao.viewholder.MyCarBQViewHolder;
import com.wanbao.viewholder.WoDeViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCarXFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author Administrator
 */
public class MyCarXFragment extends PSFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.imageViewTouX)
    CircleImageView imageViewTouX;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.imageViewXX)
    ImageView imageViewXX;
    @BindView(R.id.imageViewSheZ)
    ImageView imageViewSheZ;
    @BindView(R.id.viewTitle)
    FrameLayout viewTitle;
    @BindView(R.id.recyclerWoDe)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    RecyclerArrayAdapter<WoDe> adapter;
    ArrayList<WoDe> woDes = new ArrayList<>();
    private String mParam1;
    private View view;
    private User_My user_my;

    public MyCarXFragment() {
    }

    public static MyCarXFragment newInstance(String param1) {
        MyCarXFragment fragment = new MyCarXFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_my_car_x, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        ViewGroup.LayoutParams layoutParams = viewTitle.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.dp_53) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewTitle.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public void fetchData() {
        initRecycler();
        onRefresh();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (event.getAction().equals(BaseEvent.Change_Data)) {
            onRefresh();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(15f, context));
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<WoDe>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_my_car;
                return new WoDeViewHolder(parent, layout);
            }
        });
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private MyEasyRecyclerView recyclerViewb;
            private TextView textCheShouZZ;
            private ViewSwitcher viewSwitcher;
            private Button btnBangD;
            private View viewACDA;
            private TextView textCarName;
            private TextView textDes;
            private TextView textMoreNum;
            private ImageView imageCar;
            private View viewWDCD;
            private Group group;
            private View viewQBDD;
            private View viewDZF;
            private View viewDQR;
            private View viewDPJ;
            private MyEasyRecyclerView recyclerViewcd;
            private RecyclerArrayAdapter<User_My.InterestBean> adapterb;
            private RecyclerArrayAdapter<String> adaptercd;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_my_car, null);
                recyclerViewb = view.findViewById(R.id.recyclerView);
                textCheShouZZ = view.findViewById(R.id.textCheShouZZ);
                viewSwitcher = view.findViewById(R.id.viewSwitcher);
                btnBangD = view.findViewById(R.id.btnBangD);
                viewACDA = view.findViewById(R.id.viewACDA);
                textCarName = view.findViewById(R.id.textCarName);
                textDes = view.findViewById(R.id.textDes);
                imageCar = view.findViewById(R.id.imageCar);
                viewWDCD = view.findViewById(R.id.viewWDCD);
                recyclerViewcd = view.findViewById(R.id.recyclerViewcd);
                group = view.findViewById(R.id.group);
                viewQBDD = view.findViewById(R.id.viewQBDD);
                viewDZF = view.findViewById(R.id.viewDZF);
                viewDQR = view.findViewById(R.id.viewDQR);
                viewDPJ = view.findViewById(R.id.viewDPJ);
                textMoreNum = view.findViewById(R.id.textMoreNum);
                initRecyclerb();
                initRecyclercd();
                textCheShouZZ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.setClass(getActivity(), CheShouZiZhuanActivity.class);
                        startActivity(intent);
                    }
                });
                btnBangD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.setClass(getActivity(), BanDingCLActivity.class);
                        startActivity(intent);
                    }
                });
                viewACDA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), AiCheDangAnActivity.class);
                        startActivity(intent);
                    }
                });
                viewQBDD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.putExtra("currentItem", 0);
                        intent.setClass(getActivity(), WeiBaoDDActivity.class);
                        startActivity(intent);
                    }
                });
                viewDZF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.putExtra("currentItem", 2);
                        intent.setClass(getActivity(), WeiBaoDDActivity.class);
                        startActivity(intent);
                    }
                });
                viewDQR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.putExtra("currentItem", 3);
                        intent.setClass(getActivity(), WeiBaoDDActivity.class);
                        startActivity(intent);
                    }
                });
                viewDPJ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.putExtra("currentItem", 4);
                        intent.setClass(getActivity(), WeiBaoDDActivity.class);
                        startActivity(intent);
                    }
                });
                viewWDCD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), WoDeCheDuiActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (user_my != null) {
                    adapterb.clear();
                    adapterb.addAll(user_my.getInterest());
                    if (user_my.getCarNum() == 0) {
                        viewSwitcher.setDisplayedChild(0);
                        group.setVisibility(View.GONE);
                    } else {
                        viewSwitcher.setDisplayedChild(1);
                        group.setVisibility(View.VISIBLE);
                        if (user_my.getData() != null) {
                            textCarName.setText(user_my.getData().getCar_name());
                            textDes.setText(user_my.getData().getCar_no());
                            GlideApp.with(getContext())
                                    .asBitmap()
                                    .load(user_my.getData().getImg())
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(imageCar);
                        }
                        if (user_my.getCar_team().getIs_show() == 1) {
                            viewWDCD.setVisibility(View.VISIBLE);
                            adaptercd.clear();
                            adaptercd.addAll(user_my.getCar_team().getHeadimg());
                            if (user_my.getCar_team().getHeadimg().size() > 0) {
                                textMoreNum.setText(user_my.getCar_team().getMore_num());
                            }
                        } else {
                            viewWDCD.setVisibility(View.GONE);
                        }
                    }
                } else {
                    viewSwitcher.setDisplayedChild(0);
                    group.setVisibility(View.GONE);
                }
            }

            /**
             * 初始化recyclerview
             */
            private void initRecyclerb() {
                recyclerViewb.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewb.setAdapter(adapterb = new RecyclerArrayAdapter<User_My.InterestBean>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_bq;
                        return new MyCarBQViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(5, context));
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerViewb.addItemDecoration(spaceDecoration);
                recyclerViewb.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                recyclerViewb.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        recyclerViewb.setScroll(true);
                    }
                });
                recyclerViewb.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
            }

            /**
             * 初始化recyclerview
             */
            private void initRecyclercd() {
                recyclerViewcd.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewcd.setAdapter(adaptercd = new RecyclerArrayAdapter<String>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_image_cd;
                        return new CheDuiImgViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(5, context));
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerViewcd.addItemDecoration(spaceDecoration);
                recyclerViewcd.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                recyclerViewcd.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        recyclerViewcd.setScroll(true);
                    }
                });
                recyclerViewcd.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                switch (adapter.getItem(position).getId()) {
                    case 0:
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent = new Intent();
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent = new Intent();
                        intent.setClass(getActivity(), XinCheZTActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), YouZhiESCActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent();
                        intent.setClass(getActivity(), GengDuoFWActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), AiCheDangAnActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), WeiXiuBYActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent();
                        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                            intent.setClass(context, LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        intent.setClass(getActivity(), WoDeJKActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imageViewTouX, R.id.imageViewXX, R.id.imageViewSheZ})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageViewTouX:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), CheShouZiZhuanActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewXX:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(getActivity(), XiaoXiActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewSheZ:
                intent.setClass(getActivity(), SheZhiActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getMyCar();
    }

    private void getMyCar() {
        HttpApi.post(context, getOkObjectMyCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("User_My", s);
                try {
                    user_my = GsonUtils.parseJSON(s, User_My.class);
                    int status = user_my.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        if (user_my.getCarNum() == 0) {
                            adapter.add(new WoDe("新车展厅", R.mipmap.icon_new_xczt, 0));
                            adapter.add(new WoDe("优质二手车", R.mipmap.icon_new_yzesc, 1));
                            adapter.add(new WoDe("更多服务", R.mipmap.icon_new_gdfw, 2));
                        } else {
                            adapter.add(new WoDe("爱车体检", R.mipmap.icon_new_acty, 3));
                            adapter.add(new WoDe("新车展厅", R.mipmap.icon_new_xczt, 0));
                            adapter.add(new WoDe("优质二手车", R.mipmap.icon_new_yzesc, 1));
                            adapter.add(new WoDe("维修保养", R.mipmap.icon_new_wxby, 4));
                            adapter.add(new WoDe("我的金库", R.mipmap.icon_new_gdfw, 5));
                            adapter.add(new WoDe("更多服务", R.mipmap.icon_new_gdfw, 2));
                        }
                        if (!TextUtils.isEmpty(user_my.getNickname())) {
                            textName.setText(user_my.getNickname());
                        } else {
                            textName.setText("");
                        }
                        GlideApp.with(getContext())
                                .asBitmap()
                                .load(user_my.getHeadimg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageViewTouX);
                    } else {
                        ToastUtils.showShort(user_my.getInfo());
                        user_my = null;
                        adapter.clear();
                        adapter.add(new WoDe("新车展厅", R.mipmap.icon_new_xczt, 0));
                        adapter.add(new WoDe("优质二手车", R.mipmap.icon_new_yzesc, 1));
                        adapter.add(new WoDe("更多服务", R.mipmap.icon_new_gdfw, 2));
                    }
                } catch (Exception e) {
                    user_my = null;
                    adapter.clear();
                    adapter.add(new WoDe("新车展厅", R.mipmap.icon_new_xczt, 0));
                    adapter.add(new WoDe("优质二手车", R.mipmap.icon_new_yzesc, 1));
                    adapter.add(new WoDe("更多服务", R.mipmap.icon_new_gdfw, 2));
                }
            }

            @Override
            public void onError() {
                user_my = null;
                adapter.clear();
                adapter.add(new WoDe("新车展厅", R.mipmap.icon_new_xczt, 0));
                adapter.add(new WoDe("优质二手车", R.mipmap.icon_new_yzesc, 1));
                adapter.add(new WoDe("更多服务", R.mipmap.icon_new_gdfw, 2));
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectMyCar() {
        String url = Constant.HOST + Constant.Url.User_My;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        return new OkObject(params, url);
    }
}
