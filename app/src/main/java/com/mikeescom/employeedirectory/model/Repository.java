package com.mikeescom.employeedirectory.model;

import android.util.Log;
import android.util.MalformedJsonException;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;

import com.mikeescom.employeedirectory.model.dao.Employees;
import com.mikeescom.employeedirectory.model.network.CustomResponse;
import com.mikeescom.employeedirectory.model.network.EmployeeService;

import org.jetbrains.annotations.NotNull;

import java.net.UnknownHostException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikeescom.employeedirectory.Utils.ERROR_EMPTY_DATA;
import static com.mikeescom.employeedirectory.Utils.ERROR_ILLEGAL_STATE;
import static com.mikeescom.employeedirectory.Utils.ERROR_MALFORMED_JSON;
import static com.mikeescom.employeedirectory.Utils.ERROR_UNKNOWN_HOST;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private final MutableLiveData<CustomResponse> employeesMutableLiveData = new MutableLiveData<>();
    private final EmployeeService employeeService;
    private Callback<Employees> employeesCallback = new Callback<Employees>() {
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
            } else if (t instanceof IllegalStateException) {
                employeesMutableLiveData.postValue(new CustomResponse(ERROR_ILLEGAL_STATE));
            } else if (t instanceof MalformedJsonException) {
                employeesMutableLiveData.postValue(new CustomResponse(ERROR_MALFORMED_JSON));
            } else {
                employeesMutableLiveData.postValue(new CustomResponse(-1));
            }
        }
    };

    @Inject
    public Repository(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void callEmployeeService() {
        employeeService.getEmployees().enqueue(employeesCallback);
    }

    @VisibleForTesting
    public void callEmployeeMalformedService() {
        employeeService.getEmployeesJsonMalformed().enqueue(employeesCallback);
    }

    @VisibleForTesting
    public void callEmployeeEmptyService() {
        employeeService.getEmployeesEmpty().enqueue(employeesCallback);
    }

    public MutableLiveData<CustomResponse> getEmployees() {
        return employeesMutableLiveData;
    }
}
