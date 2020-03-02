package mai.ua.fam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 *
 */
@Configuration
@Profile("db-oracle")
public class DbConfigOracle {
  //have infrastructure related beans like DataSource, JNDI, etc.
//  @Bean
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
