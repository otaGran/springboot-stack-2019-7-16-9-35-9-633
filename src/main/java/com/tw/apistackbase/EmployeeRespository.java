package com.tw.apistackbase;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class EmployeeRespository {

    private final List<Employee> employees = new ArrayList<>();



    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public Employee findById(Long ID){
        Employee employee = employees.stream()
                .filter(v -> ID == v.getID())
                .findFirst()
                .orElse(null);


        return  employee;
    }

    public List<Employee> getEmployees(){
        return employees;
    }
}
