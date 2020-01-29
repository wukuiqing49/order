package com.wkq.order.modlue.novel.frame.presenter;

import androidx.annotation.NonNull;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.order.modlue.novel.frame.view.NoveInfolView;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.engine.EasyBook;
import com.zia.easybookmodule.rx.Disposable;
import com.zia.easybookmodule.rx.Subscriber;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NovelInfoPresenter  extends MvpBasePresenter<NoveInfolView> {


    private Disposable disposable;

    public void getNovelInfo(NovelInfoActivity novelInfoActivity, String ceotent) {
        disposable = EasyBook.getHottestRank().subscribe(new Subscriber<HottestRank>() {
              @Override
              public void onFinish(@NonNull HottestRank hottestRank) {

              }

              @Override
              public void onError(@NonNull Exception e) {

              }

              @Override
              public void onMessage(@NonNull String message) {

              }

              @Override
              public void onProgress(int progress) {

              }
          });

    }

    public void cancel(){
        if (disposable!=null)disposable.dispose();
    }
}
