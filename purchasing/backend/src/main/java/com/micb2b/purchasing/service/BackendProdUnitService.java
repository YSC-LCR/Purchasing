package com.micb2b.purchasing.service;

import com.micb2b.purchasing.domain.BackendProdUnit;

/**
 * @author jay
 * @date 2020-1-11
 */
public interface BackendProdUnitService {
	public void create(BackendProdUnit category);
	public void update(BackendProdUnit category);
	public void delete(Long unitId,String updateBy);
}
