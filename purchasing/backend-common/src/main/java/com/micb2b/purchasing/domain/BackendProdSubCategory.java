package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author jay
 * @date 2019-12-1
 */

@Entity
@Data
@Table(name = "backend_prod_sub_category")
public class BackendProdSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "name")
	private String subCategoryName;
	
	@Column(name = "sub_category_desc")
	private String desc;
	
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
	
	@Column(name = "category_id")
	private Long categoryId;

}
