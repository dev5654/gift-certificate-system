package com.epam.esm.entities;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base Entity
 */

public abstract class BaseEntity {
    protected UUID id;

    public BaseEntity() {
    }

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
