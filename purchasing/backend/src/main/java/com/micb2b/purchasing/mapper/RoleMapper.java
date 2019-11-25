package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.Role;
import com.micb2b.purchasing.dto.RoleDTO;

/**
 * @author jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {PermissionMapper.class, MenuMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
