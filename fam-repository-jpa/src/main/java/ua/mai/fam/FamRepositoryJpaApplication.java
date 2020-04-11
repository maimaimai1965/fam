package ua.mai.fam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.model.person.PersonBuilder;
import ua.mai.fam.repository.PersonRepository;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamRepositoryJpaApplication implements CommandLineRunner {

    @Autowired
    PersonRepository repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRepositoryJpaApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("repositoryJpa.count = " + repository.count());

        System.out.println("repositoryJpa.findById(50000L) = " + repository.findById(50000L));

        //Вставка без Id. Id генерится в БД.
//        Person person = new PersonBuilder().setSurname("surname01").setFirstName("firstName01")
//            .setMiddleName("middleName01").createPerson();
//        Person savedPerson = repository.save(person);
//        System.out.println(" repositoryJpa.save() = " + savedPerson);
    }

}
