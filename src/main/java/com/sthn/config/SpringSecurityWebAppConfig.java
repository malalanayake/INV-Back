package com.sthn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    private static final String APP_HREF = "STORMPATH_APP_HREF";
    private static final String API_KEY_ID = "STORMPATH_API_KEY_ID";
    private static final String API_KEY_SECRET = "STORMPATH_API_KEY_SECRET";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(stormpath()).and()
                .authorizeRequests()
                //Gave the open access to payment api No Auth required.
                .antMatchers("/" + RESTAPIConfig.PAYMENT_API + "/*").permitAll();

    }

    public static void initializationStormpath() {
        System.getProperties().put("stormpath.application.href", System.getenv().get(APP_HREF));
        System.getProperties().put("stormpath.client.apiKey.id", System.getenv().get(API_KEY_ID));
        System.getProperties().put("stormpath.client.apiKey.secret", System.getenv().get(API_KEY_SECRET));
    }
}