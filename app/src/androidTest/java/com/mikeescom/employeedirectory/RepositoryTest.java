package com.mikeescom.employeedirectory;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static com.mikeescom.employeedirectory.Utils.SUCCESS;
import static com.mikeescom.employeedirectory.Utils.ERROR_EMPTY_DATA;
import static com.mikeescom.employeedirectory.Utils.ERROR_ILLEGAL_STATE;
import static org.junit.Assert.*;

import com.mikeescom.employeedirectory.model.Repository;
import com.mikeescom.employeedirectory.model.dao.Employee;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RepositoryTest {
    private Repository repository;

    @Rule
    public InstantTaskExecutorRule instantRule = new InstantTaskExecutorRule() ;

    @Before
    public void setUp () {
        EmployeeServiceMock employeeServiceMock = new EmployeeServiceMock();
        repository = new Repository(employeeServiceMock);
    }

    @Test
    public void success_test() {
       repository.callEmployeeService();
       repository.getEmployees().observeForever(response -> {
           Employee[] employees = response.getBody().getEmployees();
           assertTrue(response.isSuccessful());
           assertEquals(response.getCode(), SUCCESS);
           assertNotEquals(employees.length, 0);
       });
    }

    @Test
    public void malformed_json_test() {
        repository.callEmployeeMalformedService();
        repository.getEmployees().observeForever(response -> {
            assertFalse(response.isSuccessful());
            assertEquals(response.getCode(), ERROR_ILLEGAL_STATE);
        });
    }

    @Test
    public void empty_test() {
        repository.callEmployeeEmptyService();
        repository.getEmployees().observeForever(response -> {
            assertFalse(response.isSuccessful());
            assertEquals(response.getCode(), ERROR_EMPTY_DATA);
        });
    }
}
