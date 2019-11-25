package com.micb2b.purchasing.mapper;

import java.util.List;

/**
 * @author jie
 * @date 2018-11-23
 */
public interface EntityMapper<D, E> {

    /**
     * DTO轉Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity轉DTO
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO集合轉Entity集合
     * @param dtoList
     * @return
     */
    List <E> toEntity(List<D> dtoList);

    /**
     * Entity集合轉DTO集合
     * @param entityList
     * @return
     */
    List <D> toDto(List<E> entityList);
}

