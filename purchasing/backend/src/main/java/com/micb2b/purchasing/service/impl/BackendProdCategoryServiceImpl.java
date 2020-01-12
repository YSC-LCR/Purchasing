package com.micb2b.purchasing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.repository.BackendProdCategoryRepository;
import com.micb2b.purchasing.service.BackendProdCategoryService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdCategoryServiceImpl implements BackendProdCategoryService {

	@Autowired
	private BackendProdCategoryRepository backendProdCategoryRepository;
	
	@Override
	public void create(BackendProdCategory bankCampaign) {
		backendProdCategoryRepository.save(bankCampaign);
		
	}

	@Override
	public void update(BackendProdCategory bankCampaign) {
		backendProdCategoryRepository.save(bankCampaign);
	}

	@Override
	public void delete(Long categoryId,String updateBy) {
		backendProdCategoryRepository.updateDeleteFlagByCategoryId(categoryId,updateBy);
	}
	
}
