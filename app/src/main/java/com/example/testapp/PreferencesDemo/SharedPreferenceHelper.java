package com.example.testapp.PreferencesDemo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testapp.MyApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SharedPreference的一个工具类，便于工程维护和管理
 */
public class SharedPreferenceHelper {

    private static final String TAG = SharedPreferenceHelper.class.getSimpleName();

    private static final String SHARED_PREFERENCE = "com_longjunhao_share_preference";
    private static final String INPUT_TYPE = "input_type";

    private static SharedPreferenceHelper mSHelper;
    private SharedPreferences.Editor mEditor;
    private ExecutorService mSingleExecutor;
    private SharedPreferences mSp;


    public static SharedPreferenceHelper getInstance() {
        if (mSHelper == null) {
            synchronized (SharedPreferenceHelper.class) {
                if (mSHelper == null) {
                    mSHelper = new SharedPreferenceHelper();
                }
            }
        }
        return mSHelper;
    }

    private SharedPreferenceHelper() {
        mSingleExecutor = Executors.newSingleThreadExecutor();
        mSp = getSharedPreferences();
        mEditor = mSp.edit();
    }

    private void commit() {
        mSingleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mEditor.commit();
            }
        });
    }

    public void commitEditor() {
        commit();
    }

    public void applyEditor() {
        mEditor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return MyApp.getContext().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }


    /**
     * eg: int inputType = SharedPreferenceHelper.getInstance(mContext).getInputType();
     *
     * @return
     */
    public int getInputType() {
        return mSp.getInt(INPUT_TYPE, 0);
    }

    /**
     * eg: SharedPreferenceHelper.getInstance().saveInputType(1);
     *
     * @param inputType
     */
    public void saveInputType(int inputType) {
        mEditor.putInt(INPUT_TYPE, inputType);
        commitEditor();
    }
}
