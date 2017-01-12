package com.sthn.service.impl;

import com.sthn.common.exception.CustomerException;
import com.sthn.model.entity.Customer;
import com.sthn.repository.ICustomerRepo;
import com.sthn.service.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements GenericCRUDService<Customer> {

    @Autowired
    ICustomerRepo iCustomerRepo;

    @Override
    public Customer create(Customer object) throws CustomerException {
        if (object.getId() == 0)
            return iCustomerRepo.save(object);
        else
            throw new CustomerException("Given customer has Id assigned");
    }

    @Override
    public Customer get(long id) {
        return iCustomerRepo.findOne(id);
    }

    @Override
    public Customer update(Customer object) {
        return iCustomerRepo.save(object);
    }

    @Override
    public Customer delete(Customer object) {
        iCustomerRepo.delete(object);
        return object;
    }
}
