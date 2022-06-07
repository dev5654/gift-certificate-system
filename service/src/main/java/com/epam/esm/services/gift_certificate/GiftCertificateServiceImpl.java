package com.epam.esm.services.gift_certificate;

import com.epam.esm.DAO.gift_certificate.GiftCertificateDAOImpl;
import com.epam.esm.DAO.tag.TagDAOImpl;
import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.DTO.TagDTO;
import com.epam.esm.DTO.response.ResponseMessage;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.NotFoundException;
import com.epam.esm.mappers.GiftCertificateMapper;
import com.epam.esm.mappers.TagMapper;
import com.epam.esm.validators.GiftCertificateValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.RescaleOp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate service
 */

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateValidator giftCertificateValidator;
    private final GiftCertificateMapper giftCertificateMapper;
    private final TagMapper tagMapper;
    private final GiftCertificateDAOImpl giftCertificateDAOImpl;
    private final TagDAOImpl tagDAOImpl;

    public GiftCertificateServiceImpl(GiftCertificateValidator giftCertificateValidator, GiftCertificateMapper giftCertificateMapper, TagMapper tagMapper, GiftCertificateDAOImpl giftCertificateDAOImpl, TagDAOImpl tagDAOImpl) {
        this.giftCertificateValidator = giftCertificateValidator;
        this.giftCertificateMapper = giftCertificateMapper;
        this.tagMapper = tagMapper;
        this.giftCertificateDAOImpl = giftCertificateDAOImpl;
        this.tagDAOImpl = tagDAOImpl;
    }

    @Override
    public ResponseDTO create(GiftCertificateDTO giftCertificateDTO) {
        giftCertificateValidator.validate(giftCertificateDTO);

        GiftCertificate giftCertificate = giftCertificateMapper.fromDTOToEntity(giftCertificateDTO);
        giftCertificate.setId(UUID.randomUUID());
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());

        UUID giftCertificateID = giftCertificateDAOImpl.create(giftCertificate);

        if (!giftCertificateDTO.getTags().isEmpty())
            createRelationBetweenGiftCertificateAndTag(giftCertificateID, giftCertificateDTO.getTags());

        return new ResponseDTO(ResponseMessage.CREATED.getValues(), giftCertificateID);
    }

    @Override
    @Transactional
    public ResponseDTO update(UUID id, GiftCertificateDTO giftCertificateDTO) {
        GiftCertificate giftCertificate = giftCertificateDAOImpl.get(id);
        if(giftCertificate == null || giftCertificate.getId() == null)
            throw new NotFoundException("Gift Certificate not found id=" + id);
        giftCertificateValidator.validate(giftCertificateDTO);

        changeGiftCertificateFieldsFromDTOToEntity(giftCertificate, giftCertificateDTO);
        giftCertificate.setLastUpdateDate(LocalDateTime.now());

        giftCertificateDAOImpl.update(giftCertificate);

        if (giftCertificateDTO.getTags() != null) {
            giftCertificateDAOImpl.deleteConnection(giftCertificate.getId());
            createRelationBetweenGiftCertificateAndTag(giftCertificate.getId(), giftCertificateDTO.getTags());
        }

        return new ResponseDTO(ResponseMessage.UPDATED.getValues());
    }

    @Override
    @Transactional
    public ResponseDTO delete(UUID id) {
        GiftCertificate giftCertificate = giftCertificateDAOImpl.get(id);
        if(giftCertificate == null || giftCertificate.getId() == null)
            throw new NotFoundException("Gift Certificate not found id=" + id);
        giftCertificateDAOImpl.deleteConnection(id);
        giftCertificateDAOImpl.delete(id);

        return new ResponseDTO(ResponseMessage.DELETED.getValues());
    }

    @Override
    public ResponseDTO get(UUID id) {
        GiftCertificate giftCertificate = giftCertificateDAOImpl.get(id);
        if(giftCertificate == null || giftCertificate.getId() == null)
            throw new NotFoundException("Gift certificate not found id =" + id);
        return new ResponseDTO(giftCertificate);
    }

    @Override
    public ResponseDTO getAll() {
        List<UUID> giftCertificateIDList = giftCertificateDAOImpl.getAll();
        List<GiftCertificate> giftCertificateList = new ArrayList<>();

        for(UUID id: giftCertificateIDList)
            giftCertificateList.add(giftCertificateDAOImpl.get(id));

        return new ResponseDTO(giftCertificateList);
    }

    @Override
    public ResponseDTO getWithParams(String name, String description, String tag, String sortParams) {
        Set<UUID> giftCertificateIDSet = giftCertificateDAOImpl.getAllWithFilter(name, description, tag, sortParams);
        List<GiftCertificate> giftCertificateList = new ArrayList<>();

        for(UUID id: giftCertificateIDSet)
            giftCertificateList.add(giftCertificateDAOImpl.get(id));

        return new ResponseDTO(giftCertificateList);

    }

    private void changeGiftCertificateFieldsFromDTOToEntity(GiftCertificate giftCertificate, GiftCertificateDTO giftCertificateDTO) {
        giftCertificate.setName(giftCertificateDTO.getName() != null ?
                giftCertificateDTO.getName() : giftCertificate.getName());
        giftCertificate.setDescription(giftCertificateDTO.getDescription() != null ?
                giftCertificateDTO.getDescription() : giftCertificate.getDescription());
        giftCertificate.setPrice(giftCertificateDTO.getPrice() != null ?
                giftCertificateDTO.getPrice() : giftCertificate.getPrice());
        giftCertificate.setDuration(giftCertificateDTO.getDuration() != null ?
                giftCertificateDTO.getDuration() : giftCertificate.getDuration());
    }

    private void createRelationBetweenGiftCertificateAndTag(UUID giftCertificateID, List<TagDTO> tags) {
        for (TagDTO tagDTO : tags) {
            Tag tag = tagDAOImpl.getByName(tagDTO.getName());
            UUID tagID = tag == null ? tagDAOImpl.create(tagMapper.fromDTOToEntity(tagDTO)) : tag.getId();
            giftCertificateDAOImpl.createConnection(giftCertificateID, tagID);
        }
    }

}
