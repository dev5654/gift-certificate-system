package com.epam.esm.DAO;

import com.epam.esm.DAO.gift_certificate.GiftCertificateDAOImpl;
import com.epam.esm.configs.BeanConfig;
import com.epam.esm.entities.GiftCertificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate DAO test
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfig.class)
class GiftCertificateDAOImplTest {

    @Autowired
    private GiftCertificateDAOImpl giftCertificateDAOImpl;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private GiftCertificate giftCertificate;

    @BeforeEach
    void setUp() {
        giftCertificate = new GiftCertificate(
                UUID.randomUUID(),
                "TestGiftCertificate",
                "Test description",
                10.89,
                2,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    @Test
    void create() {
        createTables();
        UUID giftCertificateUUID = giftCertificateDAOImpl.create(giftCertificate);
        assertEquals(giftCertificate.getId(), giftCertificateUUID);
    }

    @Test
    void update() {
        UUID id = giftCertificateDAOImpl.create(giftCertificate);
        GiftCertificate giftCertificateUpdate = new GiftCertificate(
                id,
                "TestGiftCertificateUpdate",
                "Test update description",
                11.89,
                3,
                null,
                LocalDateTime.now(),
                null
        );
        giftCertificateDAOImpl.update(giftCertificateUpdate);
        GiftCertificate updatedGiftCertificate = giftCertificateDAOImpl.get(id);
        assertEquals("TestGiftCertificateUpdate", updatedGiftCertificate.getName());
    }

    @Test
    void delete() {
        giftCertificateDAOImpl.create(giftCertificate);
        giftCertificateDAOImpl.deleteConnection(giftCertificate.getId());
        giftCertificateDAOImpl.delete(giftCertificate.getId());
        GiftCertificate giftCertificateGet = giftCertificateDAOImpl.get(giftCertificate.getId());
        assertNull(giftCertificateGet.getId());
    }

    @Test
    void get() {
        giftCertificateDAOImpl.create(giftCertificate);
        GiftCertificate giftCertificateGet = giftCertificateDAOImpl.get(giftCertificate.getId());
        assertNotNull(giftCertificateGet);
    }

    @Test
    void getAll() {
        List<UUID> giftCertificateList = giftCertificateDAOImpl.getAll();
        assertEquals(1, giftCertificateList.size());
    }

    @Test
    void getAllWithFilter() {
        giftCertificateDAOImpl.create(giftCertificate);
        Set<UUID> giftCertificateSetWithFilter = giftCertificateDAOImpl.getAllWithFilter("Test", null, null, null);
        assertNotNull(giftCertificateSetWithFilter);
    }

    void createTables() {
        String QUERY = """
                                
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
        jdbcTemplate.update(QUERY, new MapSqlParameterSource());
    }

}