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
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "rate")
    private float rate;

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
