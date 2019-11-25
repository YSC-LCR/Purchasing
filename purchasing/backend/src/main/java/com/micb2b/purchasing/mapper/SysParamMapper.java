package com.micb2b.purchasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.dto.SysParamDTO;

/**
 * @author M.K. Huang
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysParamMapper extends EntityMapper<SysParamDTO, SysParam> {

}
