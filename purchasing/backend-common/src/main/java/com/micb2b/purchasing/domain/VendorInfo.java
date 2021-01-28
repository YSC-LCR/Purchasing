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
@Table(name = "vendor_info")
public class VendorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Timestamp updateDate;
}
