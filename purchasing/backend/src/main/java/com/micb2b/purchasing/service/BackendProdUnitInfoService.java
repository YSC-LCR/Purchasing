package com.micb2b.purchasing.service;

import java.util.List;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.dto.BackendProductUnitInfoDTO;

/**
 * @author jay
 * @date 2020-1-11
 */
public interface BackendProdUnitInfoService {
	public List<BackendProductUnitInfoDTO> findAllByUnit(Long unitId);
	public void create(BackendProdUnitInfo subcategory);
	public void update(BackendProdUnitInfo subcategory);
	public void delete(Long unitInfoId);
}
