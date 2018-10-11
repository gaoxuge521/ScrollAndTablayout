package com.gxg.scrolltablayout.scrollandtablayout.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gxg.scrolltablayout.scrollandtablayout.R;
import com.gxg.scrolltablayout.scrollandtablayout.bean.HeadBean;

import java.util.List;

/**
 * Created by gaoxuge on 2017/8/16 at 19:03.
 *
 */

public class HeadScrollAdapter extends BaseQuickAdapter<HeadBean,BaseViewHolder> {

    public HeadScrollAdapter(@Nullable List<HeadBean> data) {
        super(R.layout.item_head_horizontal,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HeadBean item) {
        ImageView imageView = helper.getView(R.id.item_head_iv);
        imageView.setImageResource(item.getImg());
        helper.addOnClickListener(R.id.item_head_iv);
    }
}
