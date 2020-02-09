package com.wkq.order.modlue.novel.frame.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.wkq.order.modlue.novel.ui.activity.preview.BookActivity;
import com.wkq.order.modlue.novel.ui.activity.preview.BookMarkUtil;
import com.wkq.order.modlue.novel.ui.activity.preview.PreviewActivity;
import com.wkq.order.modlue.novel.ui.adapter.NovelChapterAdapter;
import com.wkq.order.utils.DataBindingAdapter;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.Catalog;


import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NoveInfolView implements MvpView {

    NovelInfoActivity mActivity;
    private NovelChapterAdapter mAdapter;


    public NoveInfolView(NovelInfoActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        mActivity.binding.rvChapter.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new NovelChapterAdapter(mActivity);
        mActivity.binding.rvChapter.setAdapter(mAdapter);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId() == R.id.root) {
                    Catalog catalog = (Catalog) program;
                    int index = mAdapter.getList().indexOf(catalog);
                    //更新书签
//                    BookMarkUtil.insertOrUpdate(index, mActivity.bookName, catalog.getChapterName());
//                    Intent intent = new Intent(mActivity, PreviewActivity.class);
//                    intent.putExtra("bookName", mActivity.bookName);
//                    intent.putExtra("siteName", catalog.getChapterName());
//                    mActivity.startActivity(intent);


                    Intent intent = new Intent(mActivity, BookActivity.class);
                    intent.putExtra("book", mActivity.bookName);
                    intent.putExtra("scroll", false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val p = arrayListOf<Pair<View, String>>(Pair.create(itemView.item_book_image, "book_image"))
//            val options = ActivityOptions.makeSceneTransitionAnimation(activity, *Java2Kotlin.getPairs(p))
//            startActivity(intent, options.toBundle())
//        } else {
//            startActivity(intent)
//        }
                    mActivity.startActivity(intent);

                }
            }
        });

        mActivity.binding.rlBack.setOnClickListener(view -> finish(""));
    }

    public void setBookData(Book bookData) {
        if (bookData == null) return;
        mActivity.binding.setData(bookData);
        if (!TextUtils.isEmpty(bookData.getImageUrl())) {
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivDrop);
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivIcon);
        }
    }

    public void initNovelChapter(List<Catalog> catalogs) {
        mAdapter.addItems(catalogs);
    }

    public void hindLoading() {
        mActivity.binding.rlLoading.setVisibility(View.GONE);
    }

    public void finish(String message) {
        showMessage(message);

        mActivity.finish();
    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }


}
