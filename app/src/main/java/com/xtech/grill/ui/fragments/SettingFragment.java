package com.xtech.grill.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xtech.grill.R;
import com.xtech.grill.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : 武昌丶鱼
 * email  : reyzarc@163.com
 * time   : 2017-11-25
 * desc   : 设定界面
 */
public class SettingFragment extends Fragment {

    @BindView(R.id.topbar)
    RelativeLayout topbar;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        ButterKnife.bind(this, view);

        UIUtils.initTopbar(getActivity(), topbar, false);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
