package com.tw.apistackbase;

import com.google.gson.Gson;
import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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


    @Test
    public void should_return_employees_when_request_all_employees_api() throws Exception{

        List<Employee> expectResult = new ArrayList<Employee>() {{
            add(new Employee("1000", "Alexander",15, "male", 500));
            add(new Employee("1001", "Alex", 36,"female",  5680));
            add(new Employee("1002", "Jason",75, "male",  5630));
            add(new Employee("1003", "jense",45, "male",  8000));
            add(new Employee("1004", "test",66, "female",  6200));
            add(new Employee("1005", "John", 19,"female",  10000));
        }};
        mockMvc.perform(get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Alexander\",\"age\":15,\"gender\":\"male\",\"salary\":500,\"id\":\"1000\"},{\"name\":\"Alex\",\"age\":36,\"gender\":\"female\",\"salary\":5680,\"id\":\"1001\"},{\"name\":\"Jason\",\"age\":75,\"gender\":\"male\",\"salary\":5630,\"id\":\"1002\"},{\"name\":\"jense\",\"age\":45,\"gender\":\"male\",\"salary\":8000,\"id\":\"1003\"},{\"name\":\"test\",\"age\":66,\"gender\":\"female\",\"salary\":6200,\"id\":\"1004\"},{\"name\":\"John\",\"age\":19,\"gender\":\"female\",\"salary\":10000,\"id\":\"1005\"}]\n"));
    }

    @Test
    public void should_return_specify_employee_when_requesta_certain_specific_employee_api() throws Exception{
        List<Employee> mockList = new ArrayList<Employee>(){{
                add(new Employee("1000", "Alexander", 15, "male", 500));
        add(new Employee("1001", "Alex", 36, "female", 5680));
        add(new Employee("1002", "Jason", 75, "male", 5630));
        add(new Employee("1003", "jense", 45, "male", 8000));
        add(new Employee("1004", "test", 66, "female", 6200));
        add(new Employee("1005", "John", 19, "female", 10000));
}};
        mockMvc.perform(get("/employees/1001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Alex\",\"age\":36,\"gender\":\"female\",\"salary\":5680,\"id\":\"1001\"}"));
    }


}
