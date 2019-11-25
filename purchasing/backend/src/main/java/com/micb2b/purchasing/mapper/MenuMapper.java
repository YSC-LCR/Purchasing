package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.Menu;
import com.micb2b.purchasing.dto.MenuDTO;

/**
 * @author jie
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

}
