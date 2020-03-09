package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.model.person.PersonTo;
import mai.ua.fam.repository.PersonToRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

@Profile("da-data-jdbc")
//@Repository
public interface PersonToRepository4DataJdbc extends PagingAndSortingRepository<PersonTo, Long>,
                                                     PersonToRepository {
}
