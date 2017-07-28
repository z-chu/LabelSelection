package com.labelselection.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.zchu.labelselection.Label;
import com.zchu.labelselection.LabelSelectionFragment;
import com.zchu.labelselection.OnEditFinishListener;
import com.zchu.log.Logger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnEditFinishListener {

    private LabelSelectionFragment labelSelectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init("LabelSelection");
        setContentView(R.layout.activity_main);

        ArrayList<Label> alwaySelectedLabels = new ArrayList<>();
        alwaySelectedLabels.add(new Label(1, "Android"));
        alwaySelectedLabels.add(new Label(3, "Java"));

        ArrayList<Label> selectedLabels = new ArrayList<>();
        selectedLabels.add(new Label(2, "IOS"));
        selectedLabels.add(new Label(4, "GO"));
        selectedLabels.add(new Label(5, "前端"));
        selectedLabels.add(new Label(6, "React"));
        selectedLabels.add(new Label(8, "PHP"));

        ArrayList<Label> unselectedLabels = new ArrayList<>();
        unselectedLabels.add(new Label(9, "RxJava"));
        unselectedLabels.add(new Label(10, "Dagger2"));
        unselectedLabels.add(new Label(11, "MVP"));
        unselectedLabels.add(new Label(12, "程序员"));
        unselectedLabels.add(new Label(13, "GitHub"));
        unselectedLabels.add(new Label(14, "python"));
        unselectedLabels.add(new Label(15, "Google"));
        unselectedLabels.add(new Label(16, ".Net"));
        unselectedLabels.add(new Label(17, "IT"));
        unselectedLabels.add(new Label(18, "面试"));
        unselectedLabels.add(new Label(19, "小说"));
        unselectedLabels.add(new Label(20, "游戏"));
        unselectedLabels.add(new Label(21, "动漫"));
        unselectedLabels.add(new Label(22, "博客"));
        unselectedLabels.add(new Label(23, "搞事情"));
        unselectedLabels.add(new Label(24, "互相伤害"));
        unselectedLabels.add(new Label(25, "二次元"));
        unselectedLabels.add(new Label(26, "美白"));
        unselectedLabels.add(new Label(27, "护肤"));
        unselectedLabels.add(new Label(28, "JS"));
        unselectedLabels.add(new Label(29, "妹子"));
        unselectedLabels.add(new Label(30, "干货"));

        labelSelectionFragment = LabelSelectionFragment.newInstance(selectedLabels, unselectedLabels,alwaySelectedLabels);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, labelSelectionFragment)
                .commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (labelSelectionFragment.cancelEdit()) {
                return true;//不执行父类点击事件
            }

        }
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

    @Override
    public void onEditFinish(ArrayList<Label> selectedLabels, ArrayList<Label> unselectedLabel, ArrayList<Label> alwaySelectedLabels) {
        Logger.e(selectedLabels);
        Logger.e(unselectedLabel);
        Logger.e(alwaySelectedLabels);
    }
}
