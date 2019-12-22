package com.wkq.order.modlue.main.frame.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.modlue.main.ui.adapter.MoviesAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoviesFragment;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesView implements MvpView {

    MoviesFragment mFragment;

    public MoviesView(MoviesFragment mFragment) {
        this.mFragment=mFragment;
    }

    public void initView() {

        MoviesAdapter moviesAdapter=new MoviesAdapter(mFragment.getActivity());

        mFragment.binding.rvMovies.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        mFragment.binding.rvMovies.setAdapter(moviesAdapter);

    }
}
