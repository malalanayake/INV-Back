package com.sthn.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RESTAPIConfigTest {

    @Test
    public void restAPINamesShouldBe() {
        assertEquals(RESTAPIConfig.LOG_MESSAGE_API, "log-api");
        assertEquals(RESTAPIConfig.CUSTOMER_API, "customer-api");
        assertEquals(RESTAPIConfig.PAYMENT_API, "payment-api");
    }
}
