package ua.mai.fam.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.fam.model.Person;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import javax.persistence.EntityManager;
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
    public Person save(Person entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (entity.isNew()) {
            //Если новый person
            entityManager.persist(entity);
            return entity;
        } else {
            Person managedPerson = entityManager.merge(entity);
            if (managedPerson.getId().equals(entity.getId())) {
                return managedPerson;
            }
            else {
                entityManager.detach(managedPerson);
                throw new NotFoundException("person", "id", entity.getId().toString());
            }
        }
    }

    @Override
    @Transactional
    public Person insert(Person entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Object can't be null.");
        }
        if (entity.getId() != null) {
            throw new FoundException("Exists id=" + entity.getId() + " in new inserted object.");
        }
        entityManager.persist(entity);
        return entity;
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
    public void delete(Person entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (!entity.isNew()) {
            deleteById(entity.getId());
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
        List<Person> list = entityManager.createQuery("SELECT p FROM Person AS p").getResultList();
        return list;
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
        long count = entityManager.createQuery("SELECT COUNT(*) FROM Person", Long.class).getSingleResult();
        return count;
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
