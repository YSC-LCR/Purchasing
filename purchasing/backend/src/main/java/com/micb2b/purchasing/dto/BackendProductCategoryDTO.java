package com.micb2b.purchasing.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author JayYang
 */
@Data
public class BackendProductCategoryDTO implements Serializable {
	
	private Long categoryId;
	
	private String categoryName;
	
}
