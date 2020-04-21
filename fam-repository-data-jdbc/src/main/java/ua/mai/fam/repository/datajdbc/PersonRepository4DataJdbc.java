package ua.mai.fam.repository.datajdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ua.mai.fam.model.Person;
import ua.mai.fam.repository.PersonRepository;

@Profile("da-data-jdbc-old")
public interface PersonRepository4DataJdbc extends CrudRepository<Person, Long>, WithOperations<Person>,
                                                   PersonRepository {
}
