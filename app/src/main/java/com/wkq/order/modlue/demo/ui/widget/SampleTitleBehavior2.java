package com.wkq.order.modlue.demo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

public class SampleTitleBehavior2 extends CoordinatorLayout.Behavior<View> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY=0;

    public SampleTitleBehavior2() {
    }

    public SampleTitleBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //根据逻辑判断rs的取值
    //返回false表示child不依赖dependency，ture表示依赖
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return true;
    }

    /**
     * @param parent     父布局
     * @param child      跟随滑动的布局的布局
     * @param dependency 滑动的布局
     *
     * * 当dependency发生改变时（位置、宽高等），执行这个函数
     * * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     *
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;

        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);

        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            System.out.println("上滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            System.out.println("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            System.out.println("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            System.out.println("到边界了，还在下滑。。。");
        }
    }
}