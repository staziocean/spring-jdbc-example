package fr.diginami.spring.core.mapper;

import fr.diginami.spring.core.model.Disease;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseRowMapper implements RowMapper<Disease> {
    @Override
    public Disease mapRow(ResultSet rs, int i) throws SQLException {
        String name = rs.getString(1);
        Integer lethality = rs.getInt(2);
        Integer contagion = rs.getInt(3);

        Disease disease = new Disease(name, lethality, contagion);
        return disease;
        // return new Disease(rs.getString(1), rs.getInt(2), rs.getInt(3));
    }
}
