package com.epam.esm.DAO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base DAO
 */

public interface BaseDAO<T> {

    UUID create(T t);

    void update(T t);

    void delete(UUID id);

    T get(UUID id);

    List<UUID> getAll();

}
