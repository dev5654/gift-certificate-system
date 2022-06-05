package com.epam.esm.DAO;

import com.epam.esm.DAO.tag.TagDAOImpl;
import com.epam.esm.configs.BeanConfig;
import com.epam.esm.entities.Tag;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfig.class)
class TagDAOImplTest {

    @Autowired
    private TagDAOImpl tagDAOImpl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Tag tag;

    @BeforeAll
    static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8083").start();
    }

    @BeforeEach
    void setUp() {
        tag = new Tag(
                UUID.randomUUID(),
                "TestTag"
        );
    }

    @Test
    void create() {
        createTables();
        UUID tagUUID = tagDAOImpl.create(tag);
        assertEquals(tag.getId(), tagUUID);
    }

    @Test
    void update() {
        UUID id = tagDAOImpl.create(tag);
        Tag tagUpdate = new Tag(
                id,
                "TestTagUpdate"
        );
        tagDAOImpl.update(tagUpdate);
        Tag updatedTag = tagDAOImpl.get(id);
        assertEquals("TestTagUpdate", updatedTag.getName());
    }

    @Test
    void delete() {
        tagDAOImpl.create(tag);
        tagDAOImpl.deleteConnection(tag.getId());
        tagDAOImpl.delete(tag.getId());
        Tag tagGet = tagDAOImpl.get(tag.getId());
        assertNull(tagGet);
    }

    @Test
    void get() {
        tagDAOImpl.create(tag);
        Tag tagGet = tagDAOImpl.get(this.tag.getId());
        assertNotNull(tagGet);
    }

    @Test
    void getAll() {
        List<UUID> tagList = tagDAOImpl.getAll();
        assertEquals(1, tagList.size());
    }

    @Test
    void getByName() {
        tagDAOImpl.create(tag);
        Tag tagByName = tagDAOImpl.getByName("TestTagMock");
        assertNull(tagByName);
    }

    void createTables() {
        String query = """
                drop table if exists gift_certificate_tag;
                drop table if exists gift_certificate;
                drop table if exists tag;
                create table gift_certificate
                         (
                             id uuid not null primary key,
                             name varchar,
                             description varchar,
                             price double precision,
                             duration integer,
                             create_date timestamp,
                             last_update_date timestamp
                         );
                create table tag
                         (
                             id uuid not null,
                             name character varying,
                             primary key (id)
                         );
                create table gift_certificate_tag
                         (
                             gift_certificate_id uuid,
                             tag_id uuid,
                             constraint fk_gift_certificate_id foreign key (gift_certificate_id)
                                 references gift_certificate (id),
                             constraint fk_tag_id foreign key (tag_id)
                                 references tag (id)
                         );
                """;
        jdbcTemplate.update(query);
    }

}