package com.mikeescom.employeedirectory.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mikeescom.employeedirectory.model.Repository;
import com.mikeescom.employeedirectory.model.dao.Employee;

public class MainViewModel extends ViewModel {
    private final Repository repository;

    public MainViewModel() {
        super();
        repository = new Repository();
    }

    public void callEmployeeService() {
        repository.callEmployeeService();
    }

    public MutableLiveData<Employee[]> getEmployees() {
        return repository.getEmployees();
    }
}
