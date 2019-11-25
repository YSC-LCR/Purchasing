package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.dto.SysParamDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-24T10:41:18+0800",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class SysParamMapperImpl implements SysParamMapper {

    @Override
    public SysParam toEntity(SysParamDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysParam sysParam = new SysParam();

        sysParam.setCreateBy( dto.getCreateBy() );
        sysParam.setCreateDate( dto.getCreateDate() );
        sysParam.setParamDesc( dto.getParamDesc() );
        sysParam.setParamId( dto.getParamId() );
        sysParam.setParamName( dto.getParamName() );
        sysParam.setParamType( dto.getParamType() );
        sysParam.setParamValue( dto.getParamValue() );
        sysParam.setUpdateBy( dto.getUpdateBy() );
        sysParam.setUpdateDate( dto.getUpdateDate() );
        sysParam.setValueSize( dto.getValueSize() );
        sysParam.setValueType( dto.getValueType() );

        return sysParam;
    }

    @Override
    public SysParamDTO toDto(SysParam entity) {
        if ( entity == null ) {
            return null;
        }

        SysParamDTO sysParamDTO = new SysParamDTO();

        sysParamDTO.setCreateBy( entity.getCreateBy() );
        sysParamDTO.setCreateDate( entity.getCreateDate() );
        sysParamDTO.setParamDesc( entity.getParamDesc() );
        sysParamDTO.setParamId( entity.getParamId() );
        sysParamDTO.setParamName( entity.getParamName() );
        sysParamDTO.setParamType( entity.getParamType() );
        sysParamDTO.setParamValue( entity.getParamValue() );
        sysParamDTO.setUpdateBy( entity.getUpdateBy() );
        sysParamDTO.setUpdateDate( entity.getUpdateDate() );
        sysParamDTO.setValueSize( entity.getValueSize() );
        sysParamDTO.setValueType( entity.getValueType() );

        return sysParamDTO;
    }

    @Override
    public List<SysParam> toEntity(List<SysParamDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysParam> list = new ArrayList<SysParam>();
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

        List<SysParamDTO> list = new ArrayList<SysParamDTO>();
        for ( SysParam sysParam : entityList ) {
            list.add( toDto( sysParam ) );
        }

        return list;
    }
}
