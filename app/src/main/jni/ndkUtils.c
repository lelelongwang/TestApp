//
// Created by Admitor on 2019-01-04 0004.
//

#include <jni.h>
#include "com_example_testapp_ndkjnisoDemo_ndk_JniUtils.h"

JNIEXPORT jstring JNICALL Java_com_example_testapp_ndkjnisoDemo_ndk_JniUtils_getStringFromJNI
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "通过JNI成功调用C语言");
}
