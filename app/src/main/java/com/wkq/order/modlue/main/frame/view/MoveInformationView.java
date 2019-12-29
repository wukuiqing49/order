package com.wkq.order.modlue.main.frame.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.adapter.MoveFragmentPagerAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveInformationFragment;
import com.wkq.order.modlue.main.ui.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveInformationView implements MvpView {

    List<String> titleTab = new ArrayList<>();

    MoveInformationFragment mFragment;

    public MoveInformationView(MoveInformationFragment mFragment) {
        this.mFragment = mFragment;
        titleTab.add("正在热播");
        titleTab.add("即将上映");
        titleTab.add("TOP排行榜");
    }

    public void initView() {

//        StatusBarUtil.setColor(mFragment.getActivity(), mFragment.getActivity().getResources().getColor(R.color.white), 0);
        StatusBarUtil.setDarkMode(mFragment.getActivity());

        MoveFragmentPagerAdapter fragmentAdapter = new MoveFragmentPagerAdapter(mFragment.getActivity(), mFragment.getFragmentManager());
        mFragment.binding.vpInformation.setAdapter(fragmentAdapter);


        MagicIndicator magicIndicator = (MagicIndicator) mFragment.binding.magicIndicator;
        CommonNavigator commonNavigator = new CommonNavigator(mFragment.getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleTab == null ? 0 : titleTab.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titleTab.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#88ffffff"));
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragment.binding.vpInformation.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(mFragment.getActivity(), 15));
        titleContainer.setDividerDrawable(mFragment.getResources().getDrawable(R.drawable.simple_splitter));

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mFragment.binding.vpInformation);

    }


}
