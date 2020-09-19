package com.yeongzhiwei.demovalidation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeongzhiwei.demovalidation.domain.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testValid_ValidRequest() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/valid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isOk());
    }

    @Test
    void testValid_InvalidName() throws Exception {
        Customer customer = new Customer("Adam@", "13", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/valid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValid_InvalidAge() throws Exception {
        Customer customer = new Customer("Adam", "Thirteen", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/valid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValid_InvalidJoined() throws Exception {
        Customer customer = new Customer("Adam", "13", "202013020202", "20100101");
        
        this.mockMvc.perform(post("/valid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValid_InvalidDateOfBirth() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100140");
        
        this.mockMvc.perform(post("/valid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValidate_ValidRequest() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isOk());
    }

    @Test
    void testValidate_InvalidName() throws Exception {
        Customer customer = new Customer("Adam@", "13", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValidate_InvalidAge() throws Exception {
        Customer customer = new Customer("Adam", "Thirteen", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValidate_InvalidJoined() throws Exception {
        Customer customer = new Customer("Adam", "13", "202013020202", "20100101");
        
        this.mockMvc.perform(post("/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testValidate_InvalidDateOfBirth() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100140");
        
        this.mockMvc.perform(post("/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isBadRequest());
    }


    @Test
    void testValidated_ValidRequest() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100101");
        
        this.mockMvc.perform(post("/validated")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isOk());
    }

    @Test
    void testValidated_InvalidName() throws Exception {
        Customer customer = new Customer("Adam@", "13", "202002020202", "20100101");
        
        NestedServletException ex = assertThrows(NestedServletException.class, () -> {
            this.mockMvc.perform(post("/validated")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isInternalServerError());
        });
        assertThat(ex.getMessage(), containsString("ConstraintViolationException"));
    }

    @Test
    void testValidated_InvalidAge() throws Exception {
        Customer customer = new Customer("Adam", "Thirteen", "202002020202", "20100101");
        
        NestedServletException ex = assertThrows(NestedServletException.class, () -> {
            this.mockMvc.perform(post("/validated")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isInternalServerError());
        });
        assertThat(ex.getMessage(), containsString("ConstraintViolationException"));
    }

    @Test
    void testValidated_InvalidJoined() throws Exception {
        Customer customer = new Customer("Adam", "13", "202013020202", "20100101");
        
        NestedServletException ex = assertThrows(NestedServletException.class, () -> {
            this.mockMvc.perform(post("/validated")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isInternalServerError());
        });
        assertThat(ex.getMessage(), containsString("ConstraintViolationException"));
    }

    @Test
    void testValidated_InvalidDateOfBirth() throws Exception {
        Customer customer = new Customer("Adam", "13", "202002020202", "20100140");
        
        NestedServletException ex = assertThrows(NestedServletException.class, () -> {
            this.mockMvc.perform(post("/validated")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isInternalServerError());
        });
        assertThat(ex.getMessage(), containsString("ConstraintViolationException"));
    }

}
