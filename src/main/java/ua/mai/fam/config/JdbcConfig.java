package ua.mai.fam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Profile("da-jdbc")
public class JdbcConfig {

    @Autowired
    DataSource dataSource;

//    /**
//     * @return {@link ApplicationListener} for {@link RelationalEvent}s.
//     */
//    @Bean
//    public ApplicationListener<?> loggingListener() {
//
//        return (ApplicationListener<ApplicationEvent>) event -> {
//            if (event instanceof RelationalEvent) {
//                System.out.println("Received an event: " + event);
//            }
//        };
//    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

}
