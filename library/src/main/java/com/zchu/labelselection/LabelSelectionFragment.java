package com.zchu.labelselection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : zchu
 * date   : 2017/7/10
 * desc   :
 */

public class LabelSelectionFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LabelSelectionAdapter mLabelSelectionAdapter;

    public static LabelSelectionFragment newInstance() {

        Bundle args = new Bundle();

        LabelSelectionFragment fragment = new LabelSelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(inflater.getContext());
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLabelSelectionAdapter = new LabelSelectionAdapter(null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mLabelSelectionAdapter.getItemViewType(position);
                return itemViewType == LabelSelectionItem.TYPE_LABEL_SELECTED || itemViewType == LabelSelectionItem.TYPE_LABEL_UNSELECTED ? 1 : 3;
            }
        });

    }
}
