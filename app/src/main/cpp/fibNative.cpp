//
// Created by behnam on 2/20/20.
//
#include <jni.h>
#include <string>
#include <android/log.h>

//because we don't want to repeat these  fibNR functions's input   env, class parameter evert time
static jlong fib(jlong n) {
    return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_behnam_androidndkmarakana_FibLib_fibNR(
        JNIEnv *env,
        jclass, //this//
        jlong n) {
    //ll because we are in 64 bit system
    __android_log_print(ANDROID_LOG_DEBUG, "FibLib.c", "fibNR(%lld)", n);
    return fib(n);
}
extern "C" JNIEXPORT jlong JNICALL
Java_com_behnam_androidndkmarakana_FibLib_fibNI(
        JNIEnv *env,
        jclass,
        jlong n) {
    __android_log_print(ANDROID_LOG_DEBUG, "FibLib.c", "fibNI(%lld)", n);
    jlong previous = -1;
    jlong result = 1;
    jlong i;
    for (i = 0; i <= n; i++) {
        jlong sum = result + previous;
        previous = result;
        result = sum;
    }
    return result;
}