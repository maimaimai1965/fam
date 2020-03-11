package mai.ua.fam.repository;

import mai.ua.fam.AbstractTimingExtension;
import mai.ua.fam.AbstractTimingTest;
import mai.ua.fam.model.PersonTestData;
import mai.ua.fam.model.PersonTestUtil;
import mai.ua.fam.model.person.PersonBuilder;
import mai.ua.fam.model.person.Person;
import mai.ua.fam.repository.datajdbc.PersonRepository4DataJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для реализаций репозитория.
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractPersonRepository4DataJdbcTest extends AbstractTimingTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractPersonRepository4DataJdbcTest.class);

    abstract protected PersonRepository4DataJdbc getRepository();


    public void insertTest() {
        //Вставка без заданного идентификатора.
        Person person = PersonTestData.getNewPersons01();
        //Вставка без Id. Id присваивается в БД.
        Person insertedPerson = getRepository().insert(person);
        assertTrue(insertedPerson.getId() > 0, "При вставке без идентификатора не был сгенерирован правильный Id.");
        //Проверяем вставленный объект.
        Optional<Person> findedPerson = getRepository().findById(person.getId());
        PersonTestUtil.assertMatch(findedPerson.get(), person);

        //Вставка нового объекта с заданным идентификатором. Объекта с таким идентификатором не существует в БД.
        person = PersonTestData.getNewPersons01();
        person.setId(300L);
        insertedPerson = getRepository().insert(person);
        assertEquals(300L, insertedPerson.getId(), "При вставке c Id он не был сохранен с тем же идентификатором.");
        //Проверяем вставленный объект.
        findedPerson = getRepository().findById(300L);
        PersonTestUtil.assertMatch(findedPerson.get(), person);
    }
    public void insertExistsTest() {
        //Вставляем новый объект с заданным идентификатором. Объекта с таким идентификатором не существует в БД.
        Person person = PersonTestData.getNewPersons01();
        person.setId(301L);
        getRepository().insert(person);
        //Проверяем вставленный объект.
        Optional<Person>  findedPerson = getRepository().findById(301L);
        PersonTestUtil.assertMatch(findedPerson.get(), person);

        //Вставка нового объекта с заданным идентификатором. Объект с таким идентификатором уже существует в БД.
        assertThrows(Exception.class, () -> {
            Person person2 = PersonTestData.getNewPersons01();
            person2.setId(301L);
            person2.setSurname("New Surname");
            getRepository().insert(person2);
        });
    }
    public void insertNullPersonTest() {
        assertThrows(IllegalArgumentException.class, () -> getRepository().insert(null));
    }

    public void saveTest(){
        Long id;
        //Если в person идентификатора нет, то генерируется идентификатор и создается новая запись в таблице с этим
        //идентификатором. Идентификатор прописывается в возвращаемом объекте.
        Person person = PersonTestData.getNewPersons01();
        //Вставка без Id. Id генерится в БД.
        Person savedPerson = getRepository().save(person);
        id = savedPerson.getId();
        assertTrue(id > 0, "При сохранении без идентификатора не был сгенерирован Id.");
        //Проверяем вставленный объект.
        Optional<Person> findedPerson = getRepository().findById(id);
        PersonTestUtil.assertMatch(findedPerson.get(), person);

        //Если в person идентификатор есть, и в таблице есть запись с этим идентификатором, то производится update этой
        //записи в таблице.<br>
        person = PersonTestData.getNewPersons02();
        person.setId(id);
        savedPerson = getRepository().save(person);
        assertEquals(id , savedPerson.getId(), "При сохранении c идентификатором и наличии записи в БД, был сгенерирован новый Id.");
        //Проверяем обновленный объект.
        findedPerson = getRepository().findById(id);
        PersonTestUtil.assertMatch(findedPerson.get(), person);

        // Если в person идентификатор есть, и в таблице нет записи с этим идентификатором, то генерируется
        // идентификатор и создается новая запись в таблице с этим идентификатором. Идентификатор прописывается в
        // возвращаемом объекте.
        person = PersonTestData.getNewPersons03();
        person.setId(-300L);
        savedPerson = getRepository().save(person);
        id = person.getId();
        assertTrue(id > 0, "При сохранении c идентификатором и отсутствием записи в БД не был сгенерирован Id.");
        //Проверяем вставленный объект.
        findedPerson = getRepository().findById(id);
        PersonTestUtil.assertMatch(findedPerson.get(), person);
    }
    public void saveNullPersonTest() {
        assertThrows(IllegalArgumentException.class, () -> getRepository().save(null));
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

        Person person = PersonTestData.getNewPersons01();
        //TODO
        person.setId(300L);
        Person insertedPerson = getRepository().insert(person);
//        getRepository().save(person);
        long currCount = getRepository().count();
        Assertions.assertEquals(startedCount + 1, currCount);

        Optional<Person> findedPerson = getRepository().findById(person.getId());
        PersonTestUtil.assertMatch(person, findedPerson.get());

        Iterable<Person> findedPersons = getRepository().findAll();
        Assertions.assertEquals(startedCount + 1, ((Collection)findedPersons).size());

        getRepository().deleteById(person.getId());
        currCount = getRepository().count();
        Assertions.assertEquals(startedCount, currCount);
    }

}
