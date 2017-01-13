package com.sthn.messaging.validator;

import com.google.gson.Gson;
import com.sthn.model.PaymentMessage;
import org.springframework.stereotype.Service;

@Service
public class PaymentMessageValidator implements IValidator<PaymentMessage,String> {

    @Override
    public String validate(PaymentMessage paymentMessage) {
        Gson json = new Gson();
        String message = json.toJson(paymentMessage);
        return message;
    }
}
