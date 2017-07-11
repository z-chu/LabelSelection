package com.pifubao.labelselection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zchu.labelselection.Label;
import com.zchu.labelselection.LabelSelectionFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Label> selectedLabels=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            selectedLabels.add(new Label(i,"选中"+i));
        }
        ArrayList<Label> unselectedLabels=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            unselectedLabels.add(new Label(i,"未选中"+i));
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, LabelSelectionFragment.newInstance(selectedLabels,unselectedLabels))
                .commit();
    }
}
