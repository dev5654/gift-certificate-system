package com.epam.esm.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag DTO
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO {
    private String name;

    public TagDTO() {
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
