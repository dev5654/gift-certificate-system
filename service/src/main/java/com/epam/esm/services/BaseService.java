package com.epam.esm.services;

import com.epam.esm.DTO.response.ResponseDTO;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base service
 */

public interface BaseService<DT> {

    ResponseDTO create(DT dto);

    ResponseDTO delete(UUID id);

    ResponseDTO get(UUID id);

    ResponseDTO getAll();

}
