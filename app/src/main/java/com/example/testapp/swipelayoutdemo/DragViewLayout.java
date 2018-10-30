package com.example.testapp.swipelayoutdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragViewLayout extends ViewGroup {

    private View mContentView;
    private View mDragView;
    private int mContentViewWidth;
    private int mDragViewWidth;
    private int mHeight;

    private ViewDragHelper mHelper;

    private boolean isOpened;

    public DragViewLayout(Context context) {
        super(context);
    }

    public DragViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mContentView = getChildAt(0);
        mDragView = getChildAt(1);

        LayoutParams layoutParams = mDragView.getLayoutParams();
        mDragViewWidth = layoutParams.width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量内容区域
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        //测量滑动显示区域
        int dragViewMeasureSpecWidth = MeasureSpec.makeMeasureSpec(mDragViewWidth, MeasureSpec.EXACTLY);
        mDragView.measure(dragViewMeasureSpecWidth, heightMeasureSpec);
        //确定自己的尺寸
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = mContentView.getMeasuredHeight();
        mContentViewWidth = mContentView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //布局内容区域
        mContentView.layout(0, 0, mContentViewWidth, mHeight);
        //布局滑动显示区域
        mDragView.layout(mContentViewWidth, 0, mContentViewWidth + mDragViewWidth, mHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        mHelper.processTouchEvent(event);
        //这里必须返回true，ViewDragHelper才能正常监听
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // mHelper 已经在continueSettling() 中帮我们做了移动，重绘了view的位置（这种改变位置的方法layout方法是兼容的）
        if (mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        /**
         * touch down 的时候调用，是否分析该view的touch时间
         *
         * @param child     按在了那个view
         * @param pointerId
         * @return true：分析该view的touch事件，开始监听move up事件；false：不去分析，没有任何效果
         */
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //这里表示要分析mContentView和mDragView
            return mContentView == child || mDragView == child;
        }

        /**
         * 当touch move的时候回调
         *
         * @param child ：tryCaptureView中的child
         * @param left  ：左侧边距（期望值）
         * @param dx    ：本次期望移动距离
         * @return ： 确定要移动多少，返回后正式开始移动。
         */
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            //定义真正的偏移量
            int offset = 0;

            if (child == mContentView) {
                if (left < -mDragViewWidth) {
                    offset = -mDragViewWidth;
                } else if (left > 0) {
                    offset = 0;
                } else {
                    offset = left;
                }
            } else if (child == mDragView) {
                if (left < mContentViewWidth - mDragViewWidth) {
                    offset = mContentViewWidth - mDragViewWidth;
                } else if (left > mContentViewWidth) {
                    offset = mContentViewWidth;
                } else {
                    offset = left;
                }
            }

            return offset;
        }

        /**
         * 当view的位置改变时调用，也属于touch move 的监听，但不参与移动，只是move事件的额外的监听
         * 作用：带动另外一个view移动，其实也可以在 clampViewPositionHorizontal 中实现
         *
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            //当view位置改变，刷新界面
            invalidate();

            //如果changedView是mContentView，则带懂mDragView；
            if (changedView == mContentView) {
                //翻遍mDragView的位置
                mDragView.layout(mContentViewWidth + left, 0, mContentViewWidth + left + mDragViewWidth, mHeight);
            } else if (changedView == mDragView) {
                mContentView.layout(left - mContentViewWidth, 0, left, mHeight);
            }
        }

        /**
         * 松开手的回调
         *
         * @param releasedChild ： 松开了那个view
         * @param xvel          ： 松开时x方向上的速率
         * @param yvel          ： 松开时y方向上的速率
         */
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            int left = releasedChild.getLeft();
            if (releasedChild == mContentView) {
                if (left >= -mDragViewWidth / 2) {
                    //内部开启了 Scroller 的startScroll，进行数据模拟，不断调用的是 computeScroll
                    mHelper.smoothSlideViewTo(releasedChild, 0, 0);
                    //关闭
                    isOpened = false;
                } else {
                    mHelper.smoothSlideViewTo(releasedChild, -mDragViewWidth, 0);
                    //打开
                    isOpened = true;
                }
            } else if (releasedChild == mDragView) {
                if (left >= mContentViewWidth - mDragViewWidth / 2) {
                    mHelper.smoothSlideViewTo(releasedChild, mContentViewWidth, 0);
                    //关闭
                    isOpened = false;
                } else {
                    mHelper.smoothSlideViewTo(releasedChild, mContentViewWidth - mDragViewWidth, 0);
                    //打开
                    isOpened = true;
                }
            }
            // 注意：一定要实时刷新界面
            invalidate();
        }
    }
}
