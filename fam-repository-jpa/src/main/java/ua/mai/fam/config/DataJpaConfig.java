package ua.mai.fam.config;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.RelationalEvent;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableJdbcRepositories(basePackages="ua.mai.fam.repository.jpa")
@Profile("da-jpa")
public class DataJpaConfig extends AbstractJdbcConfiguration {

    /**
     * @return {@link ApplicationListener} for {@link RelationalEvent}s.
     */
    @Bean
    public ApplicationListener<?> loggingListener() {

        return (ApplicationListener<ApplicationEvent>) event -> {
            if (event instanceof RelationalEvent) {
                System.out.println("Received an event: " + event);
            }
        };
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations operations) {
        return new NamedParameterJdbcTemplate(operations);
    }
//    @Bean
//    NamedParameterJdbcOperations operations(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

}
