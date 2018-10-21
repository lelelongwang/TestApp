package com.example.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.ui.KeyboardDemoActivity;
import com.example.testapp.ui.ToolbarDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
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

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
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
}
