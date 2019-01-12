package com.example.testapp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class MyApp extends Application {

    private static MyApp mMyApp;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onCreate() {
        super.onCreate();
        mMyApp = this;
    }

    public static Context getContext(){
       return mMyApp.getApplicationContext();
    }

    public static Handler getMainHandler() {
        return mHandler;
    }
}
