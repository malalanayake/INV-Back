package com.sthn.config;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class InitTestProcess {
    protected static String ACCESS_TOKEN;

    public InitTestProcess() {
        SpringSecurityWebAppConfig.initializationStormpath();
        ACCESS_TOKEN = System.getenv().get("STORMPATH_ACCESS_TOKEN");
    }
}
