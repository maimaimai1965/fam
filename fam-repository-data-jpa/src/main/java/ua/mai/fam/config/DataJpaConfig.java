package ua.mai.fam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories(basePackages={"ua.mai.fam.repository.datajpa"})
//@EnableJpaAuditing
@Profile("da-data-jpa")
public class DataJpaConfig {

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

//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations operations) {
//        return new NamedParameterJdbcTemplate(operations);
//    }
//    @Bean
//    NamedParameterJdbcOperations operations(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

}
