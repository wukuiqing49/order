package com.wkq.order.modlue.novel.frame.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.order.modlue.novel.frame.view.NovelView;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.engine.EasyBook;
import com.zia.easybookmodule.rx.Subscriber;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NobelPresenter extends MvpBasePresenter<NovelView> {

    public void getData(NovelFragment novelFragment) {


        EasyBook.getHottestRank().subscribe(new Subscriber<HottestRank>() {
            @Override
            public void onFinish(@NonNull HottestRank hottestRank) {

                if (getView()!=null&&hottestRank!=null)getView().setData(hottestRank);
            }

            @Override
            public void onError(@NonNull Exception e) {
                Log.e("获取数据失败:", "");
            }

            @Override
            public void onMessage(@NonNull String message) {

            }

            @Override
            public void onProgress(int progress) {

            }
        });



    }
}
