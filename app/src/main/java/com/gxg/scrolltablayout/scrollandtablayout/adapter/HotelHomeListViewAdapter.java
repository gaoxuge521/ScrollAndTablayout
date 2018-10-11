package com.gxg.scrolltablayout.scrollandtablayout.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gxg.scrolltablayout.scrollandtablayout.R;

import java.util.List;

public class HotelHomeListViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public HotelHomeListViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<String> getList() {
        return list;
    }

    public void setNewData(List<String> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public void addata(List<String> data) {
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHoler viewHoler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hotel, null);
            viewHoler = new MyViewHoler();
            viewHoler.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
            viewHoler.tv_hotel_name = (TextView) convertView.findViewById(R.id.tv_hotel_name);
            viewHoler.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            viewHoler.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHoler.tv_tip = (TextView) convertView.findViewById(R.id.tv_tip);
            viewHoler.tv_hotel_type = (TextView) convertView.findViewById(R.id.tv_hotel_type);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (MyViewHoler) convertView.getTag();
        }

        return convertView;
    }

    public class MyViewHoler {
        ImageView iv_left;
        TextView tv_hotel_name;
        TextView tv_address;
        TextView tv_price, tv_hotel_type, tv_tip;
    }
}
