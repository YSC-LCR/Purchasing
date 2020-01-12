package com.micb2b.purchasing.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author JayYang
 */
@Data
public class BackendProductUnitDTO implements Serializable {
	
	private Long unitId;
	
	private String unitName;
	
}
