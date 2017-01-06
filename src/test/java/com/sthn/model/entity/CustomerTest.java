package com.sthn.model.entity;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void generateTheJsonOutOfCustomerEntity(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Dinuka Malalanayake");
        customer.setAddress("United States");

        Gson json = new Gson();
        String message = json.toJson(customer);

        Assert.assertEquals("{\"id\":1,\"name\":\"Dinuka Malalanayake\",\"address\":\"United States\"}", message);
    }
}
