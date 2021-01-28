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
@Table(name = "backend_prod_category")
public class BackendProdCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "name")
	private String categoryName;
	
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_date")
	private Timestamp updateDate;
	
	@Column(name = "delete_flag")
	private String deleteFlag;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="category_id")
	private Set<BackendProdSubCategory> backendProdSubCategory;
}
