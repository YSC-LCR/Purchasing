package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author jay
 * @date 2019-12-1
 */

@Entity
@Data
@Table(name = "backend_prod")
public class BackendProd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
	private Long prodId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "prod_desc")
	private String desc;
	
	@Column(name = "can_fly")
	private String canFly;
	
	@Column(name = "can_sea")
	private String canSea;
	
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Timestamp updateDate;

	@Column(name = "vendor_id")
	private Long vendorId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
	@JoinColumn(name="prod_id")
	private Set<BackendProdSpecInfo> backendProdSpecInfo;

	@Column(name = "delete_flag")
	private String deleteFlag;

	@Column(name = "counrty_id")
	private Long counrtyId;

	@Column(name = "excel_id")
	private Long excelId;


}
