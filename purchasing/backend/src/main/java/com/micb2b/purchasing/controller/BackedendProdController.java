package com.micb2b.purchasing.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.micb2b.purchasing.domain.BackendProd;
import com.micb2b.purchasing.domain.BackendProdImages;
import com.micb2b.purchasing.domain.ResponseCode;
import com.micb2b.purchasing.domain.ResponseModel;
import com.micb2b.purchasing.service.BackendProdService;
import com.micb2b.purchasing.service.query.BackendProdQueryService;
import com.micb2b.purchasing.utils.ImageUtil;
import com.micb2b.purchasing.utils.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayyang
 * @date 2019-12-01
 */
@Slf4j
@RestController
@RequestMapping("homePage")
public class BackedendProdController {

	@Autowired
	private BackendProdQueryService prodQueryService;
	
	@Autowired
	BackendProdService backendProdService;
	/**
	 * 返回商品列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodMain/queryAll")
	public ResponseEntity queryAll(@RequestParam(required = false) String columnValue, Pageable pageable) {
		ResponseEntity responseEntity = new ResponseEntity(prodQueryService.queryAll(columnValue, pageable),
				HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * 返回商品表單需要初始資料
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodMain/initProdForm")
	public ResponseEntity initForm() {
		ResponseEntity responseEntity = new ResponseEntity(backendProdService.initProdForm(),HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(value = "/prod/prodMain/create")
	public ResponseModel create(
			@RequestParam(value = "subCategoryInfo") String subCategoryInfo,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "weight") int weight,
			@RequestParam(value = "canFly") boolean canFly,
			@RequestParam(value = "canSea") boolean canSea,
			@RequestParam(value = "desc") String desc,
			@RequestParam(value = "images") String images
			) throws Exception {
		ResponseModel responseModel = new ResponseModel();
		
		BackendProd resources = new BackendProd();
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		String[] subCateInfos = subCategoryInfo.split(",");
		resources.setSubCategoryId(Long.valueOf(subCateInfos[1]));
		resources.setName(name);
		resources.setDesc(desc);
		resources.setWeight(weight);
		resources.setCanFly(convertBooleantoYN(canFly));
		resources.setCanSea(convertBooleantoYN(canSea));
		resources.setCreateBy(userDetails.getUsername());
		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
		resources.setDeleteFlag("N");
		BackendProd backendProd = backendProdService.create(resources);

		JSONArray imagesJson = new JSONArray(images);
		JSONArray outsideImagesJson =(JSONArray) imagesJson.get(0);
		List<BackendProdImages> prodImages = new ArrayList<BackendProdImages>();
		for(int i = 0; i <outsideImagesJson.length(); i++) {
			JSONObject json = (JSONObject)outsideImagesJson.get(i);
			System.out.println(i);
			System.out.println(json.get("name"));
			System.out.println(json.get("path"));
			System.out.println(json.get("default"));
			
			BackendProdImages image = new BackendProdImages();
			String jsonBase64Str =(String) json.get("path");
			Integer isMain =(Integer) json.get("default");
			log.info("jsonBase64Str"+jsonBase64Str);
			String fileName = ImageUtil.saveToFileImage(jsonBase64Str.replace("image/jpeg;base64,", ""));

			
			image.setIsMain(isMain.longValue());
			image.setOrder((long) i);
			image.setFileName(fileName);
			image.setCreateBy(userDetails.getUsername());
			image.setCreateDate(new Timestamp(System.currentTimeMillis()));
			image.setProdId(backendProd.getProdId());
			prodImages.add(image);
		}
		backendProdService.creatProdImages(prodImages);
		responseModel.setStatusCode(ResponseCode.SUCCESS);
		return responseModel;
	}
	
	@DeleteMapping(value = "/prod/prodMain/delete/{prodId}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity deleteSub(@PathVariable Long prodId) {
		UserDetails userDetails = SecurityContextHolder.getUserDetails();
		backendProdService.delete(prodId,userDetails.getUsername());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * 返回主子類別資訊
	 * 
	 * @return
	 */
	@GetMapping(value = "/prod/prodMain/getCateInfo")
	public ResponseEntity getCategoryInfo(
			@RequestParam(value = "subCateId") Long subCateId
			) {
		ResponseEntity responseEntity = new ResponseEntity(backendProdService.findCategoryInfo(subCateId),HttpStatus.OK);
		return responseEntity;
	}
//	/**
//	 * 返回商品子類列表
//	 * 
//	 * @return
//	 */
//	@GetMapping(value = "/prod/prodSubCategory/querySubAll")
//	public ResponseEntity querySubAll(@RequestParam(value = "categoryId") Long categoryId) {
//		ResponseEntity responseEntity = new ResponseEntity(prodSubCategoryService.findAllByCategory(categoryId),
//				HttpStatus.OK);
//		return responseEntity;
//	}
//
//	@PostMapping(value = "/prod/prodCategory/update")
//	public ResponseModel update(@RequestParam(value = "categoryId") String categoryId,
//			@RequestParam(value = "categoryName") String categoryName) throws Exception {
//		ResponseModel responseModel = new ResponseModel();
//		if (categoryId.isEmpty() || categoryId.isEmpty()) {
//			return new ResponseModel(ResponseCode.REQUEST_PARAM_ERROR);
//		}
//		BackendProdCategory resources = backendProdCategoryRepository.findById(Long.valueOf(categoryId)).get();
//		UserDetails userDetails = SecurityContextHolder.getUserDetails();
//		resources.setCategoryName(categoryName);
//		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
//		resources.setUpdateBy(userDetails.getUsername());
//		prodCategoryService.update(resources);
//		responseModel.setStatusCode(ResponseCode.SUCCESS);
//		return responseModel;
//	}
//
//	@DeleteMapping(value = "/prod/prodCategory/delete/{categoryId}")
//	@Transactional(rollbackFor = Exception.class)
//	public ResponseEntity delete(@PathVariable Long categoryId) {
//		UserDetails userDetails = SecurityContextHolder.getUserDetails();
//		prodCategoryService.delete(categoryId,userDetails.getUsername());
//		return new ResponseEntity(HttpStatus.OK);
//	}
//
//
//	@PostMapping(value = "/prod/prodSubCategory/create")
//	public ResponseModel subCreate(
//			@RequestParam(value = "subCategoryName") String subCategoryName,
//			@RequestParam(value = "desc") String desc,
//			@RequestParam(value = "categoryId") Long categoryId)
//			throws Exception {
//		ResponseModel responseModel = new ResponseModel();
//		
//		BackendProdSubCategory resources = new BackendProdSubCategory();
//		UserDetails userDetails = SecurityContextHolder.getUserDetails();
//
//		resources.setDesc(desc);
//		resources.setSubCategoryName(subCategoryName);
//		resources.setCreateBy(userDetails.getUsername());
//		resources.setCreateDate(new Timestamp(System.currentTimeMillis()));
//		resources.setDeleteFlag("N");
//		resources.setCategoryId(categoryId);
//		prodSubCategoryService.create(resources);
//		responseModel.setStatusCode(ResponseCode.SUCCESS);
//		return responseModel;
//	}
//	
//	@PostMapping(value = "/prod/prodSubCategory/update")
//	public ResponseModel subUpdate(
//			@RequestParam(value = "subCategoryId") String subCategoryId,
//			@RequestParam(value = "subCategoryName") String subCategoryName,
//			@RequestParam(value = "desc") String desc) throws Exception {
//		ResponseModel responseModel = new ResponseModel();
//		
//		BackendProdSubCategory resources = backendProdSubCategoryRepository.findById(Long.valueOf(subCategoryId)).get();
//		UserDetails userDetails = SecurityContextHolder.getUserDetails();
//		resources.setSubCategoryName(subCategoryName);
//		resources.setDesc(desc);
//		resources.setUpdateDate(new Timestamp(System.currentTimeMillis()));
//		resources.setUpdateBy(userDetails.getUsername());
//		prodSubCategoryService.update(resources);
//		responseModel.setStatusCode(ResponseCode.SUCCESS);
//		return responseModel;
//	}
//	

	public  String convertBooleantoYN(boolean input) {
		String result = "N";
		if(Boolean.TRUE.equals(input)) {
			result = "Y";
		}
		return result;
	}
}
