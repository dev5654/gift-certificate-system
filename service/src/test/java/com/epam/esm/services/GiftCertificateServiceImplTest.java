package com.epam.esm.services;

import com.epam.esm.DAO.gift_certificate.GiftCertificateDAOImpl;
import com.epam.esm.DAO.tag.TagDAOImpl;
import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.DTO.TagDTO;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.mappers.GiftCertificateMapper;
import com.epam.esm.mappers.TagMapper;
import com.epam.esm.services.gift_certificate.GiftCertificateServiceImpl;
import com.epam.esm.validators.GiftCertificateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.epam.esm.DTO.response.ResponseMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate service test
 */

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @Mock
    private GiftCertificateValidator giftCertificateValidator;
    @Mock
    private GiftCertificateMapper giftCertificateMapper;
    @Mock
    private TagMapper tagMapper;
    @Mock
    private GiftCertificateDAOImpl giftCertificateDAOImpl;
    @Mock
    private TagDAOImpl tagDAOImpl;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateServiceImpl;

    private GiftCertificate giftCertificate;
    private GiftCertificateDTO giftCertificateDTO;

    @BeforeEach
    void setUp() {
        giftCertificate = new GiftCertificate(
                UUID.randomUUID(),
                "TestGiftCertificate",
                "Test Description",
                10.99,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                List.of(new Tag(UUID.randomUUID(), "TestTag"))
        );

        giftCertificateDTO = new GiftCertificateDTO(
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                List.of(new TagDTO("TestTag"))
        );
    }

    @Test
    void create() {
        doNothing().when(giftCertificateValidator).validate(giftCertificateDTO);
        when(giftCertificateMapper.fromDTOToEntity(giftCertificateDTO)).thenReturn(giftCertificate);
        when(giftCertificateDAOImpl.create(giftCertificate)).thenReturn(giftCertificate.getId());
        ResponseDTO responseDTO = giftCertificateServiceImpl.create(giftCertificateDTO);
        assertEquals(CREATED, responseDTO.getMessage());
    }

    @Test
    void update() {
        doNothing().when(giftCertificateValidator).validate(giftCertificateDTO);
        when(giftCertificateDAOImpl.get(giftCertificate.getId())).thenReturn(giftCertificate);
        ResponseDTO responseDTO = giftCertificateServiceImpl.update(giftCertificate.getId(), giftCertificateDTO);
        assertEquals(UPDATED, responseDTO.getMessage());
    }

    @Test
    void delete() {
        when(giftCertificateDAOImpl.get(giftCertificate.getId())).thenReturn(giftCertificate);
        doNothing().when(giftCertificateDAOImpl).deleteConnection(giftCertificate.getId());
        doNothing().when(giftCertificateDAOImpl).delete(giftCertificate.getId());
        ResponseDTO responseDTO = giftCertificateServiceImpl.delete(giftCertificate.getId());
        assertEquals(DELETED, responseDTO.getMessage());
    }

    @Test
    void get() {
        when(giftCertificateDAOImpl.get(giftCertificate.getId())).thenReturn(giftCertificate);
        ResponseDTO responseDTO = giftCertificateServiceImpl.get(giftCertificate.getId());
        assertNotNull(responseDTO.getData());
    }

    @Test
    void getAll() {
        ResponseDTO responseDTO = giftCertificateServiceImpl.getAll();
        assertNotNull(responseDTO.getData());
    }

    @Test
    void getWithParams() {
        ResponseDTO responseDTO = giftCertificateServiceImpl.getWithParams("TestTag", null, null, null);
        assertNotNull(responseDTO.getData());
    }
}