package com.mikeescom.employeedirectory.model.network;

import com.mikeescom.employeedirectory.model.dao.Employee;
import com.mikeescom.employeedirectory.model.dao.Employees;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CustomResponse {
    private Employees body;
    private int code;
    private ResponseBody errorBody;
    private boolean isSuccessful;
    private String message;

    public CustomResponse(Response<Employees> response) {
        this.body = response.body();
        this.code = response.code();
        this.errorBody = response.errorBody();
        this.isSuccessful = response.isSuccessful();
        this.message = response.message();
    }

    public CustomResponse(int code) {
        this.isSuccessful = false;
        this.code = code;
    }

    public Employees getBody() {
        return body;
    }

    public int getCode() {
        return code;
    }

    public ResponseBody getErrorBody() {
        return errorBody;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }
}
