package mai.ua.fam.repository.datajdbc;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.repository.PersonToRepository;
import mai.ua.fam.repository.abstr.AbstractPersonToRepositoryTest;
import mai.ua.fam.repository.jdbc.PersonToRepository4Jdbc;
import mai.ua.fam.repository.jdbc.ProfileResolver4RepositoryJdbc;
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
public class PersonToRepository4DataJdbcTest2 extends AbstractPersonToRepositoryTest {

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jdbc, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonToRepository repository;

    @Override
    protected PersonToRepository getRepository() {
        return repository;
    }
    @Override
    protected Class<PersonToRepository4DataJdbc> getRepositoryClass() {
        return PersonToRepository4DataJdbc.class;
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
