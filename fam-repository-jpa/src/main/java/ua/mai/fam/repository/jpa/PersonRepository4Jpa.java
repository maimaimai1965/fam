package ua.mai.fam.repository.jpa;

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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-jpa")
@Repository
public class PersonRepository4Jpa implements PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public Person save(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (person.isNew()) {
            //Если новый person
            entityManager.persist(person);
            return person;
        } else {
            return entityManager.merge(person);
        }
    }

    @Override
    @Transactional
    public Person insert(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Object can't be null.");
        }
        if (person.getId() != null) {
            throw new FoundException("Exists id=" + person.getId() + " in new inserted object.");
        }
        entityManager.persist(person);
        return person;
    }

    @Transactional
    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException();
        }
        List<Person> list = new ArrayList<Person>((entities != null) ? ((Collection) entities).size() : 0);
        entities.forEach(person -> { if (person != null) {
            Person savedPerson = save(person);
            list.add(savedPerson);
        }});
        return (Iterable<S>) list;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null.");
        }
        Query query = entityManager.createQuery("DELETE FROM Person p WHERE p.id=:id");
        query.setParameter("id", id).executeUpdate();
//        Person ref = entityManager.getReference(Person.class, id);
//        entityManager.remove(ref);
    }

    @Transactional
    @Override
    public void delete(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (!person.isNew()) {
            deleteById(person.getId());
        }
    }

    @Transactional
    @Override
    public void deleteAll(Iterable<? extends Person> entities) {
        entities.forEach(person -> delete(person));
    }

    @Transactional
    @Override
    public void deleteAll() {
        throw new RuntimeException("PersonRepository4Jdbc.deleteAll() not realised!");
    }

    @Override
    public Optional<Person> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null.");
        }
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    public Iterable<Person> findAll() {
        //TODO
        return null;
//        List<Person> persons = jdbcTemplate.query("SELECT * FROM person", ROW_MAPPER);
//        return persons;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> longs) {
        //TODO переписать
        if (longs == null) {
            throw new IllegalArgumentException();
        }
        List<Person> list = new ArrayList<Person>((longs != null) ? ((Collection) longs).size() : 0);
        longs.forEach(id -> { Optional<Person> person = findById(id);
            if (person.isPresent()) {
                list.add(person.get());
            }
        });
        return list;
    }

    @Override
    public long count() {
        //TODO
        return -1;
//        long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long.class);
//        return count;
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        try {
            Optional<Person> person = findById(id);
            return person.isPresent();
//TODO переписать на native query
//            jdbcTemplate.queryForObject("SELECT id FROM person WHERE id = ?", Long.class, id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
