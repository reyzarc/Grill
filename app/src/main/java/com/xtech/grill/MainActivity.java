package com.xtech.grill;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.xtech.grill.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_home_tab)
    RadioButton mainHomeTab;
    @BindView(R.id.main_profiles_tab)
    RadioButton mainProfilesTab;
    @BindView(R.id.main_setting_tab)
    RadioButton mainSettingTab;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));

        initView();

        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);

        //统一将状态栏颜色设置为透明,让fragment或者activity的topbar自己去控制颜色显示,从而有效避免切换时引起的视觉上的状态栏闪跳
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏和导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
        }
    }


    private void initView() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainHomeTab.setChecked(true);
                        break;
                    case 1:
                        mainProfilesTab.setChecked(true);
                        break;
                    case 2:
                        mainSettingTab.setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_home_tab://主页
                        mainHomeTab.setChecked(true);
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.main_profiles_tab://烧烤程序
                        mainProfilesTab.setChecked(true);
                        viewPager.setCurrentItem(1, true);
                        break;
                    case R.id.main_setting_tab://设定
                        mainSettingTab.setChecked(true);
                        viewPager.setCurrentItem(2, true);
                        break;

                }
            }
        });
    }
}
