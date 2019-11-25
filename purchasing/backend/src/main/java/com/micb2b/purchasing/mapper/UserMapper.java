package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.User;
import com.micb2b.purchasing.dto.UserDTO;

/**
 * @author jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
