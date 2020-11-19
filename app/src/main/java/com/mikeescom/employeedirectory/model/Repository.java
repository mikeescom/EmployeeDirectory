package com.mikeescom.employeedirectory.model;

import androidx.lifecycle.MutableLiveData;

import com.mikeescom.employeedirectory.model.dao.Employee;
import com.mikeescom.employeedirectory.model.dao.Response;
import com.mikeescom.employeedirectory.model.network.EmployeeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private final EmployeeService employeeService;
    private final MutableLiveData<Employee[]> employeesMutableLiveData = new MutableLiveData<>();

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeService = retrofit.create(EmployeeService.class);
    }

    public void callEmployeeService() {
        employeeService.getEmployees().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                employeesMutableLiveData.postValue(response.body().getEmployees());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Employee[]> getEmployees() {
        return employeesMutableLiveData;
    }
}
