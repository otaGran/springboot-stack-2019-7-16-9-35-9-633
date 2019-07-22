package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    private List<Company> companies;

    public CompanyController() {
        Employee Alex = new Employee("1001", "Alex", 36, "female", 5680);
        Employee Jason = new Employee("1002", "Jason", 75, "male", 5630);
        Employee jense = new Employee("1003", "jense", 45, "male", 8000);
        Company company1 = new Company("oocl", 100, new ArrayList<Employee>() {{
            add(Alex);
        }}, 456);
        Company company2 = new Company("b", 10, new ArrayList<Employee>() {{
            add(Jason);
            add(jense);
        }}, 123);
        companies = new ArrayList<Company>() {{
            add(company1);
            add(company2);
        }};
    }

//    @GetMapping("/companies")
//    public ResponseEntity getCompanies() {
//        return ResponseEntity.ok(companies);
//    }

    @GetMapping("/companies/{id}")
    public ResponseEntity getCompanyById(@PathVariable int id) {
        Company company = companies.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
        return company == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(company);
    }

    @GetMapping("/companies/{id}/employees")
    public ResponseEntity getEmployeesByCompanyId(@PathVariable int id) {
        Company company = companies.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
        return company == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(company.getEmployees());
    }



    @GetMapping("/companies")
    public ResponseEntity getCompaniesByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "pageSize", defaultValue = "0") int pageSize,
                                       @RequestParam(value = "name", defaultValue = "") String name) {

        if (page == 0 || pageSize == 0)
            return ResponseEntity.ok(companies);
        return ResponseEntity.ok(companies.subList((page - 1) * pageSize, page * pageSize));
    }

    @PostMapping("/companies")
    public ResponseEntity addCompany(@RequestBody Company company) {

        companies.add(company);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity updateCompanyById(@PathVariable int id, @RequestBody Company company) {
        Company updateCompany = companies.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
        if (updateCompany != null) {
            updateCompany.setCompanyName(company.getCompanyName());
            updateCompany.setEmployees(company.getEmployees());
            return ResponseEntity.ok(updateCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable int id) {
        Company deleteCompany = companies.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
        if (deleteCompany != null) {
            companies.remove(deleteCompany);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}