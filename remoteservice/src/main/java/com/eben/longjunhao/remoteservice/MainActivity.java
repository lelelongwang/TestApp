package com.eben.longjunhao.remoteservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testapp.ndkjnisoDemo.ndk.JniUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.other_jni_text);
        textView.setText("三方应用通过so文件调用c语言代码："+JniUtils.getStringFromJNI());
    }
}
