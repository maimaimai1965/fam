package ua.mai.fam.repository.jdbc;

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
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.repository.PersonDtoRepository;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-jdbc")
@Repository
public class
PersonDtoRepository4Jdbc implements PersonDtoRepository {

    public static final PersonDtoUtil.PersonDtoToRowMapper ROW_MAPPER = new PersonDtoUtil.PersonDtoToRowMapper();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPerson;

    @Autowired
    public PersonDtoRepository4Jdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsertPerson = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("person")
            .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public PersonDto save(PersonDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
//        ValidationUtil.validate(person);
        if (entity.isNew()) {
            //Если
            return this.insert(entity);
        } else {
            MapSqlParameterSource paramMap = PersonDtoUtil.getMapSqlParameterSource(entity);

            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE person " +
                      "SET surname=:surname, first_name=:first_name, middle_name=:middle_name, " +
                          "birth_date=:birth_date, death_date=:death_date, gender=:gender " +
                      "WHERE id=:id"
                , paramMap) == 0) {
                throw new NotFoundException("Not exists person with id=" + entity.getId() + ".");
            }
            return entity;
        }
    }

    @Override
    @Transactional
    public PersonDto insert(PersonDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Object can't be null.");
        }
        if (entity.getId() != null) {
            throw new FoundException("Exists id=" + entity.getId() + " in new inserted object.");
        }
        MapSqlParameterSource paramMap = PersonDtoUtil.getMapSqlParameterSource(entity);
        Number newId = simpleJdbcInsertPerson.executeAndReturnKey(paramMap);
        entity.setId(newId.longValue());
        return entity;
    }

    @Transactional
    @Override
    public <S extends PersonDto> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException();
        }
        List<PersonDto> list = new ArrayList<PersonDto>((entities != null) ? ((Collection) entities).size() : 0);
        entities.forEach(entity -> { if (entity != null) {
                                         PersonDto savedEntity = save(entity);
                                         list.add(savedEntity);
                                     }});
        return (Iterable<S>) list;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null.");
        }
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    @Transactional
    @Override
    public void delete(PersonDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (!entity.isNew()) {
            deleteById(entity.getId());
        }
    }

    @Transactional
    @Override
    public void deleteAll(Iterable<? extends PersonDto> entities) {
        entities.forEach(entity -> delete(entity));
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
        throw new RuntimeException("PersonDtoRepository4Jdbc.deleteAll() not realised!");
    }

    @Override
    public Optional<PersonDto> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null.");
        }
        List<PersonDto> entities = jdbcTemplate.query("SELECT * FROM person  WHERE id = ?", ROW_MAPPER, id);
        return Optional.ofNullable(DataAccessUtils.singleResult(entities));
    }

    @Override
    public Iterable<PersonDto> findAll() {
        List<PersonDto> entities = jdbcTemplate.query("SELECT * FROM person", ROW_MAPPER);
        return entities;
    }

    @Override
    public Iterable<PersonDto> findAllById(Iterable<Long> ids) {
        if (ids == null) {
            throw new IllegalArgumentException();
        }
        List<PersonDto> list = new ArrayList<>((ids != null) ? ((Collection) ids).size() : 0);
        ids.forEach(id -> { Optional<PersonDto> person = findById(id);
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
