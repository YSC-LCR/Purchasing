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
@Table(name = "backend_prod_price_info")
public class BackendProdPriceInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_info_id")
	private Long priceInfoId;

	@Column(name = "spec_info_id")
	private Long specInfoId;

	@Column(name = "price_local")
	private int priceLocal;
	
	@Column(name = "place_consumption")
	private String placeConsumption;
	
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
}
