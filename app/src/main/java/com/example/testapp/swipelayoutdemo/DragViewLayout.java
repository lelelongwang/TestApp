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
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        int dragViewMeasureSpecWidth = MeasureSpec.makeMeasureSpec(mDragViewWidth, MeasureSpec.EXACTLY);
        mDragView.measure(dragViewMeasureSpecWidth, heightMeasureSpec);
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
        mContentView.layout(0, 0, mContentViewWidth, mHeight);
        mDragView.layout(mContentViewWidth, 0, mContentViewWidth + mDragViewWidth, mHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        mHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
//            return false;
            return mContentView == child || mDragView == child;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
//            return super.clampViewPositionHorizontal(child, left, dx);
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

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
//            super.onViewPositionChanged(changedView, left, top, dx, dy);
            invalidate();
            if (changedView == mContentView) {
                mDragView.layout(mContentViewWidth + left, 0, mContentViewWidth + left + mDragViewWidth, mHeight);
            } else if (changedView == mDragView) {
                mContentView.layout(left - mContentViewWidth, 0, left, mHeight);
            }
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
//            super.onViewReleased(releasedChild, xvel, yvel);
            int left = releasedChild.getLeft();
            if (releasedChild == mContentView) {
                if (left >= -mDragViewWidth / 2) {
                    mHelper.smoothSlideViewTo(releasedChild, 0, 0);
                    isOpened = false;
                } else {
                    mHelper.smoothSlideViewTo(releasedChild, -mDragViewWidth, 0);
                    isOpened = true;
                }
            } else if (releasedChild == mDragView) {
                if (left >= mContentViewWidth - mDragViewWidth / 2) {
                    mHelper.smoothSlideViewTo(releasedChild, mContentViewWidth, 0);
                    isOpened = false;
                } else {
                    mHelper.smoothSlideViewTo(releasedChild, mContentViewWidth - mDragViewWidth, 0);
                    isOpened = true;
                }
            }
            invalidate();
        }
    }
}
