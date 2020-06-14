package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdView;
import com.micb2b.purchasing.dto.BackendProductViewDTO;
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
public class BackendProdViewMapperImpl implements BackendProdViewMapper {

    @Override
    public BackendProdView toEntity(BackendProductViewDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdView backendProdView = new BackendProdView();

        backendProdView.setCategoryName( dto.getCategoryName() );
        backendProdView.setProdDesc( dto.getProdDesc() );
        backendProdView.setProdId( dto.getProdId() );
        backendProdView.setProdName( dto.getProdName() );
        backendProdView.setProdPrice( dto.getProdPrice() );
        backendProdView.setProdSpec( dto.getProdSpec() );
        backendProdView.setSubCategoryName( dto.getSubCategoryName() );
        backendProdView.setVid( dto.getVid() );

        return backendProdView;
    }

    @Override
    public BackendProductViewDTO toDto(BackendProdView entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductViewDTO backendProductViewDTO = new BackendProductViewDTO();

        backendProductViewDTO.setCategoryName( entity.getCategoryName() );
        backendProductViewDTO.setProdDesc( entity.getProdDesc() );
        backendProductViewDTO.setProdId( entity.getProdId() );
        backendProductViewDTO.setProdName( entity.getProdName() );
        backendProductViewDTO.setProdPrice( entity.getProdPrice() );
        backendProductViewDTO.setProdSpec( entity.getProdSpec() );
        backendProductViewDTO.setSubCategoryName( entity.getSubCategoryName() );
        backendProductViewDTO.setVid( entity.getVid() );

        return backendProductViewDTO;
    }

    @Override
    public List<BackendProdView> toEntity(List<BackendProductViewDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdView> list = new ArrayList<BackendProdView>();
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

        List<BackendProductViewDTO> list = new ArrayList<BackendProductViewDTO>();
        for ( BackendProdView backendProdView : entityList ) {
            list.add( toDto( backendProdView ) );
        }

        return list;
    }
}
