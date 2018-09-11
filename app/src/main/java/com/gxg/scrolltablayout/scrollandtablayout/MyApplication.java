package com.gxg.scrolltablayout.scrollandtablayout;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }

    public static Context getInstance(){
        return mApplicationContext;
    }
}
