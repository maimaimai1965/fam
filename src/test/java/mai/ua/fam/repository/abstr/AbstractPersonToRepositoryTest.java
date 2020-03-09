package mai.ua.fam.repository.abstr;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.AbstractTimingTest;
import mai.ua.fam.model.PersonTo4TestUtil;
import mai.ua.fam.model.person.PersonBuilder;
import mai.ua.fam.model.person.PersonTo;
import mai.ua.fam.repository.PersonToRepository;
import mai.ua.fam.repository.jdbc.PersonToRepository4Jdbc;
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
 *
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractPersonToRepositoryTest extends AbstractTimingTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPersonToRepositoryTest.class);

    abstract protected PersonToRepository getRepository();
    abstract protected <T extends PersonToRepository> Class<T>  getRepositoryClass();


    public void classRepositoryTest() {
        PersonToRepository usedRepository = getRepository();
        try {
            usedRepository = SpringUtil.unproxyBean(usedRepository);
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
        }
        assertEquals(PersonToRepository4Jdbc.class, usedRepository.getClass(), "Error!!! Not expected repository!!!");
    }

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
        assertThrows(IllegalArgumentException.class, () -> getRepository().insert(null));
    }

    /**
     * Удаление несуществующего person;
     */
    public void deleteNonExistsIdTest() {
        assertThrows(NotFoundException.class, () -> getRepository().deleteById(-100L));
    }

    public void deleteNullPersonTest() {
        assertThrows(IllegalArgumentException.class, () -> getRepository().delete(null));
    }

    public void operationsTest(){
        long startedCount = getRepository().count();

        String surname = "Иванов_TEST";
        String firstName = "Сергей";
        String middleName = "Игнатьевич";

        PersonTo person = new PersonBuilder().setSurname(surname).setFirstName(firstName).setMiddleName(middleName)
                                             .createPersonTo();
        getRepository().insert(person);
        long currCount = getRepository().count();
        Assertions.assertEquals(startedCount + 1, currCount);

        Optional<PersonTo> findedPerson = getRepository().findById(person.getId());
        PersonTo4TestUtil.assertMatch(person, findedPerson.get());

        Iterable<PersonTo> findedPersons = getRepository().findAll();
        Assertions.assertEquals(startedCount + 1, ((Collection)findedPersons).size());

        getRepository().deleteById(person.getId());
        currCount = getRepository().count();
        Assertions.assertEquals(startedCount, currCount);
    }

}
