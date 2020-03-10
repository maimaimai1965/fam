package mai.ua.fam;

import mai.ua.fam.repository.PersonRepository;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamApplication implements CommandLineRunner {

    @Autowired
    PersonRepository4DataJdbc repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("count = " + repository.count());
    }

}
