package com.gxg.scrolltablayout.scrollandtablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxg.scrolltablayout.scrollandtablayout.R;


/**
 * create om  2018/8/15.
 * Created by  gaoxuge
 * email android_gaoxuge@163.com
 * 功能描述
 */

public class BlankFragment extends Fragment {
    public static BlankFragment newInstance() {

        Bundle args = new Bundle();

        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank,null);
        return view;
    }
}
