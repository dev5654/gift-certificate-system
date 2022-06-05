package com.epam.esm.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate DTO
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftCertificateDTO {
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private List<TagDTO> tags;

    public GiftCertificateDTO() {
    }

    public GiftCertificateDTO(String name, String description, Double price, Integer duration, List<TagDTO> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }
}
