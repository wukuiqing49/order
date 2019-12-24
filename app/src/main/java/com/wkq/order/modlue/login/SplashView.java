package com.wkq.order.modlue.login;

import android.util.Log;

import com.google.gson.Gson;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.SharedPreferencesHelper;
import com.wkq.base.utlis.TimerHelper;
import com.wkq.net.model.MoveDbComingInfo;
import com.wkq.order.modlue.main.ui.HomeActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.MoveDbMoveTypeUtlis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView {
    SplashActivity mActivity;

    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

//        {
//            "id": 10759,
//                "name": "动作冒险"
//        },
//        {
//            "id": 16,
//                "name": "动画"
//        },
//        {
//            "id": 35,
//                "name": "喜剧"
//        },
//        {
//            "id": 80,
//                "name": "犯罪"
//        },
//        {
//            "id": 99,
//                "name": "纪录"
//        },
//        {
//            "id": 18,
//                "name": "剧情"
//        },
//        {
//            "id": 10751,
//                "name": "家庭"
//        },
//        {
//            "id": 10762,
//                "name": "儿童"
//        },
//        {
//            "id": 9648,
//                "name": "悬疑"
//        },
//        {
//            "id": 10763,
//                "name": "新闻"
//        },
//        {
//            "id": 10764,
//                "name": "真人秀"
//        },
//        {
//            "id": 10765,
//                "name": "Sci-Fi & Fantasy"
//        },
//        {
//            "id": 10766,
//                "name": "肥皂剧"
//        },
//        {
//            "id": 10767,
//                "name": "脱口秀"
//        },
//        {
//            "id": 10768,
//                "name": "War & Politics"
//        },
//        {
//            "id": 37,
//                "name": "西部"
//        }
        MoveDbMoveTypeUtlis.putType(mActivity);


        mActivity.timerHelper = new TimerHelper();
        mActivity.timerHelper.startTiming();
        mActivity.timerHelper.setSchedule(0);
        mActivity.timerHelper.setOnTimerListener(new TimerHelper.OnTimerListener() {
            @Override
            public void onSchedule(int schedule) {
                if (schedule == 2) {
                    HomeActivity.startPlayHelperActivity(mActivity);
                }
                Log.e("倒计时:", schedule + "");
            }
        });
        mActivity.timerHelper.startTiming();

    }
}
