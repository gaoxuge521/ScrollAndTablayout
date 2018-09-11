package com.gxg.scrolltablayout.scrollandtablayout.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gxg.scrolltablayout.scrollandtablayout.R;

import java.util.List;

//美食详情网友点评
public class FoodReviewNetizenAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public FoodReviewNetizenAdapter(@Nullable List<String> data) {
        super(R.layout.item_review_netizen_food,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public int getItemCount() {
        return mData.size()>2?2:mData.size();
    }
}
