package ua.mai.fam.repository.datajdbc;

import org.springframework.data.repository.NoRepositoryBean;
import ua.mai.fam.model.person.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("da-data-jdbc")
public interface PersonRepository4DataJdbc extends CrudRepository<Person, Long>, WithOperations<Person>
//PagingAndSortingRepository не используется т.к. не реализовано https://stackoverflow.com/questions/55570077/can-i-do-pagination-with-spring-data-jdbc
//TODO проверить на реализацию.
// PagingAndSortingRepository<Person, Long>
{
}
