package ua.mai.fam.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import ua.mai.fam.AbstractTimingExtension;
import ua.mai.fam.model.TogetherTypeTestData;
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.model.TogetherTypeTestData;
import ua.mai.fam.model.util.TogetherTypeTestUtil;
import ua.mai.fam.util.exception.FoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для реализаций TogetherType репозитория.
 */
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractTogetherTypeRepositoryTest {

    abstract protected TogetherTypeRepository getRepository();

    protected void checkNotExsist(String code) {
        Optional<TogetherType> foundTogetherType = getRepository().findByCode(code);
        if (foundTogetherType.isPresent()) {
            getRepository().deleteByCode(code);

            foundTogetherType = getRepository().findByCode(code);
            if (foundTogetherType.isPresent()) {
                assertTrue(false, "Объекта с code = " + code + " не должно быть в БД!");
            }
        }
    }

    /** Когда в таблице нет записи с этим кодом, то производиться insert этой записи в таблицу.  */
    public void save_insert_whenTogetherTypeNotExistsInDbTest(){
        String code = "TEST_SAVE_INSERT_CODE";
        checkNotExsist(code);

        //Сохранение в БД.
        TogetherType togetherType = TogetherTypeTestData.getTogetherType01();
        getRepository().save(togetherType);
        TogetherTypeTestUtil.assertMatch(getRepository().findByCode(code).get(), togetherType);

        getRepository().deleteByCode(code);
    }
    /** Когда в таблице есть запись с этим кодом, то должен производиться update этой записи в таблице. */
    public void save_update_whenTogetherTypeExistsInDbTest() {
        String code = "TEST_SAVE_UPDATE_CODE";
        checkNotExsist(code);

        //Вставка в БД тестового объекта.
        TogetherType existsTogetherType = getRepository().save(TogetherTypeTestData.getTogetherType01());

        TogetherType changedTogetherType = TogetherTypeTestData.getTogetherType02();
        changedTogetherType.setCode(existsTogetherType.getCode());
        getRepository().save(changedTogetherType);
        Optional<TogetherType> savedTogetherType = getRepository().findByCode(existsTogetherType.getCode());
        //Проверяем обновленный объект.
        TogetherTypeTestUtil.assertMatch(savedTogetherType, changedTogetherType);

        getRepository().deleteByCode(code);
    }
    /**
     * Когда в togetherType нет кода, то при сохранении должно генерироваться исключение.
     */
    public void save_newTogetherTypeWithoutCode() {
        TogetherType togetherType = new TogetherType(null, "Test");
        assertThrows(IllegalArgumentException.class, () -> getRepository().save(togetherType));
    }
    /**
     * Когда в togetherType нет названия, то при сохранении должно генерироваться исключение.
     */
    public void save_newTogetherTypeWithoutName() {
        TogetherType togetherType = new TogetherType("TEST", null);
        assertThrows(IllegalArgumentException.class, () -> getRepository().save(togetherType));
    }
    public void save_nullTogetherTypeTest() {
        save_nullTogetherTypeTest(null);
    }
    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
    public void save_nullTogetherTypeTest(Class expectedExceptionClass) {
        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
                     () -> getRepository().save(null));
    }

//    public void saveAll_ListTest() {
//        //Вставка в БД тестовых Entity.
//        TogetherType togetherType1 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        TogetherType togetherType2 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes02());
//
//        Object[] togetherTypes = ((Collection<TogetherType>)getRepository().saveAll(Arrays.asList(togetherType1, togetherType2))).toArray();
//
//        TogetherType savedTogetherType1 = (TogetherType)togetherTypes[0];
//        assertTrue(savedTogetherType1.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        TogetherType savedTogetherType2 = (TogetherType)togetherTypes[1];
//        assertTrue(savedTogetherType2.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        Optional<TogetherType> findedTogetherType1 = getRepository().findById(savedTogetherType1.getId());
//        TogetherTypeTestUtil.assertMatch(findedTogetherType1.get(), savedTogetherType1);
//        Optional<TogetherType> findedTogetherType2 = getRepository().findById(savedTogetherType2.getId());
//        TogetherTypeTestUtil.assertMatch(findedTogetherType2.get(), savedTogetherType2);
//    }
//    public void saveAll_OneEntityNullInListTest() {
//        assertThrows(NullPointerException.class, () -> getRepository().saveAll(Arrays.asList(null)));
//    }
//    public void saveAll_NullListTest() {
//        saveAll_NullListTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка оборачивается в RuntimeException.
//    public void saveAll_NullListTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().saveAll((Iterable<TogetherType>)null));
//    }
//
//    /**
//     * Когда в togetherType нет идентификатора, то при сохранении должен генерироваться идентификатор и создаваться новая
//     * запись в таблице с этим идентификатором. Идентификатор прописывается в возвращаемом объекте.
//     */
//    public void insert_NewTogetherTypeWithEmptyIdTest() {
//        TogetherType togetherType = TogetherTypeTestData.getNewTogetherTypes01();
//        //Вставка без Id. Id генерится в БД.
//        TogetherType savedTogetherType = getRepository().insert(togetherType);
//        Long id = savedTogetherType.getId();
//        assertTrue(id > 0, "При вставке нового объекта без идентификатора не был сгенерирован Id.");
//        //Проверяем вставленный объект.
//        Optional<TogetherType> findedTogetherType = getRepository().findById(id);
//        TogetherTypeTestUtil.assertMatch(findedTogetherType.get(), togetherType);
//    }
//    /**
//     * Когда в togetherType есть идентификатор, то при вставке вызывается исключение {@link FoundException}.
//     */
//    public void insert_TogetherTypeWithId(){
//        //Вставка в БД тестового Entity.
//        TogetherType existsTogetherType = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(existsTogetherType.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        TogetherType togetherType = TogetherTypeTestData.getTogetherTypes02(existsTogetherType.getId());
//        assertThrows(FoundException.class, () -> getRepository().insert(togetherType));
//    }
//    public void insert_NullTogetherTypeTest() {
//        insert_NullTogetherTypeTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void insert_NullTogetherTypeTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().insert(null));
//    }
//
//    public void deleteByIdTest() {
//        //Вставка в БД тестового Entity.
//        TogetherType existsTogetherType = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(existsTogetherType.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        Long id = existsTogetherType.getId();
//
//        getRepository().deleteById(id);
//        Optional<TogetherType> notExistsTogetherType = getRepository().findById(id);
//        assertFalse(notExistsTogetherType.isPresent(), "Entity не удален.");
//    }
//    /**
//     * Удаление несуществующего Entity;
//     */
//    public void deleteById_NonExistsIdTest() {
//        //Проверка отсутствия в БД тестового Entity.
//        Long id = -100L;
//        Optional<TogetherType> notExistsTogetherType = getRepository().findById(id);
//        assertFalse(notExistsTogetherType.isPresent(), "Существуе тестовый Entity с id=" + id + ".");
//
//        getRepository().deleteById(-100L);
//    }
//    /**
//     * Удаление несуществующего Entity;
//     */
//    public void deleteById_NullIdTest() {
//        deleteById_NullIdTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void deleteById_NullIdTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().deleteById(null));
//    }
//
//    public void deleteTest() {
//        //Вставка в БД тестового Entity.
//        TogetherType existsTogetherType = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(existsTogetherType.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        Long id = existsTogetherType.getId();
//
//        getRepository().delete(existsTogetherType);
//        Optional<TogetherType> notExistsTogetherType = getRepository().findById(id);
//        assertFalse(notExistsTogetherType.isPresent(), "Entity не удален.");
//    }
//    public void delete_NullTogetherTypeTest() {
//        delete_NullTogetherTypeTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void delete_NullTogetherTypeTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().delete(null));
//    }
//
//    public void deleteAll_ListTest() {
//        //Вставка в БД тестовых Entity.
//        TogetherType togetherType1 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(togetherType1.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        TogetherType togetherType2 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes02());
//        assertTrue(togetherType2.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        getRepository().deleteAll(Arrays.asList(togetherType1, togetherType2));
//
//        Optional<TogetherType> notExistsTogetherType = getRepository().findById(togetherType1.getId());
//        assertFalse(notExistsTogetherType.isPresent(), "Entity 1 не удален.");
//        notExistsTogetherType = getRepository().findById(togetherType2.getId());
//        assertFalse(notExistsTogetherType.isPresent(), "Entity 2 не удален.");
//    }
//    public void deleteAll_OneEntityNullInListTest() {
//        assertThrows(NullPointerException.class, () -> getRepository().deleteAll(Arrays.asList(null)));
//    }
//    public void deleteAll_NullListTest() {
//        assertThrows(NullPointerException.class, () -> getRepository().deleteAll((Iterable<TogetherType>)null));
//    }
//
//    public void findByIdTest(){
//        TogetherType togetherType = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(togetherType.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        Optional<TogetherType> findedTogetherType = getRepository().findById(togetherType.getId());
//        TogetherTypeTestUtil.assertMatch(findedTogetherType.get(), togetherType);
//    }
//    public void findById_NonExistsIdTest(){
//        Optional<TogetherType> notExistsTogetherType = getRepository().findById(-100L);
//        assertFalse(notExistsTogetherType.isPresent());
//    }
//    public void findById_NullIdTest(){
//        findById_NullIdTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void findById_NullIdTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().findById(null));
//    }
//
//    public void findAllTest(){
//        Collection<TogetherType> togetherTypes = (Collection<TogetherType>)getRepository().findAll();
//        long count = getRepository().count();
//        assertEquals(count, togetherTypes.size());
//    }
//
//    public void findAllByIdListTest() {
//        //Вставка в БД тестовых Entity.
//        TogetherType togetherType1 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(togetherType1.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        TogetherType togetherType2 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes02());
//        assertTrue(togetherType2.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        Object[] togetherTypes =
//            ((Collection<TogetherType>)getRepository().findAllById(Arrays.asList(togetherType1.getId(), togetherType2.getId()))).toArray();
//
//        TogetherType findedTogetherType1 = (TogetherType)togetherTypes[0];
//        TogetherTypeTestUtil.assertMatch(findedTogetherType1, togetherType1);
//        TogetherType findedTogetherType2 = (TogetherType)togetherTypes[1];
//        TogetherTypeTestUtil.assertMatch(findedTogetherType2, togetherType2);
//    }
//    public void findAllByIdList_OneEntityNotExistsTest() {
//        //Вставка в БД тестовых Entity.
//        TogetherType togetherType1 = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(togetherType1.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//
//        Object[] togetherTypes =
//            ((Collection<TogetherType>)getRepository().findAllById(Arrays.asList(togetherType1.getId(), -100L))).toArray();
//
//        TogetherType findedTogetherType1 = (TogetherType)togetherTypes[0];
//        TogetherTypeTestUtil.assertMatch(findedTogetherType1, togetherType1);
//        assertEquals(1, togetherTypes.length);
//    }
//    public void findAllByIdList_EmptyListTest() {
//        getRepository().findAllById(new ArrayList());
//    }
//    public void findAllByIdList_OneEntityNullInListTest() {
//        assertThrows(NullPointerException.class, () -> getRepository().findAllById(Arrays.asList(null)));
//    }
//    public void findAllByIdList_NullListTest(){
//        findAllByIdList_NullListTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void findAllByIdList_NullListTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().findAllById(null));
//    }
//
//    public void countTest(){
//        long count = getRepository().count();
//        Collection<TogetherType> togetherTypes = (Collection<TogetherType>)getRepository().findAll();
//        assertEquals(count, togetherTypes.size());
//    }
//
//    public void existsById_ExistsIdTest(){
//        TogetherType togetherType = getRepository().save(TogetherTypeTestData.getNewTogetherTypes01());
//        assertTrue(togetherType.getId() > 0,
//            "При вставке нового объекта без идентификатора (для тестирования) не был сгенерирован Id.");
//        assertTrue(getRepository().existsById(togetherType.getId()));
//    }
//    public void existsById_NonExistsIdTest(){
//        assertFalse(getRepository().existsById(-100L));
//    }
//    public void existsById_NullIdTest(){
//        existsById_NullIdTest(null);
//    }
//    //Необходим, т.к. в JPA генерируемая ошибка IllegalArgumentException оборачивается в RuntimeException.
//    public void existsById_NullIdTest(Class expectedExceptionClass) {
//        assertThrows((expectedExceptionClass != null) ? expectedExceptionClass : IllegalArgumentException.class,
//                     () -> getRepository().existsById(null));
//    }

}
