package com.gxg.scrolltablayout.scrollandtablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gxg.scrolltablayout.scrollandtablayout.activity.DeliciousFoodDetailActivity;
import com.gxg.scrolltablayout.scrollandtablayout.activity.HeadScrollActivity;
import com.gxg.scrolltablayout.scrollandtablayout.activity.ScrollViewTopActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_dazhongdianping)
    TextView tvDazhongdianping;
    @BindView(R.id.tv_rv_tab)
    TextView tvRvTab;
    @BindView(R.id.tv_sv_tab)
    TextView tvSvTab;
    @BindView(R.id.tv_vp_tab)
    TextView tvVpTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_dazhongdianping, R.id.tv_rv_tab, R.id.tv_sv_tab, R.id.tv_vp_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dazhongdianping:
                startActivity(new Intent(MainActivity.this, DeliciousFoodDetailActivity.class));
                break;
            case R.id.tv_rv_tab:
                break;
            case R.id.tv_sv_tab:
                startActivity(new Intent(MainActivity.this, ScrollViewTopActivity.class));
                break;
            case R.id.tv_vp_tab:
                startActivity(new Intent(MainActivity.this, HeadScrollActivity.class));
                break;
        }
    }
}
