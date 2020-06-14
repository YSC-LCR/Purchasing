package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.Menu;
import com.micb2b.purchasing.domain.Permission;
import com.micb2b.purchasing.domain.Role;
import com.micb2b.purchasing.dto.MenuDTO;
import com.micb2b.purchasing.dto.PermissionDTO;
import com.micb2b.purchasing.dto.RoleDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-07T14:25:05+0800",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setCreateTime( dto.getCreateTime() );
        role.setId( dto.getId() );
        Set<Menu> set = menuDTOSetToMenuSet( dto.getMenus() );
        if ( set != null ) {
            role.setMenus( set );
        }
        role.setName( dto.getName() );
        Set<Permission> set_ = permissionDTOSetToPermissionSet( dto.getPermissions() );
        if ( set_ != null ) {
            role.setPermissions( set_ );
        }
        role.setRemark( dto.getRemark() );

        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setCreateTime( entity.getCreateTime() );
        roleDTO.setId( entity.getId() );
        Set<MenuDTO> set = menuSetToMenuDTOSet( entity.getMenus() );
        if ( set != null ) {
            roleDTO.setMenus( set );
        }
        roleDTO.setName( entity.getName() );
        Set<PermissionDTO> set_ = permissionSetToPermissionDTOSet( entity.getPermissions() );
        if ( set_ != null ) {
            roleDTO.setPermissions( set_ );
        }
        roleDTO.setRemark( entity.getRemark() );

        return roleDTO;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>();
        for ( RoleDTO roleDTO : dtoList ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>();
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    protected Set<Menu> menuDTOSetToMenuSet(Set<MenuDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Menu> set_ = new HashSet<Menu>();
        for ( MenuDTO menuDTO : set ) {
            set_.add( menuMapper.toEntity( menuDTO ) );
        }

        return set_;
    }

    protected Set<Permission> permissionDTOSetToPermissionSet(Set<PermissionDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Permission> set_ = new HashSet<Permission>();
        for ( PermissionDTO permissionDTO : set ) {
            set_.add( permissionMapper.toEntity( permissionDTO ) );
        }

        return set_;
    }

    protected Set<MenuDTO> menuSetToMenuDTOSet(Set<Menu> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuDTO> set_ = new HashSet<MenuDTO>();
        for ( Menu menu : set ) {
            set_.add( menuMapper.toDto( menu ) );
        }

        return set_;
    }

    protected Set<PermissionDTO> permissionSetToPermissionDTOSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDTO> set_ = new HashSet<PermissionDTO>();
        for ( Permission permission : set ) {
            set_.add( permissionMapper.toDto( permission ) );
        }

        return set_;
    }
}
