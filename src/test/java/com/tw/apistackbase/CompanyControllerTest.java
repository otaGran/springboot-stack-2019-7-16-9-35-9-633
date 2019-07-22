package com.tw.apistackbase;

import com.google.gson.Gson;
import com.tw.apistackbase.controller.CompanyController;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_return_all_companies_when_get_companies() throws Exception {
        Employee Alex = new Employee("1001", "Alex", 36, "female", 5680);
        Employee Jason = new Employee("1002", "Jason", 75, "male", 5630);
        Employee jense = new Employee("1003", "jense", 45, "male", 8000);
        Company companyA = new Company("oocl", 100, new ArrayList<Employee>() {{
            add(Alex);
        }}, 456);
        Company companyB = new Company("b", 10, new ArrayList<Employee>() {{
            add(Jason);
            add(jense);
        }}, 123);
        List<Company> expectResult = new ArrayList<>();
        expectResult.add(companyA);
        expectResult.add(companyB);

        mvc.perform(get("/companies")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(expectResult, List.class)));
    }

    @Test
    public void should_return_specified_company_when_get_company_by_companyID() throws Exception {
        Employee Alex =new Employee("1001", "Alex", 36, "female", 5680);
        Company expectResult = new Company("oocl", 100, new ArrayList<Employee>() {{
            add(Alex);
        }},456);
        System.out.println(new Gson().toJson(expectResult));
        mvc.perform(get("/companies/456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(expectResult)));
    }

    @Test
    public void should_return_employees_when_get_company_employees() throws Exception {
        List<Employee> expectResult = new ArrayList<Employee>() {{
            add(new Employee("1001", "Alex", 36, "female", 5680));
        }};

        mvc.perform(get("/companies/456/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(expectResult, List.class)));
    }

    @Test
    public void should_return_Paged_companies_when_get_companies_by_page() throws Exception {
        Employee Alex = new Employee("1001", "Alex", 36, "female", 5680);
        Company companyA = new Company("oocl", 100, new ArrayList<Employee>() {{
            add(Alex);
        }},456);
        List<Company> expectResult = new ArrayList<Company>() {{
            add(companyA);
        }};

        mvc.perform(get("/companies?page=1&pageSize=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(expectResult, List.class)));
    }

    @Test
    public void should_delete_specified_company_when_call_delete_company_by_companyID() throws Exception {
        mvc.perform(delete("/companies/456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_Not_Found_when_delete_a_invalid_company() throws Exception {
        mvc.perform(delete("/companies/789")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_add_a_new_company_when_call_add_company() throws Exception {
        Gson gson = new Gson();
        Company company = new Company("CargoSmart",500, new ArrayList<>(),12);
        mvc.perform(post("/companies")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(company)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(company)));
    }

    @Test
    public void should_return_updated_company_when_put_company_to_update_it() throws Exception {
        Gson gson = new Gson();
        Company company = new Company("CargoSmart",500, new ArrayList<>(),12);
        mvc.perform(put("/companies/12")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(company)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(company)));
    }
}