package com.example.testapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.testapp.R;

public class ToolbarDemoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity_layout);
        inintToolbarView();
    }

    private void inintToolbarView() {
        setTitle("toolbar demo");
        mToolbar = (Toolbar) findViewById(R.id.keyboard_activity_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("toolbar title");
        mToolbar.setSubtitle("toolbar subtitle");
        mToolbar.setLogo(R.drawable.ic_keyboard_black_24dp);
        mToolbar.setNavigationIcon(R.drawable.toolbar_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
