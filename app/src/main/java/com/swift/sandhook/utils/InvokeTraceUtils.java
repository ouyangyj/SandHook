package com.swift.sandhook.utils;

import android.util.Log;

import java.util.Map;

/**
 * @Author ouyangyujun
 * @Date 2021/11/12-下午5:05
 * @Email ouyangyujun@yy.com
 * @Since v1.0.0
 * @Desc
 */
public class InvokeTraceUtils {
    /**
     * 输出当前应用-线程堆栈的日志
     */
    public static void printTrace(String tag) {
        Log.e("Dump Stack#00: ", "---------------start----------------");
        Log.e("Dump Stack#00: ", "---------------tag: " + tag);
        StringBuffer sb = new StringBuffer("\n");
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        int off = 7;
        int len = 10;
        int idx = off;
        int max = off + len;
        while (idx < max && idx < st.length) {
            StackTraceElement el = st[idx];
            sb.append("at ").append(el).append("\n");
            idx++;
        }
        Log.e("Dump Stack#00: ", sb.toString());
        Log.e("Dump Stack#00: ", "---------------over----------------");
    }

    /**
     * 跨进程线程，输出实际调用的方法堆栈
     */
    public static void printTrace1(String tag) {
        // 函数调用完成之后打印堆栈调用的信息
        // 方法一:
        Log.e("Dump Stack#1: ", "---------------start----------------");
        Log.e("Dump Stack#1: ", "---------------tag: " + tag);
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                Log.i("Dump Stack-" + i + ": ",
                        stackElements[i].getClassName()
                        + "." + stackElements[i].getMethodName()
                        + "(" + stackElements[i].getFileName()
                        + ":" + stackElements[i].getLineNumber()
                        + ")"
                );
            }
        }
        Log.e("Dump Stack1: ", "---------------over----------------");
    }

    public static void printTrace2() {
        // 方法二:
        Log.e("Dump Stack2: ", "---------------start----------------");
        new Exception().printStackTrace(); // 直接干脆
        Log.e("Dump Stack2: ", "---------------over----------------");
    }

    public static void printTrace3() {
        Log.e("Dump Stack3: ", "---------------start----------------");
        // 方法三:
        Thread.dumpStack(); // 直接暴力
        Log.e("Dump Stack3: ", "---------------over----------------");
    }

    public static void printTrace4() {
        // 方法四:
        // 打印调用堆栈: http://blog.csdn.net/jk38687587/article/details/51752436
        Log.e("Dump Stack4: ", "---------------start----------------");
        RuntimeException e = new RuntimeException("<Start dump Stack !>");
        e.fillInStackTrace();
        Log.e("<Dump Stack>:", "++++++++++++", e);
        Log.e("Dump Stack4: ", "---------------over----------------");
    }

    /**
     * Thread类的getAllStackTraces（）方法获取虚拟机中所有线程的StackTraceElement对象，可以查看堆栈
     * 具体方法调用信息深度不明确
     */
    public static void printTrace5(String tag) {
        // 方法五:
        // Thread类的getAllStackTraces（）方法获取虚拟机中所有线程的StackTraceElement对象，可以查看堆栈
        Log.e("Dump Stack4: ", "---------------start----------------");
        Log.e("Dump Stack4: ", "---------------tag: " + tag);
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace: Thread.getAllStackTraces().entrySet())
        {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();

            // 进行过滤
            if (thread.equals(Thread.currentThread())) {
                continue;
            }

            Log.e("[Dump Stack]","**********Thread name：" + thread.getName()+"**********");
            int i = 0;
            for (StackTraceElement stackTraceElement : stack) {
                Log.e("Dump Stack-" + i + ": ",
                    stackTraceElement.getClassName()
                    + "." + stackTraceElement.getMethodName()
                    + "(" + stackTraceElement.getFileName()
                    + ":" + stackTraceElement.getLineNumber()
                    + ")"
                );
            }
            // 增加序列号
            i++;
        }
        Log.e("Dump Stack5: ", "---------------over----------------");
    }
}
