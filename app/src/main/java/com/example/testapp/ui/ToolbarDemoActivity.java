package com.example.testapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.testapp.R;

import java.lang.reflect.Method;

/**
 * 本类中涉及到的知识点：
 * 1、显示menu有两种方式
 * 2、监听menu的点击事件也有两种方式
 * 3、显示隐藏起来的menu的icon也有两种方式
 * 感谢博客https://www.jianshu.com/p/7b5c99e1cfa3
 * 和https://mp.weixin.qq.com/s/felm09sf0mHQaHW78mwc6w
 * https://blog.csdn.net/da_caoyuan/article/details/79557704
 * https://blog.csdn.net/zhyh1986/article/details/51810803/
 */
public class ToolbarDemoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity_layout);
        inintToolbarView();
    }

    private void inintToolbarView() {
        setTitle("demo");
        mToolbar = (Toolbar) findViewById(R.id.keyboard_activity_toolbar);
        setSupportActionBar(mToolbar);//写了这行就不能通过mToolbar.inflateMenu()来显示menu了
        mToolbar.setTitle("title");
        mToolbar.setSubtitle("subtitle");
        mToolbar.setLogo(R.drawable.settings_black_24dp);
        mToolbar.setNavigationIcon(R.drawable.toolbar_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mToolbar.setPopupTheme(R.style.ToolbarPopupStyle);//通过代码给隐藏的menu设置样式
//        mToolbar.inflateMenu(R.menu.tool_bar_menu);//显示menu
//        setToolbarOptionalIconsVisible();//显示隐藏的menu
        mToolbar.setOnMenuItemClickListener(new MenuItemClickListener());//监听menu的点击事件
    }

    /**
     * MenuItemClickListener这个类可以监听menu的点击。
     * 不管是通过mToolbar.inflateMenu()获取的menu、还是通过onCreateOptionsMenu()获取的menu，都可以
     * 用设置监听的方法获取点击事件
     */
    private class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tool_bar_menu_1:
                    Toast.makeText(ToolbarDemoActivity.this, "onMenuItemClick 搜索", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tool_bar_menu_2:
                    Toast.makeText(ToolbarDemoActivity.this, "onMenuItemClick 通知", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tool_bar_menu_3:
                    Toast.makeText(ToolbarDemoActivity.this, "onMenuItemClick 设置", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tool_bar_menu_4:
                    Toast.makeText(ToolbarDemoActivity.this, "onMenuItemClick ？？", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    /**
     * 显示Toolbar中的menu菜单有两种方法：
     * 1、如果没有设置setSupportActionBar(mToolbar)，则直接通过
     * mToolbar.inflateMenu(R.menu.tool_bar_menu)即可显示menu菜单。
     * 2、如果设置了setSupportActionBar(mToolbar)，通过上述方式menu不显示，
     * 只能通过重写Activity的onCreateOptionsMenu()方法来显示menu。
     * 3、通过上述两种方法显示的menu，都可以通过mToolbar.setOnMenuItemClickListener()来对menu的点击
     * 事件进行监听，如内部类MenuItemClickListener。
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 对点击menu事件进行监听。
     * 通过重写Activity的onOptionsItemSelected()方法，可以对menu的点击事件进行监听。但是这种监听方式
     * 只对通过onCreateOptionsMenu()获取的menu生效。如果是通过mToolbar.inflateMenu()获取的menu则只能
     * 通过setOnMenuItemClickListener()来进行监听点击事件。（这个结论是在两种监听的返回值都是默认的
     * 情况下测试的）
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tool_bar_menu_1:
                Toast.makeText(ToolbarDemoActivity.this, "onOptionsItemSelected 搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tool_bar_menu_2:
                Toast.makeText(ToolbarDemoActivity.this, "onOptionsItemSelected 通知", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tool_bar_menu_3:
                Toast.makeText(ToolbarDemoActivity.this, "onOptionsItemSelected 设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tool_bar_menu_4:
                Toast.makeText(ToolbarDemoActivity.this, "onOptionsItemSelected ？？", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 每次菜单被关闭时调用.
     * 菜单被关闭有三种情形:
     * 1.展开menu的按钮被再次点击
     * 2.back按钮被点击
     * 3.用户选择了某一个菜单项
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。
     * 同样的，返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如
     * 加载不同的菜单等）
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 显示menu的icon,通过反射,设置Menu的icon显示.
     *
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onPrepareOptionsPanel: unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    /**
     * setToolbarOptionalIconsVisible()这个方法是用来显示隐藏起来的menu的图标的
     */
    private void setToolbarOptionalIconsVisible() {
        Menu menu = mToolbar.getMenu();
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    MenuBuilder menuBuilder = (MenuBuilder) menu;
                    menuBuilder.setOptionalIconsVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
