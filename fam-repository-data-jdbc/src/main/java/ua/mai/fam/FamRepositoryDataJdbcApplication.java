package ua.mai.fam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.repository.TogetherTypeRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamRepositoryDataJdbcApplication implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TogetherTypeRepository togetherTypeRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRepositoryDataJdbcApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("** Person repositoryDataJdbc.count = " + personRepository.count());
        System.out.println("** Person repositoryDataJdbc.findById(50000L) = " + personRepository.findById(50000L));

        System.out.println("** TogetherType repositoryDataJdbc.findById('MARRIAGE') = " +
            togetherTypeRepository.findByCode("MARRIAGE"));

//        System.out.println("** TogetherType repositoryDataJdbc.findById('MARRIAGE') = " +
//            togetherTypeRepository.existsByCode("MARRIAGE"));
//        List<String> list = new ArrayList<>();
//        list.add("MARRIAGE");
//        System.out.println("** TogetherType repositoryDataJdbc.findById('MARRIAGE') = " +
//            togetherTypeRepository.findAllByCode(list));

    }

}
