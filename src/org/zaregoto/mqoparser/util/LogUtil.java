package org.zaregoto.mqoparser.util;

import org.zaregoto.mqoparser.Settings;

public class LogUtil {

    public static void d(String msg) {
        if (Settings.USE_LOG) {
            System.out.println(msg);
        }
    }

}