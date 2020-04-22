package ua.mai.fam.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ua.mai.fam.ProfileResolver4RepositoryJpa;
import ua.mai.fam.repository.AbstractPersonRepositoryTest;
import ua.mai.fam.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */

//@DataJpaTest - не получилось использовать, поэтому используется @SpringBootTest
@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryJpa.class)
public class PersonRepository4JpaTest extends AbstractPersonRepositoryTest {

    @Autowired
    //Нельзя объявлять класс PersonToRepository4Jpa, т.к. есть transactionManager - должен быть интерфейс.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private PersonRepository repository;

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }

    @Test
    @Override
    public void save_NewPersonWithEmptyIdTest() {
        super.save_NewPersonWithEmptyIdTest();
    }

    @Test
    @Override
    public void save_PersonWithIdWhenPersonExistsInDbTest() {
        super.save_PersonWithIdWhenPersonExistsInDbTest();
    }
    @Test
    @Override
    public void save_PersonWithIdWhenPersonNotExistsInDbTest() {
        super.save_PersonWithIdWhenPersonNotExistsInDbTest();
    }
    @Test
    @Override
    public void save_NullPersonTest() {
        super.save_NullPersonTest(RuntimeException.class);
    }
    @Test
    @Override
    public void save_PersonWhenVersionChangedInDb(){
        super.save_PersonWhenVersionChangedInDb();
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
        super.saveAll_NullListTest(RuntimeException.class);
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
        super.insert_NullPersonTest(RuntimeException.class);
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
        super.deleteById_NullIdTest(RuntimeException.class);
    }

    @Test
    @Override
    public void deleteTest() {
        super.deleteTest();
    }
    @Test
    @Override
    public void delete_NullPersonTest() {
        super.delete_NullPersonTest(RuntimeException.class);
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
        super.findById_NullIdTest(RuntimeException.class);
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
        super.findAllByIdList_NullListTest(RuntimeException.class);
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
        super.existsById_NullIdTest(RuntimeException.class);
    }

}
