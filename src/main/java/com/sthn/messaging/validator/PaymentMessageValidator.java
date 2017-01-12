package com.sthn.messaging.validator;

import com.sthn.model.PaymentMessage;
import org.springframework.stereotype.Service;

@Service
public class PaymentMessageValidator implements IValidator<PaymentMessage> {

    @Override
    public PaymentMessage validate(PaymentMessage paymentMessage) {
        return paymentMessage;
    }
}
