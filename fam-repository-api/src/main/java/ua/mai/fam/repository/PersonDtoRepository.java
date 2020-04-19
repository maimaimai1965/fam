package ua.mai.fam.repository;

import ua.mai.fam.dto.PersonDto;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface PersonDtoRepository extends EntityWithPkIdRepository<PersonDto, Long>, PersonRepositoryMarker {
}
