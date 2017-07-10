package com.zchu.labelselection;

import java.io.Serializable;

/**
 * author : zchu
 * date   : 2017/7/10
 * desc   :
 */

public class LabelSelectionItem implements Serializable {
    static final int TYPE_LABEL_UNSELECTED = 1;
    static final int TYPE_LABEL_SELECTED = 2;
    static final int TYPE_LABEL_SELECTED_TITLE = 3;
    static final int TYPE_LABEL_UNSELECTED_TITLE = 4;


    private int itemType;
    private String title;
    private Label label;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getItemType() {
        return itemType;
    }
}
