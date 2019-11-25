package com.micb2b.purchasing.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jay
 */
@Entity
@Data
@Table(name = "sys_param")
public class SysParam implements Serializable{

	/**
	 *  系統參數類別-系統設定參數
	 */
	public static final String SYSPARAM_PARAM_TYPE_SYSTEM = "SYSTEM";
	/**
	 *  系統參數值資料型態-文字
	 */
	public static final String SYSPARAM_VALUE_TYPE_STRING = "STR";
	/**
	 *  系統參數值資料型態-數字
	 */
	public static final String SYSPARAM_VALUE_TYPE_NUMBER = "NUM";
	/**
	 *  系統參數值資料型態-日期
	 */
	public static final String SYSPARAM_VALUE_TYPE_DATE = "DAT";
	
	@Id
	@Column(name = "param_id")
	private String paramId;
	
	@Column(name = "param_name")
	private String paramName;
	
	@Column(name = "param_desc")
	private String paramDesc;
	
	@Column(name = "param_type")
	private String paramType;

	@Column(name = "param_value")
	private String paramValue;
	
	@Column(name = "value_type")
	private String valueType;
	
	@Column(name = "value_size")
	private int valueSize;
	
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Timestamp updateDate;
	
}
