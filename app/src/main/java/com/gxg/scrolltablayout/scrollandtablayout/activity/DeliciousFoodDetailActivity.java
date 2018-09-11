package com.gxg.scrolltablayout.scrollandtablayout.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.gxg.scrolltablayout.scrollandtablayout.WeakHandler;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.BaseFragmentPagerAdapterWithoutTitle;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.FoodMoreAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.FoodRecommendNetizenAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.FoodReviewNetizenAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.adapter.TagAdapter;
import com.gxg.scrolltablayout.scrollandtablayout.bean.TabEntity;
import com.gxg.scrolltablayout.scrollandtablayout.fragment.BlankFragment;
import com.gxg.scrolltablayout.scrollandtablayout.widght.ObservableScrollView;
import com.gxg.scrolltablayout.scrollandtablayout.widght.TagFlowLayout;
import com.gxg.scrolltablayout.scrollandtablayout.widght.flycotablayout.CommonTabLayout;
import com.gxg.scrolltablayout.scrollandtablayout.widght.flycotablayout.listener.CustomTabEntity;
import com.gxg.scrolltablayout.scrollandtablayout.widght.flycotablayout.listener.OnTabSelectListener;
import com.gxg.scrolltablayout.scrollandtablayout.widght.ratingbar.CBRatingBar;
import com.zhy.view.flowlayout.FlowLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 美食详情
 */
public class DeliciousFoodDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.table_layout)
    CommonTabLayout tableLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_hm_title1)
    TextView tvHmTitle1;
    @BindView(R.id.tv_html)
    TextView tvHtml;
    @BindView(R.id.tv_hm_title2)
    TextView tvHmTitle2;
    @BindView(R.id.tv_html2)
    TextView tvHtml2;
    @BindView(R.id.tv_hm_title3)
    TextView tvHmTitle3;
    @BindView(R.id.tv_html3)
    TextView tvHtml3;
    @BindView(R.id.tv_hm_title4)
    TextView tvHtml4;
    @BindView(R.id.nsv)
    ObservableScrollView nsv;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.tv_food_title)
    TextView tvFoodTitle;
    @BindView(R.id.rating_bar)
    CBRatingBar ratingBar;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.ll_topview)
    LinearLayout llTopView;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_service_score)
    TextView tvServiceScore;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.flow_layout)
    TagFlowLayout flowLayout;
    @BindView(R.id.tv_mai)
    TextView tvMai;
    @BindView(R.id.btn_maidan)
    TextView btnMaidan;
    @BindView(R.id.rv_recommend_netizen)
    RecyclerView rvRecommendNetizen;
    @BindView(R.id.rv_review_netizen)
    RecyclerView rvReviewNetizen;
    @BindView(R.id.rv_more_food)
    RecyclerView rvMoreFood;
    @BindView(R.id.ll_fixedView)
    LinearLayout llFixedView;
    @BindView(R.id.ll_top_main)
    LinearLayout llTopMain;
    @BindView(R.id.title_top)
    View titleTop;
    @BindView(R.id.fm_tablayout)
    FrameLayout fmTablayout;
    @BindView(R.id.fm_blank)
    FrameLayout fmBlank;
    private Unbinder bind;
    public static final String FOOD_ID = "footId";
    private String footId;

    private String[] visaTypes = {"优惠信息", "网友点评", "更多推荐"};
    private List<Fragment> fragments = new ArrayList<>();
    private int mHeight;
    private List<TextView> textViewList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.drawable.icon_home_pre, R.drawable.icon_home_pre,
            R.drawable.icon_home_pre, R.drawable.icon_home_pre};
    private int[] mIconSelectIds = {
            R.drawable.icon_home_pre, R.drawable.icon_home_pre,
            R.drawable.icon_home_pre, R.drawable.icon_home_pre};


    private WeakHandler handler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            nsv.smoothScrollTo(0, textViewList.get(msg.what).getTop() - textViewList.get(msg.what).getHeight());
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
        initView();
        afterInitView();
        initListener();

    }

    public void setContentLayout() {
        setContentView(R.layout.activity_delicious_food_detail);
        bind = ButterKnife.bind(this);
    }


    public void initView() {
        tvTitle.setText("酒店预定");
        ivLeft.setImageResource(R.drawable.back_hui);
        ivLeft.setVisibility(View.VISIBLE);
        footId = getIntent().getStringExtra(FOOD_ID);
    }

    public void afterInitView() {
        nsv.setFillViewport(true);
        fragments.clear();
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        for (int i = 0; i < visaTypes.length; i++) {
            mTabEntities.add(new TabEntity(visaTypes[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        vp.setAdapter(new BaseFragmentPagerAdapterWithoutTitle(getSupportFragmentManager(), visaTypes, fragments));
        vp.setOffscreenPageLimit(fragments.size());
        tableLayout.setTabData(mTabEntities);
        textViewList.clear();
        textViewList.add(tvHmTitle1);
        textViewList.add(tvHmTitle2);
        textViewList.add(tvHmTitle3);
        textViewList.add(tvHtml4);
        String[] strings = {"人气火锅","健康菜品","文艺范儿","闺蜜聚集地"};
        //标签
//        flowLayout
        TagAdapter hotelTypeAdapter = new TagAdapter<String>(Arrays.asList(strings)) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                TextView tvTag = (TextView) LayoutInflater.from(DeliciousFoodDetailActivity.this).inflate(R.layout.layout_flow_tag_food, flowLayout, false);
                tvTag.setText(tag);
                return tvTag;
            }
        };
        flowLayout.setAdapter(hotelTypeAdapter);

        //网友推荐
        rvRecommendNetizen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        FoodRecommendNetizenAdapter foodRecommendNetizenAdapter = new FoodRecommendNetizenAdapter(new ArrayList<String>());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }
        rvRecommendNetizen.setHasFixedSize(true);
//        rvRecommendNetizen.setNestedScrollingEnabled(false);
//        rvRecommendNetizen.setFocusableInTouchMode(false);
        rvRecommendNetizen.setAdapter(foodRecommendNetizenAdapter);
        foodRecommendNetizenAdapter.setNewData(list);

        //网友点评
        rvReviewNetizen.setLayoutManager(new LinearLayoutManager(this));
        rvReviewNetizen.setHasFixedSize(true);
        rvReviewNetizen.setNestedScrollingEnabled(false);
//        rvReviewNetizen.setFocusableInTouchMode(false);
        FoodReviewNetizenAdapter foodReviewNetizenAdapter = new FoodReviewNetizenAdapter(new ArrayList<String>());
        rvReviewNetizen.setAdapter(foodReviewNetizenAdapter);
        foodReviewNetizenAdapter.setNewData(list);

        //更多
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rvMoreFood.setLayoutManager(gridLayoutManager);
        FoodMoreAdapter foodMoreAdapter = new FoodMoreAdapter(new ArrayList<String>());
        rvMoreFood.setNestedScrollingEnabled(false);
//        rvMoreFood.setFocusableInTouchMode(false);
        rvMoreFood.setAdapter(foodMoreAdapter);
        rvMoreFood.setHasFixedSize(true);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            list2.add(""+i);
        }
        foodMoreAdapter.setNewData(list2);
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private int currentPosition;
    private boolean tabInterceptTouchEventTag = true;//标志位，用来区分是点击了tab还是手动滑动scrollview
    private void initListener() {
        fmBlank.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tabInterceptTouchEventTag = true;//让tab来处理滑动
                return false;
            }
        });
        tableLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currentPosition = position;
                if(!tabInterceptTouchEventTag){//手动滑动页面时则不再次处理滑动
                    return;
                }
                nsv.computeScroll();
                handler.sendEmptyMessage(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        nsv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tabInterceptTouchEventTag = false;//让scrollview处理滑动
                return false;
            }
        });
        nsv.setOnObservableScrollViewScrollChanged(new ObservableScrollView.OnObservableScrollViewScrollChanged() {
            @Override
            public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt, boolean isTop, boolean isButton) {
                if(mHeight==0){
                    mHeight = llTopMain.getHeight();
                }
                if (t >= mHeight) {
                    if (fmTablayout.getParent() != llFixedView) {
                        llTopView.removeView(fmTablayout);
                        llFixedView.addView(fmTablayout);
                    }
                    if (tabInterceptTouchEventTag) {//让tab来处理滑动
                        return;
                    }
                    if (isButton) {
                        tableLayout.setCurrentTab(2);
                    } else {
                        if (mHeight < t && t < (textViewList.get(1).getTop() - textViewList.get(1).getHeight())) {
                            tableLayout.setCurrentTab(0);
                        } else if ((textViewList.get(1).getTop() - textViewList.get(1).getHeight()) <= t && t < (textViewList.get(2).getTop() - textViewList.get(2).getHeight())) {
                            tableLayout.setCurrentTab(1);
                        } else if ((textViewList.get(2).getTop() - textViewList.get(2).getHeight()) <= t) {
                            tableLayout.setCurrentTab(2);
                        }
                    }
                } else {
                    if (fmTablayout.getParent() != llTopView) {
                        llFixedView.removeView(fmTablayout);
                        llTopView.addView(fmTablayout);
                    }
                }
            }
        });
    }



    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    @OnClick({R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;

        }
    }


}
