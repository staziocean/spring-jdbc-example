package fr.diginami.spring.core.repository;

import fr.diginami.spring.core.mapper.DiseaseRowMapper;
import fr.diginami.spring.core.model.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DiseaseRepository {

    private JdbcTemplate jdbcTemplate;

    public int countDiseaseStartingWith(String startWith) {
        String sqlQuery = "select count(1) from disease where name_ like ?";
        String formattedParameter = startWith + "%";
        return jdbcTemplate.queryForObject(sqlQuery, Integer.class, formattedParameter);
    }

    public List<String> getDiseaseNameHavingLethalitySuperiorTo(int value) {
        String sqlQuery = "select name_ from disease where lethality > ?";
        return jdbcTemplate.queryForList(sqlQuery, String.class, value);
    }

    public List<Disease> getDiseasesHavingContagionInferiorTo(int value) {
        String sqlQuery = "select name_, lethality, contagiosity from disease where contagiosity < ? order by name_";
        return jdbcTemplate.query(
                sqlQuery,
                (rs, i) -> new Disease(rs.getString(1), rs.getInt(2), rs.getInt(3)),
                value);
    }

    public List<Disease> findAll() {
        String sqlQuery = "select name_, lethality, contagiosity from disease order by name_";
        return jdbcTemplate.query(
                sqlQuery,
                (rs, i) -> new Disease(rs.getString(1), rs.getInt(2), rs.getInt(3)));
    }

    public Integer countByName(String name) {
        String sqlQuery = "select count(1) from disease where name_ = ?";
        return jdbcTemplate.queryForObject(
                sqlQuery,
                Integer.class,
                name);
    }

    public void createDisease(Disease disease) {
        if (countByName(disease.getName()) > 0) {
            throw new RuntimeException("The disease '" + disease.getName() + "' already exists");
        }

        String sqlQuery = "insert into disease (name_, lethality, contagiosity) values (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, disease.getName(), disease.getLethality(), disease.getContagion());
    }

    public void updateDisease(String name, Disease disease) {
        String sqlQuery = "update disease set name_ = ?, lethality = ?, contagiosity = ? where name_ = ?";
        jdbcTemplate.update(sqlQuery, disease.getName(), disease.getLethality(), disease.getContagion(), name);
    }

    public void deleteDisease(String name) {
        String sqlQuery = "delete from disease where name_ = ?";
        jdbcTemplate.update(sqlQuery, name);
    }

    @Autowired
    public void setDatasource(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }


    public RowMapper<Disease> createRowMapperLambda() {
        RowMapper<Disease> rowMapper = (rs, i) -> new Disease(rs.getString(1), rs.getInt(2), rs.getInt(3));
        return rowMapper;
    }

    public RowMapper<Disease> createRowMapperAnonymousClass() {
        RowMapper<Disease> rowMapper = new RowMapper<Disease>() {
            @Override
            public Disease mapRow(ResultSet rs, int i) throws SQLException {
                return new Disease(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
        };
        return rowMapper;
    }

    public RowMapper<Disease> createRowMapperNamedClass() {
        RowMapper<Disease> rowMapper = new DiseaseRowMapper();
        return rowMapper;
    }
}
