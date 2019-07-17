package com.tw.apistackbase;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRespositoryTest {
    @Autowired
    private EmployeeRespository employeeRespository;

    @Test
    public void should_return_all_employees_when_query_function(){
        List<Employee> findEmployees = employeeRespository.findAll();
        Assertions.assertThat(findEmployees.size()).isEqualTo(2);
    }


    @Test
    public void should_return_update_employee_when_update_function(){
        Employee employee = employeeRespository.findAll().stream().findFirst().orElse(null);
        employee.setName("123");
        employeeRespository.updateEmployeeById(employee,employee.getID());
        Employee updatedEmployee = employeeRespository.findById(employee.getID()).orElse(null);
        assertEquals("123", updatedEmployee.getName());

    }

    @Before
    public void setUp(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("william", 18, "male"));
        employees.add(new Employee("Alex", 22, "male"));
        employeeRespository.saveAll(employees);

    }

}