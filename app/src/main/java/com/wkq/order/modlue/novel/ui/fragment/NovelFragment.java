package com.wkq.order.modlue.novel.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.fragment.MvpBindingFragment;
import com.wkq.order.R;
import com.wkq.order.modlue.novel.frame.presenter.NobelPresenter;
import com.wkq.order.modlue.novel.frame.view.NovelView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelFragment extends MvpBindingFragment<NovelView, NobelPresenter, ViewDataBinding> {




    public static NovelFragment newInstance(Context context) {
        Bundle args = new Bundle();
//        args.putString("test", xx);
        NovelFragment moviesFragment = new NovelFragment();
        moviesFragment.setArguments(args);
        return moviesFragment;

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_novel;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
