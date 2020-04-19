package ua.mai.fam.repository.datajdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.mai.fam.ProfileResolver4RepositoryDataJdbc;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.repository.AbstractPersonDtoRepositoryTest;
import ua.mai.fam.repository.AbstractPersonRepositoryTest;
import ua.mai.fam.repository.PersonDtoRepository;
import ua.mai.fam.repository.PersonRepository;

/**
 *
 */
//@DataJdbcTest - не получилось использовать, поэтому используется @SpringBootTest
@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryDataJdbc.class)
public class PersonDtoRepository4DataJdbcTest extends AbstractPersonDtoRepositoryTest {

    @Autowired
    //PersonRepository должен быть интерфейсом, т.к. есть transactionManager.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonDtoRepository repository;

    @Override
    protected PersonDtoRepository getRepository() {
        return repository;
    }

    @Test
    @Override
    public void save_NewPersonWithEmptyIdTest() {
        super.save_NewPersonWithEmptyIdTest();
    }
    //@Test
    @Override
    public void save_PersonWithIdWhenPersonExistsInDbTest() {
        super.save_PersonWithIdWhenPersonExistsInDbTest();
    }
    //TODO Открыть тест, когда будет реализовано для этого случая возникновение исключения для Data JDBC репозитория.
    //@Test
    @Override
    public void save_PersonWithIdWhenPersonNotExistsInDbTest() {
        super.save_PersonWithIdWhenPersonNotExistsInDbTest();
    }
    @Test
    @Override
    public void save_NullPersonTest() {
        super.save_NullPersonTest();
    }

    @Test
    @Override
    public void saveAll_ListTest() {
        super.saveAll_ListTest();
    }
    @Test
    @Override
    public void saveAll_OneEntityNullInListTest() {
        super.saveAll_OneEntityNullInListTest();
    }
    @Test
    @Override
    public void saveAll_NullListTest() {
        super.saveAll_NullListTest();
    }

    @Test
    @Override
    public void insert_NewPersonWithEmptyIdTest() {
        super.insert_NewPersonWithEmptyIdTest();
    }
    @Test
    @Override
    public void insert_PersonWithId() {
        super.insert_PersonWithId();
    }
    @Test
    @Override
    public void insert_NullPersonTest() {
        super.insert_NullPersonTest();
    }

    @Test
    @Override
    public void deleteByIdTest() {
        super.deleteByIdTest();
    }
    @Test
    @Override
    public void deleteById_NonExistsIdTest() {
        super.deleteById_NonExistsIdTest();
    }
    @Test
    @Override
    public void deleteById_NullIdTest() {
        super.deleteById_NullIdTest();
    }

    @Test
    @Override
    public void deleteTest() {
        super.deleteTest();
    }
    @Test
    @Override
    public void delete_NullPersonTest() {
        super.delete_NullPersonTest();
    }

    @Test
    @Override
    public void deleteAll_ListTest() {
        super.deleteAll_ListTest();
    }
    @Test
    @Override
    public void deleteAll_OneEntityNullInListTest() {
        super.deleteAll_OneEntityNullInListTest();
    }
    @Test
    @Override
    public void deleteAll_NullListTest() {
        super.deleteAll_NullListTest();
    }

    @Test
    @Override
    public void findByIdTest() {
        super.findByIdTest();
    }
    @Test
    @Override
    public void findById_NonExistsIdTest() {
        super.findById_NonExistsIdTest();
    }
    @Test
    @Override
    public void findById_NullIdTest() {
        super.findById_NullIdTest();
    }

    @Test
    @Override
    public void findAllTest() {
        super.findAllTest();
    }

    @Test
    @Override
    public void findAllByIdListTest() {
        super.findAllByIdListTest();
    }
    @Test
    @Override
    public void findAllByIdList_OneEntityNotExistsTest() {
        super.findAllByIdList_OneEntityNotExistsTest();
    }
    @Test
    @Override
    public void findAllByIdList_EmptyListTest() {
        super.findAllByIdList_EmptyListTest();
    }
    @Test
    @Override
    public void findAllByIdList_OneEntityNullInListTest() {
        super.findAllByIdList_OneEntityNullInListTest();
    }
    @Test
    @Override
    public void findAllByIdList_NullListTest() {
        super.findAllByIdList_NullListTest();
    }

    @Test
    @Override
    public void countTest() {
        super.countTest();
    }

    @Test
    @Override
    public void existsById_ExistsIdTest() {
        super.existsById_ExistsIdTest();
    }
    @Test
    @Override
    public void existsById_NonExistsIdTest() {
        super.existsById_NonExistsIdTest();
    }
    @Test
    @Override
    public void existsById_NullIdTest() {
        super.existsById_NullIdTest();
    }

}
