package com.wanbao.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.fragment.PSFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends PSFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.lv_main)
    ListView lvMain;
    Unbinder unbinder;
    @BindView(R.id.wl_main)
    SwipeRefreshLayout wlMain;

    public static MainFragment newInstance() {
        MainFragment mf = new MainFragment();
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View vHead= View.inflate(context, R.layout.header_shouye, null);
        lvMain.addHeaderView(vHead);
        mList=new ArrayList<>();
        if (mList.size()==0){
            mList.add("已到底线了");
        }
        myAdapter=new MyAdapter(context);
        lvMain.setAdapter(myAdapter);
        setsl();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {

    }

    private void setsl(){
        wlMain.setProgressBackgroundColorSchemeResource(android.R.color.white);
// 设置下拉进度的主题颜色
        wlMain.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

// 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        wlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (wlMain!=null){
                            wlMain.setRefreshing(false);
                        }
                    }
                }, 1200);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private List<String> mList;
    MyAdapter myAdapter;
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;//布局装载器对象

        public MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        //ListView需要显示的数据数量
        public int getCount() {
            return mList.size();
        }

        @Override
        //指定的索引对应的数据项
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        //指定的索引对应的数据项ID
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            //如果view未被实例化过，缓存池中没有对应的缓存
            if (convertView == null) {
                viewHolder = new ViewHolder();
                // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
                convertView = mInflater.inflate(R.layout.item, null);

                //对viewHolder的属性进行赋值
                viewHolder.dixian = (TextView) convertView.findViewById(R.id.dixian);

                //通过setTag将convertView与viewHolder关联
                convertView.setTag(viewHolder);
            }else{//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 取出bean对象
            String bean = mList.get(position);

            // 设置控件的数据
            viewHolder.dixian.setText(bean);

            return convertView;
        }
        // ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
        class ViewHolder{
            public TextView dixian;
        }
    }
}
