package com.epam.esm.validators;

import com.epam.esm.DTO.TagDTO;
import com.epam.esm.exceptions.NotValidException;
import org.springframework.stereotype.Component;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag validator
 */

@Component
public class TagValidator implements BaseValidator<TagDTO> {

    @Override
    public void validate(TagDTO tagDTO) {
        if (tagDTO.getName() == null)
            throw new NotValidException("Tag fields must not be null");
    }
}

