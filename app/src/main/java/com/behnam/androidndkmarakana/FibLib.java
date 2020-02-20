package com.behnam.androidndkmarakana;

public class FibLib {


    static {
        System.loadLibrary("fibo-lib");
    }

    public static long fibJR(long n) {
        return n <= 0 ? 0 : n == 1 ? 1 : fibJR(n - 1) + fibJR(n - 2);
    }

    public static native long fibNR(long n);

    public static long fibJI(long n) {
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
