package com.mikeescom.employeedirectory;

public class Utils {
    public static final int ERROR_MALFORMED_URL = 2001;
    public static final int ERROR_MALFORMED_JSON = 2002;
    public static final int ERROR_UNKNOWN_HOST = 2003;
    public static final int ERROR_EMPTY_DATA = 2004;

    public static String getErrorMessage(int code) {
        switch (code) {
            case ERROR_MALFORMED_URL :
            case ERROR_MALFORMED_JSON :
            case ERROR_UNKNOWN_HOST : return "Server problem. Please try again later.";
            case ERROR_EMPTY_DATA : return "No employees in directory";
            default: return "Unknown error";
        }
    }
}
