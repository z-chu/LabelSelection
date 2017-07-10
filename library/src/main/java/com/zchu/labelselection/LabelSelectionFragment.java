package com.zchu.labelselection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zchu
 * date   : 2017/7/10
 * desc   :
 */

public class LabelSelectionFragment extends Fragment implements OnChannelDragListener {
    private static final String BUNDLE_SELECTED_LABELS = "selected_labels";
    private static final String BUNDLE_UNSELECTED_LABELS = "unselected_labels";


    private RecyclerView mRecyclerView;
    private LabelSelectionAdapter mLabelSelectionAdapter;
    private ItemTouchHelper mHelper;

    public static LabelSelectionFragment newInstance(ArrayList<Label> selectedLabels, ArrayList<Label> unselectedLabels) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_SELECTED_LABELS, selectedLabels);
        args.putParcelableArrayList(BUNDLE_UNSELECTED_LABELS, unselectedLabels);
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

        Bundle arguments = getArguments();
        if(arguments!=null){
            final ArrayList<LabelSelectionItem> labelSelectionItems = new ArrayList<>();
            ArrayList<Label> selectedLabels = arguments.getParcelableArrayList(BUNDLE_SELECTED_LABELS);
            if(selectedLabels!=null&&selectedLabels.size()>0){
                labelSelectionItems.add(new LabelSelectionItem(LabelSelectionItem.TYPE_LABEL_SELECTED_TITLE, "已选择"));
                for (Label selectedLabel : selectedLabels) {
                    labelSelectionItems.add(new LabelSelectionItem(LabelSelectionItem.TYPE_LABEL_SELECTED,selectedLabel));
                }
            }
            ArrayList<Label> unselectedLabels = arguments.getParcelableArrayList(BUNDLE_UNSELECTED_LABELS);
            if(unselectedLabels!=null&&unselectedLabels.size()>0){
                labelSelectionItems.add(new LabelSelectionItem(LabelSelectionItem.TYPE_LABEL_UNSELECTED_TITLE, "未选择"));
                for (Label unselectedLabel : unselectedLabels) {
                    labelSelectionItems.add(new LabelSelectionItem(LabelSelectionItem.TYPE_LABEL_UNSELECTED,unselectedLabel));
                }
            }
            mLabelSelectionAdapter = new LabelSelectionAdapter(labelSelectionItems);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = mLabelSelectionAdapter.getItemViewType(position);
                    return itemViewType == LabelSelectionItem.TYPE_LABEL_SELECTED || itemViewType == LabelSelectionItem.TYPE_LABEL_UNSELECTED ? 1 : 3;
                }
            });
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(mLabelSelectionAdapter);

            ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
            mLabelSelectionAdapter.setOnChannelDragListener(this);
            mHelper = new ItemTouchHelper(callBack);
            mHelper.attachToRecyclerView(mRecyclerView);

        }




    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        List<LabelSelectionItem> data = mLabelSelectionAdapter.getData();
        LabelSelectionItem labelSelectionItem = data.get(starPos);
        //先删除之前的位置
        data.remove(starPos);
        //添加到现在的位置
        data.add(endPos, labelSelectionItem);
        mLabelSelectionAdapter.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onStarDrag(RecyclerView.ViewHolder viewHolder) {
        mHelper.startDrag(viewHolder);
    }
}
