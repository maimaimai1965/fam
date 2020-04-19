package ua.mai.fam.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.model.Gender;
import ua.mai.fam.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Утилиты для PersonDto.
 */
public class PersonDtoUtil {

    private PersonDtoUtil(){}

    /**
     *
     */
    public static class PersonDtoToRowMapper implements RowMapper<PersonDto> {

        @Override
        public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            PersonDto personDtoTo = new PersonDto();
            personDtoTo.setId(rs.getLong("id"));
            personDtoTo.setSurname(rs.getString("surname"));
            personDtoTo.setFirstName(rs.getString("first_name"));
            personDtoTo.setMiddleName(rs.getString("middle_name"));
            personDtoTo.setBirthDate(DateTimeUtil.convertUtilDateToLocalDate(rs.getDate("birth_date")));
            personDtoTo.setDeathDate(DateTimeUtil.convertUtilDateToLocalDate(rs.getDate("death_date")));
            personDtoTo.setGender(Gender.valueOf(rs.getString("gender")));
            return personDtoTo;
        }
    }

    public static MapSqlParameterSource getMapSqlParameterSource(PersonDto personDtoTo) {
        return new MapSqlParameterSource()
            .addValue("id", personDtoTo.getId())
            .addValue("surname", personDtoTo.getSurname())
            .addValue("first_name", personDtoTo.getFirstName())
            .addValue("middle_name", personDtoTo.getMiddleName())
            .addValue("birth_date", DateTimeUtil.convertLocalDateToUtilDate(personDtoTo.getBirthDate()))
            .addValue("death_date", DateTimeUtil.convertLocalDateToUtilDate(personDtoTo.getDeathDate()))
            .addValue("gender", personDtoTo.getGender().name());
    }

    public static MapSqlParameterSource getMapSqlParameterSource4Id(PersonDto personDtoTo) {
        return new MapSqlParameterSource().addValue("id", personDtoTo.getId());
    }

}
