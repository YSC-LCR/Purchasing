package com.micb2b.purchasing.service;

import java.util.List;

import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.dto.BackendProdSubCategoryDTO;

/**
 * @author jay
 * @date 2020-1-11
 */
public interface BackendProdSubCategoryService {
	public List<BackendProdSubCategoryDTO> findAllByCategory(Long categoryId);
	public void create(BackendProdSubCategory subcategory);
	public void update(BackendProdSubCategory subcategory);
	public void delete(Long subCategoryId,String updateBy);
}
