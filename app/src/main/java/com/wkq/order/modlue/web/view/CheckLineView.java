package com.wkq.order.modlue.web.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.web.model.CheckLineInfo;
import com.wkq.order.modlue.web.ui.CheckLineActivity;
import com.wkq.order.modlue.web.ui.adapter.CheckLineAdapter;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/20
 * <p>
 * 简介:
 */
public class CheckLineView implements MvpView {

    CheckLineActivity mActivity;

    public CheckLineView(CheckLineActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        List<CheckLineInfo> ckeckLines = new ArrayList<>();

        CheckLineAdapter mAdapter = new CheckLineAdapter(mActivity);
        mActivity.binding.rvLine.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvLine.setAdapter(mAdapter);
        mAdapter.addItems(ckeckLines);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId() == R.id.ll_root) {
                    CheckLineInfo info = (CheckLineInfo) program;
                    showMessage(info.getLineUrl());
                }
            }
        });

    }

    public void showMessage(String message) {

        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);

    }
}
