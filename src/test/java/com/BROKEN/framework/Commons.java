package com.BROKEN.framework;

import org.apache.commons.logging.Log;

public class Commons {

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Log.class.getName())
                    && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static boolean containsIgnoreCase(String src, String word) {
        final int length = word.length();
        if (length == 0) {
            return true;
        }

        final char firstLo = Character.toLowerCase(word.charAt(0));
        final char firstUp = Character.toUpperCase(word.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, word, 0, length))
                return true;
        }
        return false;
    }
}
