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
@Table(name = "backend_prod_spec_info")
public class BackendProdSpecInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spec_info_id")
	private Long specInfoId;

	@Column(name = "name")
	private String name;

	@Column(name = "weight")
	private int weight;
	
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
	@JoinColumn(name="spec_info_id")
	private Set<BackendProdPriceInfo> backendProdPriceInfo;

	@Column(name = "delete_flag")
	private String deleteFlag;
	
}
