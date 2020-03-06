package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.model.datajdbc.Person4DataJdbc;
import mai.ua.fam.repository.PersonRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository4DataJdbc extends PersonRepository<Person4DataJdbc, Long>,
                                                   PagingAndSortingRepository<Person4DataJdbc, Long> {
}
