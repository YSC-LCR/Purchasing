package com.micb2b.purchasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.dto.BackendProdSubCategoryDTO;
import com.micb2b.purchasing.mapper.BackendProdSubCategoryMapper;
import com.micb2b.purchasing.repository.BackendProdSubCategoryRepository;
import com.micb2b.purchasing.service.BackendProdSubCategoryService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdSubCategoryServiceImpl implements BackendProdSubCategoryService {

	@Autowired
	private BackendProdSubCategoryRepository backendProdSubCategoryRepository;
	
	@Autowired
	private BackendProdSubCategoryMapper backendProdSubCategoryMapper;

	@Override
	public List<BackendProdSubCategoryDTO> findAllByCategory(Long categoryId) {
		
		return backendProdSubCategoryMapper.toDto(backendProdSubCategoryRepository.findAllByCategory(categoryId));
	}

	@Override
	public void create(BackendProdSubCategory subcategory) {
		backendProdSubCategoryRepository.save(subcategory);
	}

	@Override
	public void update(BackendProdSubCategory subcategory) {
		backendProdSubCategoryRepository.save(subcategory);
	}

	@Override
	public void delete(Long subCategoryId,String updateBy) {
		backendProdSubCategoryRepository.updateDeleteFlagBySubCategoryId(subCategoryId,updateBy);
	}
	
	
	
}
