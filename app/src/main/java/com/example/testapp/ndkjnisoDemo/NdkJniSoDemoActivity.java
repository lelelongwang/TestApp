package com.example.testapp.ndkjnisoDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.ndkjnisoDemo.ndk.JniUtils;

/**
 * 参考文档：
 * https://github.com/wobiancao/NdkJniDemo
 * https://www.jianshu.com/p/d8cde65cb4f7
 * 在最新版本上gradle.properties中android.useDeprecatedNdk和android.deprecatedNdkCompileLease = 1512283120054
 * 不在支持，需要通过先通过SDKManager下载：CMake和LLDB 来生成so文件，参考：
 * https://blog.csdn.net/xiaozhu0922/article/details/78835144
 * 个人博客：
 * https://www.cnblogs.com/longjunhao/p/10220173.html
 */
public class NdkJniSoDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_jni_so_demo);
        TextView textView = (TextView)findViewById(R.id.jni_text);
        textView.setText(JniUtils.getStringFromJNI());
    }
}
