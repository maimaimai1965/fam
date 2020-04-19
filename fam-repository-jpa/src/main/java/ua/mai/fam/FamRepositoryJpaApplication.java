package ua.mai.fam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.service.PersonService;

@EnableTransactionManagement
//Не используем Security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamRepositoryJpaApplication implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FamRepositoryJpaApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("** Person Repository Jpa.count = " + personRepository.count());
        System.out.println("** Person Repository Jpa.findById(50000L) = " + personRepository.findById(50000L));

//        //Вставка без Id. Id генерится в БД.
//        Person person = new PersonBuilder().setSurname("surname01").setFirstName("firstName01")
//                                           .setMiddleName("middleName01").createPerson();
//        Person savedPerson = repository.save(person);
//        System.out.println(" repositoryJpa.save() = " + savedPerson);

        System.out.println("** Person Service Jpa.count = " + personService.count());
        System.out.println("** Person Service Jpa.findById(50000L) = " + personService.findById(50000L));

    }

}
