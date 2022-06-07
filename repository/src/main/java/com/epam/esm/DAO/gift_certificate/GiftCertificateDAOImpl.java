package com.epam.esm.DAO.gift_certificate;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.*;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate DAO
 */

@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   /* private static final String INSERT_CERTIFICATE = "INSERT INTO public.gift_certificate(id, name, description, price, duration, " +
            "create_date, last_update_date) VALUES(:id,:name, :description, :price, :duration, " +
            ":create_date, :last_update_date)";
*/
    @Override
    public UUID create(GiftCertificate giftCertificate) {
        String QUERY_INSERT_CERTIFICATE = """
                insert into gift_certificate(id, name, description, price, duration, create_date, last_update_date)
                            values(?, ?, ?, ?, ?, ?, ?);""";
        jdbcTemplate.update(QUERY_INSERT_CERTIFICATE,
                giftCertificate.getId(),
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate());
        return giftCertificate.getId();
      /*  MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", giftCertificate.getId())
                .addValue("name", giftCertificate.getName())
                .addValue("description", giftCertificate.getDescription())
                .addValue("price", giftCertificate.getPrice())
                .addValue("duration", giftCertificate.getDuration())
                .addValue("create_date", giftCertificate.getCreateDate())
                .addValue("last_update_date", giftCertificate.getLastUpdateDate());
//        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_CERTIFICATE, namedParameters);
        return giftCertificate.getId();*/

    }

    @Override
    public void createConnection(UUID giftCertificateID, UUID tagID) {
        String QUERY_INSERT = """
                insert into gift_certificate_tag(gift_certificate_id, tag_id)
                            values(?, ?);""";
        jdbcTemplate.update(QUERY_INSERT, giftCertificateID, tagID);
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        String QUERY_UPDATE_CERTIFICATE = """
                update gift_certificate set name = ?, description = ?, price = ?, duration = ?, last_update_date = ? where id = ?
                """;
        jdbcTemplate.update(QUERY_UPDATE_CERTIFICATE,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getLastUpdateDate(),
                giftCertificate.getId());
    }

    @Override
    public void delete(UUID id) {
        String QUERY_DELETE_CERTIFICATE = "delete from gift_certificate where id='" + id + "'";
        jdbcTemplate.update(QUERY_DELETE_CERTIFICATE);
    }


    @Override
    public void deleteConnection(UUID id) {
        String QUERY_DELETE = "delete from gift_certificate_tag where gift_certificate_id='" + id + "'";
        jdbcTemplate.update(QUERY_DELETE);
    }


    @Override
    public GiftCertificate get(UUID id) {
        String QUERY_GET_CERTIFICATE = "select gc.id gift_certificate_id, gc.name gift_certificate_name,\n" +
                "       gc.description gift_certificate_description, gc.price gift_certificate_price,\n" +
                "       gc.duration gift_certificate_duration, t.id tag_id, t.name tag_name  from gift_certificate gc\n" +
                "    left join gift_certificate_tag gct on gc.id = gct.gift_certificate_id\n" +
                "    left join tag t on gct.tag_id = t.id where gc.id='" + id + "'";

        try {
            return jdbcTemplate.query(QUERY_GET_CERTIFICATE, (ResultSet rs) -> {
                GiftCertificate giftCertificate = new GiftCertificate();
                List<Tag> tags = new ArrayList<>();
                while (rs.next()) {
                    giftCertificate.setId(UUID.fromString(rs.getString("gift_certificate_id")));
                    giftCertificate.setName(rs.getString("gift_certificate_name"));
                    giftCertificate.setDescription(rs.getString("gift_certificate_description"));
                    giftCertificate.setPrice(rs.getDouble("gift_certificate_price"));
                    giftCertificate.setDuration(rs.getInt("gift_certificate_duration"));
                    if (rs.getString("tag_id") != null &&
                            rs.getString("tag_name") != null) {
                        Tag tag = new Tag();
                        tag.setId(UUID.fromString(rs.getString("tag_id")));
                        tag.setName(rs.getString("tag_name"));
                        tags.add(tag);
                    }
                }
                giftCertificate.setTags(tags);
                return giftCertificate;
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UUID> getAll() {
        String QUERY_GET_ALL_CERTIFICATE = "select id from gift_certificate";
        return jdbcTemplate.query(QUERY_GET_ALL_CERTIFICATE, (rs, rowNum) -> UUID.fromString(rs.getString("id")));
    }

    @Override
    public Set<UUID> getAllWithFilter(String name, String description, String tag, String sortParams) {
        String QUERY_GET_CERTIFICATE_WITH_FILTER = "select gc.id gift_certificate_id" +
                "    from gift_certificate gc\n" +
                "    left join gift_certificate_tag gct on gc.id = gct.gift_certificate_id\n" +
                "    left join tag t on gct.tag_id = t.id ";

        if (name != null)
            QUERY_GET_CERTIFICATE_WITH_FILTER += "where gc.name like '%" + name + "%' ";

        if (description != null)
            QUERY_GET_CERTIFICATE_WITH_FILTER += "where gc.description like '%" + description + "%' ";

        if (tag != null) {
            if (QUERY_GET_CERTIFICATE_WITH_FILTER.contains("where"))
                QUERY_GET_CERTIFICATE_WITH_FILTER += " and t.name='" + tag + "' ";
            else
                QUERY_GET_CERTIFICATE_WITH_FILTER += "where t.name='" + tag + "' ";
        }

        if (sortParams != null) {
            if (sortParams.contains("name/create_date"))
                QUERY_GET_CERTIFICATE_WITH_FILTER += "order by gc.name, gc.create_date";
            else if (sortParams.contains("name/create_date/desc"))
                QUERY_GET_CERTIFICATE_WITH_FILTER += "order by gc.name, gc.create_date desc;";
            else
                QUERY_GET_CERTIFICATE_WITH_FILTER += "order by gc." + sortParams;
        }

        return jdbcTemplate.query(QUERY_GET_CERTIFICATE_WITH_FILTER, (ResultSet rs) -> {
            Set<UUID> giftCertificateIDSet = new LinkedHashSet<>();
            while (rs.next()) {
                giftCertificateIDSet.add(UUID.fromString(rs.getString("gift_certificate_id")));
            }
            return giftCertificateIDSet;
        });
    }

}
