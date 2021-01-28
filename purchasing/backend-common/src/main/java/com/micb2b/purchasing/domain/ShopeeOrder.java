package com.micb2b.purchasing.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "shopee_order")
public class ShopeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_seq")
    Long orderSeq;

    @Column(name = "order_id")
    String orderId; //訂單編號

    @Column(name = "create_date")
    Date createDate; //建立時間

    @Column(name = "gift_fee")
    double giftFee; //贈品費用

    @Column(name = "handle_fee")
    double handleFee; //手續費

    @Column(name = "discount_fee")
    double discountFee; //折扣金額

    @Column(name = "private_fee")
    double privateFee; //私人折扣

    @Column(name = "total_cost")
    double totalCost; //總成本

    @Column(name = "total_sale")
    double totalSale; //總銷售

    @Column(name = "profit")
    double profit; //利潤

    @Column(name = "payment_fee")
    double paymentFee; // 金流服務

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
    @JoinColumn(name="order_seq")
    Set<ShopeeProd> shopeeProds;//商品
}
