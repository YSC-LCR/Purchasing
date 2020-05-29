package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.BackendProdView;
import com.micb2b.purchasing.dto.BackendProductViewDTO;

/**
 * @author JayYang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BackendProdViewMapper extends EntityMapper<BackendProductViewDTO, BackendProdView>{

}
