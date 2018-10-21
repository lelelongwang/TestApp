package com.example.testapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.keyboardDemo.KeyboardManager;

public class KeyboardDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private EditText mEditText1;
    private EditText mEditText2;
    private TextView mTextView;
    private KeyboardManager mKeyboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_activity_layout);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        setTitle("键盘demo");
        mToolbar = (Toolbar) findViewById(R.id.keyboard_activity_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.toolbar_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mEditText1 = findViewById(R.id.edit_1);
        mEditText2 = findViewById(R.id.edit_2);
        mTextView = findViewById(R.id.text1);
        mTextView.setOnClickListener(this);
        mEditText1.setOnClickListener(this);
        mEditText2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_1:
                mKeyboardManager = new KeyboardManager(this, this, mEditText1);
                mKeyboardManager.showKeyboard();
                break;
            case R.id.edit_2:
                break;
            case R.id.text1:
                mKeyboardManager = new KeyboardManager(this, this, mEditText1);
                mKeyboardManager.showKeyboard();
                break;
            default:
                break;
        }
    }
}
