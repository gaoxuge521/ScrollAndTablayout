package com.gxg.scrolltablayout.scrollandtablayout.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.socks.library.KLog;

public class ListViewForScrollView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private View mFootView;
    private int mTotalItemCount;
    private OnLoadMoreListener mLoadMoreListener;
    private boolean mIsLoading=false;
    private boolean isLoadingMore = false;

    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
    }

    public ListViewForScrollView(Context context) {
        super(context);
        init(context);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        this.mContext=context;
        mFootView= LayoutInflater.from(context).inflate(R.layout.foot_view_listview,null);
        this.setOnScrollListener(this);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_MOVE:
//                if(!mIsLoading){
//                    //上拉
//                    this.requestDisallowInterceptTouchEvent(false);
//                    this.getParent().getParent().requestDisallowInterceptTouchEvent(true);
//                }else{
//                    //下滑
//                    this.requestDisallowInterceptTouchEvent(true);
//                    this.getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
// 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        int lastVisibleIndex=view.getLastVisiblePosition();
        KLog.e("sss  "+lastVisibleIndex);
//        if(isLoadingMore){
//            if (!mIsLoading&&scrollState == OnScrollListener.SCROLL_STATE_IDLE
//                    && lastVisibleIndex ==mTotalItemCount-1) {
//                mIsLoading=true;
//                addFooterView(mFootView);
//                if (mLoadMoreListener!=null) {
//                    mLoadMoreListener.onloadMore();
//                }
//            }
//        }
    }

    public void loading(){
        if(isLoadingMore){
            if (!mIsLoading) {
                mIsLoading=true;
                addFooterView(mFootView);
                if (mLoadMoreListener!=null) {
                    mLoadMoreListener.onloadMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        mTotalItemCount=totalItemCount;
    }
    public void setOnLoadMoreListener(OnLoadMoreListener listener){
        mLoadMoreListener=listener;
    }

    public interface OnLoadMoreListener{
        void onloadMore();
    }
    public void setLoadCompleted(){
        mIsLoading=false;
        removeFooterView(mFootView);
    }
}
