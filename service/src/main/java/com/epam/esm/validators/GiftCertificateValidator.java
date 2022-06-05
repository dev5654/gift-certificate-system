package com.epam.esm.validators;

import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.exceptions.NotValidException;
import org.springframework.stereotype.Component;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift Certificate validator
 */

@Component
public class GiftCertificateValidator implements BaseValidator<GiftCertificateDTO> {

    @Override
    public void validate(GiftCertificateDTO giftCertificateDTO) {
        if (giftCertificateDTO.getName() == null)
            throw new NotValidException("Gift certificate name field must not be null");

        if (giftCertificateDTO.getPrice() != null && giftCertificateDTO.getPrice() < 0)
            throw new NotValidException("Price field must not be negative: price = " + giftCertificateDTO.getPrice());

        if (giftCertificateDTO.getDuration() != null && giftCertificateDTO.getDuration() < 0)
            throw new NotValidException("Duration field must not be negative: duration = " + giftCertificateDTO.getDuration());
    }

}
