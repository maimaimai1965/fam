package mai.ua.fam.repository.jdbc;

import mai.ua.fam.AbstractTimingTest;
import mai.ua.fam.model.PersonTo4TestUtil;
import mai.ua.fam.model.person.PersonBuilder;
import mai.ua.fam.model.person.PersonTo;
import mai.ua.fam.repository.PersonToRepository;
import mai.ua.fam.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
@SpringBootTest
public class PersonToRepository4JdbcTest extends AbstractRepository4JdbcTest {

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jdbc, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    PersonToRepository repository;

    @Test
    public void count(){
        repository.count();
    }

    @Test
    public void findByIdNonExistsId(){
        repository.findById(-100L);
    }

    @Test
    public void findAll(){
        repository.findAll();
    }

    @Test
    public void saveNullPerson() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    public void insertNullPerson() {
        assertThrows(IllegalArgumentException.class, () -> repository.insert(null));
    }

    /**
     * Удаление несуществующего person;
     */
    @Test
    public void deleteNonExistsId() {
        assertThrows(NotFoundException.class, () -> repository.deleteById(-100L));
    }

    @Test
    public void deleteNullPerson() {
        assertThrows(IllegalArgumentException.class, () -> repository.delete(null));
    }

    @Test
    public void operations(){
        long startedCount = repository.count();

        String surname = "Иванов_TEST";
        String firstName = "Сергей";
        String middleName = "Игнатьевич";

        PersonTo person = new PersonBuilder().setSurname(surname).setFirstName(firstName).setMiddleName(middleName)
                                             .createPersonTo();
        repository.insert(person);
        long currCount = repository.count();
        Assertions.assertEquals(startedCount + 1, currCount);

        Optional<PersonTo> findedPerson = repository.findById(person.getId());
        PersonTo4TestUtil.assertMatch(person, findedPerson.get());

        Iterable<PersonTo> findedPersons = repository.findAll();
        Assertions.assertEquals(startedCount + 1, ((Collection)findedPersons).size());

        repository.deleteById(person.getId());
        currCount = repository.count();
        Assertions.assertEquals(startedCount, currCount);
    }

}
