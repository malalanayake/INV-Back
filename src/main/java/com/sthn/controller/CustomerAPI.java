package com.sthn.controller;

import com.sthn.config.RESTAPIConfig;
import com.sthn.model.LogMessage;
import com.sthn.model.Waki;
import com.sthn.model.entity.Customer;
import com.sthn.service.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(RESTAPIConfig.CUSTOMER_API)
public class CustomerAPI {

    @Autowired
    @Qualifier(value = "customerServiceImpl")
    GenericCRUDService<Customer> customerGenericCRUDService;

    @ExceptionHandler(value = {Exception.class})
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody Customer customer) throws Exception {
        customerGenericCRUDService.create(customer);
        return customer;
    }

    @ExceptionHandler(value = {Exception.class})
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer get(@PathVariable("id") long id) throws Exception {
        Customer customer = customerGenericCRUDService.get(id);
        return customer;
    }
}
