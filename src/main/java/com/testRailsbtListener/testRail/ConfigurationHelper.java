package com.testRailsbtListener.testRail;

public class ConfigurationHelper {

    public static String getProperty(String key) {
        String value = null;
        try {
            value = System.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = null;
        try {
            value = System.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    public static void setPropertyIfEmpty(String key, String value) {
        if (getProperty(key) == null) {
            System.setProperty(key, value);
        }
    }

    public static boolean skipTest(String flag, boolean skipByDefault) {
        String value = getProperty(String.format("%s.skip", flag));
        if (value == null) {
            return skipByDefault;
        }
        return value.equals("true");
    }

    public static boolean skipTest(String flag) {
        return skipTest(flag, false);
    }

    public static boolean isEnabled(String flag) {
        String value = getProperty(String.format("%s.enabled", flag));
        if (value == null) {
            return false;
        }
        return value.equals("true");
    }

}