package ua.mai.fam.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import ua.mai.fam.model.TogetherType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Утилиты для TogetherType.
 */
public class TogetherTypeUtil {

    private TogetherTypeUtil(){}

    /**
     *
     */
    public static class TogetherTypeToRowMapper implements RowMapper<TogetherType> {
        @Override
        public TogetherType mapRow(ResultSet rs, int rowNum) throws SQLException {
            TogetherType togetherTypeTo = new TogetherType();
            togetherTypeTo.setCode(rs.getString("code"));
            togetherTypeTo.setName(rs.getString("name"));
            return togetherTypeTo;
        }
    }

    public static MapSqlParameterSource getMapSqlParameterSource(TogetherType togetherTypeTo) {
        return new MapSqlParameterSource()
            .addValue("code", togetherTypeTo.getCode())
            .addValue("name", togetherTypeTo.getName());
    }

    public static MapSqlParameterSource getMapSqlParameterSource4Id(TogetherType togetherTypeTo) {
        return new MapSqlParameterSource().addValue("code", togetherTypeTo.getCode());
    }

}
