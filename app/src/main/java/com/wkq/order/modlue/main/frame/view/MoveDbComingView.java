package com.wkq.order.modlue.main.frame.view;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.AppBarLayout;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.PixelsUtil;
import com.wkq.base.utlis.RandomUtil;
import com.wkq.base.utlis.SharedPreferencesHelper;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbComingInfo;
import com.wkq.net.model.MoveDbNowPlayingInfo;
import com.wkq.net.model.MoveDbPopularInfo;
import com.wkq.order.R;
import com.wkq.order.modlue.main.modle.BannerInfo;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.modlue.main.ui.adapter.MoveDbComingAdapter;
import com.wkq.order.modlue.main.ui.fragment.MoveDbComingFragment;
import com.wkq.order.utils.BannerImageLoader;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.MoveDbDataSaveUtlis;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbComingView implements MvpView {
    MoveDbComingFragment mFragment;
    private MoveDbComingAdapter moviesAdapter;
    private List<BannerInfo> mBannerBeanList = new ArrayList<>();

    public MoveDbComingView(MoveDbComingFragment moveDbComingFragment) {
        mFragment = moveDbComingFragment;
    }


    public void initView() {
        initBanner();
        initToolBar();


        moviesAdapter = new MoveDbComingAdapter(mFragment.getActivity());

        mFragment.binding.rvMovies.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
        mFragment.binding.rvMovies.setAdapter(moviesAdapter);

    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        if (fraction >= 0.5) {
            fraction = (float) 0.5;
        }
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private void initToolBar() {


        mFragment.binding.appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (!mFragment.isExpend) {
                        StatusBarUtil.setLightMode(mFragment.getActivity());
                        mFragment.isExpend = !mFragment.isExpend;
                    }
                } else {
                    if (mFragment.isExpend) {
                        StatusBarUtil.setDarkMode(mFragment.getActivity());
                        mFragment.isExpend = !mFragment.isExpend;
                    }
                }
            }
        });
    }


    private void initBanner() {
        //轮播图的常规设置
        mFragment.binding.bannerMovies.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mFragment.binding.bannerMovies.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置显示圆形指示器和标题（水平显示）
        mFragment.binding.bannerMovies.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //banner设置方法全部调用完毕时最后调用

//        mBannerBeanList = MoveDbDataSaveUtlis.getBannerList(mFragment.getActivity());
        mBannerBeanList = MoveDbDataSaveUtlis.getBannerList(mFragment.getActivity());
        playBaner();
        mFragment.binding.bannerMovies.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerInfo bannerInfo = mBannerBeanList.get(position);
                if (bannerInfo.getUrlPath().equals("1008611"))
                    startVideoPlay();
                else
                    showMessage("待开发");
            }
        });


    }

    private void playBaner() {
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for (BannerInfo bannerBean : mBannerBeanList) {
            images.add(bannerBean.getImgUrl());
            titles.add(bannerBean.getTitle());
        }
        mFragment.binding.bannerMovies.setDelayTime(2000);

        mFragment.binding.bannerMovies.setImages(images);
        mFragment.binding.bannerMovies.setBannerTitles(titles);
        mFragment.binding.bannerMovies.start();
    }

    private void startVideoPlay() {

        VideoWebListActivity.startVideoWebList(mFragment.getActivity());
    }

    public void setData(BaseInfo<MoveDbPopularInfo> data) {

        if (data.getData() != null && data.getData().getResults() != null) {
            moviesAdapter.addItems(data.getData().getResults());
        }

    }

    public void showMessage(String message) {
        if (mFragment == null && mFragment.getActivity() == null) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }

    public void setBanner(BaseInfo<MoveDbNowPlayingInfo> data) {

        if (data != null && data.getData() != null && data.getData().getResults() != null && data.getData().getResults().size() > 0) {
            int size = data.getData().getResults().size();
            List<MoveDbNowPlayingInfo.ResultsBean> list = data.getData().getResults();
            if (mBannerBeanList != null && mBannerBeanList.size() > 0) mBannerBeanList.clear();
            BannerInfo helpBanner = new BannerInfo();
            helpBanner.setTitle("免费看电影");
            helpBanner.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100383423448679.jpg");
            helpBanner.setUrlPath("1008611");
            mBannerBeanList.add(helpBanner);
            for (int i = 0; i < 5; i++) {
                int one = RandomUtil.getRandomForIntegerBounded(0, size);
                MoveDbNowPlayingInfo.ResultsBean info = list.get(one);
                BannerInfo bannerBean = new BannerInfo(info.getTitle(), Constant.MOVE_DB_IMG_BASE_400.concat(info.getPoster_path()), info.getId() + "");
                mBannerBeanList.add(bannerBean);
            }
            MoveDbDataSaveUtlis.saveBannerData(mFragment.getActivity(), mBannerBeanList);
            playBaner();
        }
    }
}
