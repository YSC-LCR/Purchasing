package com.micb2b.purchasing.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;

/**
 * @author jay
 * @date 2019-11-23
 */
@Data
public class SysParamDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000342501472447663L;

	private String paramId;
	
	private String paramName;
	
	private String paramDesc;
	
	private String paramType;

	private String paramValue;
	
	private String valueType;
	
	private int valueSize;
	
	private String createBy;

	private Timestamp createDate;
	
	private String updateBy;

	private Timestamp updateDate;
	
}
