package mai.ua.fam.repository;

import mai.ua.fam.model.person.Person;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import mai.ua.fam.util.exception.NotFoundException;

import java.util.Optional;

public interface PersonRepository<T, ID> extends PersonRepository4DataJdbc {

    /**
     * Insert person.
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    Person insert(Person person);

    /**
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    @Override
    Person save(Person person);

    /**
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     * @throws NotFoundException - если нет записи с id
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
