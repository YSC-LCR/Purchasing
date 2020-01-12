package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.dto.BackendProdSubCategoryDTO;

/**
 * @author JayYang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BackendProdSubCategoryMapper extends EntityMapper<BackendProdSubCategoryDTO, BackendProdSubCategory>{

}
