package com.wkq.order.modlue.developer.frame.view;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.order.modlue.developer.model.DeveloperInfo;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperAdapter;
import com.wkq.order.modlue.developer.ui.fragment.DeveloperFragment;
import com.wkq.order.modlue.web.ui.PlayHelperActivity;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-24
 * <p>
 * 用途:
 */


public class DeveloperView implements MvpView {

    DeveloperFragment mFragment;

    public DeveloperView(DeveloperFragment fragment) {
        mFragment = fragment;
    }

    public void initView() {
        DeveloperAdapter moviesAdapter = new DeveloperAdapter(mFragment.getActivity());
        mFragment.binding.rvDeveloper.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        mFragment.binding.rvDeveloper.setAdapter(moviesAdapter);

        List<DeveloperInfo> list = new ArrayList<>();
        list.add(new DeveloperInfo(0, "播放视频"));
        list.add(new DeveloperInfo(1, "播放帮助"));
        list.add(new DeveloperInfo(2, "广告介绍"));

        list.add(new DeveloperInfo(3, "联系开发者"));
        list.add(new DeveloperInfo(4, "关于我们"));

        moviesAdapter.addItems(list);

        moviesAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                DeveloperInfo info = (DeveloperInfo) program;
                switch (info.getId()) {
                    //播放视频
                    case 0:
                        VideoWebListActivity.startVideoWebList(mFragment.getActivity());
                        break;
                    case 1:
                        PlayHelperActivity.startPlayHelperActivity(mFragment.getActivity());
                        break;
                    case 2:
                        showMessage("广告介绍");
                        break;
                    case 3:
                        showMessage("联系开发者");
                        break;
                    case 4:
                        showMessage("关于我们");
                        break;
                    case 5:

                        break;

                }
            }
        });
    }

    public void showMessage(String message) {

        if (mFragment == null || TextUtils.isEmpty(message)) return;

        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }
}
