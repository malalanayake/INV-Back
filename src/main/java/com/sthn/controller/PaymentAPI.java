package com.sthn.controller;

import com.sthn.common.exception.PaymentException;
import com.sthn.config.RESTAPIConfig;
import com.sthn.model.PaymentMessage;
import com.sthn.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RESTAPIConfig.LOG_MESSAGE_API)
public class PaymentAPI {

    @Autowired
    IPaymentService iPaymentService;

    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentMessage pay(PaymentMessage paymentMessage) throws PaymentException {

        paymentMessage = iPaymentService.pay(paymentMessage);
        return paymentMessage;
    }
}
