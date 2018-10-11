package com.gxg.scrolltablayout.scrollandtablayout.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.HeadFmPagerAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.HeadScrollAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.bean.HeadBean;
import com.gxg.scrolltablayout.scrollandtablayout.fragment.Head1Fragment;
import com.gxg.scrolltablayout.scrollandtablayout.util.Contans;
import com.gxg.scrolltablayout.scrollandtablayout.util.LocalImageHolderView;
import com.gxg.scrolltablayout.scrollandtablayout.widght.headvp.HeaderViewPager;
import com.gxg.scrolltablayout.scrollandtablayout.widght.headvp.HeaderViewPagerFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HeadScrollActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.findBanner)
    ConvenientBanner mFindBanner;
    @BindView(R.id.tv_interesteds)
    TextView mTvInteresteds;
    @BindView(R.id.rv_recommend_follow)
    RecyclerView mRvRecommendFollow;
    @BindView(R.id.ll_recommend_follow)
    LinearLayout mLlRecommendFollow;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.activity_head_scroll)
    RelativeLayout mActivityHeadScroll;
    @BindView(R.id.refresh_headsrcoll)
    SmartRefreshLayout refresh;

    ArrayList<String> images = new ArrayList<String>();
    @BindView(R.id.head_vp_scroll)
    HeaderViewPager mHeadVpScroll;
    private HeadScrollAdapter mAdapter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_scroll);
        bind = ButterKnife.bind(this);

        if (refresh != null) {
            refresh.setEnableRefresh(true);//是否启用下拉刷新功能
            refresh.setEnableLoadMore(false);
            refresh.setEnableAutoLoadMore(false);
            refresh.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
            refresh.setEnableOverScrollBounce(false);
            refresh.setEnabled(false);
        }

        initBanner();

        initHorizontalRecycle();

//        refresh_headsrcoll.setHeaderView(View.inflate(this,R.layout.refresh_view,refresh_headsrcoll));

        addFragment();

        mHeadVpScroll.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {

                if (currentY == 0) {
                    refresh.setEnabled(true);
                } else {
                    refresh.setEnabled(false);
                }
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.finishRefresh();
                    }
                },500);
            }
        });

    }

    private String titles[] = {"最新", "价格", "热门", "筛选","MFS","时尚","日记","趋势"};
    private List<HeaderViewPagerFragment> mFragments = new ArrayList<>();

    /**
     * 设置viewpager的数据
     */
    private void addFragment() {

        for (int i = 0; i < titles.length; i++) {
            Head1Fragment fragment = Head1Fragment.newInstance(titles[i]);
            mFragments.add(fragment);
        }

        HeadFmPagerAdapter pagerAdapter = new HeadFmPagerAdapter(getSupportFragmentManager(), titles, mFragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        if(titles.length>4){//条目多的话设置可以滑动
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }else{//条目少的话设置一屏展示
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        mHeadVpScroll.setCurrentScrollableContainer(mFragments.get(0));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mHeadVpScroll.setCurrentScrollableContainer(mFragments.get(position));
            }
        });
    }


    List<HeadBean> mHeadBeanList = new ArrayList<>();

    /**
     * 设置横向列表的数据
     */
    private void initHorizontalRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvRecommendFollow.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < 10; i++) {
            HeadBean headBean = new HeadBean();
            headBean.setImg(R.drawable.yidian_1167278026);
            mHeadBeanList.add(headBean);
        }

        mAdapter = new HeadScrollAdapter(mHeadBeanList);
        mRvRecommendFollow.setAdapter(mAdapter);

        mRvRecommendFollow.addOnItemTouchListener(new OnItemChildClickListener() {
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

    private void initBanner() {
        for (int i = 0; i < Contans.bannerImgs.length; i++) {
            images.add(Contans.bannerImgs[i]);
        }
        setBanner(images);
    }

    private void setBanner(ArrayList<String> images) {
        boolean isSingleBanner = images.size() == 1;
        mFindBanner.setPages(
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
            mFindBanner.setCanLoop(false);
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        if (mFindBanner != null) {
            mFindBanner.startTurning(5000);
        }
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (mFindBanner != null) {
            mFindBanner.stopTurning();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onItemClick(int position) {
        showToast(position + "");
    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
