package ua.mai.fam.repository;

import ua.mai.fam.dto.PersonDto;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.<br>
 * Используется для JDBC и DATA JDBC репозитариев.
 */
public interface PersonDtoRepository extends EntityWithPkIdRepository<PersonDto, Long>, PersonRepositoryMarker {
}
