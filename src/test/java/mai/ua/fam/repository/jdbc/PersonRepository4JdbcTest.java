package mai.ua.fam.repository.jdbc;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.repository.PersonRepository;
import mai.ua.fam.repository.AbstractPersonRepository4DataJdbcTest;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import mai.ua.fam.util.SpringUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
@SpringBootTest
@ExtendWith(AbstractTimingExtension.class)
@ActiveProfiles(resolver = ProfileResolver4RepositoryJdbc.class)
public class PersonRepository4JdbcTest extends AbstractPersonRepository4DataJdbcTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPersonRepository4DataJdbcTest.class);

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jdbc, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonRepository repository;

    @Override
    public PersonRepository getRepository() {
        return repository;
    }


    @Test
    public void classRepositoryTest() throws Exception {
        PersonRepository4DataJdbc usedRepository;
        usedRepository = SpringUtil.unproxyBean(repository);
        assertEquals(PersonRepository4Jdbc.class, usedRepository.getClass(), "Error!!! Not expected repository!!!");
    }

    @Test
    @Override
    public void saveNewPersonWithEmptyIdTest() {
        super.saveNewPersonWithEmptyIdTest();
    }
    @Test
    @Override
    public void savePersonWithIdWhenPersonExistsInDbTest() {
        super.savePersonWithIdWhenPersonExistsInDbTest();
    }
    @Test
    @Override
    public void savePersonWithIdWhenPersonNotExistsInDbTest() {
        super.savePersonWithIdWhenPersonNotExistsInDbTest();
    }
    @Override
    public void saveNullPersonTest() {
        super.saveNullPersonTest();
    }


    @Test
    @Override
    public void insertNewPersonWithEmptyIdTest() {
        super.insertNewPersonWithEmptyIdTest();
    }
    @Test
    @Override
    public void insertPersonWithIdWhenPersonExistsInDbTest() {
        super.insertPersonWithIdWhenPersonExistsInDbTest();
    }
    @Test
    @Override
    public void insertTest() {
        super.insertTest();
    }
    @Test
    @Override
    public void insertExistsTest() {
        super.insertExistsTest();
    }
    @Test
    @Override
    public void insertNullPersonTest() {
        super.insertNullPersonTest();
    }


    @Test
    @Override
    public void countTest() {
        super.countTest();
    }

    @Test
    @Override
    public void findByIdNonExistsIdTest() {
        super.findByIdNonExistsIdTest();
    }

    @Test
    @Override
    public void findAllTest() {
        super.findAllTest();
    }

    @Test
    @Override
    public void deleteNonExistsIdTest() {
        super.deleteNonExistsIdTest();
    }

    @Test
    @Override
    public void deleteNullPersonTest() {
        super.deleteNullPersonTest();
    }

    @Test
    @Override
    public void operationsTest() {
        super.operationsTest();
    }

}
