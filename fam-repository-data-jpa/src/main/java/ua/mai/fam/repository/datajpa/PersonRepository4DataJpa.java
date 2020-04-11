package ua.mai.fam.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-data-jpa")
@Repository
public interface PersonRepository4DataJpa extends PersonRepository, JpaRepository<Person, Long>, WithOperations<Person> {

//    @PersistenceContext
//    private EntityManager em;
//
////    @Autowired
////    public PersonRepository4DataJpa(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
////    }
//
//    @Transactional
//    @Override
//    public Person save(Person person) {
//        if (person == null) {
//            throw new IllegalArgumentException("Person can't be null.");
//        }
////        ValidationUtil.validate(person);
//
//        if (person.isNew()) {
//            //Если
//            em.persist(person);
//            return person;
//        } else {
//            return em.merge(person);
//        }
//    }
//
//    @Override
//    @Transactional
//    public Person insert(Person person) {
//        //TODO
//        return  null;
////        if (person == null) {
////            throw new IllegalArgumentException("Object can't be null.");
////        }
////        if (person.getId() != null) {
////            throw new FoundException("Exists id=" + person.getId() + " in new inserted object.");
////        }
////        MapSqlParameterSource paramMap = PersonUtil.getMapSqlParameterSource(person);
////        Number newId = simpleJdbcInsertPerson.executeAndReturnKey(paramMap);
////        person.setId(newId.longValue());
////        return person;
//    }
//
//    @Transactional
//    @Override
//    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
//        //TODO
//        return  null;
////        if (entities == null) {
////            throw new IllegalArgumentException();
////        }
////        List<Person> list = new ArrayList<Person>((entities != null) ? ((Collection) entities).size() : 0);
////        entities.forEach(person -> { if (person != null) {
////                                         Person savedPerson = save(person);
////                                         list.add(savedPerson);
////                                     }});
////        return (Iterable<S>) list;
//    }
//
//    @Transactional
//    @Override
//    public void deleteById(Long id) {
//        //TODO
////        if (id == null) {
////            throw new IllegalArgumentException("id can't be null.");
////        }
////        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
//    }
//
//    @Transactional
//    @Override
//    public void delete(Person person) {
//        //TODO
////        if (person == null) {
////            throw new IllegalArgumentException("Person can't be null.");
////        }
////        if (!person.isNew()) {
////            deleteById(person.getId());
////        }
//    }
//
//    @Transactional
//    @Override
//    public void deleteAll(Iterable<? extends Person> entities) {
//        entities.forEach(person -> delete(person));
//    }
//
//    @Transactional
//    @Override
//    public void deleteAll() {
//        //TODO
////        throw new RuntimeException("PersonRepository4Jdbc.deleteAll() not realised!");
//    }
//
//    @Override
//    public Optional<Person> findById(Long id) {
//        //TODO
//        return  null;
////        if (id == null) {
////            throw new IllegalArgumentException("id can't be null.");
////        }
////        List<Person> persons = jdbcTemplate.query("SELECT * FROM person WHERE id = ?", ROW_MAPPER, id);
////        return Optional.ofNullable(DataAccessUtils.singleResult(persons));
//    }
//
//    @Override
//    public Iterable<Person> findAll() {
//        //TODO
//        return  null;
////        List<Person> persons = jdbcTemplate.query("SELECT * FROM person", ROW_MAPPER);
////        return persons;
//    }
//
//    @Override
//    public Iterable<Person> findAllById(Iterable<Long> longs) {
//        //TODO
//        return  null;
////        if (longs == null) {
////            throw new IllegalArgumentException();
////        }
////        List<Person> list = new ArrayList<Person>((longs != null) ? ((Collection) longs).size() : 0);
////        longs.forEach(id -> { Optional<Person> person = findById(id);
////                              if (person.isPresent()) {
////                                  list.add(person.get());
////                              }
////                            });
////        return list;
//    }
//
//    @Override
//    public long count() {
//        //TODO
//        return 0;
////        long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long.class);
////        return count;
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        //TODO
//        return  false;
////        if (id == null) {
////            throw new IllegalArgumentException();
////        }
////        try {
////            jdbcTemplate.queryForObject("SELECT id FROM person WHERE id = ?", Long.class, id);
////            return true;
////        } catch (EmptyResultDataAccessException e) {
////            return false;
////        }
//    }

}
