package com.wkq.order.modlue.demo.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.demo.ui.activity.CustomBehaviorActivity;
import com.wkq.order.modlue.demo.ui.adapter.CustomBehaviorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-11
 * <p>
 * 用途:
 */


public class CustomBehaviorView implements MvpView {
    CustomBehaviorActivity mActivity;

    public CustomBehaviorView(CustomBehaviorActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);

        mActivity.binding.rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        CustomBehaviorAdapter customBehaviorAdapter = new CustomBehaviorAdapter(mActivity);
        mActivity.binding.rvContent.setAdapter(customBehaviorAdapter);

        List<String> listString = new ArrayList<>();

        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");

        customBehaviorAdapter.addItems(listString);

    }
}
