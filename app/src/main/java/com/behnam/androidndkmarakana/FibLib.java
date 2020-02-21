package com.behnam.androidndkmarakana;

import android.util.Log;

public class FibLib {

    private static final String TAG = "FibLib";

    private static long fib(long n) {
        return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);

    }

    static {
        System.loadLibrary("fibo-lib");
    }

    public static long fibJR(long n) {
        return fib(n);
    }

    public static native long fibNR(long n);

    public static long fibJI(long n) {

        Log.d(TAG, "FibJI(" + n + ")");
        long previuos = -1;
        long result = 1;
        for (long i = 0; i <= n; i++) {
            long sum = result + previuos;
            previuos = result;
            result = sum;
        }
        return result;
    }

    public static native long fibNI(long n);

}
