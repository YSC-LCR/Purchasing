package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.dto.BackendProductUnitInfoDTO;
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
public class BackendProdUnitInfoMapperImpl implements BackendProdUnitInfoMapper {

    @Override
    public BackendProdUnitInfo toEntity(BackendProductUnitInfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BackendProdUnitInfo backendProdUnitInfo = new BackendProdUnitInfo();

        backendProdUnitInfo.setDesc( dto.getDesc() );
        backendProdUnitInfo.setUnitId( dto.getUnitId() );
        backendProdUnitInfo.setUnitInfoId( dto.getUnitInfoId() );
        backendProdUnitInfo.setUnitInfoName( dto.getUnitInfoName() );

        return backendProdUnitInfo;
    }

    @Override
    public BackendProductUnitInfoDTO toDto(BackendProdUnitInfo entity) {
        if ( entity == null ) {
            return null;
        }

        BackendProductUnitInfoDTO backendProductUnitInfoDTO = new BackendProductUnitInfoDTO();

        backendProductUnitInfoDTO.setDesc( entity.getDesc() );
        backendProductUnitInfoDTO.setUnitId( entity.getUnitId() );
        backendProductUnitInfoDTO.setUnitInfoId( entity.getUnitInfoId() );
        backendProductUnitInfoDTO.setUnitInfoName( entity.getUnitInfoName() );

        return backendProductUnitInfoDTO;
    }

    @Override
    public List<BackendProdUnitInfo> toEntity(List<BackendProductUnitInfoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BackendProdUnitInfo> list = new ArrayList<BackendProdUnitInfo>();
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

        List<BackendProductUnitInfoDTO> list = new ArrayList<BackendProductUnitInfoDTO>();
        for ( BackendProdUnitInfo backendProdUnitInfo : entityList ) {
            list.add( toDto( backendProdUnitInfo ) );
        }

        return list;
    }
}
