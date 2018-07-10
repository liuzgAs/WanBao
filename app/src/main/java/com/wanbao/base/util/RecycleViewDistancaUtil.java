package com.wanbao.base.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuzhigang on 2018/7/10/010.
 *
 * @author LiuZG
 */

public class RecycleViewDistancaUtil {
    public RecycleViewDistancaUtil() {
    }

    public static int getDistance(RecyclerView recyclerView, int ItemPosition) {
        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        View firstVisibItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemHeight = firstVisibItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
        return firstItemPosition == ItemPosition?(firstItemPosition + 1) * itemHeight - firstItemBottom:-1;
    }

    public static int getShaiXuanDistance(RecyclerView recyclerView, int ItemPosition) {
        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        View firstVisibItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemHeight = firstVisibItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
        return firstItemPosition == ItemPosition?firstItemBottom:(firstItemPosition > 3?0:-1);
    }

    public static int getLocation(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        View firstVisibItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();
        int recycleViewHeight = recyclerView.getHeight();
        int itemHeight = firstVisibItem.getHeight();
        return (itemCount - firstItemPosition - 1) * itemHeight - recycleViewHeight;
    }

    public static int getReDistance(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        View firstVisibItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();
        int recycleViewHeight = recyclerView.getHeight();
        int itemHeight = firstVisibItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
        return (itemCount - firstItemPosition - 1) * itemHeight - recycleViewHeight + firstItemBottom;
    }
}
