package com.wanbao.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wanbao.R;
import com.wanbao.adapter.BaoYangTopAdapter;
import com.wanbao.adapter.MultipleItemQuickAdapter;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.fragment.BaseFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.ObservableHorizontalScrollView;
import com.wanbao.modle.MultipleItem;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Manual;
import com.wanbao.viewholder.BaoYangSCLeftViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaoYangSCXFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaoYangSCXFragment extends BaseFragment {
    private static final String ID = "ID";
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.recyclerLeft)
    EasyRecyclerView recyclerLeft;
    @BindView(R.id.recyclerTop)
    RecyclerView recyclerTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.HorizontalTop)
    ObservableHorizontalScrollView hScrollView;
    @BindView(R.id.textDes)
    TextView textDes;
    private String id;
    private View view;
    private BaoYangTopAdapter adapterTop;
    //    private RecyclerArrayAdapter<Usercar_Manual.Cm21kmBean> adapterTop;
    private RecyclerArrayAdapter<Usercar_Manual.DataBean> adapterLeft;


    public BaoYangSCXFragment() {
    }

    public static BaoYangSCXFragment newInstance(String id) {
        BaoYangSCXFragment fragment = new BaoYangSCXFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ID);
        }
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
        initTopRecycler();
        initLeftRecycler();
    }

    @Override
    protected void setListeners() {
        setSyncScrollListener();
        hScrollView.setScrollViewListener(new ObservableHorizontalScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                recyclerLeft.getRecyclerView().removeOnScrollListener(mRoomOSL);
                recyclerView.removeOnScrollListener(mLayerOSL);
            }
        });
    }

    @Override
    protected void initData() {
        getStore();
    }

    /**
     * 初始化recyclerview
     */
    private void initTopRecycler() {
        recyclerTop.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(0f, context));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerTop.addItemDecoration(spaceDecoration);

//        recyclerTop.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recycler, int dx, int dy) {
//                super.onScrolled(recycler, dx, dy);
//                recyclerTop.setScroll(true);
//            }
//        });
//        recyclerTop.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
//            @Override
//            public void daoDiLe() {
//            }
//        });
    }

    /**
     * 初始化recyclerview
     */
    private void initLeftRecycler() {
        recyclerLeft.setLayoutManager(new LinearLayoutManager(context));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, 0, 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerLeft.addItemDecoration(itemDecoration);
        recyclerLeft.setRefreshingColorResources(R.color.basic_color);
        recyclerLeft.setAdapter(adapterLeft = new RecyclerArrayAdapter<Usercar_Manual.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_baoyang_sc_left;
                return new BaoYangSCLeftViewHolder(parent, layout);
            }
        });
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
//                        adapterTop.clear();
//                        adapterTop.addAll(uManual.getCm21km());
                        StringBuffer stringBuffer=new StringBuffer();
                        for (int j=0;j<uManual.getDes().size();j++){
                            if (j==0){
                                stringBuffer.append(uManual.getDes().get(j).getV());
                            }else {
                                stringBuffer.append("\n"+uManual.getDes().get(j).getV());
                            }
                        }
                        textDes.setText(stringBuffer.toString());
                        adapterTop = new BaoYangTopAdapter(uManual.getCm21km());
                        recyclerTop.setAdapter(adapterTop);
                        adapterLeft.clear();
                        adapterLeft.addAll(uManual.getData());
                        final List<MultipleItem> data = new ArrayList<>();
                        for (int i = 0; i < uManual.getData().size(); i++) {
                            if (uManual.getData().get(i).getIsv() == 1) {
                                data.add(new MultipleItem(MultipleItem.TEXT, uManual.getCm21km().size(), uManual.getData().get(i).getV1()));
                            } else {
                                for (int j = 0; j < uManual.getData().get(i).getV0().size(); j++) {
                                    data.add(new MultipleItem(MultipleItem.IMG, 1, uManual.getData().get(i).getV0().get(j)));
                                }
                            }
                        }
                        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(context, data);
                        final GridLayoutManager manager = new GridLayoutManager(context, uManual.getCm21km().size());
                        recyclerView.setLayoutManager(manager);
                        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                                return data.get(position).getSpanSize();
                            }
                        });
                        recyclerView.setAdapter(multipleItemAdapter);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_bao_yang_scx, container, false);
            unbinder = ButterKnife.bind(this, view);
            init();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    /**
     * @创建者 CSDN_LQR
     * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
     */
    public class MyOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this);
            }
        }
    }

    private final RecyclerView.OnScrollListener mLayerOSL = new MyOnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerViews, int dx, int dy) {
            super.onScrolled(recyclerViews, dx, dy);
            // 当楼层列表滑动时，单元（房间）列表也滑动
            recyclerLeft.getRecyclerView().scrollBy(dx, dy);
        }
    };
    private final RecyclerView.OnScrollListener mRoomOSL = new MyOnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerViews, int dx, int dy) {
            super.onScrolled(recyclerViews, dx, dy);
            // 当单元（房间）列表滑动时，楼层列表也滑动
            recyclerView.scrollBy(dx, dy);
        }
    };

    /**
     * 设置两个列表的同步滚动
     */
    private void setSyncScrollListener() {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                // 当列表是空闲状态时
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                // 若是手指按下的动作，且另一个列表处于空闲状态
                if (e.getAction() == MotionEvent.ACTION_DOWN && recyclerLeft.getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    // 记录当前另一个列表的y坐标并对当前列表设置滚动监听
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mLayerOSL);
                } else {
                    // 若当前列表原地抬起手指时，移除当前列表的滚动监听
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mLayerOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerLeft.getRecyclerView().addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mRoomOSL);
                } else {
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRoomOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

}
