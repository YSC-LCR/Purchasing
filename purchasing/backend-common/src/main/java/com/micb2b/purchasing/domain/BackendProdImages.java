package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

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
