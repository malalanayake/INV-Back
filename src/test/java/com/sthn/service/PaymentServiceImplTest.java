package com.sthn.service;

import com.sthn.config.InitTestProcess;
import com.sthn.config.RouteConfig;
import com.sthn.config.SpringSecurityWebAppConfig;
import com.sthn.messaging.impl.RouteInitServiceImpl;
import com.sthn.model.PaymentMessage;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class PaymentServiceImplTest extends InitTestProcess {

    private static final String MOCK_DIRECT_M_PAYMENT_TOPIC = "mock:" + RouteConfig.DIRECT_M_PAYMENT_TOPIC;

    @Autowired
    IPaymentService paymentService;
    @Autowired
    ModelCamelContext modelCamelContext;

    @EndpointInject(uri = MOCK_DIRECT_M_PAYMENT_TOPIC)
    MockEndpoint mockPublishRoute;


    @PostConstruct
    public void init() throws Exception {
        modelCamelContext.getRouteDefinition(RouteConfig.ROUTE_NAME_PAYMENT).adviceWith(modelCamelContext, new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(RouteConfig.DIRECT_M_PAYMENT_TOPIC).skipSendToOriginalEndpoint().to(MOCK_DIRECT_M_PAYMENT_TOPIC);
            }
        });

    }

    @Test
    public void paymentMessageShouldEnterToThePaymentRoute() throws InterruptedException {
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setId(200);
        paymentMessage.setAccountNo("9876589");
        paymentMessage.setRoutingNo("2564789");
        paymentMessage.setAmount("10000");
        paymentMessage.setMessage("Supplier invoice payment");

        assertEquals(null, paymentMessage.getDateTime());

        paymentMessage = paymentService.pay(paymentMessage);
        assertNotEquals(null, paymentMessage.getExchangeId());
        assertNotEquals(null, paymentMessage.getDateTime());

        mockPublishRoute.setExpectedMessageCount(1);
        mockPublishRoute.assertIsSatisfied();
    }
}
