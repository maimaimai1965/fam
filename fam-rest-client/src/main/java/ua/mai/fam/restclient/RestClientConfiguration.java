package ua.mai.fam.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class RestClientConfiguration {

    @Bean
    public FamClient famClient(@Value("${app.rest.base-url}") String baseUrl) {
        return new FamClient(baseUrl);
    }

}
