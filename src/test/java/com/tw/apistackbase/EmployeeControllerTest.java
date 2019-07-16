package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeRespository mockEmployeeRespository;

    @Test
    public void should_return_employees_when_request_all_employees_api() throws Exception{
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee("Alexander", 18, "male",1L));

        Mockito.when(mockEmployeeRespository.getEmployees()).thenReturn(mockList);

        System.out.println(mockEmployeeRespository.getEmployees().size());

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"name\": \"Alexander\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"id\": 1\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void should_return_specify_employee_when_requesta_certain_specific_employee_api() throws Exception{
        List<Employee> mockList = new ArrayList<>();
        mockList.add(new Employee("Alexander", 18, "male",1L));
        mockList.add(new Employee("Eric", 19, "male",2L));
        mockList.add(new Employee("William", 22, "male",3L));

        Mockito.when(mockEmployeeRespository.findById(1L)).thenReturn(mockList.get(0));
        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Alexander\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"id\": 1\n" +
                        "}"));
    }
}
