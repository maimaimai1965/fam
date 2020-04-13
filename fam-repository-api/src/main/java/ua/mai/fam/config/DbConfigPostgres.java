package ua.mai.fam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.mai.fam.util.Profiles;
import ua.mai.fam.util.SpringUtil;

import javax.sql.DataSource;

/*
 *
 */
@Configuration
@Profile(Profiles.DB_POSTGRES)
public class DbConfigPostgres {

    @Bean
    public String dbProfile(DataSource dataSource) {
        SpringUtil.checkActiveDbProfile(Profiles.DB_POSTGRES, dataSource);
        return Profiles.DB_POSTGRES;
    }

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
