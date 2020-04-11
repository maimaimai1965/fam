package ua.mai.fam.repository.datajdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.PersonRepository;

@Profile("da-data-jdbc")
public interface PersonRepository4DataJdbc extends PersonRepository,
                                                   CrudRepository<Person, Long>, WithOperations<Person>  {

}
