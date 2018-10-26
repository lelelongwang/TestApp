package com.example.testapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.testapp.R;

public class ActionModeDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmode_activity_layout);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        setTitle("ActionMode demo");
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
        mTextView = findViewById(R.id.action_mode_text);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_mode_text:
                v.startActionMode(new TextActionModeCallback(), ActionMode.TYPE_FLOATING);
                break;
            default:
                break;
        }
    }

    private class TextActionModeCallback extends ActionMode.Callback2 {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle(null);
            mode.setSubtitle(null);
            mode.setTitleOptionalHint(true);
            mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
            populateMenuWithItems(menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }

        private void populateMenuWithItems(Menu menu) {
            menu.add(0, R.id.action_mode_menu_1, 0, "11").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add("menu_2").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add("menu_3").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add("menu_4").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("menu_5").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }
}
