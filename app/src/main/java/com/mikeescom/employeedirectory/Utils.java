package com.mikeescom.employeedirectory;

import com.mikeescom.employeedirectory.model.dao.Employees;

import com.google.gson.Gson;

public class Utils {

    public final static int SUCCESS = 200;
    public final static int ERROR_ILLEGAL_STATE = 2001;
    public final static int ERROR_MALFORMED_JSON = 2002;
    public final static int ERROR_UNKNOWN_HOST = 2003;
    public final static int ERROR_EMPTY_DATA = 2004;


    public static String getErrorMessage(int code) {
        switch (code) {
            case ERROR_ILLEGAL_STATE:
            case ERROR_MALFORMED_JSON:
            case ERROR_UNKNOWN_HOST: return "Server problem. Please try again later.";
            case ERROR_EMPTY_DATA: return "No employees in directory";
            default: return "Unknown error";
        }
    }

    public static Employees fromJson(String s) {
        return new Gson().fromJson(s, Employees.class);
    }
}
