package com.micb2b.purchasing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.domain.BackendProd;
import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.domain.BackendProdImages;
import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.repository.BackendProdCategoryRepository;
import com.micb2b.purchasing.repository.BackendProdImagesRepository;
import com.micb2b.purchasing.repository.BackendProdRepository;
import com.micb2b.purchasing.repository.BackendProdSubCategoryRepository;
import com.micb2b.purchasing.repository.BackendProdUnitRepository;
import com.micb2b.purchasing.service.BackendProdService;

import ch.qos.logback.classic.Logger;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdServiceImpl implements BackendProdService{
	
	@Autowired
	BackendProdCategoryRepository backendProdCategoryRepository;
	@Autowired
	BackendProdSubCategoryRepository backendProdSubCategoryRepository;
	@Autowired
	BackendProdUnitRepository backendProdUnitRepository;
	@Autowired
	BackendProdRepository backendProdRepository;
	@Autowired
	BackendProdImagesRepository backendProdImagesRepository; 
	
	@Override
	public Map<String, Object> initProdForm() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<HashMap> cateViews = new ArrayList<HashMap>();
		List<HashMap> unitViews = new ArrayList<HashMap>();
			
		List<BackendProdCategory> cateInfo = backendProdCategoryRepository.findAll();
		for (BackendProdCategory backendProdCategory : cateInfo) {
			HashMap<String, Object> cateView = new HashMap<String, Object>();
			List<HashMap<String, Object>> subCateViews = new ArrayList<HashMap<String, Object>>();
			cateView.put("label", backendProdCategory.getCategoryName());
			cateView.put("value", backendProdCategory.getCategoryId());
			Set<BackendProdSubCategory> subCateInfos = backendProdCategory.getBackendProdSubCategory();
			for (BackendProdSubCategory subCateInfo : subCateInfos) {
				HashMap<String, Object> subCateView = new HashMap<String, Object>();
				subCateView.put("label", subCateInfo.getSubCategoryName());
				subCateView.put("value", subCateInfo.getSubCategoryId());
				subCateViews.add(subCateView);
			}
			cateView.put("children", subCateViews);
			cateViews.add(cateView);
		}
		
		List<BackendProdUnit> unitInfo = backendProdUnitRepository.findAll();
		for (BackendProdUnit backendProdUnit : unitInfo) {
			HashMap<String, Object> unitView = new HashMap<String, Object>();
			List<HashMap<String, Object>> subUnitViews = new ArrayList<HashMap<String, Object>>();
			unitView.put("label", backendProdUnit.getUnitName());
			Set<BackendProdUnitInfo> subUnits = backendProdUnit.getBackendProdUnitInfo();

			for (BackendProdUnitInfo subUnit : subUnits) {
				HashMap<String, Object> subCateView = new HashMap<String, Object>();
				subCateView.put("label", subUnit.getUnitInfoName());
				subCateView.put("value", subUnit.getUnitInfoId());
				subUnitViews.add(subCateView);
			}
			unitView.put("options", subUnitViews);
			unitViews.add(unitView);
		}

		result.put("cateGoryView", cateViews);
		result.put("unitViews", unitViews);
		return result;
	}

	@Override
	public BackendProd create(BackendProd prod) {
		return backendProdRepository.save(prod);
	}

	@Override
	public void delete(Long prodId, String updateBy) {
		backendProdRepository.updateDeleteFlagByProdId(prodId, updateBy);
	}
	
	@Override
	public List<Long> findCategoryInfo(Long subCateId) {
		List<Long> result = new ArrayList<Long>();
		BackendProdSubCategory subCategory = backendProdSubCategoryRepository.findById(subCateId).get();
		Long cateGoryId = subCategory.getCategoryId();
		result.add(cateGoryId);
		result.add(subCateId);
		return result;
	}

	@Override
	public void creatProdImages(List<BackendProdImages> images) {
		try {
			backendProdImagesRepository.saveAll(images);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
