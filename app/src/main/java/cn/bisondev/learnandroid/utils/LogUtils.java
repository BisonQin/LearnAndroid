package cn.bisondev.learnandroid.utils;

/**
 * Created by Bison on 2017/4/27.
 */

public class LogUtils {

    /**
     * 打印相关的的参数
     * @return
     */
    public static String logThis() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        return elements[3].getClassName() + "-->" + elements[3].getMethodName();
    }
}
