package com.example.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.AppWidgetDemo.AppWidgetDemoActivity;
import com.example.testapp.PreferencesDemo.PreferencesDemoActivity;
import com.example.testapp.ndkjnisoDemo.NdkJniSoDemoActivity;
import com.example.testapp.ui.ActionModeDemoActivity;
import com.example.testapp.ui.AidlDemoActivity;
import com.example.testapp.ui.KeyboardDemoActivity;
import com.example.testapp.ui.ToolbarDemoActivity;
import com.example.testapp.ui.SwipeLayoutDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton7;
    private Button mAidlBtn;
    private Button mJniBtn;
    private Button mAppWidgetBtn;
    private Button mPreferencesBtn;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(mToolbar);

        mButton1 = findViewById(R.id.btn_1);
        mButton2 = findViewById(R.id.btn_2);
        mButton3 = findViewById(R.id.btn_3);
        mButton7 = findViewById(R.id.btn_7);
        mAidlBtn = findViewById(R.id.aidl_btn);
        mJniBtn = findViewById(R.id.jni_btn);
        mAppWidgetBtn = findViewById(R.id.appwidget_btn);
        mPreferencesBtn = findViewById(R.id.preferences_demo_btn);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mAidlBtn.setOnClickListener(this);
        mJniBtn.setOnClickListener(this);
        mAppWidgetBtn.setOnClickListener(this);
        mPreferencesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startKeyboardDemo();
                break;
            case R.id.btn_2:
                startToolbarDemo();
                break;
            case R.id.btn_3:
                startActionModePopuWindow();
                break;
            case R.id.btn_7:
                startSwipeLayoutDemo();
                break;
            case R.id.aidl_btn:
                startAidlDemo();
                break;
            case R.id.jni_btn:
                startJniDemo();
                break;
            case R.id.appwidget_btn:
                startAppWidgetDemo();
                break;
            case R.id.preferences_demo_btn:
                startPreferencesDemo();
                break;
            default:
                break;
        }
    }

    private void startKeyboardDemo() {
        Toast.makeText(this, "进入键盘demo界面", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, KeyboardDemoActivity.class);
        startActivity(intent);
    }

    private void startToolbarDemo() {
        Toast.makeText(this, "进入toolbar demo界面", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ToolbarDemoActivity.class);
        startActivity(intent);
    }

    private void startActionModePopuWindow(){
        Toast.makeText(this,"进入仿ActionMode demo界面",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ActionModeDemoActivity.class);
        startActivity(intent);
    }

    private void startSwipeLayoutDemo() {
        Toast.makeText(this, "进入滑动布局demo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SwipeLayoutDemoActivity.class);
        startActivity(intent);
    }

    private void startAidlDemo(){
        Toast.makeText(this, "进入aidl demo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AidlDemoActivity.class);
        startActivity(intent);
    }

    private void startJniDemo(){
        Toast.makeText(this, "进入jni demo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NdkJniSoDemoActivity.class);
        startActivity(intent);
    }

    private void startAppWidgetDemo(){
        Toast.makeText(this, "进入AppWidget demo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AppWidgetDemoActivity.class);
        startActivity(intent);
    }

    private void startPreferencesDemo(){
        Toast.makeText(this, "进入Preferences demo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PreferencesDemoActivity.class);
        startActivity(intent);
    }
}
