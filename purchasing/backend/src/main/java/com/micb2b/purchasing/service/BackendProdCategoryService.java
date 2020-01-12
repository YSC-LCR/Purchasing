package com.micb2b.purchasing.service;

import com.micb2b.purchasing.domain.BackendProdCategory;

/**
 * @author jay
 * @date 2018-12-17
 */
public interface BackendProdCategoryService {
	public void create(BackendProdCategory category);
	public void update(BackendProdCategory category);
	public void delete(Long categoryId,String updateBy);
}
