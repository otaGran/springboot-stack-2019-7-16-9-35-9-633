package com.tw.apistackbase;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class EmployeeRespository {

    @Autowired
    private List<Employee> employees;

    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public List<Employee> getEmployees(){
        return employees;
    }
}
