package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.repository.PersonRepository;
import mai.ua.fam.repository.abstr.AbstractPersonToRepositoryTest;
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
public class PersonRepository4DataJdbcTest extends AbstractPersonToRepositoryTest {

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jdbc, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonRepository repository;

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }
    @Override
    protected Class<PersonRepository4DataJdbc> getRepositoryClass() {
        return PersonRepository4DataJdbc.class;
    }


    @Test
    @Override
    public void classRepositoryTest() {
        super.classRepositoryTest();
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
    public void saveNullPersonTest() {
        super.saveNullPersonTest();
    }

    @Test
    @Override
    public void insertNullPersonTest() {
        super.insertNullPersonTest();
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
