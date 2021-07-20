package mx.com.santander.customer.service.controller.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import mx.com.santander.customer.service.model.CustomerEntity;
import mx.com.santander.customer.service.service.CustomerServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        Assertions.assertNotNull(customerService);
    }


    @Test
    @Order(1)
    public void saveCustomerTest() throws Exception {
        CustomerEntity customerEntity = CustomerEntity
                .builder()
                .firstName("Sergio")
                .lastName("Peña")
                .dateOfBirth(new Date())
                .build();
        this.mockMvc.perform(
                post("http://localhost:8023/customers")
                        .content(objectMapper.writeValueAsString(customerEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void updateCustomerTest() throws Exception {
        CustomerEntity customerEntity = CustomerEntity
                .builder()
                .firstName("Sergio")
                .lastName("Peña Orozco")
                .dateOfBirth(new Date())
                .build();
        this.mockMvc.perform(
                put("http://localhost:8023/customers")
                        .content(objectMapper.writeValueAsString(customerEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getCustomerByIdTest() throws Exception {
        this.mockMvc.perform(
                get("http://localhost:8023/customers/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getAllCustomerTest() throws Exception {
        this.mockMvc.perform(
                get("http://localhost:8023/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void getAllCustomerTestWhenStatusIs204() throws Exception {
        this.mockMvc.perform(
                get("http://localhost:8023/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    public void deleteCustomerTest() throws Exception {
        this.mockMvc.perform(
                delete("http://localhost:8023/customers/1"))
                .andExpect(status().isOk());
    }
}
