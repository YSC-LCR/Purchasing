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
@Table(name = "country_info")
public class CountryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Timestamp updateDate;

}
