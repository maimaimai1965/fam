package ua.mai.fam;

import org.springframework.http.ResponseEntity;
import ua.mai.fam.controller.rest.PersonRestController;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.repository.datajdbc.PersonRepository4DataJdbc;
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
public class FamRestServerApplication implements CommandLineRunner {

    public static final String APPLICATION_VERSION_PATH = "v1";
    public static final String APPLICATION_REST_PATH = "api/" + APPLICATION_VERSION_PATH;

//    @Autowired
//    PersonRepository repository;

    @Autowired
    PersonRestController personRestController;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRestServerApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
//        System.out.println("count = " + repository.count());

        ResponseEntity<?> responseEntity = personRestController.find(50000L);
        Object entity = responseEntity.getBody();
        System.out.println("+++ personRestController.find(50000L): " + entity.toString());
    }

}
