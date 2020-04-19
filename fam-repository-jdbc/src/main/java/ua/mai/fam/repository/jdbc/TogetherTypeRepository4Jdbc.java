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
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.repository.TogetherTypeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-jdbc")
@Repository
public class TogetherTypeRepository4Jdbc implements TogetherTypeRepository {

    public static final TogetherTypeUtil.TogetherTypeToRowMapper ROW_MAPPER =
        new TogetherTypeUtil.TogetherTypeToRowMapper();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public TogetherTypeRepository4Jdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert =
            new SimpleJdbcInsert(jdbcTemplate).withTableName("togetherType").usingGeneratedKeyColumns("code");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public TogetherType save(TogetherType togetherType) {
        if (togetherType == null) {
            throw new IllegalArgumentException("TogetherType can't be null.");
        }
//        ValidationUtil.validate(togetherType);
        MapSqlParameterSource paramMap = TogetherTypeUtil.getMapSqlParameterSource(togetherType);
        int updCount =
            namedParameterJdbcTemplate.update("UPDATE together_type SET name=:name WHERE code=:code", paramMap);
        if (updCount == 0) {
            //Если нет такой записи в БД.
            return this.insert(togetherType);
        }
        return togetherType;
    }

    @Override
    @Transactional
    public TogetherType insert(TogetherType togetherType) {
        if (togetherType == null) {
            throw new IllegalArgumentException("Object can't be null.");
        }
//        ValidationUtil.validate(togetherType);
        MapSqlParameterSource paramMap = TogetherTypeUtil.getMapSqlParameterSource(togetherType);
        int countRows = simpleJdbcInsert.execute(paramMap);

        return togetherType;
    }

    @Transactional
    @Override
    public <S extends TogetherType> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException();
        }
        List<TogetherType> list = new ArrayList<TogetherType>((entities != null) ? ((Collection) entities).size() : 0);
        entities.forEach(togetherType -> { if (togetherType != null) {
                                         TogetherType savedTogetherType = save(togetherType);
                                         list.add(savedTogetherType);
                                     }});
        return (Iterable<S>) list;
    }

    @Transactional
    @Override
    public void deleteByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code can't be null.");
        }
        jdbcTemplate.update("DELETE FROM together_type WHERE code = ?", code);
    }

    @Transactional
    @Override
    public void delete(TogetherType togetherType) {
        if (togetherType == null) {
            throw new IllegalArgumentException("TogetherType can't be null.");
        }
        deleteByCode(togetherType.getCode());
    }

    @Transactional
    @Override
    public void deleteAll(Iterable<? extends TogetherType> entities) {
        entities.forEach(togetherType -> delete(togetherType));
//        if (entities != null) {
//            entities.forEach(togetherType -> delete(togetherType));
//                                       { if (togetherType != null) {
//                                             delete(togetherType);
//                                         }});
//        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        throw new RuntimeException("TogetherTypeRepository4Jdbc.deleteAll() not realised!");
    }

    @Override
    public Optional<TogetherType> findByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code can't be null.");
        }
        List<TogetherType> togetherTypes =
            jdbcTemplate.query("SELECT * FROM together_type WHERE code = ?", ROW_MAPPER, code);
        return Optional.ofNullable(DataAccessUtils.singleResult(togetherTypes));
    }

    @Override
    public Iterable<TogetherType> findAll() {
        List<TogetherType> togetherTypes = jdbcTemplate.query("SELECT * FROM together_type", ROW_MAPPER);
        return togetherTypes;
    }

    @Override
    public Iterable<TogetherType> findAllByCode(Iterable<String> codes) {
        if (codes == null) {
            throw new IllegalArgumentException();
        }
        List<TogetherType> list = new ArrayList<TogetherType>((codes != null) ? ((Collection) codes).size() : 0);
        codes.forEach(code -> { Optional<TogetherType> togetherType = findByCode(code);
                                if (togetherType.isPresent()) {
                                    list.add(togetherType.get());
                                }
                              });
        return list;
    }

    @Override
    public long count() {
        long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM together_type", Long.class);
        return count;
    }

    @Override
    public boolean existsByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException();
        }
        try {
            jdbcTemplate.queryForObject("SELECT code FROM together_type WHERE code = ?", Long.class, code);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
