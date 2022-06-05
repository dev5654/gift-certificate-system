package com.epam.esm.DAO.tag;

import com.epam.esm.entities.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag DAO
 */

@Repository
public class TagDAOImpl implements TagDAO {

    private final JdbcTemplate jdbcTemplate;

    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID create(Tag tag) {
        String QUERY_INSERT_TAG = """
                insert into tag(id, name)
                            values(?, ?);""";
        jdbcTemplate.update(QUERY_INSERT_TAG,
                tag.getId(),
                tag.getName());
        return tag.getId();
    }

    @Override
    public void update(Tag tag) {
        String QUERY_UPDATE_TAG = """
                update tag set name = ? where id = ?
                """;
        jdbcTemplate.update(QUERY_UPDATE_TAG,
                tag.getName(),
                tag.getId());
    }

    @Override
    public void delete(UUID id) {
        String QUERY_DELETE_TAG = "delete from tag where id='" + id + "'";
        jdbcTemplate.update(QUERY_DELETE_TAG);
    }

    @Override
    public void deleteConnection(UUID id) {
        String QUERY_DELETE = "delete from gift_certificate_tag where tag_id='" + id + "'";
        jdbcTemplate.update(QUERY_DELETE);
    }

    @Override
    public Tag get(UUID id) {
        String QUERY_GET_TAG = "select * from tag where id='" + id + "'";

        try {
            return jdbcTemplate.queryForObject(QUERY_GET_TAG, (rs, rowNum) -> {
                Tag tag = new Tag();
                tag.setId(UUID.fromString(rs.getString("id")));
                tag.setName(rs.getString("name"));
                return tag;
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UUID> getAll() {
        String QUERY_GET_ALL_TAG = "select id from tag";
        return jdbcTemplate.query(QUERY_GET_ALL_TAG, (rs, rowNum) -> UUID.fromString(rs.getString("id")));
    }

    @Override
    public Tag getByName(String name) {
        String QUERY_GET_TAG_BY_NAME = "select * from tag where name='" + name + "'";

        try {
            return jdbcTemplate.queryForObject(QUERY_GET_TAG_BY_NAME, (rs, rowNum) -> {
                Tag tag = new Tag();
                tag.setId(UUID.fromString(rs.getString("id")));
                tag.setName(rs.getString("name"));
                return tag;
            });
        } catch (Exception e) {
            return null;
        }
    }

}
