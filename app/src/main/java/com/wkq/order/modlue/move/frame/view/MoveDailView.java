package com.wkq.order.modlue.move.frame.view;

import android.text.TextUtils;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.modlue.move.ui.MoveCreditsAdapter;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.modlue.move.ui.adapter.RecommendSimilarMoveAdapter;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.GlideUtlis;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDailView implements MvpView {


    MoveDetailActivity mActivity;
    private MoveCreditsAdapter moveCreditsAdapter;
    private RecommendSimilarMoveAdapter rsAdapter;

    public MoveDailView(MoveDetailActivity activity) {
        mActivity = activity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        mActivity.binding.setOnclick(mActivity);
        mActivity.binding.rvCredits.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        moveCreditsAdapter = new MoveCreditsAdapter(mActivity);
        mActivity.binding.rvCredits.setAdapter(moveCreditsAdapter);

        mActivity.binding.rvRs.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        rsAdapter = new RecommendSimilarMoveAdapter(mActivity);
        mActivity.binding.rvRs.setAdapter(rsAdapter);


    }


    public void showMessage(String message) {
        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);
    }

    public void setData(BaseInfo<MoveDbMoveDetailInfo> data) {

        if (data == null || mActivity == null) return;
        MoveDbMoveDetailInfo info = data.getData();

        mActivity.binding.setData(info);
        //类型
        String genreStr = "";

        List<MoveDbMoveDetailInfo.GenresBean> genres = info.getGenres();
        if (genres != null) {
            for (MoveDbMoveDetailInfo.GenresBean genre : genres) {
                genreStr = genreStr.concat(genre.getName()).concat("/");
            }
        }

        if (TextUtils.isEmpty(genreStr)) {
            genreStr = "未知";
        } else {
            int last = genreStr.lastIndexOf("/");
            genreStr = genreStr.substring(0, last);
        }
        mActivity.binding.tvMoveType.setText(genreStr);
        GlideUtlis.loadMoveImg200(mActivity, info.getPoster_path(), mActivity.binding.ivIcon);
        GlideUtlis.loadMoveImg500(mActivity, info.getBackdrop_path(), mActivity.binding.ivDrop);

        moveCreditsAdapter.addItems(info.getCredits().getCast());
        rsAdapter.addItems(info.getSimilar_movies().getResults());


    }
}
