package mai.ua.fam;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamApplication {

  public static void main(String[] args) {
    SpringApplication.run(FamApplication.class, args);
  }

}
