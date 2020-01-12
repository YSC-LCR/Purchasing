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
import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.domain.ResponseCode;
import com.micb2b.purchasing.domain.ResponseModel;
import com.micb2b.purchasing.repository.BackendProdUnitInfoRepository;
import com.micb2b.purchasing.repository.BackendProdUnitRepository;
import com.micb2b.purchasing.service.BackendProdUnitInfoService;
import com.micb2b.purchasing.service.BackendProdUnitService;
import com.micb2b.purchasing.service.query.BackendProdUnitQueryService;
import com.micb2b.purchasing.utils.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayyang
 * @date 2020-11-11
 */
@Slf4j
@RestController
@RequestMapping("homePage")
public class BackedendProdUnitController {

	@Autowired
	private BackendProdUnitQueryService prodUnitQueryService;
	
	@Autowired
	private BackendProdUnitService prodUnitService;
	
	@Autowired
	private BackendProdUnitInfoService prodUnitInfoService;

	@Autowired
	private BackendProdUnitRepository backendProdUnitRepository;
	
	@Autowired
	private BackendProdUnitInfoRepository backendProdUnitInfoRepository;

	/**
	 * 返回主類列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodUnit/queryAll")
	public ResponseEntity queryAll(@RequestParam(required = false) String columnValue, Pageable pageable) {

		ResponseEntity responseEntity = new ResponseEntity(prodUnitQueryService.queryAll(columnValue, pageable),
				HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 返回子類列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodUnitInfo/queryInfoAll")
	public ResponseEntity querySubAll(@RequestParam(required = false) Long unitId) {

		ResponseEntity responseEntity = new ResponseEntity(prodUnitInfoService.findAllByUnit(unitId),
				HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(value = "/prod/prodUnit/create")
	public ResponseModel create(@RequestParam(value = "unitName") String unitName) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		if (unitName.isEmpty()) {
			return new ResponseModel(ResponseCode.REQUEST_PARAM_ERROR);
		}
		BackendProdUnit resources = new BackendProdUnit();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();

		resources.setUnitName(unitName);
		resources.setCreateBy(userDetails.getUsername());
		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
		resources.setDeleteFlag("N");
		prodUnitService.create(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}

	@PostMapping(value = "/prod/prodUnit/update")
	public ResponseModel update(@RequestParam(value = "unitId") String unitId,
			@RequestParam(value = "unitName") String unitName) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		if (unitId.isEmpty() || unitId.isEmpty()) {
			return new ResponseModel(ResponseCode.REQUEST_PARAM_ERROR);
		}
		BackendProdUnit resources = backendProdUnitRepository.findById(Long.valueOf(unitId)).get();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		resources.setUnitName(unitName);
		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		resources.setUpdateBy(userDetails.getUsername());
		prodUnitService.update(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}

	@DeleteMapping(value = "/prod/prodUnit/delete/{unitId}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity delete(@PathVariable Long unitId) {
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		prodUnitService.delete(unitId,userDetails.getUsername());
		return new ResponseEntity(HttpStatus.OK);
	}


	@PostMapping(value = "/prod/prodUnitInfo/create")
	public ResponseModel subCreate(
			@RequestParam(value = "unitInfoName") String unitInfoName,
			@RequestParam(value = "desc") String desc,
			@RequestParam(value = "unitId") Long unitId)
			throws Exception {
		ResponseModel responseModel = new ResponseModel();
		
		BackendProdUnitInfo resources = new BackendProdUnitInfo();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();

		resources.setDesc(desc);
		resources.setUnitInfoName(unitInfoName);
		resources.setCreateBy(userDetails.getUsername());
		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
		resources.setUnitId(unitId);
		prodUnitInfoService.create(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}
	
	@PostMapping(value = "/prod/prodUnitInfo/update")
	public ResponseModel subUpdate(
			@RequestParam(value = "unitInfoId") String unitInfoId,
			@RequestParam(value = "unitInfoName") String unitInfoName,
			@RequestParam(value = "desc") String desc) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		
		BackendProdUnitInfo resources = backendProdUnitInfoRepository.findById(Long.valueOf(unitInfoId)).get();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		resources.setUnitInfoName(unitInfoName);
		resources.setDesc(desc);
		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		resources.setUpdateBy(userDetails.getUsername());
		prodUnitInfoService.update(resources);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}
	
	@DeleteMapping(value = "/prod/prodUnitInfo/delete/{unitInfoId}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity deleteSub(@PathVariable Long unitInfoId) {
		prodUnitInfoService.delete(unitInfoId);
		return new ResponseEntity(HttpStatus.OK);
	}
}
