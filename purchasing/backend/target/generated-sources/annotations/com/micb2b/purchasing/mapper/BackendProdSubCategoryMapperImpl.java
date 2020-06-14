package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.dto.BackendProdSubCategoryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-14T23:47:43+0800",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class BackendProdSubCategoryMapperImpl implements BackendProdSubCategoryMapper {

    @Override
    public BackendProdSubCategory toEntity(BackendProdSubCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdSubCategory backendProdSubCategory = new BackendProdSubCategory();

        backendProdSubCategory.setCategoryId( dto.getCategoryId() );
        backendProdSubCategory.setDesc( dto.getDesc() );
        backendProdSubCategory.setSubCategoryId( dto.getSubCategoryId() );
        backendProdSubCategory.setSubCategoryName( dto.getSubCategoryName() );

        return backendProdSubCategory;
    }

    @Override
    public BackendProdSubCategoryDTO toDto(BackendProdSubCategory entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProdSubCategoryDTO backendProdSubCategoryDTO = new BackendProdSubCategoryDTO();

        backendProdSubCategoryDTO.setCategoryId( entity.getCategoryId() );
        backendProdSubCategoryDTO.setDesc( entity.getDesc() );
        backendProdSubCategoryDTO.setSubCategoryId( entity.getSubCategoryId() );
        backendProdSubCategoryDTO.setSubCategoryName( entity.getSubCategoryName() );

        return backendProdSubCategoryDTO;
    }

    @Override
    public List<BackendProdSubCategory> toEntity(List<BackendProdSubCategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdSubCategory> list = new ArrayList<BackendProdSubCategory>();
        for ( BackendProdSubCategoryDTO backendProdSubCategoryDTO : dtoList ) {
            list.add( toEntity( backendProdSubCategoryDTO ) );
        }

        return list;
    }

    @Override
    public List<BackendProdSubCategoryDTO> toDto(List<BackendProdSubCategory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BackendProdSubCategoryDTO> list = new ArrayList<BackendProdSubCategoryDTO>();
        for ( BackendProdSubCategory backendProdSubCategory : entityList ) {
            list.add( toDto( backendProdSubCategory ) );
        }

        return list;
    }
}
