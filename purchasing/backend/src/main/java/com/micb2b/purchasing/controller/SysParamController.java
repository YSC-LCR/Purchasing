package com.micb2b.purchasing.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micb2b.purchasing.domain.ResponseCode;
import com.micb2b.purchasing.domain.ResponseModel;
import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.service.SysParamService;
import com.micb2b.purchasing.service.query.SysParamQueryService;
import com.micb2b.purchasing.utils.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author M.K. Huang
 */
@Slf4j
@RestController
@RequestMapping("system")
public class SysParamController {

	@Autowired
	private SysParamQueryService sysParamQueryService;

	@Autowired
	private SysParamService sysParamService;

	/**
	 * 返回全部的訊息
	 * 
	 * @return
	 */
	@GetMapping(value = "/sysparam/queryAll")
	public ResponseEntity queryAll(@RequestParam(required = false) String paramName, Pageable pageable) {
		log.info("system/sysparam/queryAll start");

		ResponseEntity entity = new ResponseEntity(sysParamQueryService.queryAll(paramName, pageable), HttpStatus.OK);
		log.info("system/sysparam/queryAll finished");
		
		return entity;
	}
	
	@PostMapping(value = "/sysparam/create")
	public ResponseModel create(
			@RequestParam(value = "paramId") String paramId,
			@RequestParam(value = "paramName") String paramName,
			@RequestParam(value = "paramDesc") String paramDesc,
			@RequestParam(value = "paramValue") String paramValue
			) throws Exception {
		log.info("system/sysparam/create start");
		
		Timestamp createDate = new Timestamp(new Date().getTime());
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		log.info("User Name: {}", userDetails.getUsername());
		log.info("paramId: {}", paramId);
		log.info("paramName: {}", paramName);
		log.info("paramDesc: {}", paramDesc);
		log.info("paramValue: {}", paramValue);
		
		SysParam sysParam = new SysParam();
		sysParam.setParamId(paramId);
		sysParam.setParamName(paramName);
		sysParam.setParamDesc(paramDesc);
		sysParam.setParamValue(paramValue);
		sysParam.setCreateDate(createDate);
		sysParam.setCreateBy(userDetails.getUsername());
		
		ResponseModel responseModel = new ResponseModel();
		
		sysParamService.create(sysParam);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}

	@PostMapping(value = "/sysparam/update")
	public ResponseModel update(
			@RequestParam(value = "paramId") String paramId,
			@RequestParam(value = "paramName") String paramName,
			@RequestParam(value = "paramDesc") String paramDesc,
			@RequestParam(value = "paramValue") String paramValue
			) throws Exception {
		log.info("system/sysparam/update start");

		Timestamp updateDate = new Timestamp(new Date().getTime());
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		log.info("paramId: {}", paramId);
		log.info("paramName: {}", paramName);
		log.info("paramDesc: {}", paramDesc);
		log.info("paramValue: {}", paramValue);

		SysParam sysParam = sysParamService.findById(paramId);

		sysParam.setParamName(paramName);
		sysParam.setParamDesc(paramDesc);
		sysParam.setParamValue(paramValue);
		sysParam.setUpdateDate(updateDate);
		sysParam.setUpdateBy(userDetails.getUsername());
		
		sysParamService.update(sysParam);

		return new ResponseModel(ResponseCode.SUCCESS);
	}

	@DeleteMapping(value = "/sysparam/delete/{paramId}")
	public ResponseEntity delete(@PathVariable String paramId) {
		log.info("system/sysparam/delete start");
		log.info("paramId: {}", paramId);
		
		Map<String, String> resMap = new Hashtable<String, String>();
		
		SysParam sysParam = sysParamService.findById(paramId);
		sysParamService.delete(sysParam);
		
		resMap.put("errCode", ResponseCode.SUCCESS.getCode());
		resMap.put("errMsg", ResponseCode.SUCCESS.getDescription());
		
		ResponseEntity entity = new ResponseEntity(resMap, HttpStatus.OK);
		
		return entity;
	}

}
