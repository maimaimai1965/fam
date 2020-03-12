package mai.ua.fam.repository;

import mai.ua.fam.model.person.Person;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import mai.ua.fam.util.exception.NotFoundException;
import org.springframework.context.annotation.Profile;
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
     * Insert person.
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    @Override
    Person insert(Person person);

    /**
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     */
    @Override
    void deleteById(Long id);

    /**
     * Deletes a given entity.
     *
     * @param person must not be null.
     * @throws IllegalArgumentException in case the given person is null.
     */
    @Override
    void delete(Person person);

    /**
     *
     * @param id
     * @return
     * @throws IllegalArgumentException in case the given id is null.
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
     * Returns the number of persons available.
     *
     * @return the number of persons.
     */
    @Override
    long count();

    @Override
    <S extends Person> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Person> findAllById(Iterable<Long> longs);

    @Override
    void deleteAll(Iterable<? extends Person> entities);

    @Override
    void deleteAll();

}
