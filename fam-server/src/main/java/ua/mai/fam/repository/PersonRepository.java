package ua.mai.fam.repository;

import ua.mai.fam.model.Person;
import ua.mai.fam.repository.datajdbc.PersonRepository4DataJdbc;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository extends PersonRepository4DataJdbc {

    /**
     * Сохранение person.<br>
     * Алгоритм сохранения:
     * <ul>
     * <li>Если в person идентификатора нет, то генерируется идентификатор и создается новая запись в таблице с этим
     *     идентификатором. Идентификатор прописывается в возвращаемом объекте.<br>
     * <li>Если в person идентификатор есть, и в таблице есть запись с этим идентификатором, то производится update этой
     *     записи в таблице.<br>
     * <li>Если в person идентификатор есть, а в таблице нет записи с этим идентификатором, то:
     *     <ul>
     *     <li>для Data JDBC репозитария объект не сохраняется, но возвращается этот же объект.
     *         TODO нужно реализовать такое же поведение, как и для JDBC репозитария.
     *     <li>для JDBC репозитария возникает {@link NotFoundException}.
     *     </ul>
     * </ul>
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    @Override
    Person save(Person person);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
     *         as the {@literal Iterable} passed as an argument.
     * @throws NullPointerException in case one of its entities of the given {@link Iterable entities} is {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable entities} is {@literal null}.
     */
    @Override
    <S extends Person> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Вставка person.<br>
     * Алгоритм вставки:
     * <ul>
     * <li>когда в person нет идентификатора, то при сохранении генерируется идентификатор и создается новая
     *     запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте;
     * <li>когда в person есть идентификатор, то при вставке вызывается исключение {@link FoundException}.
     * </ul>
     *
     * @param person must not be null.
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     * @throws FoundException in case the given person contains id.
     */
    @Override
    Person insert(Person person);

    /**
     * Удаление person по его идентификатору.
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     */
    @Override
    void deleteById(Long id);

    /**
     * Удаление person.
     *
     * @param person must not be null.
     * @throws IllegalArgumentException in case the given person is null.
     */
    @Override
    void delete(Person person);

    /**
     * Deletes the given entities.
     *
     * @param persons must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws NullPointerException in case the given {@literal entities} or one of its persons is {@literal null}.
     */
    @Override
    void deleteAll(Iterable<? extends Person> persons);

    @Override
    void deleteAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    Optional<Person> findById(Long id);

    /**
     * Returns all instances of the type.
     *
     * @return all persons
     */
    @Override
    Iterable<Person> findAll();

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
    @Override
    Iterable<Person> findAllById(Iterable<Long> ids);

    /**
     * Returns the number of persons available.
     *
     * @return the number of persons.
     */
    @Override
    long count();


    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    boolean existsById(Long id);

}
