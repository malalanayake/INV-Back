package com.sthn.model.entity;


import com.google.gson.Gson;
import com.sthn.model.PaymentMessage;
import org.junit.Assert;
import org.junit.Test;

public class PaymentMessageTest {

    @Test
    public void generateTheJsonOutOfPaymentMessage() {
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setId(1);
        paymentMessage.setAccountNo("123456789");
        paymentMessage.setRoutingNo("987654321");
        paymentMessage.setAmount("2000");
        paymentMessage.setMessage("Payment for the suppliers");
        paymentMessage.setExchangeId("ABC-TEST-MESSAGE-0101");

        Gson json = new Gson();
        String message = json.toJson(paymentMessage);
        //System.out.println(message);
        Assert.assertEquals("{\"id\":1,\"message\":\"Payment for the suppliers\",\"accountNo\":\"123456789\",\"routingNo\":\"987654321\",\"amount\":\"2000\",\"exchangeId\":\"ABC-TEST-MESSAGE-0101\"}", message);
    }
}
