package com.sthn.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig extends RouteBuilder {

    //Direct:Routes
    public static final String DIRECT_PUBLISH = "direct:publish";
    public static final String DIRECT_STATUS = "direct:status";
    public static final String DIRECT_PAYMENT = "direct:payment";

    //Middle:Routes with M
    public static final String DIRECT_M_PAYMENT_TOPIC = "direct:m-payment-topic";

    //Route:Name-Tags
    public static final String ROUTE_NAME_PUBLISH = "publish-route";
    public static final String ROUTE_NAME_STATUS = "status-route";
    public static final String ROUTE_NAME_PAYMENT = "payment-route";
    public static final String ROUTE_NAME_M_PAYMENT_TOPIC = "m-payment-topic";

    @Override
    public void configure() throws Exception {
        configureRoutesForLogAPI();
        configureRoutesForPaymentAPI();
    }


    public void configureRoutesForPaymentAPI() {
        from(DIRECT_PAYMENT).to("paymentMessageValidator").to(DIRECT_M_PAYMENT_TOPIC).routeId(ROUTE_NAME_PAYMENT);
        from(DIRECT_M_PAYMENT_TOPIC).to("kafka:localhost:9092?topic=test").routeId(ROUTE_NAME_M_PAYMENT_TOPIC);
    }

    public void configureRoutesForLogAPI() {
        from(DIRECT_PUBLISH).to("publishMessageExtractor").routeId(ROUTE_NAME_PUBLISH);
        from(DIRECT_STATUS).to("statusMessageExtractor").routeId(ROUTE_NAME_STATUS);
    }
}
