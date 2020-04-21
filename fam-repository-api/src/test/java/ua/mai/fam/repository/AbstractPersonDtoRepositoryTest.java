package ua.mai.fam.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import ua.mai.fam.AbstractTimingExtension;
import ua.mai.fam.dto.PersonDtoTestData;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.dto.PersonDtoTestUtil;
import ua.mai.fam.util.exception.FoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для реализаций PersonDto репозитория.
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractPersonDtoRepositoryTest {

    abstract protected PersonDtoRepository getRepository();

    /**
     * Когда в person нет идентификатора, то при сохранении должен генерироваться идентификатор и создаваться новая
     * запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте.
     */
    public void save_NewPersonWithEmptyIdTest() {
        //Если в person идентификатора нет, то генерируется идентификатор и создается новая запись в таблице с этим
        //идентификатором. Идентификатор прописывается в возвращаемом объекте.
        PersonDto person = PersonDtoTestData.getNewPersonDto01();
        //Вставка без Id. Id генерится в БД.
        PersonDto savedPersonDto = getRepository().save(person);
        Long id = savedPersonDto.getId();
        assertTrue(id > 0, "При сохранении нового объекта без идентификатора не был сгенерирован Id.");
        //Проверяем вставленный объект.
        Optional<PersonDto> findedPersonDto = getRepository().findById(id);
        PersonDtoTestUtil.assertMatch(findedPersonDto.get(), person);
    }
    /**
     * Когда в person есть идентификатор, а в таблице есть запись с этим идентификатором, то должен производиться
     * update этой записи в таблице.
     */
    public void save_PersonWithIdWhenPersonExistsInDbTest(){
        //Вставка в БД тестового объекта.
        PersonDto existsPersonDto = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(existsPersonDto.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        PersonDto person = PersonDtoTestData.getPersonDto02(existsPersonDto.getId());
        PersonDto savedPersonDto = getRepository().save(person);
        //Проверяем обновленный объект.
        assertEquals(existsPersonDto.getId(), savedPersonDto.getId());
        PersonDtoTestUtil.assertMatch(savedPersonDto, person);
    }
    /**
     * Когда в person есть идентификатор, а в таблице нет записи с этим идентификатором, то вызывается исключение.
     */
    public void save_PersonWithIdWhenPersonNotExistsInDbTest(){
        Long id = -100L;

        Optional<PersonDto> foundPersonDto = getRepository().findById(id);
        if (foundPersonDto.isPresent()) {
            assertTrue(false, "Объекта с Id = " + id + " не должно быть в БД!");
        }

        //Сохранение в БД.
        PersonDto person = PersonDtoTestData.getPersonDto01(id);
        assertThrows(Exception.class, () -> getRepository().save(person));
    }
    public void save_NullPersonTest() {
        save_NullPersonDtoTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void save_NullPersonDtoTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().save(null));
    }

    public void saveAll_ListTest() {
        //Вставка в БД тестовых Entity.
        PersonDto person1 = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        PersonDto person2 = getRepository().save(PersonDtoTestData.getNewPersonDto02());

        Object[] persons = ((Collection<PersonDto>)getRepository().saveAll(Arrays.asList(person1, person2))).toArray();

        PersonDto savedPersonDto1 = (PersonDto)persons[0];
        assertTrue(savedPersonDto1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        PersonDto savedPersonDto2 = (PersonDto)persons[1];
        assertTrue(savedPersonDto2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Optional<PersonDto> findedPersonDto1 = getRepository().findById(savedPersonDto1.getId());
        PersonDtoTestUtil.assertMatch(findedPersonDto1.get(), savedPersonDto1);
        Optional<PersonDto> findedPersonDto2 = getRepository().findById(savedPersonDto2.getId());
        PersonDtoTestUtil.assertMatch(findedPersonDto2.get(), savedPersonDto2);
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
                     () -> getRepository().saveAll((Iterable<PersonDto>)null));
    }

    /**
     * Когда в person нет идентификатора, то при сохранении должен генерироваться идентификатор и создаваться новая
     * запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте.
     */
    public void insert_NewPersonWithEmptyIdTest() {
        PersonDto person = PersonDtoTestData.getNewPersonDto01();
        //Вставка без Id. Id генерится в БД.
        PersonDto savedPersonDto = getRepository().insert(person);
        Long id = savedPersonDto.getId();
        assertTrue(id > 0, "При вставке нового объекта без идентификатора не был сгенерирован Id.");
        //Проверяем вставленный объект.
        Optional<PersonDto> findedPersonDto = getRepository().findById(id);
        PersonDtoTestUtil.assertMatch(findedPersonDto.get(), person);
    }
    /**
     * Когда в person есть идентификатор, то при вставке вызывается исключение {@link FoundException}.
     */
    public void insert_PersonWithId(){
        //Вставка в БД тестового Entity.
        PersonDto existsPersonDto = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(existsPersonDto.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        PersonDto person = PersonDtoTestData.getPersonDto02(existsPersonDto.getId());
        assertThrows(FoundException.class, () -> getRepository().insert(person));
    }
    public void insert_NullPersonTest() {
        insert_NullPersonDtoTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void insert_NullPersonDtoTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().insert(null));
    }

    public void deleteByIdTest() {
        //Вставка в БД тестового Entity.
        PersonDto existsPersonDto = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(existsPersonDto.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Long id = existsPersonDto.getId();

        getRepository().deleteById(id);
        Optional<PersonDto> notExistsPersonDto = getRepository().findById(id);
        assertFalse(notExistsPersonDto.isPresent(), "Entity не удален.");
    }
    /**
     * Удаление несуществующего Entity;
     */
    public void deleteById_NonExistsIdTest() {
        //Проверка отсутствия в БД тестового Entity.
        Long id = -100L;
        Optional<PersonDto> notExistsPersonDto = getRepository().findById(id);
        assertFalse(notExistsPersonDto.isPresent(), "Существуе тестовый Entity с id=" + id + ".");

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
        PersonDto existsPersonDto = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(existsPersonDto.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        Long id = existsPersonDto.getId();

        getRepository().delete(existsPersonDto);
        Optional<PersonDto> notExistsPersonDto = getRepository().findById(id);
        assertFalse(notExistsPersonDto.isPresent(), "Entity не удален.");
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
        PersonDto person1 = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        PersonDto person2 = getRepository().save(PersonDtoTestData.getNewPersonDto02());
        assertTrue(person2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        getRepository().deleteAll(Arrays.asList(person1, person2));

        Optional<PersonDto> notExistsPersonDto = getRepository().findById(person1.getId());
        assertFalse(notExistsPersonDto.isPresent(), "Entity 1 не удален.");
        notExistsPersonDto = getRepository().findById(person2.getId());
        assertFalse(notExistsPersonDto.isPresent(), "Entity 2 не удален.");
    }
    public void deleteAll_OneEntityNullInListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().deleteAll(Arrays.asList(null)));
    }
    public void deleteAll_NullListTest() {
        assertThrows(NullPointerException.class, () -> getRepository().deleteAll((Iterable<PersonDto>)null));
    }

    public void findByIdTest(){
        PersonDto person = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(person.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Optional<PersonDto> findedPersonDto = getRepository().findById(person.getId());
        PersonDtoTestUtil.assertMatch(findedPersonDto.get(), person);
    }
    public void findById_NonExistsIdTest(){
        Optional<PersonDto> notExistsPersonDto = getRepository().findById(-100L);
        assertFalse(notExistsPersonDto.isPresent());
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
        Collection<PersonDto> persons = (Collection<PersonDto>)getRepository().findAll();
        long count = getRepository().count();
        assertEquals(count, persons.size());
    }

    public void findAllByIdListTest() {
        //Вставка в БД тестовых Entity.
        PersonDto person1 = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
        PersonDto person2 = getRepository().save(PersonDtoTestData.getNewPersonDto02());
        assertTrue(person2.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Object[] persons =
            ((Collection<PersonDto>)getRepository().findAllById(Arrays.asList(person1.getId(), person2.getId()))).toArray();

        PersonDto foundPersonDto1 = (PersonDto)persons[0];
        PersonDtoTestUtil.assertMatch(foundPersonDto1, person1);
        PersonDto foundPersonDto2 = (PersonDto)persons[1];
        PersonDtoTestUtil.assertMatch(foundPersonDto2, person2);
    }
    public void findAllByIdList_OneEntityNotExistsTest() {
        //Вставка в БД тестовых Entity.
        PersonDto person1 = getRepository().save(PersonDtoTestData.getNewPersonDto01());
        assertTrue(person1.getId() > 0,
            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");

        Object[] persons =
            ((Collection<PersonDto>)getRepository().findAllById(Arrays.asList(person1.getId(), -100L))).toArray();

        PersonDto findedPersonDto1 = (PersonDto)persons[0];
        PersonDtoTestUtil.assertMatch(findedPersonDto1, person1);
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
        Collection<PersonDto> persons = (Collection<PersonDto>)getRepository().findAll();
        assertEquals(count, persons.size());
    }

    public void existsById_ExistsIdTest(){
        PersonDto person = getRepository().save(PersonDtoTestData.getNewPersonDto01());
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
