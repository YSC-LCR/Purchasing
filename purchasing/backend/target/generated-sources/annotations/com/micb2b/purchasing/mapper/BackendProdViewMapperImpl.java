package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdView;
import com.micb2b.purchasing.dto.BackendProductViewDTO;
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
public class BackendProdViewMapperImpl implements BackendProdViewMapper {

    @Override
    public BackendProdView toEntity(BackendProductViewDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdView backendProdView = new BackendProdView();

        backendProdView.setVid( dto.getVid() );
        backendProdView.setProdId( dto.getProdId() );
        backendProdView.setCategoryName( dto.getCategoryName() );
        backendProdView.setSubCategoryName( dto.getSubCategoryName() );
        backendProdView.setProdName( dto.getProdName() );
        backendProdView.setProdSpec( dto.getProdSpec() );
        backendProdView.setProdPrice( dto.getProdPrice() );
        backendProdView.setProdDesc( dto.getProdDesc() );

        return backendProdView;
    }

    @Override
    public BackendProductViewDTO toDto(BackendProdView entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductViewDTO backendProductViewDTO = new BackendProductViewDTO();

        backendProductViewDTO.setVid( entity.getVid() );
        backendProductViewDTO.setProdId( entity.getProdId() );
        backendProductViewDTO.setCategoryName( entity.getCategoryName() );
        backendProductViewDTO.setSubCategoryName( entity.getSubCategoryName() );
        backendProductViewDTO.setProdName( entity.getProdName() );
        backendProductViewDTO.setProdSpec( entity.getProdSpec() );
        backendProductViewDTO.setProdPrice( entity.getProdPrice() );
        backendProductViewDTO.setProdDesc( entity.getProdDesc() );

        return backendProductViewDTO;
    }

    @Override
    public List<BackendProdView> toEntity(List<BackendProductViewDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdView> list = new ArrayList<BackendProdView>( dtoList.size() );
        for ( BackendProductViewDTO backendProductViewDTO : dtoList ) {
            list.add( toEntity( backendProductViewDTO ) );
        }

        return list;
    }

    @Override
    public List<BackendProductViewDTO> toDto(List<BackendProdView> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BackendProductViewDTO> list = new ArrayList<BackendProductViewDTO>( entityList.size() );
        for ( BackendProdView backendProdView : entityList ) {
            list.add( toDto( backendProdView ) );
        }

        return list;
    }
}
