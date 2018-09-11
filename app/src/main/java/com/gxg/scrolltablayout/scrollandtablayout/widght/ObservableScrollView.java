package com.gxg.scrolltablayout.scrollandtablayout.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 监听ScrollView的滑动数据
 * Create by: chenwei.li
 * Date: 2017/8/22
 * time: 11:36
 * Email: lichenwei.me@foxmail.com
 */
public class ObservableScrollView extends ScrollView {
    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;
    private ISmartScrollChangedListener mSmartScrollChangedListener;
    public void setScanScrollChangedListener(ISmartScrollChangedListener smartScrollChangedListener) {
        mSmartScrollChangedListener = smartScrollChangedListener;
    }

    public ObservableScrollView(Context context) {
        this(context,null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (isScrolledToBottom) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToBottom();
            }
        }
    }

    public boolean isScrolledToTop() {
        return isScrolledToTop;
    }

    public boolean isScrolledToBottom() {
        return isScrolledToBottom;
    }
    private OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged;

    public void setOnObservableScrollViewScrollChanged(OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged) {
        this.mOnObservableScrollViewScrollChanged = mOnObservableScrollViewScrollChanged;
    }


    public interface OnObservableScrollViewScrollChanged{
        void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt, boolean isTop, boolean isButton);
    }

    /**
     * @param l Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t Current vertical scroll origin. 当前滑动的y轴距离
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距离
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
            isScrolledToTop = true;
            isScrolledToBottom = false;
        } else if (getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()) {
            isScrolledToBottom = true;
            isScrolledToTop = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = false;
        }
        notifyScrollChangedListeners();

        if(mOnObservableScrollViewScrollChanged!=null){
            mOnObservableScrollViewScrollChanged.onObservableScrollViewScrollChanged(l,t,oldl,oldt,isScrolledToTop,isScrolledToBottom);
        }
    }

    /** 定义监听接口 */
    public interface ISmartScrollChangedListener {
        void onScrolledToBottom();
        void onScrolledToTop();
    }
}
