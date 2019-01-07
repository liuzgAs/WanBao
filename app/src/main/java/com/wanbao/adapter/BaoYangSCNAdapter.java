package com.wanbao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.PanelAdapter;
import com.wanbao.R;
import com.wanbao.modle.Usercar_Manual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhigang on 2019/1/3/003.
 *
 * @author LiuZG
 */
public class BaoYangSCNAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 4;
    private static final int ROOM_TYPE = 0;
    private static final int DATE_TYPE = 1;
    private static final int ORDER_TYPE = 2;
    private List<Usercar_Manual.Cm21kmBean> roomInfoList = new ArrayList<>();
    private List<Usercar_Manual.DataBean> dateInfoList = new ArrayList<>();
    private List<Usercar_Manual.DataBean> ordersList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return roomInfoList.size();
    }

    @Override
    public int getColumnCount() {
        return dateInfoList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case DATE_TYPE:
                setDateView(column, (DateViewHolder) holder);
                break;
            case ROOM_TYPE:
                setRoomView(row, (RoomViewHolder) holder);
                break;
            case ORDER_TYPE:
                setOrderView(row, column, (OrderViewHolder) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setOrderView(row, column, (OrderViewHolder) holder);
        }
    }

    private void setDateView(int pos, DateViewHolder viewHolder) {
        Usercar_Manual.DataBean dateInfo = dateInfoList.get(pos);
        if (dateInfo != null && pos > 0) {
            viewHolder.dateTextView.setText(dateInfo.getName());
            viewHolder.weekTextView.setText("");
        }
    }

    private void setRoomView(int pos, RoomViewHolder viewHolder) {
        Usercar_Manual.Cm21kmBean roomInfo = roomInfoList.get(pos);
        if (roomInfo != null && pos > 0) {
            viewHolder.roomTypeTextView.setText(roomInfo.getM());
            viewHolder.roomNameTextView.setText(roomInfo.getK());
        }
    }

    private void setOrderView(final int row, final int column, OrderViewHolder viewHolder) {
        ArrayList<List<String>> orderInfo = new ArrayList<>();
        if (ordersList.get(row).getIsv() == 0) {
            orderInfo.add(ordersList.get(row).getV0());
            String a = orderInfo.get(row - 1).get(column - 1);
            viewHolder.nameTextView.setText(a);
        }else {
            viewHolder.nameTextView.setText(ordersList.get(row).getV1());
        }
    }

    private static class DateViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView weekTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            this.dateTextView = (TextView) itemView.findViewById(R.id.date);
            this.weekTextView = (TextView) itemView.findViewById(R.id.week);
        }

    }

    private static class RoomViewHolder extends RecyclerView.ViewHolder {
        public TextView roomTypeTextView;
        public TextView roomNameTextView;

        public RoomViewHolder(View view) {
            super(view);
            this.roomTypeTextView = (TextView) view.findViewById(R.id.room_type);
            this.roomNameTextView = (TextView) view.findViewById(R.id.room_name);
        }
    }

    private static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView statusTextView;
        public View view;

        public OrderViewHolder(View view) {
            super(view);
            this.view = view;
            this.statusTextView = (TextView) view.findViewById(R.id.status);
            this.nameTextView = (TextView) view.findViewById(R.id.guest_name);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATE_TYPE:
                return new DateViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_date_info, parent, false));
            case ROOM_TYPE:
                return new RoomViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_room_info, parent, false));
            case ORDER_TYPE:
                return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_order_info, parent, false));
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_order_info, parent, false));
    }

    public void setRoomInfoList(List<Usercar_Manual.Cm21kmBean> roomInfoList) {
        this.roomInfoList = roomInfoList;
    }

    public void setDateInfoList(List<Usercar_Manual.DataBean> dateInfoList) {
        this.dateInfoList = dateInfoList;
    }

    public void setOrdersList(List<Usercar_Manual.DataBean> ordersList) {
        this.ordersList = ordersList;
    }
}
