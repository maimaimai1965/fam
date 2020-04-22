package ua.mai.fam.service;

import ua.mai.fam.model.Person;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import javax.persistence.Entity;
import java.util.Optional;

/** Сервис для JPA реализации. */
public interface EntityWithPkIdService<T, ID> {

    /**
     * Сохранение person.<br>
     * Алгоритм сохранения:
     * <ul>
     * <li>Если в entity идентификатора нет, то генерируется идентификатор и создается новая запись в таблице с этим
     *     идентификатором. Идентификатор прописывается в возвращаемом объекте.<br>
     * <li>Если в person идентификатор есть, и в таблице есть запись с этим идентификатором, то производится update этой
     *     записи в таблице.<br>
     * <li>Если в entity идентификатор есть, а в таблице нет записи с этим идентификатором, то возникает
     *     {@link NotFoundException}.
     * </ul>
     *
     * @param entity
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    T save(T entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
     *         as the {@literal Iterable} passed as an argument.
     * @throws NullPointerException in case one of its entities of the given {@link Iterable entities} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable entities} is {@literal null}.
     */
     Iterable<T> saveAll(Iterable<T> entities);

    /**
     * Вставка entity.<br>
     * Алгоритм вставки:
     * <ul>
     * <li>когда в entity нет идентификатора, то при сохранении генерируется идентификатор и создается новая
     *     запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте;
     * <li>когда в entity есть генерируемый идентификатор, то при вставке вызывается исключение {@link FoundException}.
     * </ul>
     *
     * @param entity must not be null.
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     * @throws FoundException in case the given person contains id.
     */
    T insert(T entity);

    /**
     * Удаление person по его идентификатору.
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     */
    void deleteById(ID id);

    /**
     * Удаление person.
     *
     * @param entity must not be null.
     * @throws IllegalArgumentException in case the given person is null.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws NullPointerException in case the given {@literal entities} or one of its persons is {@literal null}.
     */
    void deleteAll(Iterable<T> entities);

    void deleteAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<T> findById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param ids must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     *         {@literal ids}.
     * @throws NullPointerException in case one of items of the given {@link Iterable ids} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable ids} is {@literal null}.
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    long count();


    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsById(ID id);

}
