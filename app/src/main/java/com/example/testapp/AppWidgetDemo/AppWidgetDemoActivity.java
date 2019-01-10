package com.example.testapp.AppWidgetDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.testapp.R;

/**
 *
 * 参考文档：
 * https://www.cnblogs.com/joy99/p/6346829.html
 */
public class AppWidgetDemoActivity extends AppCompatActivity {

    private static final String TAG = AppWidgetDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_widget_demo);
        Log.d(TAG, "onCreate: ljh savedInstanceState="+savedInstanceState+"  this="+this);
    }
}
