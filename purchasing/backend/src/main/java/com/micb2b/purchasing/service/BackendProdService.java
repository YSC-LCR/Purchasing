package com.micb2b.purchasing.service;

import java.util.List;
import java.util.Map;

import com.micb2b.purchasing.domain.BackendProd;
import com.micb2b.purchasing.domain.BackendProdImages;

/**
 * @author jay
 * @date 2020-01-19
 */
public interface BackendProdService {
	public BackendProd create(BackendProd prod);
//	public void update(BackendProdCategory category);
	public void delete(Long prodId,String updateBy);
	public Map<String,Object> initProdForm();
	public List<Long> findCategoryInfo(Long subCateId);
	public void  creatProdImages(List<BackendProdImages> images);
}
