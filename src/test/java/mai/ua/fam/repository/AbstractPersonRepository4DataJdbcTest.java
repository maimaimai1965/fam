package mai.ua.fam.repository;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.AbstractTimingTest;
import mai.ua.fam.model.PersonTo4TestUtil;
import mai.ua.fam.model.person.PersonBuilder;
import mai.ua.fam.model.person.Person;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import mai.ua.fam.repository.jdbc.PersonRepository4Jdbc;
import mai.ua.fam.util.SpringUtil;
import mai.ua.fam.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для реализаций репозитория.
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractPersonRepository4DataJdbcTest extends AbstractTimingTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractPersonRepository4DataJdbcTest.class);

    abstract protected PersonRepository4DataJdbc getRepository();


    public void countTest(){
        getRepository().count();
    }

    public void findByIdNonExistsIdTest(){
        getRepository().findById(-100L);
    }

    public void findAllTest(){
        getRepository().findAll();
    }

    public void saveNullPersonTest() {
        assertThrows(IllegalArgumentException.class, () -> getRepository().save(null));
    }

    public void insertNullPersonTest() {
        //TODO
//        assertThrows(IllegalArgumentException.class, () -> getRepository().insert(null));
    }

    /**
     * Удаление несуществующего person;
     */
    public void deleteNonExistsIdTest() {
        getRepository().deleteById(-100L);
    }

    public void deleteNullPersonTest() {
        assertThrows(IllegalArgumentException.class, () -> getRepository().delete(null));
    }

    public void operationsTest(){
        long startedCount = getRepository().count();

        String surname = "Иванов_TEST";
        String firstName = "Сергей";
        String middleName = "Игнатьевич";

        Person person = new PersonBuilder().setSurname(surname).setFirstName(firstName).setMiddleName(middleName)
                                             .createPerson();
        //TODO
        //getRepository().insert(person);
        getRepository().save(person);
        long currCount = getRepository().count();
        Assertions.assertEquals(startedCount + 1, currCount);

        Optional<Person> findedPerson = getRepository().findById(person.getId());
        PersonTo4TestUtil.assertMatch(person, findedPerson.get());

        Iterable<Person> findedPersons = getRepository().findAll();
        Assertions.assertEquals(startedCount + 1, ((Collection)findedPersons).size());

        getRepository().deleteById(person.getId());
        currCount = getRepository().count();
        Assertions.assertEquals(startedCount, currCount);
    }

}
