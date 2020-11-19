package com.mikeescom.employeedirectory.model.network;

import com.mikeescom.employeedirectory.model.dao.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {
    @GET("sq-mobile-interview/employees.json")
    Call<Response> getEmployees();
}
