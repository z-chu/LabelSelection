# LabelSelection
仿今日头条 、网易新闻、UC 的频道排序、移动

![](http://7xq7yd.com1.z0.glb.clouddn.com/LabelSelection.gif)

## 使用

**1. 创建**
```java
   ArrayList<Label> selectedLabels = new ArrayList<>();
        selectedLabels.add(new Label(1, "Android"));
        selectedLabels.add(new Label(2, "IOS"));
        ...

        ArrayList<Label> unselectedLabels = new ArrayList<>();
        unselectedLabels.add(new Label(9, "RxJava"));
        unselectedLabels.add(new Label(10, "Dagger2"));
        ...
        //创建LabelSelectionFragment绑定到你的Activity即可
        labelSelectionFragment=LabelSelectionFragment.newInstance(selectedLabels, unselectedLabels);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, labelSelectionFragment)
                .commit();
```

**2. 监听**

给绑定LabelSelectionFragment的Activity实现`OnEditFinishListener`接口，即可监听数据变化
```java

 @Override
    public void onEditFinish(ArrayList<Label> selectedLabels, ArrayList<Label> unselectedLabel) {
        Logger.t("selectedLabels").e(selectedLabels);
        Logger.t("unselectedLabel").e(unselectedLabel);
    }
```
