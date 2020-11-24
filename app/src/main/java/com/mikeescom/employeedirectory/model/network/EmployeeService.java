package com.mikeescom.employeedirectory.model.network;

import com.mikeescom.employeedirectory.model.dao.Employees;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {
    @GET("sq-mobile-interview/employees.json")
    Call<Employees> getEmployees();

    @GET("sq-mobile-interview/employees_malformed.json")
    Call<Employees> getEmployeesJsonMalformed();

    @GET("sq-mobile-interview/employees_empty.json")
    Call<Employees> getEmployeesEmpty();
}
