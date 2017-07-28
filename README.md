# LabelSelection
仿今日头条 、网易新闻、UC 的频道排序、移动

![](http://7xq7yd.com1.z0.glb.clouddn.com/LabelSelection2.gif)

[下载 app-simple.apk](https://raw.githubusercontent.com/z-chu/LabelSelection/master/app-simple.apk)
## 使用

**1. 创建**
```java
		//创建置顶且默认选择标签
  		ArrayList<Label> alwaySelectedLabels = new ArrayList<>();
        alwaySelectedLabels.add(new Label(1, "Android"));
        alwaySelectedLabels.add(new Label(3, "Java"));
		//创建默认选择标签
        ArrayList<Label> selectedLabels = new ArrayList<>();
        selectedLabels.add(new Label(2, "IOS"));
        selectedLabels.add(new Label(4, "GO"));
        ...
		//其他标签
        ArrayList<Label> unselectedLabels = new ArrayList<>();
        unselectedLabels.add(new Label(9, "RxJava"));
        unselectedLabels.add(new Label(10, "Dagger2"));
        ...
        //创建LabelSelectionFragment绑定到你的Activity即可
        labelSelectionFragment=LabelSelectionFragment.newInstance(selectedLabels, unselectedLabels,alwaySelectedLabels);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, labelSelectionFragment)
                .commit();
```

**2. 监听**

给绑定LabelSelectionFragment的Activity实现`OnEditFinishListener`接口，即可监听数据变化
```java

 	@Override
    public void onEditFinish(ArrayList<Label> selectedLabels, ArrayList<Label> unselectedLabel, ArrayList<Label> alwaySelectedLabels) {
        Logger.t("selectedLabels").e(selectedLabels);
        Logger.t("unselectedLabel").e(unselectedLabel);
		Logger.e(alwaySelectedLabels);
    }
```
**3.处理返回按键**
```java
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (labelSelectionFragment.cancelEdit()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

```