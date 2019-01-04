package com.example.testapp.ndkjnisoDemo.ndk;

public class JniUtils {

    // 加载native-jni
    static {
        System.loadLibrary("serial_port");
    }

    //java调C中的方法都需要用native声明且方法名必须和c的方法名一样
    public static native String getStringFromJNI();
}
