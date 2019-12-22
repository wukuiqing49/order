package com.wkq.order.modlue.web.view;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.web.model.CheckLineInfo;
import com.wkq.order.modlue.web.ui.CheckLineActivity;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.modlue.web.ui.WebDemoActivity;
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

        List<CheckLineInfo> checkLines = new ArrayList<>();
        checkLines.add(new CheckLineInfo("VIP无广告","http://demo.hao0606.com/?url="));
        checkLines.add(new CheckLineInfo("VIP2无广告","https://jiexi.bm6ig.cn/?url="));

        checkLines.add(new CheckLineInfo("上滑动全屏去广告备用(有广告)","http://jx.aeidu.cn/index.php?url="));

        checkLines.add(new CheckLineInfo("重点(有广告)","http://jx.du2.cc/?url="));
        checkLines.add(new CheckLineInfo("重点(有广告","http://jx.jx.jx1jx1.drgxj.com/jxjxjx1jx1/598ASJoihjUY1_d256F15.php?url=url="));
        checkLines.add(new CheckLineInfo("速度1(有广告)","http://jx.drgxj.com/?url="));


//        checkLines.add(new CheckLineInfo("速度2(有广告)","http://jx.618ge.com/?url="));
//        checkLines.add(new CheckLineInfo("测试1","http://jiexi.071811.cc/jx2.php?url="));
//        checkLines.add(new CheckLineInfo("测试2","https://www.administratorw.com/admin.php?url="));

//        checkLines.add(new CheckLineInfo("测试4","http://jx.618g.com/?url="));
//        checkLines.add(new CheckLineInfo("测试5","http://api.baiyug.vip/index.php?url="));
//        checkLines.add(new CheckLineInfo("测试6","http://jx.reclose.cn/jx.php/?url="));
//        checkLines.add(new CheckLineInfo("测试7","http://jx.618g.com/?url="));
//        checkLines.add(new CheckLineInfo("测试8","http://jiexi.071811.cc/jx2.php?url="));


//        http://jx.drgxj.com/?url=https://www.iqiyi.com/v_19rrk406qo.html



//        checkLines.add(new CheckLineInfo("备用(有广告)","http://jx.598110.com/?url=url="));
//
//        checkLines.add(new CheckLineInfo("备用(有广告)","http://jx.aeidu.cn/index.php?url="));


        CheckLineAdapter mAdapter = new CheckLineAdapter(mActivity);
        mActivity.binding.rvLine.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvLine.setAdapter(mAdapter);
        mAdapter.addItems(checkLines);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId() == R.id.ll_root) {
                    CheckLineInfo info = (CheckLineInfo) program;
                   if (info!=null&& !TextUtils.isEmpty(mActivity.videoUrl)&&!TextUtils.isEmpty(info.getLineUrl())){
//                       VideoWebviewActivity.startActivity(mActivity,info.getLineUrl().concat(mActivity.videoUrl));
                       VideoWebviewActivity.startActivity(mActivity,info.getLineUrl().concat("http://v.qq.com/x/cover/uzuqdig87eggmiw.html?ptag=douban.movie"));

//                       WebDemoActivity.startActivity(mActivity,info.getLineUrl().concat(mActivity.videoUrl));
                   }else {
                       showMessage("无效视频地址");
                   }
                }
            }
        });
        mActivity.binding.tvCancel.setOnClickListener(v -> {
            mActivity.finish();
        });

    }

    public void showMessage(String message) {

        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);

    }
}
