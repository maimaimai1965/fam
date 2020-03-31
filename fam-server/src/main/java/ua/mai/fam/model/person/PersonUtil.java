package ua.mai.fam.model.person;

import ua.mai.fam.util.DateTimeUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Утилиты для Person.
 */
public class PersonUtil {

    private PersonUtil(){}

    /**
     *
     */
    public static class PersonToRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person personTo = new Person();
            personTo.setId(rs.getLong("id"));
            personTo.setSurname(rs.getString("surname"));
            personTo.setFirstName(rs.getString("first_name"));
            personTo.setMiddleName(rs.getString("middle_name"));
            personTo.setBirthDate(DateTimeUtil.convertUtilDateToLocalDate(rs.getDate("birth_date")));
            personTo.setDeathDate(DateTimeUtil.convertUtilDateToLocalDate(rs.getDate("death_date")));
            personTo.setGender(rs.getString("gender"));
            return personTo;
        }
    }

    public static MapSqlParameterSource getMapSqlParameterSource(Person personTo) {
        return new MapSqlParameterSource()
            .addValue("id", personTo.getId())
            .addValue("surname", personTo.getSurname())
            .addValue("first_name", personTo.getFirstName())
            .addValue("middle_name", personTo.getMiddleName())
            .addValue("birth_date", DateTimeUtil.convertLocalDateToUtilDate(personTo.getBirthDate()))
            .addValue("death_date", DateTimeUtil.convertLocalDateToUtilDate(personTo.getDeathDate()))
            .addValue("gender", personTo.getGender());
    }

    public static MapSqlParameterSource getMapSqlParameterSource4Id(Person personTo) {
        return new MapSqlParameterSource().addValue("id", personTo.getId());
    }

}
