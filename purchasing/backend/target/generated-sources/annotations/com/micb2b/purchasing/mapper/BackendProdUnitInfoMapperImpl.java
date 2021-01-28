package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.dto.BackendProductUnitInfoDTO;
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
public class BackendProdUnitInfoMapperImpl implements BackendProdUnitInfoMapper {

    @Override
    public BackendProdUnitInfo toEntity(BackendProductUnitInfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdUnitInfo backendProdUnitInfo = new BackendProdUnitInfo();

        backendProdUnitInfo.setUnitInfoId( dto.getUnitInfoId() );
        backendProdUnitInfo.setUnitInfoName( dto.getUnitInfoName() );
        backendProdUnitInfo.setDesc( dto.getDesc() );
        backendProdUnitInfo.setUnitId( dto.getUnitId() );

        return backendProdUnitInfo;
    }

    @Override
    public BackendProductUnitInfoDTO toDto(BackendProdUnitInfo entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductUnitInfoDTO backendProductUnitInfoDTO = new BackendProductUnitInfoDTO();

        backendProductUnitInfoDTO.setUnitInfoId( entity.getUnitInfoId() );
        backendProductUnitInfoDTO.setUnitId( entity.getUnitId() );
        backendProductUnitInfoDTO.setUnitInfoName( entity.getUnitInfoName() );
        backendProductUnitInfoDTO.setDesc( entity.getDesc() );

        return backendProductUnitInfoDTO;
    }

    @Override
    public List<BackendProdUnitInfo> toEntity(List<BackendProductUnitInfoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdUnitInfo> list = new ArrayList<BackendProdUnitInfo>( dtoList.size() );
        for ( BackendProductUnitInfoDTO backendProductUnitInfoDTO : dtoList ) {
            list.add( toEntity( backendProductUnitInfoDTO ) );
        }

        return list;
    }

    @Override
    public List<BackendProductUnitInfoDTO> toDto(List<BackendProdUnitInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BackendProductUnitInfoDTO> list = new ArrayList<BackendProductUnitInfoDTO>( entityList.size() );
        for ( BackendProdUnitInfo backendProdUnitInfo : entityList ) {
            list.add( toDto( backendProdUnitInfo ) );
        }

        return list;
    }
}
