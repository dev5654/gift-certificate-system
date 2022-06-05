package com.epam.esm.DAO.gift_certificate;

import com.epam.esm.DAO.BaseDAO;
import com.epam.esm.entities.GiftCertificate;

import java.util.Set;
import java.util.UUID;

public interface GiftCertificateDAO extends BaseDAO<GiftCertificate> {

    Set<UUID> getAllWithFilter(String name, String description, String tag, String sortParams);

    void deleteConnection(UUID id);

    void createConnection(UUID giftCertificateID, UUID tagID);

}
