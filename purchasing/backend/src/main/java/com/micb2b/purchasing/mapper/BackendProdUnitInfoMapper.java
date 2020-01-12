package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.dto.BackendProductUnitInfoDTO;

/**
 * @author JayYang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BackendProdUnitInfoMapper extends EntityMapper<BackendProductUnitInfoDTO, BackendProdUnitInfo>{

}
