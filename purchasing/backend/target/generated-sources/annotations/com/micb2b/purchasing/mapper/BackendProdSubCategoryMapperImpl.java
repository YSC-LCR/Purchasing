package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.dto.BackendProdSubCategoryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-11T15:58:43+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class BackendProdSubCategoryMapperImpl implements BackendProdSubCategoryMapper {

    @Override
    public BackendProdSubCategory toEntity(BackendProdSubCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdSubCategory backendProdSubCategory = new BackendProdSubCategory();

        backendProdSubCategory.setSubCategoryId( dto.getSubCategoryId() );
        backendProdSubCategory.setSubCategoryName( dto.getSubCategoryName() );
        backendProdSubCategory.setDesc( dto.getDesc() );
        backendProdSubCategory.setCategoryId( dto.getCategoryId() );

        return backendProdSubCategory;
    }

    @Override
    public BackendProdSubCategoryDTO toDto(BackendProdSubCategory entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProdSubCategoryDTO backendProdSubCategoryDTO = new BackendProdSubCategoryDTO();

        backendProdSubCategoryDTO.setSubCategoryId( entity.getSubCategoryId() );
        backendProdSubCategoryDTO.setCategoryId( entity.getCategoryId() );
        backendProdSubCategoryDTO.setSubCategoryName( entity.getSubCategoryName() );
        backendProdSubCategoryDTO.setDesc( entity.getDesc() );

        return backendProdSubCategoryDTO;
    }

    @Override
    public List<BackendProdSubCategory> toEntity(List<BackendProdSubCategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdSubCategory> list = new ArrayList<BackendProdSubCategory>( dtoList.size() );
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

        List<BackendProdSubCategoryDTO> list = new ArrayList<BackendProdSubCategoryDTO>( entityList.size() );
        for ( BackendProdSubCategory backendProdSubCategory : entityList ) {
            list.add( toDto( backendProdSubCategory ) );
        }

        return list;
    }
}
