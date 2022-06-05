package com.epam.esm.mappers;

import com.epam.esm.DTO.TagDTO;
import com.epam.esm.entities.Tag;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag mapper
 */

@Component
public class TagMapper implements BaseMapper<Tag, TagDTO> {

    @Override
    public Tag fromDTOToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(UUID.randomUUID());
        tag.setName(tagDTO.getName());
        return tag;
    }

    @Override
    public TagDTO fromEntityToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(tag.getName());
        return tagDTO;
    }

}
