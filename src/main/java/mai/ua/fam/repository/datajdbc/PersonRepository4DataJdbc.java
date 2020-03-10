package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.model.person.Person;
import mai.ua.fam.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//@Profile("da-data-jdbc")
public interface PersonRepository4DataJdbc extends CrudRepository<Person, Long>
//Не используется т.к. не реализовано https://stackoverflow.com/questions/55570077/can-i-do-pagination-with-spring-data-jdbc
//TODO проверить на реализацию.
// PagingAndSortingRepository<Person, Long>
 {}
