package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shoppe_prod")
public class ShopeeProd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_seq")
    Long prodSeq;

    @Column(name = "prod_id")
    Long prodId; //商品標號

    @Column(name = "prod_name")
    String prodName;//商品名稱

    @Column(name = "prod_spec")
    String prodSpec;//商品規格

    @Column(name = "sale_price")
    double salepPrice; //商品售價

    @Column(name = "cost")
    double cost; //成本

    @Column(name = "qt")
    int qt; //數量

    @Column(name = "order_seq")
    Long orderSeq;
}
