package ua.mai.fam.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.jdbc.PersonUtil;
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
@Profile("da-jpa")
@Repository
public class PersonRepository4Jpa implements PersonRepository {

    @PersistenceContext
    private EntityManager em;


    public static final PersonUtil.PersonToRowMapper ROW_MAPPER = new PersonUtil.PersonToRowMapper();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPerson;

    @Autowired
    public PersonRepository4Jpa(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsertPerson = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("person")
            .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public Person save(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
//        ValidationUtil.validate(person);
        if (person.isNew()) {
            //Если
            return this.insert(person);
        } else {
            MapSqlParameterSource paramMap = PersonUtil.getMapSqlParameterSource(person);

            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE person " +
                      "SET surname=:surname, first_name=:first_name, middle_name=:middle_name, " +
                          "birth_date=:birth_date, death_date=:death_date, gender=:gender " +
                      "WHERE id=:id"
                , paramMap) == 0) {
                throw new NotFoundException("Not exists person with id=" + person.getId() + ".");
            }
            return person;
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
        MapSqlParameterSource paramMap = PersonUtil.getMapSqlParameterSource(person);
        Number newId = simpleJdbcInsertPerson.executeAndReturnKey(paramMap);
        person.setId(newId.longValue());
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
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
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
//        if (entities != null) {
//            entities.forEach(person -> delete(person));
//                                       { if (person != null) {
//                                             delete(person);
//                                         }});
//        }
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
        List<Person> persons = jdbcTemplate.query("SELECT * FROM person WHERE id = ?", ROW_MAPPER, id);
        return Optional.ofNullable(DataAccessUtils.singleResult(persons));
    }

    @Override
    public Iterable<Person> findAll() {
        List<Person> persons = jdbcTemplate.query("SELECT * FROM person", ROW_MAPPER);
        return persons;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> longs) {
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
        long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long.class);
        return count;
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        try {
            jdbcTemplate.queryForObject("SELECT id FROM person WHERE id = ?", Long.class, id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
