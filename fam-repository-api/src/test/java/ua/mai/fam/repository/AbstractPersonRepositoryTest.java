package ua.mai.fam.repository;

import ua.mai.fam.AbstractTimingExtension;
import ua.mai.fam.model.PersonTestData;
import ua.mai.fam.model.util.PersonTestUtil;
import ua.mai.fam.model.Person;
import ua.mai.fam.util.exception.FoundException;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для реализаций Person репозитория.
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractPersonRepositoryTest {

    abstract protected PersonRepository getRepository();

    /**
     * Когда в person нет идентификатора, то при сохранении должен генерироваться идентификатор и создаваться новая
     * запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте.
     */
    public void save_NewPersonWithEmptyIdTest() {
        //Если в person идентификатора нет, то генерируется идентификатор и создается новая запись в таблице с этим
        //идентификатором. Идентификатор прописывается в возвращаемом объекте.
        Person person = PersonTestData.getNewPerson01();
        //Вставка без Id. Id генерится в БД.
        Person savedPerson = getRepository().save(person);
        Long id = savedPerson.getId();
        assertTrue(id > 0, "При сохранении нового объекта без идентификатора не был сгенерирован Id.");
        //Проверяем вставленный объект.
        Optional<Person> findedPerson = getRepository().findById(id);
        PersonTestUtil.assertMatch(findedPerson.get(), person);
    }
    /**
     * Когда в person есть идентификатор, а в таблице есть запись с этим идентификатором, то должен производиться
     * update этой записи в таблице.
     */
    public void save_PersonWithIdWhenPersonExistsInDbTest(){
        //Вставка в БД тестового объекта.
        Person existsPerson = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(existsPerson.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        existsPerson.setSurname(PersonTestData.surname02);
        existsPerson.setFirstName(PersonTestData.firstName02);
        existsPerson.setMiddleName(PersonTestData.middleName02);

        Person savedPerson = getRepository().save(existsPerson);
        Optional<Person> readedPerson = getRepository().findById(existsPerson.getId());
        assertEquals(existsPerson.getId(), savedPerson.getId());
        PersonTestUtil.assertMatch(readedPerson.get(), savedPerson);
        //version должен увеличиться
        assertTrue(readedPerson.get().getId() > existsPerson.getVersion());
    }
    /**
     * Когда в person есть идентификатор, а в таблице нет записи с этим идентификатором, то вызывается исключение.
     */
    public void save_PersonWithIdWhenPersonNotExistsInDbTest(){
        Long id = -100L;

        Optional<Person> findedPerson = getRepository().findById(id);
        if (findedPerson.isPresent()) {
            assertTrue(false, "Объекта с Id = " + id + " не должно быть в БД!");
        }

        //Сохранение в БД.
        Person person = PersonTestData.getPerson01(id);
        assertThrows(Exception.class, () -> getRepository().save(person));
    }
    public void save_NullPersonTest() {
        save_NullPersonTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void save_NullPersonTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().save(null));
    }

    public void saveAll_ListTest() {
        //Вставка в БД тестовых Entity.
        Person person1 = getRepository().save(PersonTestData.getNewPerson01());
        Person person2 = getRepository().save(PersonTestData.getNewPerson02());

        Object[] persons = ((Collection<Person>)getRepository().saveAll(Arrays.asList(person1, person2))).toArray();

        Person savedPerson1 = (Person)persons[0];
        assertTrue(savedPerson1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Person savedPerson2 = (Person)persons[1];
        assertTrue(savedPerson2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Optional<Person> findedPerson1 = getRepository().findById(savedPerson1.getId());
        PersonTestUtil.assertMatch(findedPerson1.get(), savedPerson1);
        Optional<Person> findedPerson2 = getRepository().findById(savedPerson2.getId());
        PersonTestUtil.assertMatch(findedPerson2.get(), savedPerson2);
    }
    public void saveAll_OneEntityNullInListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().saveAll(Arrays.asList(null)));
    }
    public void saveAll_NullListTest() {
        saveAll_NullListTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка оборачивается в RuntimeException.
    public void saveAll_NullListTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().saveAll((Iterable<Person>)null));
    }

    /**
     * Когда в person нет идентификатора, то при сохранении должен генерироваться идентификатор и создаваться новая
     * запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте.
     */
    public void insert_NewPersonWithEmptyIdTest() {
        Person person = PersonTestData.getNewPerson01();
        //Вставка без Id. Id генерится в БД.
        Person savedPerson = getRepository().insert(person);
        Long id = savedPerson.getId();
        assertTrue(id > 0, "При вставке нового объекта без идентификатора не был сгенерирован Id.");
        //Проверяем вставленный объект.
        Optional<Person> findedPerson = getRepository().findById(id);
        PersonTestUtil.assertMatch(findedPerson.get(), person);
    }
    /**
     * Когда в person есть идентификатор, то при вставке вызывается исключение {@link FoundException}.
     */
    public void insert_PersonWithId(){
        //Вставка в БД тестового Entity.
        Person existsPerson = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(existsPerson.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Person person = PersonTestData.getPerson02(existsPerson.getId());
        assertThrows(FoundException.class, () -> getRepository().insert(person));
    }
    public void insert_NullPersonTest() {
        insert_NullPersonTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void insert_NullPersonTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().insert(null));
    }

    public void deleteByIdTest() {
        //Вставка в БД тестового Entity.
        Person existsPerson = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(existsPerson.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Long id = existsPerson.getId();

        getRepository().deleteById(id);
        Optional<Person> notExistsPerson = getRepository().findById(id);
        assertFalse(notExistsPerson.isPresent(), "Entity не удален.");
    }
    /**
     * Удаление несуществующего Entity;
     */
    public void deleteById_NonExistsIdTest() {
        //Проверка отсутствия в БД тестового Entity.
        Long id = -100L;
        Optional<Person> notExistsPerson = getRepository().findById(id);
        assertFalse(notExistsPerson.isPresent(), "Существуе тестовый Entity с id=" + id + ".");

        getRepository().deleteById(-100L);
    }
    /**
     * Удаление несуществующего Entity;
     */
    public void deleteById_NullIdTest() {
        deleteById_NullIdTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void deleteById_NullIdTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().deleteById(null));
    }

    public void deleteTest() {
        //Вставка в БД тестового Entity.
        Person existsPerson = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(existsPerson.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Long id = existsPerson.getId();

        getRepository().delete(existsPerson);
        Optional<Person> notExistsPerson = getRepository().findById(id);
        assertFalse(notExistsPerson.isPresent(), "Entity не удален.");
    }
    public void delete_NullPersonTest() {
        delete_NullPersonTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void delete_NullPersonTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().delete(null));
    }

    public void deleteAll_ListTest() {
        //Вставка в БД тестовых Entity.
        Person person1 = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Person person2 = getRepository().save(PersonTestData.getNewPerson02());
        assertTrue(person2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        getRepository().deleteAll(Arrays.asList(person1, person2));

        Optional<Person> notExistsPerson = getRepository().findById(person1.getId());
        assertFalse(notExistsPerson.isPresent(), "Entity 1 не удален.");
        notExistsPerson = getRepository().findById(person2.getId());
        assertFalse(notExistsPerson.isPresent(), "Entity 2 не удален.");
    }
    public void deleteAll_OneEntityNullInListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().deleteAll(Arrays.asList(null)));
    }
    public void deleteAll_NullListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().deleteAll((Iterable<Person>)null));
    }

    public void findByIdTest(){
        Person person = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(person.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Optional<Person> findedPerson = getRepository().findById(person.getId());
        PersonTestUtil.assertMatch(findedPerson.get(), person);
    }
    public void findById_NonExistsIdTest(){
        Optional<Person> notExistsPerson = getRepository().findById(-100L);
        assertFalse(notExistsPerson.isPresent());
    }
    public void findById_NullIdTest(){
        findById_NullIdTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void findById_NullIdTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().findById(null));
    }

    public void findAllTest(){
        Collection<Person> persons = (Collection<Person>)getRepository().findAll();
        long count = getRepository().count();
        assertEquals(count, persons.size());
    }

    public void findAllByIdListTest() {
        //Вставка в БД тестовых Entity.
        Person person1 = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Person person2 = getRepository().save(PersonTestData.getNewPerson02());
        assertTrue(person2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Object[] persons =
            ((Collection<Person>)getRepository().findAllById(Arrays.asList(person1.getId(), person2.getId()))).toArray();

        Person findedPerson1 = (Person)persons[0];
        PersonTestUtil.assertMatch(findedPerson1, person1);
        Person findedPerson2 = (Person)persons[1];
        PersonTestUtil.assertMatch(findedPerson2, person2);
    }
    public void findAllByIdList_OneEntityNotExistsTest() {
        //Вставка в БД тестовых Entity.
        Person person1 = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Object[] persons =
            ((Collection<Person>)getRepository().findAllById(Arrays.asList(person1.getId(), -100L))).toArray();

        Person findedPerson1 = (Person)persons[0];
        PersonTestUtil.assertMatch(findedPerson1, person1);
        assertEquals(1, persons.length);
    }
    public void findAllByIdList_EmptyListTest() {
        getRepository().findAllById(new ArrayList());
    }
    public void findAllByIdList_OneEntityNullInListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().findAllById(Arrays.asList(null)));
    }
    public void findAllByIdList_NullListTest(){
        findAllByIdList_NullListTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void findAllByIdList_NullListTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().findAllById(null));
    }

    public void countTest(){
        long count = getRepository().count();
        Person addedPerson = getRepository().save(PersonTestData.getNewPerson01());

        long countPlusOne = getRepository().count();
        assertEquals(countPlusOne, count+1);

        getRepository().deleteById(addedPerson.getId());

        long countRestored = getRepository().count();
        assertEquals(countRestored, count);
    }

    public void existsById_ExistsIdTest(){
        Person person = getRepository().save(PersonTestData.getNewPerson01());
        assertTrue(person.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        assertTrue(getRepository().existsById(person.getId()));
    }
    public void existsById_NonExistsIdTest(){
        assertFalse(getRepository().existsById(-100L));
    }

    public void existsById_NullIdTest(){
        existsById_NullIdTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void existsById_NullIdTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().existsById(null));
    }

}
