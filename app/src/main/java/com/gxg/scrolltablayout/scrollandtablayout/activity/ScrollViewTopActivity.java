package com.gxg.scrolltablayout.scrollandtablayout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.HeadScrollAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.HotelHomeListViewAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.bean.HeadBean;
import com.gxg.scrolltablayout.scrollandtablayout.util.Contans;
import com.gxg.scrolltablayout.scrollandtablayout.util.LocalImageHolderView;
import com.gxg.scrolltablayout.scrollandtablayout.widght.ListViewForScrollView;
import com.gxg.scrolltablayout.scrollandtablayout.widght.ObservableScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollViewTopActivity extends AppCompatActivity implements OnItemClickListener {
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sort_tuijie)
    TextView tvSortTuijie;
    @BindView(R.id.tv_sort_jiage)
    TextView tvSortJiage;
    @BindView(R.id.tv_sort_zonghe)
    TextView tvSortZonghe;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ll_tablayout)
    LinearLayout llTablayout;
    @BindView(R.id.ll_topView)
    LinearLayout llTopView;
    @BindView(R.id.rv)
    ListViewForScrollView rv;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(android.R.id.empty)
    LinearLayout empty;
    @BindView(R.id.nsv)
    ObservableScrollView nsv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.ll_fixedView)
    LinearLayout llFixedView;
    @BindView(R.id.findBanner)
    ConvenientBanner findBanner;
    @BindView(R.id.tv_interesteds)
    TextView tvInteresteds;
    @BindView(R.id.rv_recommend_follow)
    RecyclerView rvRecommendFollow;
    @BindView(R.id.ll_recommend_follow)
    LinearLayout llRecommendFollow;
    private int mHeight;
    private HotelHomeListViewAdapter hotelHomeListViewAdapter;
    ArrayList<String> images = new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_top);
        ButterKnife.bind(this);
        initView();
        initBanner();
        initHorizontalRecycle();
        initData();
        initListener();
    }

    private void initBanner() {
        for (int i = 0; i < Contans.bannerImgs.length; i++) {
            images.add(Contans.bannerImgs[i]);
        }
        setBanner(images);
    }

    private void setBanner(ArrayList<String> images) {
        boolean isSingleBanner = images.size() == 1;
        findBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.icon_banner_nomal, R.drawable.icon_banner_select})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this)
                .setPointViewVisible(!isSingleBanner)
                .setCanLoop(!isSingleBanner);


        if (images.size() == 1)
            findBanner.setCanLoop(false);
    }
    /**
     * 设置横向列表的数据
     */
    List<HeadBean> mHeadBeanList = new ArrayList<>();
    private HeadScrollAdapter mAdapter;
    private void initHorizontalRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRecommendFollow.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < 10; i++) {
            HeadBean headBean = new HeadBean();
            headBean.setImg(R.drawable.yidian_1167278026);
            mHeadBeanList.add(headBean);
        }

        mAdapter = new HeadScrollAdapter(mHeadBeanList);
        rvRecommendFollow.setAdapter(mAdapter);

        rvRecommendFollow.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast(mAdapter.getData().get(position).getName()+position);
            }

            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_head_iv:
                        showToast("点击了图片"+position);
                        break;
                }
            }
        });
    }

    private void initListener() {
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.finishRefresh();
                    }
                }, 500);
            }
        });

        rv.setOnLoadMoreListener(new ListViewForScrollView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv.setLoadingMore(false);
                        if (rv != null) {
                            rv.setLoadCompleted();
                        }
                    }
                }, 2000);

            }
        });
        nsv.setScanScrollChangedListener(new ObservableScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                rv.loading();
            }

            @Override
            public void onScrolledToTop() {

            }
        });
        nsv.setOnObservableScrollViewScrollChanged(new ObservableScrollView.OnObservableScrollViewScrollChanged() {
            @Override
            public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt, boolean isTop, boolean isButton) {
                if (t == 0) {
                    refresh.setEnabled(true);
                } else {
                    refresh.setEnabled(false);
                }
                if (t >= mHeight) {
                    if (llTablayout.getParent() != llFixedView) {
                        llTopView.removeView(llTablayout);
                        llFixedView.addView(llTablayout);
                    }
                } else {
                    if (llTablayout.getParent() != llTopView) {
                        llFixedView.removeView(llTablayout);
                        llTopView.addView(llTablayout);
                    }
                }
            }
        });
    }

    private void initData() {
        llTopView.post(new Runnable() {
            @Override
            public void run() {
                mHeight = llTopView.getTop();
            }
        });
        hotelHomeListViewAdapter = new HotelHomeListViewAdapter(new ArrayList<String>(), this);
        rv.setAdapter(hotelHomeListViewAdapter);
        rv.setLoadingMore(true);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("");
        }
        hotelHomeListViewAdapter.setNewData(list);

    }

    private void initView() {
        tvTitle.setText("酒店预定");
        ivLeft.setImageResource(R.drawable.back_hui);
        ivLeft.setVisibility(View.VISIBLE);
        rv.setEmptyView(findViewById(android.R.id.empty));
        if (refresh != null) {
            refresh.setEnableRefresh(true);//是否启用下拉刷新功能
            refresh.setEnableLoadMore(false);
            refresh.setEnableAutoLoadMore(false);
            refresh.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
            refresh.setEnableOverScrollBounce(false);
            refresh.setEnabled(false);
        }
        rv.setFocusable(false);
    }

    @OnClick({R.id.iv_left, R.id.tv_sort_tuijie, R.id.tv_sort_jiage, R.id.tv_sort_zonghe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_sort_tuijie:
                break;
            case R.id.tv_sort_jiage:
                break;
            case R.id.tv_sort_zonghe:
                break;
        }
    }
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemClick(int position) {

    }
}
