package com.epam.esm.services.gift_certificate;

import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.services.BaseService;

public interface GiftCertificateService extends BaseService<GiftCertificateDTO> {


    ResponseDTO getWithParams(String name, String description, String tag, String sortParams);

}
