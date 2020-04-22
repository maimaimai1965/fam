package ua.mai.fam.repository;

import ua.mai.fam.model.Person;

import java.util.Optional;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface EntityWithPkCodeRepository<T> extends EntityWithPkIdRepository<T, String> {

    /**
     * Retrieves an entity by its code.
     *
     * @param code must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal code} is {@literal null}.
     */
    Optional<T> findByCode(String code);

    /**
     * Retrieves an entity by its id.
     *
     * @param code must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    default Optional<T> findById(String code) {
        return findByCode(code);
    }

    /**
     * Удаление entity по его идентификатору.
     *
     * @param code
     * @throws IllegalArgumentException in case the given code is null.
     */
    void deleteByCode(String code);

    /**
     * Удаление entity по его идентификатору.
     *
     * @param code
     * @throws IllegalArgumentException in case the given id is null.
     */
    @Override
    default void deleteById(String code) {
        deleteByCode(code);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param code must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsByCode(String code);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param code must not be {@literal null}.
     * @return {@literal true} if an entity with the given code exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    default boolean existsById(String code) {
        return existsByCode(code);
    }

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param codes must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     *         {@literal codes}.
     * @throws NullPointerException in case one of items of the given {@link Iterable ids} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable codes} is {@literal null}.
     */
    Iterable<T> findAllByCode(Iterable<String> codes);

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param codes must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     *         {@literal codes}.
     * @throws NullPointerException in case one of items of the given {@link Iterable codes} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable codes} is {@literal null}.
     */
    @Override
    default Iterable<T> findAllById(Iterable<String> codes) {
        return findAllByCode(codes);
    }

}
