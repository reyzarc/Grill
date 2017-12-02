package com.xtech.grill.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtech.grill.R;
import com.xtech.grill.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : 武昌丶鱼
 * email  : reyzarc@163.com
 * time   : 2017-11-24
 * desc   : 主页界面
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.topbar)
    RelativeLayout topbar;
    @BindView(R.id.tv_wifi_info)
    TextView tvWifiInfo;
    @BindView(R.id.tv_wifi)
    TextView tvWifi;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    @BindView(R.id.tv_timer)
    TextView tvTimer;
    @BindView(R.id.tv_flashlight)
    TextView tvFlashlight;

    private WifiManager mWifiManager;
    private WifiInfo mWifiInfo;

    private ProgressDialog mDialog;

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

        UIUtils.initTopbar(getActivity(), topbar, false);


        return view;
    }


    @OnClick({R.id.tv_wifi, R.id.tv_switch, R.id.tv_timer, R.id.tv_flashlight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wifi:
                mDialog = new ProgressDialog(getActivity());
                mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mDialog.setMessage("处理中。。。");
                mDialog.setCancelable(false);
                mDialog.show();
                isConnectToGrill();
                break;
            case R.id.tv_switch:
                break;
            case R.id.tv_timer:
                break;
            case R.id.tv_flashlight:
                break;
        }
    }

    /**
     * 检查是否连接上grill
     */
    private void isConnectToGrill() {
        mWifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
        if (null != mWifiInfo && null != mWifiInfo.getSSID()) {
            String info = "getSSID()=" + mWifiInfo.getSSID() + "\n"
                    + "getBSSID()=" + mWifiInfo.getBSSID() + "\n"
                    + "getHiddenSSID()=" + mWifiInfo.getHiddenSSID() + "\n"
                    + "getLinkSpeed()=" + mWifiInfo.getLinkSpeed() + "\n"
                    + "getMacAddress()=" + mWifiInfo.getMacAddress() + "\n"
                    + "getNetworkId()=" + mWifiInfo.getNetworkId() + "\n"
                    + "getRssi()=" + mWifiInfo.getRssi() + "\n"
                    + "getSupplicantState()=" + mWifiInfo.getSupplicantState() + "\n"
                    + "getDetailedStateOf()=" + mWifiInfo.getDetailedStateOf(mWifiInfo.getSupplicantState());
            tvWifiInfo.setText(info);
        } else {
            tvWifiInfo.setText("没有连接到wifi");
        }

        mDialog.dismiss();
    }
}
