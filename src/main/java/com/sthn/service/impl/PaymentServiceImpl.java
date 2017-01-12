package com.sthn.service.impl;


import com.sthn.config.RouteConfig;
import com.sthn.messaging.IRouteInitService;
import com.sthn.model.PaymentMessage;
import com.sthn.service.IPaymentService;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    IRouteInitService iRouteInitService;

    @Override
    public PaymentMessage pay(PaymentMessage paymentMessage) {

        Exchange message = iRouteInitService.enter(RouteConfig.DIRECT_PAYMENT, paymentMessage);
        paymentMessage.setExchangeId(message.getExchangeId());
        return paymentMessage;
    }
}
