package com.mikeescom.employeedirectory.model;

import android.util.Log;
import android.util.MalformedJsonException;

import androidx.lifecycle.MutableLiveData;

import com.mikeescom.employeedirectory.model.dao.Employees;
import com.mikeescom.employeedirectory.model.network.CustomResponse;
import com.mikeescom.employeedirectory.model.network.EmployeeService;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mikeescom.employeedirectory.Utils.ERROR_EMPTY_DATA;
import static com.mikeescom.employeedirectory.Utils.ERROR_MALFORMED_JSON;
import static com.mikeescom.employeedirectory.Utils.ERROR_MALFORMED_URL;
import static com.mikeescom.employeedirectory.Utils.ERROR_UNKNOWN_HOST;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private final EmployeeService employeeService;
    private final MutableLiveData<CustomResponse> employeesMutableLiveData = new MutableLiveData<>();

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeService = retrofit.create(EmployeeService.class);
    }

    public void callEmployeeService() {
        //employeeService.getEmployees().enqueue(new Callback<Employees>() {
        employeeService.getEmployeesEmpty().enqueue(new Callback<Employees>() {
        //employeeService.getEmployeesMalformed().enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(@NotNull Call<Employees> call, @NotNull Response<Employees> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEmployees().length == 0) {
                        employeesMutableLiveData.postValue(new CustomResponse(ERROR_EMPTY_DATA));
                    } else {
                        employeesMutableLiveData.postValue(new CustomResponse(response));
                    }
                } else {
                    employeesMutableLiveData.postValue(new CustomResponse(response));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Employees> call, @NotNull Throwable t) {
                Log.e(TAG, t.getMessage());

                if (t instanceof UnknownHostException) {
                    employeesMutableLiveData.postValue(new CustomResponse(ERROR_UNKNOWN_HOST));
                } else if (t instanceof MalformedURLException) {
                    employeesMutableLiveData.postValue(new CustomResponse(ERROR_MALFORMED_URL));
                } else if (t instanceof MalformedJsonException) {
                    employeesMutableLiveData.postValue(new CustomResponse(ERROR_MALFORMED_JSON));
                } else {
                    employeesMutableLiveData.postValue(new CustomResponse(-1));
                }
            }
        });
    }

    public MutableLiveData<CustomResponse> getEmployees() {
        return employeesMutableLiveData;
    }
}
