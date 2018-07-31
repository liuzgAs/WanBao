package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.SideLetterBar;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ListViewUtility;
import com.wanbao.modle.City_List;
import com.wanbao.modle.OkObject;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XuanZheCSActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.listview_all_Star)
    ListView listviewAllStar;
    @BindView(R.id.side_letter_bar)
    SideLetterBar sideLetterBar;
    @BindView(R.id.tv_letter_overlay)
    TextView tvLetterOverlay;
    private List<City_List.CityBean> indexCitylistCity = new ArrayList<>();
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zhe_cs);
        ButterKnife.bind(this);
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
        titleText.setText("选择城市");
        sideLetterBar.setOverlay(tvLetterOverlay);
        mAdapter = new MyAdapter();
        listviewAllStar.setAdapter(mAdapter);
        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {

            @Override
            public void onLetterChanged(String letter, int position) {
                listviewAllStar.setSelection(position);
            }
        });
    }

    @Override
    protected void initData() {
        getCity();
    }

    private void getCity(){
        HttpApi.post(context, getOkObjectCS(), new HttpApi.CallBack() {
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
                    City_List indexCitylist = GsonUtils.parseJSON(s, City_List.class);
                    if (indexCitylist.getStatus() == 1) {
                        indexCitylistCity = indexCitylist.getCity();
                        mAdapter.notifyDataSetChanged();
                    } else if (indexCitylist.getStatus() == 3) {
//                        MyDialog.showReLoginDialog(ChengShiXZActivity.this);
                    } else {
                        ToastUtils.showShort(indexCitylist.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常！");
                finish();
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectCS() {
        String url = Constant.HOST+Constant.Url.City_List;
        HashMap<String, String> params = new HashMap<>();
//        params.put("p", String.valueOf(1));
        return new OkObject(params, url);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
    class MyAdapter extends BaseAdapter {


        class ViewHolder {
            public ListView listViewAStarLetter;
            public TextView textViewLetter;
            public TextView textViewLine2;
        }

        @Override
        public int getCount() {
            return indexCitylistCity.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.star_listitem, null);
                holder.listViewAStarLetter = (ListView) convertView.findViewById(R.id.listViewAStarLetter);
                holder.textViewLetter = (TextView) convertView.findViewById(R.id.textViewLetter);
                holder.textViewLine2 = (TextView) convertView.findViewById(R.id.textViewLine2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final List<City_List.CityBean.ListBean> listEntityList = indexCitylistCity.get(position).getList();
            holder.listViewAStarLetter.setAdapter(new MyStarAdapter(listEntityList));
            ListViewUtility.setListViewHeightBasedOnChildren(holder.listViewAStarLetter);
            holder.listViewAStarLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    finish();
                }
            });

            holder.textViewLetter.setText(indexCitylistCity.get(position).getTitle());
            if (listEntityList.size() == 0) {
                holder.textViewLetter.setVisibility(View.GONE);
                holder.textViewLine2.setVisibility(View.GONE);
            } else {
                holder.textViewLetter.setVisibility(View.VISIBLE);
                holder.textViewLine2.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        class MyStarAdapter extends BaseAdapter {
            private List<City_List.CityBean.ListBean> listEntityList = new ArrayList<>();

            public MyStarAdapter(List<City_List.CityBean.ListBean> listEntityList) {
                this.listEntityList = listEntityList;
            }

            class ViewHolder {
                public TextView textCity;
            }

            @Override
            public int getCount() {
                return listEntityList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = getLayoutInflater().inflate(R.layout.item_city, null);
                    holder.textCity = (TextView) convertView.findViewById(R.id.textCity);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.textCity.setText(listEntityList.get(position).getName());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Choose_CS,listEntityList.get(position)));
                        finish();
                    }
                });
                return convertView;
            }
        }
    }
}
