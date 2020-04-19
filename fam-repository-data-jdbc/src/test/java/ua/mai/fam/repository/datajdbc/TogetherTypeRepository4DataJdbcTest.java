package ua.mai.fam.repository.datajdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.mai.fam.ProfileResolver4RepositoryDataJdbc;
import ua.mai.fam.repository.AbstractTogetherTypeRepositoryTest;
import ua.mai.fam.repository.TogetherTypeRepository;

/**
 *
 */
//@DataJdbcTest - не получилось использовать, поэтому используется @SpringBootTest
@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryDataJdbc.class)
public class TogetherTypeRepository4DataJdbcTest extends AbstractTogetherTypeRepositoryTest {

    @Autowired
    //TogetherTypeRepository должен быть интерфейсом, т.к. есть transactionManager.
    //https://ru.stackoverflow.com/questions/663704/unsatisfieddependencyexception-error-creating-beanby-beannotofrequiredtypeexce
    private TogetherTypeRepository repository;

    @Override
    protected TogetherTypeRepository getRepository() {
        return repository;
    }

    @Test
    @Override
    public void save_insert_whenTogetherTypeNotExistsInDbTest() {
        super.save_insert_whenTogetherTypeNotExistsInDbTest();
    }
    @Test
    @Override
    public void save_update_whenTogetherTypeExistsInDbTest() {
        super.save_update_whenTogetherTypeExistsInDbTest();
    }
    @Test
    @Override
    public void save_newTogetherTypeWithoutCode() {
        super.save_newTogetherTypeWithoutCode();
    }
    @Test
    @Override
    public void save_newTogetherTypeWithoutName() {
        super.save_newTogetherTypeWithoutName();
    }
    @Test
    @Override
    public void save_nullTogetherTypeTest() {
        super.save_nullTogetherTypeTest();
    }

//    @Test
//    @Override
//    public void saveAll_ListTest() {
//        super.saveAll_ListTest();
//    }
//    @Test
//    @Override
//    public void saveAll_OneEntityNullInListTest() {
//        super.saveAll_OneEntityNullInListTest();
//    }
//    @Test
//    @Override
//    public void saveAll_NullListTest() {
//        super.saveAll_NullListTest();
//    }
//
//    @Test
//    @Override
//    public void insert_NewTogetherTypeWithEmptyIdTest() {
//        super.insert_NewTogetherTypeWithEmptyIdTest();
//    }
//    @Test
//    @Override
//    public void insert_TogetherTypeWithId() {
//        super.insert_TogetherTypeWithId();
//    }
//    @Test
//    @Override
//    public void insert_NullTogetherTypeTest() {
//        super.insert_NullTogetherTypeTest();
//    }
//
//    @Test
//    @Override
//    public void deleteByIdTest() {
//        super.deleteByIdTest();
//    }
//    @Test
//    @Override
//    public void deleteById_NonExistsIdTest() {
//        super.deleteById_NonExistsIdTest();
//    }
//    @Test
//    @Override
//    public void deleteById_NullIdTest() {
//        super.deleteById_NullIdTest();
//    }
//
//    @Test
//    @Override
//    public void deleteTest() {
//        super.deleteTest();
//    }
//    @Test
//    @Override
//    public void delete_NullTogetherTypeTest() {
//        super.delete_NullTogetherTypeTest();
//    }
//
//    @Test
//    @Override
//    public void deleteAll_ListTest() {
//        super.deleteAll_ListTest();
//    }
//    @Test
//    @Override
//    public void deleteAll_OneEntityNullInListTest() {
//        super.deleteAll_OneEntityNullInListTest();
//    }
//    @Test
//    @Override
//    public void deleteAll_NullListTest() {
//        super.deleteAll_NullListTest();
//    }
//
//    @Test
//    @Override
//    public void findByIdTest() {
//        super.findByIdTest();
//    }
//    @Test
//    @Override
//    public void findById_NonExistsIdTest() {
//        super.findById_NonExistsIdTest();
//    }
//    @Test
//    @Override
//    public void findById_NullIdTest() {
//        super.findById_NullIdTest();
//    }
//
//    @Test
//    @Override
//    public void findAllTest() {
//        super.findAllTest();
//    }
//
//    @Test
//    @Override
//    public void findAllByIdListTest() {
//        super.findAllByIdListTest();
//    }
//    @Test
//    @Override
//    public void findAllByIdList_OneEntityNotExistsTest() {
//        super.findAllByIdList_OneEntityNotExistsTest();
//    }
//    @Test
//    @Override
//    public void findAllByIdList_EmptyListTest() {
//        super.findAllByIdList_EmptyListTest();
//    }
//    @Test
//    @Override
//    public void findAllByIdList_OneEntityNullInListTest() {
//        super.findAllByIdList_OneEntityNullInListTest();
//    }
//    @Test
//    @Override
//    public void findAllByIdList_NullListTest() {
//        super.findAllByIdList_NullListTest();
//    }
//
//    @Test
//    @Override
//    public void countTest() {
//        super.countTest();
//    }
//
//    @Test
//    @Override
//    public void existsById_ExistsIdTest() {
//        super.existsById_ExistsIdTest();
//    }
//    @Test
//    @Override
//    public void existsById_NonExistsIdTest() {
//        super.existsById_NonExistsIdTest();
//    }
//    @Test
//    @Override
//    public void existsById_NullIdTest() {
//        super.existsById_NullIdTest();
//    }

}
