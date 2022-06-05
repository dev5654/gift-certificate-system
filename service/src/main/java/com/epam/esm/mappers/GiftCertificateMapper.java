package com.epam.esm.mappers;

import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.entities.GiftCertificate;
import org.springframework.stereotype.Component;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate mapper
 */

@Component
public class GiftCertificateMapper implements BaseMapper<GiftCertificate, GiftCertificateDTO> {

    @Override
    public GiftCertificate fromDTOToEntity(GiftCertificateDTO giftCertificateDTO) {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(giftCertificateDTO.getName());
        if(giftCertificateDTO.getDescription() != null)
            giftCertificate.setDescription(giftCertificateDTO.getDescription());
        if(giftCertificateDTO.getPrice() != null)
            giftCertificate.setPrice(giftCertificateDTO.getPrice());
        if(giftCertificateDTO.getDuration() != null)
            giftCertificate.setDuration(giftCertificateDTO.getDuration());
        return giftCertificate;
    }

    @Override
    public GiftCertificateDTO fromEntityToDTO(GiftCertificate giftCertificate) {
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setName(giftCertificate.getName());
        if(giftCertificate.getDescription() != null)
            giftCertificateDTO.setDescription(giftCertificate.getDescription());
        if(giftCertificate.getPrice() != null)
            giftCertificateDTO.setPrice(giftCertificate.getPrice());
        if(giftCertificate.getDuration() != null)
            giftCertificateDTO.setDuration(giftCertificate.getDuration());
        return giftCertificateDTO;
    }
}
