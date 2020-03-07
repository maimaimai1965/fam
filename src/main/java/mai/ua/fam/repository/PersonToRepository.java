package mai.ua.fam.repository;

import mai.ua.fam.model.person.PersonTo;
import mai.ua.fam.util.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

public interface PersonToRepository {

    /**
     * Insert person.
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    PersonTo insert(PersonTo person);

    /**
     *
     * @param person
     * @return
     * @throws IllegalArgumentException in case the given person is null.
     */
    PersonTo save(PersonTo person);

    /**
     *
     * @param id
     * @throws IllegalArgumentException in case the given id is null.
     * @throws NotFoundException - если нет записи с id
     */
    void deleteById(Long id);

    /**
     * Deletes a given entity.
     *
     * @param person must not be null.
     * @throws IllegalArgumentException in case the given person is null.
     */
    void delete(PersonTo person);

    /**
     *
     * @param id
     * @return
     * @throws IllegalArgumentException in case the given id is null.
     */
    Optional<PersonTo> findById(Long id);

    /**
     * Returns all instances of the type.
     *
     * @return all persons
     */
    Iterable<PersonTo> findAll();

    /**
     * Returns the number of persons available.
     *
     * @return the number of persons.
     */
    long count();

}
