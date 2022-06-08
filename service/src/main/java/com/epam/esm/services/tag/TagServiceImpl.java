package com.epam.esm.services.tag;

import com.epam.esm.DAO.tag.TagDAOImpl;
import com.epam.esm.DTO.TagDTO;
import com.epam.esm.DTO.response.ResponseMessage;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.NotFoundException;
import com.epam.esm.mappers.TagMapper;
import com.epam.esm.validators.TagValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag service
 */

@Service
public class TagServiceImpl implements TagService {

    private final TagDAOImpl tagDAOImpl;
    private final TagValidator tagValidator;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagDAOImpl tagDAOImpl, TagValidator tagValidator, TagMapper tagMapper) {
        this.tagDAOImpl = tagDAOImpl;
        this.tagValidator = tagValidator;
        this.tagMapper = tagMapper;
    }

    @Override
    public ResponseDTO create(TagDTO tagDTO) {
        tagValidator.validate(tagDTO);

        Tag tag = tagMapper.fromDTOToEntity(tagDTO);
        tag.setId(UUID.randomUUID());

        UUID tagID = tagDAOImpl.create(tag);

        return new ResponseDTO(ResponseMessage.CREATED.getValues(), tagID);
    }

    @Override
    @Transactional
    public ResponseDTO delete(UUID id) {
        if (tagDAOImpl.get(id) == null)
            throw new NotFoundException("Tag not found id=" + id);
        tagDAOImpl.deleteConnection(id);
        tagDAOImpl.delete(id);

        return new ResponseDTO(ResponseMessage.DELETED.getValues());
    }

    @Override
    public ResponseDTO get(UUID id) {
        Tag tag=tagDAOImpl.get(id);
        if (tag == null || tag.getId()==null )
            throw new NotFoundException("Tag not found id=" + id);
        return new ResponseDTO(tagDAOImpl.get(id));
    }

    @Override
    public ResponseDTO getAll() {
        List<UUID> tagIDList = tagDAOImpl.getAll();
        List<Tag> tagList = new ArrayList<>();

        for (UUID tagID : tagIDList)
            tagList.add((Tag) get(tagID).getData());

        return new ResponseDTO(tagList);
    }

}
