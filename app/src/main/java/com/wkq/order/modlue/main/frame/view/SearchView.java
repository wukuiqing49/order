package com.wkq.order.modlue.main.frame.view;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.main.ui.SearchActivity;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchView implements MvpView {

    SearchActivity mActivity;

    public SearchView(SearchActivity activity) {
        mActivity = activity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);


        mActivity.binding.rlBack.setOnClickListener(view -> mActivity.finish());
        mActivity.binding.rlSearch.setOnClickListener(view -> {

            if(mActivity!=null&&mActivity.getPresenter()!=null){
                if (mActivity.binding.etSearch.getText()==null)
                mActivity.getPresenter().searchData(mActivity,mActivity.binding.etSearch.getText().toString());
            }

        });


    }
}
