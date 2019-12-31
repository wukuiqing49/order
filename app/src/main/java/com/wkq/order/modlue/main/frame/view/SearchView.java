package com.wkq.order.modlue.main.frame.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.DoublePressed;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.database.dao.MoveSearchHistory;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.activity.SearchActivity;
import com.wkq.order.modlue.main.ui.adapter.MoveDbComingAdapter;
import com.wkq.order.modlue.main.ui.adapter.MoveSearchHistoryAdapter;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchView implements MvpView {

    SearchActivity mActivity;

    MoveSearchHistoryAdapter moveSearchHistoryAdapter;

    MoveDbComingAdapter moveDbComingAdapter;

    public SearchView(SearchActivity activity) {
        mActivity = activity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);
        mActivity.binding.rvSearch.setVisibility(View.GONE);
        mActivity.binding.rlSearchHisory.setVisibility(View.VISIBLE);
        moveSearchHistoryAdapter = new MoveSearchHistoryAdapter(mActivity);
        mActivity.binding.rlBack.setOnClickListener(view -> mActivity.finish());
        mActivity.binding.rlSearch.setOnClickListener(view -> {

            if (mActivity != null && mActivity.getPresenter() != null) {
                if (DoublePressed.onDoublePressed()) return;
                if (mActivity.binding.etSearch.getText() != null) {
                    DataBaseUtils.insertHistoryData(mActivity, mActivity.binding.etSearch.getText().toString());
                    mActivity.getPresenter().searchData(mActivity, mActivity.binding.etSearch.getText().toString());

                }
            }

        });

        mActivity.binding.rvSearchHistory.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvSearchHistory.setAdapter(moveSearchHistoryAdapter);
        List<MoveSearchHistory> historyList = DataBaseUtils.getMoveHistoryData(mActivity);
        if (historyList == null || historyList.size() == 0) {
            mActivity.binding.rlEmpty.setVisibility(View.VISIBLE);
            mActivity.binding.rvSearchHistory.setVisibility(View.GONE);
        } else {
            mActivity.binding.rlEmpty.setVisibility(View.GONE);
            mActivity.binding.rvSearchHistory.setVisibility(View.VISIBLE);
            moveSearchHistoryAdapter.addItems(historyList);

        }

        mActivity.binding.rvSearch.setLayoutManager(new LinearLayoutManager(mActivity));


        moveDbComingAdapter = new MoveDbComingAdapter(mActivity);
        mActivity.binding.rvSearch.setAdapter(moveDbComingAdapter);


        moveDbComingAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program != null && program instanceof MoveDataInfo.ResultsBean) {
                    if (DoublePressed.onDoublePressed()) return;
                    MoveDataInfo.ResultsBean bean = (MoveDataInfo.ResultsBean) program;
                    MoveDetailActivity.startMoveDetail(mActivity, bean.getId() + "");
                }
            }
        });
        moveSearchHistoryAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (program != null && program instanceof MoveSearchHistory) {
                    if (DoublePressed.onDoublePressed()) return;
                    MoveSearchHistory bean = (MoveSearchHistory) program;
                    mActivity.getPresenter().searchData(mActivity, bean.getMoveName());
                }
            }
        });


    }

    public void setSearchData(BaseInfo<MoveDataInfo> data) {
        if (data != null && data.getData() != null) {
            mActivity.binding.rvSearch.setVisibility(View.VISIBLE);
            mActivity.binding.rlSearchHisory.setVisibility(View.GONE);
            moveDbComingAdapter.addItems(data.getData().getResults());
        }

    }
}
