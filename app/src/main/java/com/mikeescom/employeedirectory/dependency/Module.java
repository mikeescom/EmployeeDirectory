package com.mikeescom.employeedirectory.dependency;

import com.mikeescom.employeedirectory.model.network.EmployeeService;

import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@dagger.Module
public class Module {
    @Provides
    public EmployeeService provideEmployeeService() {
        String BASE_URL = "https://s3.amazonaws.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EmployeeService.class);
    }
}
