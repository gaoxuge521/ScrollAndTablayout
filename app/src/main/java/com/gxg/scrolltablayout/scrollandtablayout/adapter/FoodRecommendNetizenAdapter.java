package com.gxg.scrolltablayout.scrollandtablayout.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gxg.scrolltablayout.scrollandtablayout.R;

import java.util.List;

/**
 * 美食网友推荐
 */
public class FoodRecommendNetizenAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public FoodRecommendNetizenAdapter(@Nullable List<String> data) {
        super(R.layout.item_food_recommend_natizen,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
