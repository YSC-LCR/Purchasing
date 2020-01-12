package com.micb2b.purchasing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.repository.BackendProdUnitRepository;
import com.micb2b.purchasing.service.BackendProdUnitService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdUnitServiceImpl implements BackendProdUnitService {

	@Autowired
	private BackendProdUnitRepository backendProdUnitRepository;
	
	@Override
	public void create(BackendProdUnit backendProdUnit) {
		backendProdUnitRepository.save(backendProdUnit);
		
	}

	@Override
	public void update(BackendProdUnit backendProdUnit) {
		backendProdUnitRepository.save(backendProdUnit);
	}

	@Override
	public void delete(Long unitId,String updateBy) {
		backendProdUnitRepository.updateDeleteFlagByUnitId(unitId, updateBy);
	}
	
}
