package com.example.testapp.keyboardDemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.testapp.R;

public class KeyboardManager {

    private Context mContext;
    private View mRootView;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;
    private ImageView mImageView;
    private WindowManager wm;

    public KeyboardManager(AppCompatActivity activity, Context context, EditText editText) {
        this.mContext = context;
        initKeyboardView();
    }

    private void initKeyboardView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = inflater.inflate(R.layout.keyboard_base,null);
        mImageView = mRootView.findViewById(R.id.navigation_bar_hide);
        mImageView.setOnClickListener(clickListener);

        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.gravity = Gravity.BOTTOM;
        lp.height=726;
        lp.width = 1080;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        lp.format = PixelFormat.TRANSLUCENT;
        wm.addView(mRootView, lp);

        mKeyboardView = mRootView.findViewById(R.id.keyboard_view);
        mKeyboard = new Keyboard(mContext,R.xml.keyboard_26_en);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(true);
        mKeyboardView.setOnKeyboardActionListener(keyboardListener);
    }
    public void showKeyboard(){
        mKeyboardView.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navigation_bar_hide:
                    if (mRootView != null) {
                        wm.removeView(mRootView);
                        mRootView = null;
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private KeyboardView.OnKeyboardActionListener keyboardListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {

        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };
}
