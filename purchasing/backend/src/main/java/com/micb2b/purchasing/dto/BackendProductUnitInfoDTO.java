package com.micb2b.purchasing.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * @author JayYang
 */
@Data
public class BackendProductUnitInfoDTO implements Serializable {
	
	private Long unitInfoId;

	private Long unitId;
	
	private String unitInfoName;
	
	private String desc;
	
	private Timestamp create_date;
	
	private String create_by;
	
	private Timestamp update_date;
	
	private String update_by;
	
}
