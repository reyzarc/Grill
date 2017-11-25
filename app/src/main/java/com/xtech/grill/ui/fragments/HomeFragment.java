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
 * time   : 2017-11-24
 * desc   :
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.topbar)
    RelativeLayout topbar;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        ButterKnife.bind(this, view);

        UIUtils.initTopbar(getActivity(), topbar, true);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
