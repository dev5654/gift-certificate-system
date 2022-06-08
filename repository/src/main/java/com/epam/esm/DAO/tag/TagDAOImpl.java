package com.epam.esm.DAO.tag;

import com.epam.esm.entities.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag DAO
 */

@Repository
public class TagDAOImpl implements TagDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MapSqlParameterSource parameterSource;

    public TagDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, MapSqlParameterSource parameterSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterSource = parameterSource;
    }

    private static final String INSERT_TAG = "INSERT INTO tag(id, name) VALUES(:id, :name);";

    @Override
    public UUID create(Tag tag) {
        parameterSource.addValue("id", tag.getId())
                .addValue("name", tag.getName());
        jdbcTemplate.update(INSERT_TAG, parameterSource);
        return tag.getId();
    }

    private static final String UPDATE_TAG = "UPDATE tag SET name=:name WHERE id=:id";

    @Override
    public void update(Tag tag) {
        parameterSource.addValue("id", tag.getId())
                .addValue("name", tag.getName());
        jdbcTemplate.update(UPDATE_TAG, parameterSource);
    }

    private static final String DELETE_TAG = "delete from tag where id=:id";

    @Override
    public void delete(UUID id) {
        parameterSource.addValue("id", id);
        jdbcTemplate.update(DELETE_TAG, parameterSource);
    }

    private static final String DELETE_GIFT_CERTIFICATE_TAG = "delete from gift_certificate_tag where tag_id=:id";

    @Override
    public void deleteConnection(UUID id) {
        parameterSource.addValue("id", id);
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATE_TAG, parameterSource);
    }

    private static final String GET_TAG = "select * from tag where id=:id";

    @Override
    public Tag get(UUID id) {
//        String QUERY_GET_TAG = "select * from tag where id='" + id + "'";
        parameterSource.addValue("id", id);
        return jdbcTemplate.query(GET_TAG, parameterSource, new ResultSetExtractor<Tag>() {
            @Override
            public Tag extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(UUID.fromString(rs.getString("id")));
                    tag.setName(rs.getString("name"));
                    return tag;
                }
                return null;
            }
        });

        /*return tag;
         *//*try {
            return jdbcTemplate.queryForObject(QUERY_GET_TAG, (rs, rowNum) -> {
                Tag tag = new Tag();
                tag.setId(UUID.fromString(rs.getString("id")));
                tag.setName(rs.getString("name"));
                return tag;
            });
        } catch (Exception e) {
            return null;
        }*/
    }

    @Override
    public List<UUID> getAll() {
        String QUERY_GET_ALL_TAG = "select id from tag";
        return jdbcTemplate.query(QUERY_GET_ALL_TAG, (rs, rowNum) -> UUID.fromString(rs.getString("id")));
    }

    private static final String GET_BY_NAME = "select t.* from tag t where t.name = :name;";

    @Override
    public Tag getByName(String name) {

        parameterSource.addValue("name", name);
        return jdbcTemplate.query(GET_BY_NAME, parameterSource, new ResultSetExtractor<Tag>() {
            @Override
            public Tag extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(UUID.fromString(rs.getString("id")));
                    tag.setName(name);
                }
                return null;
            }
        });


/*
        try {
            return jdbcTemplate.queryForObject(QUERY_GET_TAG_BY_NAME, (rs, rowNum) -> {
                Tag tag = new Tag();
                tag.setId(UUID.fromString(rs.getString("id")));
                tag.setName(rs.getString("name"));
                return tag;
            });
        } catch (Exception e) {
            return null;
        }*/
    }

}
