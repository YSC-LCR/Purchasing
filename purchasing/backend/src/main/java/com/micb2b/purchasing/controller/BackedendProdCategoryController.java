package com.micb2b.purchasing.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.domain.BackendProdSubCategory;
import com.micb2b.purchasing.domain.ResponseCode;
import com.micb2b.purchasing.domain.ResponseModel;
import com.micb2b.purchasing.repository.BackendProdCategoryRepository;
import com.micb2b.purchasing.repository.BackendProdSubCategoryRepository;
import com.micb2b.purchasing.service.BackendProdCategoryService;
import com.micb2b.purchasing.service.BackendProdSubCategoryService;
import com.micb2b.purchasing.service.query.BackendProdQueryService;
import com.micb2b.purchasing.utils.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayyang
 * @date 2019-12-01
 */
@Slf4j
@RestController
@RequestMapping("homePage")
public class BackedendProdCategoryController {

	@Autowired
	private BackendProdQueryService prodQueryService;

	@Autowired
	private BackendProdSubCategoryService prodSubCategoryService;

	@Autowired
	private BackendProdCategoryService prodCategoryService;

	@Autowired
	private BackendProdCategoryRepository backendProdCategoryRepository;
	
	@Autowired
	private BackendProdSubCategoryRepository backendProdSubCategoryRepository;

	/**
	 * 返回商品主類列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodCategory/queryAll")
	public ResponseEntity queryAll(@RequestParam(required = false) String columnValue, Pageable pageable) {

		ResponseEntity responseEntity = new ResponseEntity(prodQueryService.queryAll(columnValue, pageable),
				HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 返回商品子類列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodSubCategory/querySubAll")
	public ResponseEntity querySubAll(@RequestParam(value = "categoryId") Long categoryId) {
		ResponseEntity responseEntity = new ResponseEntity(prodSubCategoryService.findAllByCategory(categoryId),
				HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(value = "/prod/prodCategory/create")
	public ResponseModel create(@RequestParam(value = "categoryName") String categoryName) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		if (categoryName.isEmpty()) {
			return new ResponseModel(ResponseCode.REQUEST_PARAM_ERROR);
		}
		BackendProdCategory resources = new BackendProdCategory();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();

		resources.setCategoryName(categoryName);
		resources.setCreateBy(userDetails.getUsername());
		resources.setDeleteFlag("N");
		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
		prodCategoryService.create(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}

	@PostMapping(value = "/prod/prodCategory/update")
	public ResponseModel update(@RequestParam(value = "categoryId") String categoryId,
			@RequestParam(value = "categoryName") String categoryName) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		if (categoryId.isEmpty() || categoryId.isEmpty()) {
			return new ResponseModel(ResponseCode.REQUEST_PARAM_ERROR);
		}
		BackendProdCategory resources = backendProdCategoryRepository.findById(Long.valueOf(categoryId)).get();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		resources.setCategoryName(categoryName);
		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		resources.setUpdateBy(userDetails.getUsername());
		prodCategoryService.update(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}

	@DeleteMapping(value = "/prod/prodCategory/delete/{categoryId}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity delete(@PathVariable Long categoryId) {
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		prodCategoryService.delete(categoryId,userDetails.getUsername());
		return new ResponseEntity(HttpStatus.OK);
	}


	@PostMapping(value = "/prod/prodSubCategory/create")
	public ResponseModel subCreate(
			@RequestParam(value = "subCategoryName") String subCategoryName,
			@RequestParam(value = "desc") String desc,
			@RequestParam(value = "categoryId") Long categoryId)
			throws Exception {
		ResponseModel responseModel = new ResponseModel();
		
		BackendProdSubCategory resources = new BackendProdSubCategory();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();

		resources.setDesc(desc);
		resources.setSubCategoryName(subCategoryName);
		resources.setCreateBy(userDetails.getUsername());
		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
		resources.setDeleteFlag("N");
		resources.setCategoryId(categoryId);
		prodSubCategoryService.create(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}
	
	@PostMapping(value = "/prod/prodSubCategory/update")
	public ResponseModel subUpdate(
			@RequestParam(value = "subCategoryId") String subCategoryId,
			@RequestParam(value = "subCategoryName") String subCategoryName,
			@RequestParam(value = "desc") String desc) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		
		BackendProdSubCategory resources = backendProdSubCategoryRepository.findById(Long.valueOf(subCategoryId)).get();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		resources.setSubCategoryName(subCategoryName);
		resources.setDesc(desc);
		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		resources.setUpdateBy(userDetails.getUsername());
		prodSubCategoryService.update(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}
	
	@DeleteMapping(value = "/prod/prodSubCategory/delete/{subCategoryId}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity deleteSub(@PathVariable Long subCategoryId) {
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		prodSubCategoryService.delete(subCategoryId,userDetails.getUsername());
		return new ResponseEntity(HttpStatus.OK);
	}
}
