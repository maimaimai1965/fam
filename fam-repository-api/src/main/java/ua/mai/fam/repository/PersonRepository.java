package ua.mai.fam.repository;

import ua.mai.fam.model.Person;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface PersonRepository extends EntityWithPkIdRepository<Person, Long>, PersonRepositoryMarker {
}
