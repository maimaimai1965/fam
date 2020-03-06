package mai.ua.fam.repository;

import mai.ua.fam.model.datajdbc.Person4DataJdbc;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PersonRepository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

}
