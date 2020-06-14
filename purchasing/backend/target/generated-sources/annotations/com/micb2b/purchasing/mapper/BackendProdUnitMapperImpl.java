package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.dto.BackendProductUnitDTO;
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
public class BackendProdUnitMapperImpl implements BackendProdUnitMapper {

    @Override
    public BackendProdUnit toEntity(BackendProductUnitDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdUnit backendProdUnit = new BackendProdUnit();

        backendProdUnit.setUnitId( dto.getUnitId() );
        backendProdUnit.setUnitName( dto.getUnitName() );

        return backendProdUnit;
    }

    @Override
    public BackendProductUnitDTO toDto(BackendProdUnit entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductUnitDTO backendProductUnitDTO = new BackendProductUnitDTO();

        backendProductUnitDTO.setUnitId( entity.getUnitId() );
        backendProductUnitDTO.setUnitName( entity.getUnitName() );

        return backendProductUnitDTO;
    }

    @Override
    public List<BackendProdUnit> toEntity(List<BackendProductUnitDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdUnit> list = new ArrayList<BackendProdUnit>();
        for ( BackendProductUnitDTO backendProductUnitDTO : dtoList ) {
            list.add( toEntity( backendProductUnitDTO ) );
        }

        return list;
    }

    @Override
    public List<BackendProductUnitDTO> toDto(List<BackendProdUnit> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BackendProductUnitDTO> list = new ArrayList<BackendProductUnitDTO>();
        for ( BackendProdUnit backendProdUnit : entityList ) {
            list.add( toDto( backendProdUnit ) );
        }

        return list;
    }
}
