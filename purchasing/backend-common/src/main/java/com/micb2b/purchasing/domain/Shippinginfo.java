package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author jay
 * @date 2021-01-14
 */

@Entity
@Data
@Table(name = "shipping_info")
public class Shippinginfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "cost")
    private int cost;

    @Column(name = "type")
    private int type;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "is_use")
    private String isUse;
}
