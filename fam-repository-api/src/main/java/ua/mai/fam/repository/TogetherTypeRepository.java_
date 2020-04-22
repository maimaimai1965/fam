package ua.mai.fam.repository;

import ua.mai.fam.model.TogetherType;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import java.util.Optional;

/**
 * Содержит все методы которые определяет org.springframework.data.repository.CrudRepository.
 */
public interface TogetherTypeRepository extends TogetherTypeRepositoryMarker {

    /** ------------------ Методы соответствующие интерфейсу CrudRepository ------------------------------------------*/
    /**
     * Сохранение togetherType.<br>
     * Алгоритм сохранения:
     * <ul>
     * <li>Если в togetherType есть код, а в таблице нет записи с этим кодом, то производится insert в таблицу.
     * <li>Если в togetherType есть код, и в таблице есть запись с этим идентификатором, то производится update этой
     *     записи в таблице.
     * <li>Если в togetherType нет кода или названия, то генерируется исключение.
     * </ul>
     *
     * @param togetherType
     * @return
     * @throws IllegalArgumentException in case the given togetherType is null.
     */
    TogetherType save(TogetherType togetherType);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
     *         as the {@literal Iterable} passed as an argument.
     * @throws NullPointerException in case one of its entities of the given {@link Iterable entities} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable entities} is {@literal null}.
     */
    <S extends TogetherType> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Вставка togetherType.<br>
     * Алгоритм вставки:
     * <ul>
     * <li>Если в togetherType есть код, а в таблице нет записи с этим кодом, то производится insert в таблицу.
     * <li>Если в togetherType есть код, а в таблице есть запись с этим кодом, то генерируется исключение.
     * <li>Если в togetherType нет кода или названия, то генерируется исключение.
     * </ul>
     *
     * @param togetherType must not be null.
     * @return
     * @throws IllegalArgumentException in case the given togetherType is null.
     * @throws FoundException in case the given togetherType contains id.
     */
    TogetherType insert(TogetherType togetherType);

    /**
     * Удаление togetherType по его коду.
     *
     * @param code
     * @throws IllegalArgumentException in case the given id is null.
     */
    void deleteByCode(String code);

    /**
     * Удаление togetherType.
     *
     * @param togetherType must not be null.
     * @throws IllegalArgumentException in case the given togetherType is null.
     */
    void delete(TogetherType togetherType);

    /**
     * Deletes the given entities.
     *
     * @param togetherTypes must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws NullPointerException in case the given {@literal entities} or one of its persons is {@literal null}.
     */
    default void deleteAll(Iterable<? extends TogetherType> togetherTypes) {
        togetherTypes.forEach(togetherType -> delete(togetherType));
    }

    void deleteAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param code must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<TogetherType> findByCode(String code);

    /**
     * Returns all instances of the type.
     *
     * @return all persons
     */
    Iterable<TogetherType> findAll();

    /**
     * Returns all instances of the type {@code T} with the given codes.
     * <p>
     * If some or all ids are not found, no entities are returned for these codes.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param codes must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     *         {@literal codes}.
     * @throws NullPointerException in case one of items of the given {@link Iterable codes} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable codes} is {@literal null}.
     */
    Iterable<TogetherType> findAllByCode(Iterable<String> codes);

    /**
     * Returns the number of togetherTypes available.
     *
     * @return the number of togetherTypes.
     */
    long count();


    /**
     * Returns whether an entity with the given code exists.
     *
     * @param code must not be {@literal null}.
     * @return {@literal true} if an entity with the given code exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsByCode(String code);

}
