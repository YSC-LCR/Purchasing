package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.dto.BackendProductDTO;

/**
 * @author JayYang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BackendProdCategoryMapper extends EntityMapper<BackendProductDTO, BackendProdCategory>{

}
