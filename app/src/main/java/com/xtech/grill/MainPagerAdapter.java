package com.xtech.grill;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xtech.grill.ui.fragments.HomeFragment;


/**
 * author : 武昌丶鱼
 * email  : reyzarc@163.com
 * time   : 2017-11-24
 * desc   :
 */
public class MainPagerAdapter extends FragmentPagerAdapter{

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return HomeFragment.newInstance();
            case 2:
                return HomeFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}