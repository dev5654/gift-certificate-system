package com.epam.esm.mappers;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base mapper
 * @param <D> -> DTO
 * @param <E> -> Entity
 */

public interface BaseMapper<E, D> {

    E fromDTOToEntity(D d);

    D fromEntityToDTO(E e);

}
