package org.zaregoto.mqoparser.util;

public class LogUtil {

    private static final boolean USE_LOG = true;

    public static void d(String msg) {
        if (USE_LOG) {
            System.out.println(msg);
        }
    }

}