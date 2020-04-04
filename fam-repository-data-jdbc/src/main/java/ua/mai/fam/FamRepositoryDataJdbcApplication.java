package ua.mai.fam;

import ua.mai.fam.model.person.Person;
import ua.mai.fam.model.person.PersonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.mai.fam.repository.datajdbc.PersonRepository4DataJdbc;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamRepositoryDataJdbcApplication implements CommandLineRunner {

    @Autowired
    PersonRepository4DataJdbc repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRepositoryDataJdbcApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("count = " + repository.count());

        Person person = new PersonBuilder().setSurname("surname01").setFirstName("firstName01").setMiddleName("middleName01")
            .createPerson();
        //Вставка без Id. Id генерится в БД.
//        Person savedPerson = repository.save(person);
    }

}
