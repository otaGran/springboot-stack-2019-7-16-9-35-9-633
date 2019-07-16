package com.tw.apistackbase.controller;

import com.tw.apistackbase.Employee;
import com.tw.apistackbase.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    //private List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeRespository employees;
    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        employee.setID(1);
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employees")
    public ResponseEntity getEmployees(){
        return ResponseEntity.ok(employees.getEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getSpecifyEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employees.findById(id));
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployees(@PathVariable long id,@RequestBody Employee employee){
        Employee originEmployee = employees.getEmployees()
                .stream()
                .filter(v->v.getID()==id)
                .findFirst()
                .orElse(null);

        if (originEmployee != null){
            originEmployee.setID(employee.getID());

            return ResponseEntity.ok(employee);
        }

        return ResponseEntity.notFound().build();
    }
}