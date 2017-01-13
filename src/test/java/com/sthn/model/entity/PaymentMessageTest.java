package com.sthn.model.entity;


import com.google.gson.Gson;
import com.sthn.model.PaymentMessage;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class PaymentMessageTest {

    @Test
    public void generateTheJsonOutOfPaymentMessage() {
        DateTime dt = new DateTime("2015-05-28T12:45:59.000-04:00");

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setId(1);
        paymentMessage.setAccountNo("123456789");
        paymentMessage.setRoutingNo("987654321");
        paymentMessage.setAmount("2000");
        paymentMessage.setMessage("Payment for the suppliers");
        paymentMessage.setExchangeId("ABC-TEST-MESSAGE-0101");
        paymentMessage.setDateTime(dt.toString());

        Gson json = new Gson();
        String message = json.toJson(paymentMessage);
        System.out.println(message);
        Assert.assertEquals("{\"id\":1,\"message\":\"Payment for the suppliers\",\"accountNo\":\"123456789\",\"routingNo\":\"987654321\",\"amount\":\"2000\",\"exchangeId\":\"ABC-TEST-MESSAGE-0101\",\"dateTime\":\"2015-05-28T11:45:59.000-05:00\"}", message);

    }
}
