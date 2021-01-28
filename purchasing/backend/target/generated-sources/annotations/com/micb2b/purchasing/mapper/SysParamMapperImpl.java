package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.dto.SysParamDTO;
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
public class SysParamMapperImpl implements SysParamMapper {

    @Override
    public SysParam toEntity(SysParamDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysParam sysParam = new SysParam();

        sysParam.setParamId( dto.getParamId() );
        sysParam.setParamName( dto.getParamName() );
        sysParam.setParamDesc( dto.getParamDesc() );
        sysParam.setParamType( dto.getParamType() );
        sysParam.setParamValue( dto.getParamValue() );
        sysParam.setValueType( dto.getValueType() );
        sysParam.setValueSize( dto.getValueSize() );
        sysParam.setCreateBy( dto.getCreateBy() );
        sysParam.setCreateDate( dto.getCreateDate() );
        sysParam.setUpdateBy( dto.getUpdateBy() );
        sysParam.setUpdateDate( dto.getUpdateDate() );

        return sysParam;
    }

    @Override
    public SysParamDTO toDto(SysParam entity) {
        if ( entity == null ) {
            return null;
        }

        SysParamDTO sysParamDTO = new SysParamDTO();

        sysParamDTO.setParamId( entity.getParamId() );
        sysParamDTO.setParamName( entity.getParamName() );
        sysParamDTO.setParamDesc( entity.getParamDesc() );
        sysParamDTO.setParamType( entity.getParamType() );
        sysParamDTO.setParamValue( entity.getParamValue() );
        sysParamDTO.setValueType( entity.getValueType() );
        sysParamDTO.setValueSize( entity.getValueSize() );
        sysParamDTO.setCreateBy( entity.getCreateBy() );
        sysParamDTO.setCreateDate( entity.getCreateDate() );
        sysParamDTO.setUpdateBy( entity.getUpdateBy() );
        sysParamDTO.setUpdateDate( entity.getUpdateDate() );

        return sysParamDTO;
    }

    @Override
    public List<SysParam> toEntity(List<SysParamDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysParam> list = new ArrayList<SysParam>( dtoList.size() );
        for ( SysParamDTO sysParamDTO : dtoList ) {
            list.add( toEntity( sysParamDTO ) );
        }

        return list;
    }

    @Override
    public List<SysParamDTO> toDto(List<SysParam> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SysParamDTO> list = new ArrayList<SysParamDTO>( entityList.size() );
        for ( SysParam sysParam : entityList ) {
            list.add( toDto( sysParam ) );
        }

        return list;
    }
}
