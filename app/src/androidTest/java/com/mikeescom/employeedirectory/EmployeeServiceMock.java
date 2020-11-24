package com.mikeescom.employeedirectory;

import com.mikeescom.employeedirectory.model.dao.Employees;
import com.mikeescom.employeedirectory.model.network.EmployeeService;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeServiceMock implements EmployeeService {
    @Override
    public Call<Employees> getEmployees() {
        return employeeSuccess;
    }

    @Override
    public Call<Employees> getEmployeesJsonMalformed() {
        return employeeJsonMalformed;
    }

    @Override
    public Call<Employees> getEmployeesEmpty() {
        return employeeEmpty;
    }

    Call<Employees> employeeSuccess = new Call<Employees>() {
        private final Employees employees = Utils.fromJson(JsonStrings.SUCCESS_CALL);
        @Override
        public Response<Employees> execute() throws IOException {
            return Response.success(employees);
        }

        @Override
        public void enqueue(Callback<Employees> callback) {
            callback.onResponse(this, Response.success(employees));
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<Employees> clone() {
            return null;
        }

        @Override
        public Request request() {
            return new Request.Builder().build();
        }
    };

    Call<Employees> employeeJsonMalformed = new Call<Employees>() {
        @Override
        public Response<Employees> execute() throws IOException {
            return null;
        }

        @Override
        public void enqueue(Callback<Employees> callback) {
            callback.onFailure(this, new IllegalStateException("Expected BEGIN_ARRAY but was BEGIN_OBJECT"));
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<Employees> clone() {
            return null;
        }

        @Override
        public Request request() {
            return new Request.Builder().build();
        }
    };

    Call<Employees> employeeEmpty = new Call<Employees>() {
        private final Employees employees = Utils.fromJson(JsonStrings.EMPTY_CALL);
        @Override
        public Response<Employees> execute() throws IOException {
            return Response.success(employees);
        }

        @Override
        public void enqueue(Callback<Employees> callback) {
            callback.onResponse(this, Response.success(employees));
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<Employees> clone() {
            return null;
        }

        @Override
        public Request request() {
            return new Request.Builder().build();
        }
    };
}
