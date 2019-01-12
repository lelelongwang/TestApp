package com.example.testapp.PreferencesDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.PreferencesDemo.DefaultGenerationSetting.SettingsActivity;
import com.example.testapp.R;

public class PreferencesDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAppCompatPreActBtn;
    private Button mPreActBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_demo);
        initView();
    }

    private void initView(){
        mAppCompatPreActBtn = findViewById(R.id.appcompat_preferences_activity_btn);
        mAppCompatPreActBtn.setOnClickListener(this);
        mPreActBtn = findViewById(R.id.preferences_activity_btn);
        mPreActBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appcompat_preferences_activity_btn:
                Toast.makeText(this, "进入 AppCompatPreferenceActivity 界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.preferences_activity_btn:
                Toast.makeText(this, "进入 PreferenceActivity 界面", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this,MyPreferenceActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
