package ua.mai.fam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 *
 */
@Configuration
@Profile("db-postgres")
public class DbConfigPostgres {

//have infrastructure related beans like DataSource, JNDI, etc.
//  @Bean
//  @Profile("db-h2")
//  public DataSource dataSource(){
//    EmbeddedDatabaseBuilder builder =
//        new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2);//in-memory
//    builder.addScript("schema.scripts");
//    builder.addScript("data.scripts");
//   return builder.build();
//  }

//  @Bean //implementation
//  public PlatformTransactionManager transactionManager(){
//    return new DataSourceTransactionManager(dataSource());
//  }
}
