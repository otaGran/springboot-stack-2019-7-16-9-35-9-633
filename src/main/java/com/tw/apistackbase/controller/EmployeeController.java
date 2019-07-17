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
        return ResponseEntity.ok(employees.save(employee));
    }




    @GetMapping("/employees")
    public ResponseEntity getEmployees(@RequestParam(value = "page",defaultValue = "0")int page,
                                       @RequestParam(value = "pageSize",defaultValue = "0")int pageSize,
                                       @RequestParam(value = "name",defaultValue = "")String name){

        if(page==0 || pageSize==0)
            return ResponseEntity.ok(employees.findAll());
        return ResponseEntity.ok(employees.findAll().subList((page-1)*pageSize,page*pageSize));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getSpecifyEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employees.findById(id));
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployees(@PathVariable long id,@RequestBody Employee employee){
        int result = employees.updateEmployeeById(employee,id);
        Employee byId = employees.findById(id).orElse(null);
        return ResponseEntity.ok(byId);
    }
}