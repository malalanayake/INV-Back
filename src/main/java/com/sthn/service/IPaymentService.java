package com.sthn.service;


import com.sthn.model.PaymentMessage;

public interface IPaymentService {

    PaymentMessage pay(PaymentMessage paymentMessage);

}
