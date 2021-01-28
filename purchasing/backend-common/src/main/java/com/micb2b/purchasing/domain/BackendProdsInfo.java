package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "backend_prods_info")
public class BackendProdsInfo {

    @Id
    @Column(name = "vid")
    private String vid;

    @Column(name = "prod_id")
    private Long prodId;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "spec_id")
    private Long specId;

    @Column(name = "spec_Name")
    private String specName;

    @Column(name = "cost")
    private double cost;
}
