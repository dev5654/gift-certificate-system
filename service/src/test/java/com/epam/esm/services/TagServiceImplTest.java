package com.epam.esm.services;

import com.epam.esm.DAO.tag.TagDAOImpl;
import com.epam.esm.DTO.TagDTO;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.entities.Tag;
import com.epam.esm.mappers.TagMapper;
import com.epam.esm.services.tag.TagServiceImpl;
import com.epam.esm.validators.TagValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.epam.esm.DTO.response.ResponseMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag service test
 */

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagDAOImpl tagDAOImpl;
    @Mock
    private TagValidator tagValidator;
    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagServiceImpl tagServiceImpl;

    private Tag tag;
    private TagDTO tagDTO;

    @BeforeEach
    void setUp() {
        tag = new Tag(UUID.randomUUID(), "TestTag");
        tagDTO = new TagDTO("TestTag");
    }

    @Test
    void create() {
        doNothing().when(tagValidator).validate(tagDTO);
        ResponseDTO responseDTO = tagServiceImpl.create(tagDTO);
        assertEquals(CREATED, responseDTO.getMessage());
    }

    @Test
    void update() {
        doNothing().when(tagValidator).validate(tagDTO);
        when(tagDAOImpl.get(tag.getId())).thenReturn(tag);
        doNothing().when(tagDAOImpl).update(tag);
        ResponseDTO responseDTO = tagServiceImpl.update(tag.getId(), tagDTO);
        assertEquals(UPDATED, responseDTO.getMessage());
    }

    @Test
    void delete() {
        when(tagDAOImpl.get(tag.getId())).thenReturn(tag);
        doNothing().when(tagDAOImpl).deleteConnection(tag.getId());
        doNothing().when(tagDAOImpl).delete(tag.getId());
        ResponseDTO responseDTO = tagServiceImpl.delete(tag.getId());
        assertEquals(DELETED, responseDTO.getMessage());
    }

    @Test
    void get() {
        when(tagDAOImpl.get(tag.getId())).thenReturn(tag);
        ResponseDTO responseDTO = tagServiceImpl.get(tag.getId());
        assertNotNull(responseDTO.getData());
    }

    @Test
    void getAll() {
        ResponseDTO responseDTO = tagServiceImpl.getAll();
        assertNotNull(responseDTO.getData());
    }
}