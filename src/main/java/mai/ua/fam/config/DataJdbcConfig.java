package mai.ua.fam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.activation.DataSource;

@Configuration
@EnableJdbcRepositories
public class DataJdbcConfig {
//    @Bean
//    NamedParameterJdbcOperations operations(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

}
