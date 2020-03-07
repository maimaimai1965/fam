package mai.ua.fam.repository.jdbc;

import mai.ua.fam.model.person.PersonTo;
import mai.ua.fam.model.person.PersonToUtil;
import mai.ua.fam.repository.PersonToRepository;
import mai.ua.fam.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-jdbc")
@Repository
public class PersonToRepository4Jdbc implements PersonToRepository {

    public static final PersonToUtil.PersonToRowMapper ROW_MAPPER = new PersonToUtil.PersonToRowMapper();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPerson;

    @Autowired
    public PersonToRepository4Jdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsertPerson = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("person")
            .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public PersonTo insert(PersonTo person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        MapSqlParameterSource paramMap = PersonToUtil.getMapSqlParameterSource(person);
        Number newId = simpleJdbcInsertPerson.executeAndReturnKey(paramMap);
        person.setId(newId.longValue());
        return person;
    }

    @Override
    @Transactional
    public PersonTo save(PersonTo person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
//        ValidationUtil.validate(person);
        if (person.isNew()) {
            return insert(person);
        } else {
            MapSqlParameterSource paramMap = PersonToUtil.getMapSqlParameterSource(person);

            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE person " +
                      "SET surname=:surname, first_name=:first_name, middle_name=:middle_name, " +
                          "birth_date=:birth_date, death_date=:death_date, gender=:gender " +
                      "WHERE id=:id"
                , paramMap) == 0) {
                return null;
            }
            return person;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            new IllegalArgumentException("id can't be null.");
        }
        if (jdbcTemplate.update("DELETE FROM person WHERE id=?", id) == 0) {
            throw new NotFoundException("Don't exist deleted row id=" + Long.toString(id));
        }
    }

    @Override
    @Transactional
    public void delete(PersonTo person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can't be null.");
        }
        if (!person.isNew()) {
            deleteById(person.getId());
        }
    }

    @Override
    public Optional<PersonTo> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null.");
        }
        List<PersonTo> persons = jdbcTemplate.query("SELECT * FROM person WHERE id = ?", ROW_MAPPER, id);
        return Optional.ofNullable(DataAccessUtils.singleResult(persons));
    }

    @Override
    public Iterable<PersonTo> findAll() {
        List<PersonTo> persons = jdbcTemplate.query("SELECT * FROM person", ROW_MAPPER);
        return persons;
    }

    @Override
    public long count() {
        long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long.class);
        return count;
    }

}
