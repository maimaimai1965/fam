package ua.mai.fam.repository;

import ua.mai.fam.model.Person;
import ua.mai.fam.model.TogetherType;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface TogetherTypeRepository extends EntityWithPkCodeRepository<TogetherType>,
                                                TogetherTypeRepositoryMarker {
}
