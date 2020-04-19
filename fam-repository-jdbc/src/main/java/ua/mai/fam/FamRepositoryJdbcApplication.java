package ua.mai.fam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.mai.fam.repository.PersonDtoRepository;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamRepositoryJdbcApplication implements CommandLineRunner {

    @Autowired
    PersonDtoRepository repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRepositoryJdbcApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("** PersonDto Repository Jdbc.count = " + repository.count());
        System.out.println("** PersonDto Repository Jdbc.findById(50000L) = " + repository.findById(50000L));
    }

}
