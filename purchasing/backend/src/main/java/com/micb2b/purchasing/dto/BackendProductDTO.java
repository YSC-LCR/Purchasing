package com.micb2b.purchasing.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author JayYang
 */
@Data
public class BackendProductDTO implements Serializable {
	
	private Long categoryId;
	
	private String categoryName;
	
}
