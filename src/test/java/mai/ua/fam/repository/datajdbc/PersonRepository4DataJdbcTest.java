package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.repository.AbstractPersonRepository4DataJdbcTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 */
@SpringBootTest
@ExtendWith(AbstractTimingExtension.class)
@ActiveProfiles(resolver = ProfileResolver4RepositoryDataJdbc.class)
public class PersonRepository4DataJdbcTest extends AbstractPersonRepository4DataJdbcTest {

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jdbc, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonRepository4DataJdbc repository;

    @Override
    protected PersonRepository4DataJdbc getRepository() {
        return repository;
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
    public void saveNullPersonTest() {
        super.saveNullPersonTest();
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
