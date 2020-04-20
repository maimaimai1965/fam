package ua.mai.fam.repository;

import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import java.util.Optional;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface PersonRepository extends EntityWithPkIdRepository<Person, Long>, PersonRepositoryMarker {
}
