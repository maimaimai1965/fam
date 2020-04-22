package ua.mai.fam.repository;

import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.dto.TogetherTypeDto;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.<br>
 * Используется для JDBC и DATA JDBC репозитариев.
 */
public interface TogetherTypeDtoRepository extends EntityWithPkIdRepository<TogetherTypeDto, String>,
                                                   TogetherTypeRepositoryMarker {
}
