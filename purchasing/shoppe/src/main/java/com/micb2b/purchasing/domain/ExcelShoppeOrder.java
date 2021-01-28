package com.micb2b.purchasing.domain;

import lombok.Data;

@Data
public class ExcelShoppeOrder {

    // 0
    private String orderID ;
    // 1
    private String orderStatus ;
    // 5
    private String createDate;
    // 6
    private double totalSale ;
    // 9
    private double privateFee;
    // 19
    private double giftFee;
    // 20
    private double discountFee;
    // 16
    private double handleFee;
    // 18
    private double paymentFee;
    // 22
    private String specID ;
    // 24
    private double salepPrice;
    // 28
    private double qt ;

}
