package com.epam.esm.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag entity
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag extends BaseEntity implements Serializable {
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
