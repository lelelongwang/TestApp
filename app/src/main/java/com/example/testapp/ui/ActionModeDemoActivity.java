package com.example.testapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.R;

public class ActionModeDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTextView1;
    private TextView mTextView2;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_mode_activity_layout);
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
        mTextView1 = findViewById(R.id.action_mode_text);
        mTextView2 = findViewById(R.id.action_mode_text2);
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
    }

    /**
     * 当menu弹框显示之后，点击空白处需要finsh弹框
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mActionMode != null) {
            mActionMode.finish();
            mActionMode = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_mode_text:
                v.startActionMode(new TextActionModeCallback(), ActionMode.TYPE_FLOATING);
                break;
            case R.id.action_mode_text2:
                v.startActionMode(new TextActionModeCallback(), ActionMode.TYPE_PRIMARY);
                break;
            default:
                break;
        }
    }

    private class TextActionModeCallback extends ActionMode.Callback2 {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (mActionMode != null) {
                mActionMode.finish();
                mActionMode = null;
            }
            mActionMode = mode;
            mode.setTitle("title");
            mode.setSubtitle("subitle");
            mode.setTitleOptionalHint(true);
            mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
            //不知可否不再xml中写menu，直接在代码中add呢？
//            populateMenuWithItems(menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_mode_menu_1:
                    Toast.makeText(ActionModeDemoActivity.this,"你",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_mode_menu_2:
                    Toast.makeText(ActionModeDemoActivity.this,"好",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_mode_menu_3:
                    Toast.makeText(ActionModeDemoActivity.this,"吗",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_mode_menu_4:
                    Toast.makeText(ActionModeDemoActivity.this,"？",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;

        }

        private void populateMenuWithItems(Menu menu) {
            menu.add(1, R.id.action_mode_menu_1, 1, "what").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(2, R.id.action_mode_menu_2, 2, "is").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(3, R.id.action_mode_menu_3, 3, "this").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add(4, R.id.action_mode_menu_4, 4, "?!").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }
}
