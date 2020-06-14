package com.micb2b.purchasing.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author JayYang
 */
@Data
public class BackendProductViewDTO implements Serializable {
	
	private String vid;
	
	private Long prodId;
	
	private String categoryName;
	
	private String subCategoryName;
	
	private String prodName;
	
	private String prodSpec;
	
	private String prodPrice;
	
	private String prodDesc;
	
}
