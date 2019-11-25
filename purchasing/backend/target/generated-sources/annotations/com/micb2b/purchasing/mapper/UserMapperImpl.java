package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.Role;
import com.micb2b.purchasing.domain.User;
import com.micb2b.purchasing.dto.RoleDTO;
import com.micb2b.purchasing.dto.UserDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-24T10:41:18+0800",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setAvatar( dto.getAvatar() );
        user.setCreateTime( dto.getCreateTime() );
        user.setEmail( dto.getEmail() );
        user.setEnabled( dto.getEnabled() );
        user.setId( dto.getId() );
        user.setLastPasswordResetTime( dto.getLastPasswordResetTime() );
        user.setPassword( dto.getPassword() );
        Set<Role> set = roleDTOSetToRoleSet( dto.getRoles() );
        if ( set != null ) {
            user.setRoles( set );
        }
        user.setUsername( dto.getUsername() );

        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAvatar( entity.getAvatar() );
        userDTO.setCreateTime( entity.getCreateTime() );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setEnabled( entity.getEnabled() );
        userDTO.setId( entity.getId() );
        userDTO.setLastPasswordResetTime( entity.getLastPasswordResetTime() );
        userDTO.setPassword( entity.getPassword() );
        Set<RoleDTO> set = roleSetToRoleDTOSet( entity.getRoles() );
        if ( set != null ) {
            userDTO.setRoles( set );
        }
        userDTO.setUsername( entity.getUsername() );

        return userDTO;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>();
        for ( UserDTO userDTO : dtoList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>();
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set_ = new HashSet<Role>();
        for ( RoleDTO roleDTO : set ) {
            set_.add( roleMapper.toEntity( roleDTO ) );
        }

        return set_;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set_ = new HashSet<RoleDTO>();
        for ( Role role : set ) {
            set_.add( roleMapper.toDto( role ) );
        }

        return set_;
    }
}
