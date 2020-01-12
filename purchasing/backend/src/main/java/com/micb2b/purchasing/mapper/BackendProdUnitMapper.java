package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.dto.BackendProductUnitDTO;

/**
 * @author JayYang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BackendProdUnitMapper extends EntityMapper<BackendProductUnitDTO, BackendProdUnit>{

}
