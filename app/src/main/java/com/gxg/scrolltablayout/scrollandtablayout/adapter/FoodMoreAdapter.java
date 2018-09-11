package com.gxg.scrolltablayout.scrollandtablayout.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.gxg.scrolltablayout.scrollandtablayout.util.ScreenSizeUtil;

import java.util.List;

public class FoodMoreAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private  int ITEM_DIVIDER;
    public FoodMoreAdapter(@Nullable List<String> data) {
        super(R.layout.item_food_more,data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if(ITEM_DIVIDER==0){
            ITEM_DIVIDER = ScreenSizeUtil.Dp2Px(mContext, 9);
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
        if (helper.getAdapterPosition() % 2 == 0) {
            layoutParams.setMargins(ITEM_DIVIDER, 0, ITEM_DIVIDER, ITEM_DIVIDER);
        } else{
            layoutParams.setMargins(0, 0, ITEM_DIVIDER, ITEM_DIVIDER);
        }
    }
}
