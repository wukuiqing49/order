package com.wkq.order.modlue.developer.frame.view;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.database.AppDatabase;
import com.wkq.database.bean.LocalBook;
import com.wkq.database.bean.NetBook;
import com.wkq.order.R;
import com.wkq.order.modlue.developer.ui.activity.NovelDownLoadActivity;
import com.wkq.order.modlue.developer.ui.activity.NovelSubscriptionActivity;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperNovelDownLoadAdapter;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperNovelSubscribeAdapter;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-02-09
 * <p>
 * 用途:
 */


public class NovelDownLoadView implements MvpView {

    NovelDownLoadActivity mActivity;

    public NovelDownLoadView(NovelDownLoadActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);


        List<LocalBook> books = AppDatabase.getAppDatabase().localBookDao().getLocalBooks();

        TextView tvTitle = mActivity.binding.toolBar.findViewById(R.id.tv_title);
        tvTitle.setText("小说下载");

        DeveloperNovelDownLoadAdapter mAdapter = new DeveloperNovelDownLoadAdapter(mActivity);

        mActivity.binding.rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvContent.setAdapter(mAdapter);
        mAdapter.addItems(books);


    }
}
