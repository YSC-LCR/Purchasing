package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.Permission;
import com.micb2b.purchasing.dto.PermissionDTO;
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
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setAlias( dto.getAlias() );
        permission.setCreateTime( dto.getCreateTime() );
        permission.setId( dto.getId() );
        permission.setName( dto.getName() );
        permission.setPid( dto.getPid() );

        return permission;
    }

    @Override
    public PermissionDTO toDto(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setAlias( entity.getAlias() );
        permissionDTO.setCreateTime( entity.getCreateTime() );
        permissionDTO.setId( entity.getId() );
        permissionDTO.setName( entity.getName() );
        permissionDTO.setPid( entity.getPid() );

        return permissionDTO;
    }

    @Override
    public List<Permission> toEntity(List<PermissionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Permission> list = new ArrayList<Permission>();
        for ( PermissionDTO permissionDTO : dtoList ) {
            list.add( toEntity( permissionDTO ) );
        }

        return list;
    }

    @Override
    public List<PermissionDTO> toDto(List<Permission> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PermissionDTO> list = new ArrayList<PermissionDTO>();
        for ( Permission permission : entityList ) {
            list.add( toDto( permission ) );
        }

        return list;
    }
}
