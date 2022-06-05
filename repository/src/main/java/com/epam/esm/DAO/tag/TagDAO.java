package com.epam.esm.DAO.tag;

import com.epam.esm.DAO.BaseDAO;
import com.epam.esm.entities.Tag;

import java.util.UUID;

public interface TagDAO extends BaseDAO<Tag> {

    Tag getByName(String name);

    void deleteConnection(UUID id);

}
