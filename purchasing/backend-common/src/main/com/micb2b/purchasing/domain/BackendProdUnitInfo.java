package com.micb2b.purchasing.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jay
 * @date 2020-1-11
 */

@Entity
@Data
@Table(name = "backend_prod_unit_info")
public class BackendProdUnitInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_info_id")
	private Long unitInfoId;
	
	@Column(name = "name")
	private String unitInfoName;
	
	@Column(name = "unit_info_desc")
	private String desc;
	
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Timestamp updateDate;
	
	@Column(name = "unit_id")
	private Long unitId;

}
