package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.dto.BackendProductCategoryDTO;
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
public class BackendProdCategoryMapperImpl implements BackendProdCategoryMapper {

    @Override
    public BackendProdCategory toEntity(BackendProductCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdCategory backendProdCategory = new BackendProdCategory();

        backendProdCategory.setCategoryId( dto.getCategoryId() );
        backendProdCategory.setCategoryName( dto.getCategoryName() );

        return backendProdCategory;
    }

    @Override
    public BackendProductCategoryDTO toDto(BackendProdCategory entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductCategoryDTO backendProductCategoryDTO = new BackendProductCategoryDTO();

        backendProductCategoryDTO.setCategoryId( entity.getCategoryId() );
        backendProductCategoryDTO.setCategoryName( entity.getCategoryName() );

        return backendProductCategoryDTO;
    }

    @Override
    public List<BackendProdCategory> toEntity(List<BackendProductCategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdCategory> list = new ArrayList<BackendProdCategory>();
        for ( BackendProductCategoryDTO backendProductCategoryDTO : dtoList ) {
            list.add( toEntity( backendProductCategoryDTO ) );
        }

        return list;
    }

    @Override
    public List<BackendProductCategoryDTO> toDto(List<BackendProdCategory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BackendProductCategoryDTO> list = new ArrayList<BackendProductCategoryDTO>();
        for ( BackendProdCategory backendProdCategory : entityList ) {
            list.add( toDto( backendProdCategory ) );
        }

        return list;
    }
}
