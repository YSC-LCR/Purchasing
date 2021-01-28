package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "backend_prod_subcategory_prod")
public class BackendProdSubCategoryProd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(name = "prod_id")
    private Long prodId;
}
