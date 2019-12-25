package com.wkq.order.modlue.main.frame.view;

import androidx.core.content.ContextCompat;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.HomeActivity;
import com.wkq.order.modlue.main.ui.adapter.HomeFragmentPagerAdapter;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeView implements MvpView {

    HomeActivity mActivity;

    public HomeView(HomeActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
//        StatusBarUtil.setStatusBarWrite(mActivity);
//        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.white), 0);
        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(mActivity, mActivity.getSupportFragmentManager());

        QMUITabBuilder builder = mActivity.binding.tabs.tabBuilder();
        builder.setSelectedIconScale(1.2f)
                .setTextSize(QMUIDisplayHelper.sp2px(mActivity, 13), QMUIDisplayHelper.sp2px(mActivity, 15))
                .setDynamicChangeIconColor(false);
        QMUITab component = builder
                .setNormalDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_component))
                .setSelectedDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_component_selected))
                .setText("电影")
                .build(mActivity);
        QMUITab util = builder
                .setNormalDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_util))
                .setSelectedDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_util_selected))
                .setText("资讯")
                .build(mActivity);
        QMUITab lab = builder
                .setNormalDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_lab))
                .setSelectedDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.icon_tabbar_lab_selected))
                .setText("开发者")
                .build(mActivity);

        mActivity.binding.tabs.addTab(component)
                .addTab(util)
                .addTab(lab);

        mActivity.binding.pager.setAdapter(homeFragmentPagerAdapter);

        mActivity.binding.pager.setOffscreenPageLimit(3);

        mActivity.binding.tabs.setupWithViewPager(mActivity.binding.pager, false);

        mActivity.binding.tabs.setOnTabClickListener(new QMUITabSegment.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                switch (index) {

                    case 0:
                        StatusBarUtil.setLightMode(mActivity);
                        break;
                    case 1:
                        StatusBarUtil.setDarkMode(mActivity);
                        break;
                    case 2:
                        StatusBarUtil.setDarkMode(mActivity);
//

                        break;
                }
            }
        });

    }
}