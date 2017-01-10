package com.sthn.controller;

import com.google.gson.Gson;
import com.sthn.config.InitTestProcess;
import com.sthn.config.RESTAPIConfig;
import com.sthn.model.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class CustomerAPITestTestProcess extends InitTestProcess {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCustomerShouldWorkWithCorrectData() throws Exception {
        Customer customer = new Customer();
        customer.setName("Jon Tucker");
        customer.setAddress("300 Grand Avenue, Iowa City, USA");

        Gson json = new Gson();
        String message = json.toJson(customer);
        System.out.println("TEST DATA : [" + message + "]");
        this.mockMvc.perform(post("/" + RESTAPIConfig.CUSTOMER_API + "/create")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                .content(message).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jon Tucker"))
                .andExpect(jsonPath("$.address").value("300 Grand Avenue, Iowa City, USA"));
    }


    @Test(expected = NestedServletException.class)
    public void createCustomerShouldThrowExceptionWhenPassTheCustomerID() throws Exception {
        Customer customer = new Customer();
        customer.setId(1l);

        Gson json = new Gson();
        String message = json.toJson(customer);
        System.out.println("TEST DATA : [" + message + "]");
        this.mockMvc.perform(post("/" + RESTAPIConfig.CUSTOMER_API + "/create")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                .content(message).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        );

    }

}
