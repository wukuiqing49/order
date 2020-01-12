package com.wkq.order.modlue.demo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-11
 * <p>
 * 用途:
 */

public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * @param parent     父布局
     * @param child      跟随滑动的布局的布局
     * @param dependency 滑动的布局
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }
        float dy = dependency.getY() - child.getHeight();
        //设置顶部平移
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();

        child.setTranslationY(y);
        //设置透明

        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }
}