package mai.ua.fam.repository;

import mai.ua.fam.model.Person;
import mai.ua.fam.model.datajdbc.Person4DataJdbc;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PersonRepository {

    Person save(Person entity);

    Optional<Person> findById(Long id);

}
