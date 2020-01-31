package com.wkq.order.modlue.novel.frame.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.modlue.novel.ui.adapter.NovelAdapter;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;
import com.zia.easybookmodule.bean.rank.HottestRank;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelView implements MvpView {
    NovelFragment mFragment;
    private NovelAdapter mAdapter;

    public NovelView(NovelFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void initView() {
        StatusBarUtil.setDarkMode(mFragment.getActivity());
        mFragment.binding.rvContent.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));

        mAdapter = new NovelAdapter(mFragment.getActivity());
        mFragment.binding.rvContent.setAdapter(mAdapter);

    }

    public void setData(HottestRank hottestRank) {

        mAdapter.addItems(hottestRank.getHottestRankClassifies());


    }
}
