package ua.mai.fam.repository.datajdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.repository.PersonDtoRepository;

@Profile("da-data-jdbc")
public interface PersonDtoRepository4DataJdbc extends CrudRepository<PersonDto, Long>, WithOperations<PersonDto>,
                                                      PersonDtoRepository {
}
