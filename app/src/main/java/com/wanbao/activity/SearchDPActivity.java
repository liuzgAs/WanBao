package com.wanbao.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.utils.LogUtil;
import com.blankj.utilcode.util.SPUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Store_Map;
import com.wanbao.viewholder.HeaderSerarchXViewHolder;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

public class SearchDPActivity extends BaseActivity implements View.OnClickListener {
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Store_Map.DataBean> adapter;
    private EditText editSouSuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dp);
        init();
    }


    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
    }


    @Override
    protected void initViews() {
        viewBar = findViewById(R.id.viewBar);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        editSouSuo = (EditText) findViewById(R.id.editSouSuo);
        ((TextView) findViewById(R.id.titleText)).setText("搜索");
        initRecycler();
        setListeners();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Store_Map.DataBean>(SearchDPActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_header_sousuo_location;
                return new HeaderSerarchXViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                Intent intent = new Intent();
                intent.putExtra(Constant.INTENT_KEY.value, adapter.getItem(position));
                setResult(Constant.REQUEST_RESULT_CODE.address, intent);
                finish();
            }
        });
    }

    private void setListeners() {
        findViewById(R.id.imageback).setOnClickListener(this);
        editSouSuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                recyclerView.showProgress();
                HttpApi.post(SearchDPActivity.this, getOkObject(editable.toString().trim()), new HttpApi.CallBack() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            Store_Map skillIndex = GsonUtils.parseJSON(s, Store_Map.class);
                            if (skillIndex.getStatus() == 1) {
                                adapter.clear();
                                adapter.addAll(skillIndex.getData());
                                adapter.notifyDataSetChanged();
                            } else if (skillIndex.getStatus() == 3) {
                            } else {
                                Toast.makeText(SearchDPActivity.this, skillIndex.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(SearchDPActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(SearchDPActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject(String address) {
        String url = Constant.HOST + Constant.Url.Store_Map;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
//        params.put("lat", String.valueOf(latLng.latitude));
//        params.put("lng", String.valueOf(latLng.longitude));
        params.put("lng", SPUtils.getInstance().getString(Constant.SF.Longitude) + "");
        params.put("lat", SPUtils.getInstance().getString(Constant.SF.Latitude) + "");
        params.put("cid", "0");
        params.put("keywords", address);
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        HttpApi.post(SearchDPActivity.this, getOkObject(""), new HttpApi.CallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtil.e("SearchLocationActivity--onSuccess", s + "");
                try {
                    Store_Map skillIndex = GsonUtils.parseJSON(s, Store_Map.class);
                    if (skillIndex.getStatus() == 1) {
                        adapter.clear();
                        adapter.addAll(skillIndex.getData());
                        adapter.notifyDataSetChanged();
                    } else if (skillIndex.getStatus() == 3) {
                    } else {
                        Toast.makeText(SearchDPActivity.this, skillIndex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SearchDPActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(SearchDPActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
