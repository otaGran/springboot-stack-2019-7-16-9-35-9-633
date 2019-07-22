package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    private List<Employee> employees ;


    public EmployeeController() {
        employees = new ArrayList<Employee>() {{
            add(new Employee("1000", "Alexander", 15, "male", 500));
            add(new Employee("1001", "Alex", 36, "female", 5680));
            add(new Employee("1002", "Jason", 75, "male", 5630));
            add(new Employee("1003", "jense", 45, "male", 8000));
            add(new Employee("1004", "test", 66, "female", 6200));
            add(new Employee("1005", "John", 19, "female", 10000));
        }};
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employees.add(employee));
    }


    @GetMapping("/employees")
    public ResponseEntity getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "pageSize", defaultValue = "0") int pageSize,
                                       @RequestParam(value = "name", defaultValue = "") String name) {

        if (page == 0 || pageSize == 0)
            return ResponseEntity.ok(employees);
        return ResponseEntity.ok(employees.subList((page - 1) * pageSize, page * pageSize));
    }




    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeesById(@PathVariable String id) {
        Employee employee = employees.stream()
                .filter(v -> v.getID().equals(id))
                .findFirst()
                .orElse(null);
        return employee == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(employee);
    }


    @GetMapping(value = "/employees", params = "gender")
    public ResponseEntity getEmployeesByGender(@RequestParam String gender) {
        List<Employee> employeeList = employees.stream()
                .filter(v -> v.getGender().equals(gender))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeList);
    }

//    @PostMapping("/employees")
//    public ResponseEntity addEmployee(@RequestBody Employee employee) {
//        employee.setID("007");
//        employees.add(employee);
//        return ResponseEntity.ok(employee);
//    }

    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        Employee originEmployee = employees.stream()
                .filter(v -> v.getID().equals(id))
                .findFirst()
                .orElse(null);
        if (originEmployee != null) {
            originEmployee.setName(employee.getName());
            originEmployee.setGender(employee.getGender());
            originEmployee.setSalary(employee.getSalary());
            originEmployee.setAge(employee.getAge());
            return ResponseEntity.ok(originEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        Employee deleteEmployee = employees.stream()
                .filter(v -> v.getID().equals(id))
                .findFirst()
                .orElse(null);
        if (deleteEmployee != null) {
            employees.remove(deleteEmployee);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }
}