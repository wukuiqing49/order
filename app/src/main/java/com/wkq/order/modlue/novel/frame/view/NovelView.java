package com.wkq.order.modlue.novel.frame.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.modlue.novel.ui.activity.preview.BookActivity;
import com.wkq.order.modlue.novel.ui.activity.search.SearchActivity;
import com.wkq.order.modlue.novel.ui.adapter.NovelAdapter;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.bean.rank.RankBook;

import java.util.List;

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

        mAdapter.setOnClickListener(new NovelAdapter.OnNovelAdapterClickListener() {
            @Override
            public void onItemClick(RankBook book) {
//                if (mFragment != null && mFragment.getPresenter() != null)
//                    mFragment.getPresenter().getNovelInfo(book.getBookName());

                Intent intent = new Intent(mFragment.getActivity(), SearchActivity.class);
                intent.putExtra("searchKey",book.getBookName());
                mFragment.getActivity().startActivity(intent);
            }
        });

    }

    public void setData(HottestRank hottestRank) {
        mAdapter.addItems(hottestRank.getHottestRankClassifies());
    }

    public void showMessage(String message) {
        if (mFragment == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }

    public void processData(List<Book> books) {

        if (books != null && books.size() > 1) {

            for (Book book : books) {
                if (!TextUtils.isEmpty(book.getImageUrl())) {
//
//                    Intent intent = new Intent(mFragment.getActivity(), BookActivity.class);
//                    intent.putExtra("book", book);
//                    intent.putExtra("scroll", false);

//                    mFragment.getActivity().startActivity(intent);
                    Intent intent = new Intent(mFragment.getActivity(), SearchActivity.class);
                    mFragment.getActivity().startActivity(intent);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        val p = arrayListOf<Pair<View, String>>(
//                                Pair.create(bg_searchEt, "transition_search_bg"),
//                                Pair.create(bookstore_search_icon, "transition_search")
//                )
//                        val options = ActivityOptions.makeSceneTransitionAnimation(activity, *Java2Kotlin.getPairs(p))
//                        mFragment.getActivity().startActivity(intent, options.toBundle());
//                    } else {
//
//                    }

                    return;
                }
            }


        }
    }
}
