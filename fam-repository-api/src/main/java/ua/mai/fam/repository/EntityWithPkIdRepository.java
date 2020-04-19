package ua.mai.fam.repository;

import ua.mai.fam.model.person.Person;
import ua.mai.fam.util.HasId;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import java.util.Optional;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface EntityWithPkIdRepository<T, ID> {

    /** ------------------ Методы соответствующие интерфейсу CrudRepository ------------------------------------------*/
    /**
     * Сохранение entity.<br>
     * Алгоритм сохранения:
     * <ul>
     * <li>Если в entity идентификатора id нет, то генерируется идентификатор и создается новая запись в таблице с этим
     *     идентификатором. Идентификатор прописывается в возвращаемом объекте.<br>
     * <li>Если в entity идентификатор id есть, и в таблице есть запись с этим идентификатором, то производится update
     *     этой записи в таблице.<br>
     * <li>Если в entity идентификатор id есть, а в таблице нет записи с этим идентификатором, то:
     *     <ul>
     *     <li>для Data JDBC репозитария объект не сохраняется, но возвращается этот же объект.
     *         TODO нужно реализовать такое же поведение, как и для JDBC репозитария.
     *     <li>для JDBC репозитария возникает {@link NotFoundException}.
     *     </ul>
     * </ul>
     *
     * @param entity
     * @return
     * @throws IllegalArgumentException in case the given entity is null.
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
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Вставка entity.<br>
     * Алгоритм вставки:
     * <ul>
     * <li>когда в entity нет идентификатора id, то при сохранении генерируется идентификатор и создается новая
     *     запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте;
     * <li>когда в entity есть идентификатор, то при вставке вызывается исключение {@link FoundException}.
     * </ul>
     *
     * @param entity must not be null.
     * @return
     * @throws IllegalArgumentException in case the given entity is null.
     * @throws FoundException in case the given entity contains id.
     */
    T insert(T entity);

    /**
     * Удаление entity по его идентификатору.
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     */
    void deleteById(ID id);

    /**
     * Удаление entity.
     *
     * @param entity must not be null.
     * @throws IllegalArgumentException in case the given entity is null.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws NullPointerException in case the given {@literal entities} or one of its entities is {@literal null}.
     */
    void deleteAll(Iterable<? extends T> entities);

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
