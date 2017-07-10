package com.zchu.labelselection;


import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public interface OnChannelDragListener extends OnChannelListener {
    void onStarDrag(RecyclerView.ViewHolder viewHolder);

}
