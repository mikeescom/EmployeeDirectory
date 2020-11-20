package com.mikeescom.employeedirectory.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mikeescom.employeedirectory.dependency.DaggerComponent;
import com.mikeescom.employeedirectory.model.Repository;
import com.mikeescom.employeedirectory.model.network.CustomResponse;

public class MainViewModel extends ViewModel {
    private final Repository repository;

    public MainViewModel() {
        super();
        repository = DaggerComponent.create().buildRepository();
    }

    public void callEmployeeService() {
        repository.callEmployeeService();
    }

    public MutableLiveData<CustomResponse> getEmployees() {
        return repository.getEmployees();
    }
}
