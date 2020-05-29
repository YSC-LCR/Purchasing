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
 * @date 2020-03-31
 */

@Entity
@Data
@Table(name = "backend_prod_images")
public class BackendProdImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "order")
	private Long order;
	
	@Column(name = "is_main")
	private Long isMain;
	
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Timestamp updateDate;
	
	@Column(name = "prod_id")
	private Long prodId;
}
