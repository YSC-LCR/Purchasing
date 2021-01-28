package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jay
 * @date 2019-12-1
 */

@Entity
@Data
@Table(name = "backend_prod_view")
public class BackendProdView {

	@Id
	@Column(name = "vid")
	private String vid;
	
	@Column(name = "prod_id")
	private Long prodId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "sub_category_name")
	private String subCategoryName;
	
	@Column(name = "prod_name")
	private String prodName;
	
	@Column(nullable=true,name = "prod_spec")
	private String prodSpec;
	
	@Column(nullable=true,name = "prod_price")
	private String prodPrice;
	
	@Column(name = "prod_desc")
	private String prodDesc;
	
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "weight")
	private int weight;
	
	@Column(name = "can_fly")
	private String canFly;
	
	@Column(name = "can_sea")
	private String canSea;
}
