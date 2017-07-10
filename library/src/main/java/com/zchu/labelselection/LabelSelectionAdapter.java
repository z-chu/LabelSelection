package com.zchu.labelselection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * author : zchu
 * date   : 2017/7/10
 * desc   :
 */

public class LabelSelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LabelSelectionItem> mData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;

    private boolean isEditing;

    public LabelSelectionAdapter(List<LabelSelectionItem> data) {
        this.mData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        mRecyclerView = (RecyclerView) parent;
        mContext = parent.getContext();
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(mContext);
        }
        switch (viewType) {
            case LabelSelectionItem.TYPE_LABEL_SELECTED:
                viewHolder = new LabelSelectedViewHolder(mLayoutInflater.inflate(R.layout.item_label, parent, false));
                break;
            case LabelSelectionItem.TYPE_LABEL_UNSELECTED:
                viewHolder = new LabelUnselectedViewHolder(mLayoutInflater.inflate(R.layout.item_label, parent, false));
                break;
            case LabelSelectionItem.TYPE_LABEL_SELECTED_TITLE:
                //LabelTitleViewHolder selectedTitleViewHolder = new LabelTitleViewHolder(mLayoutInflater.inflate(R.layout.item_label_title, parent, false));
                viewHolder = new LabelTitleViewHolder(mLayoutInflater.inflate(R.layout.item_label_title, parent, false));
                break;
            case LabelSelectionItem.TYPE_LABEL_UNSELECTED_TITLE:
                LabelTitleViewHolder unselectedTitleViewHolder = new LabelTitleViewHolder(mLayoutInflater.inflate(R.layout.item_label_title, parent, false));
                unselectedTitleViewHolder.tvAction.setVisibility(View.GONE);
                viewHolder = unselectedTitleViewHolder;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LabelSelectionItem labelSelectionItem = mData.get(position);
        if (holder instanceof LabelSelectedViewHolder) {
            ((LabelSelectedViewHolder) holder).tvName.setText(labelSelectionItem.getLabel().getName());
        } else if (holder instanceof LabelUnselectedViewHolder) {
            ((LabelUnselectedViewHolder) holder).tvName.setText(labelSelectionItem.getLabel().getName());
        } else if (holder instanceof LabelTitleViewHolder) {
            ((LabelTitleViewHolder) holder).tvTitle.setText(labelSelectionItem.getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setNewData(List<LabelSelectionItem> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public static class LabelSelectedViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView ivRemove;

        public LabelSelectedViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivRemove = (ImageView) itemView.findViewById(R.id.iv_remove);
        }
    }

    public static class LabelUnselectedViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;

        public LabelUnselectedViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public static class LabelTitleViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAction;

        public LabelTitleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAction = (TextView) itemView.findViewById(R.id.tv_action);
        }
    }
}
