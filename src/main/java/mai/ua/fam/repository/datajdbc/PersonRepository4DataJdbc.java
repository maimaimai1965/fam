package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.model.datajdbc.Person4DataJdbc;
import mai.ua.fam.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Profile("da-data-jdbc")
@Repository
public interface PersonRepository4DataJdbc extends PagingAndSortingRepository<Person4DataJdbc, Long> {
}
