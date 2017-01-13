package com.sthn.controller;

import com.google.gson.Gson;
import com.sthn.config.InitTestProcess;
import com.sthn.config.RESTAPIConfig;
import com.sthn.config.RouteConfig;
import com.sthn.model.PaymentMessage;
import com.sthn.model.entity.Customer;
import com.sthn.service.IPaymentService;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PaymentAPITest extends InitTestProcess {
    private static final String MOCK_DIRECT_M_PAYMENT_TOPIC = "mock:" + RouteConfig.DIRECT_M_PAYMENT_TOPIC;

    @Autowired
    private MockMvc mockMvc;

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
    public void paymentShouldWorkWithCorrectData() throws Exception {
        DateTime dateTime = new DateTime();

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setId(200);
        paymentMessage.setAccountNo("9876589");
        paymentMessage.setRoutingNo("2564789");
        paymentMessage.setAmount("10000");
        paymentMessage.setMessage("Supplier invoice payment");
        paymentMessage.setDateTime(dateTime.toString());

        Gson json = new Gson();
        String message = json.toJson(paymentMessage);
        System.out.println("TEST DATA : [" + message + "]");
        this.mockMvc.perform(post("/" + RESTAPIConfig.PAYMENT_API + "/pay")
                .content(message).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(200))
                .andExpect(jsonPath("$.accountNo").value("9876589"))
                .andExpect(jsonPath("$.routingNo").value("2564789"))
                .andExpect(jsonPath("$.amount").value("10000"))
                .andExpect(jsonPath("$.message").value("Supplier invoice payment"));
    }

}
