package com.xtech.grill.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtech.grill.R;
import com.xtech.grill.ui.activity.EditProfileActivity;
import com.xtech.grill.ui.adapter.DragListAdapter;
import com.xtech.grill.utils.UIUtils;
import com.xtech.grill.widget.DynamicListView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : 武昌丶鱼
 * email  : reyzarc@163.com
 * time   : 2017-12-02
 * desc   :
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.topbar)
    RelativeLayout topbar;
    @BindView(R.id.drag_list_view)
    DynamicListView dragListView;
    @BindView(R.id.tv_edit_list)
    TextView tvEditList;
    @BindView(R.id.tv_create_profile)
    TextView tvCreateProfile;
    @BindView(R.id.tv_save_list)
    TextView tvSaveList;


    private DragListAdapter mListAdapter;
    private List<String> rowData = Arrays.asList("1", "2", "3", "4", "5");


    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        ButterKnife.bind(this, view);

        UIUtils.initTopbar(getActivity(), topbar, false);
        initView();

        return view;
    }

    private void initView() {
        mListAdapter = new DragListAdapter(getActivity(), rowData);
        mListAdapter.fillTranslarentMobileRowBackground(getResources().getColor(android.R.color.holo_orange_dark));
        mListAdapter.bindToListView(dragListView);
    }

    @OnClick({R.id.tv_save_list,R.id.tv_edit_list, R.id.tv_create_profile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_list://编辑烧烤列表
                dragListView.enableDrag(true);
                break;
            case R.id.tv_create_profile://创建烧烤程序
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;
            case R.id.tv_save_list://保存烧烤列表
                dragListView.enableDrag(false);
                mListAdapter.notifyDataSetChanged();
                break;
        }
    }

}
