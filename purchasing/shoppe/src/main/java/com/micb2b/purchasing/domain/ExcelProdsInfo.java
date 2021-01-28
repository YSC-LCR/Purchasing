package com.micb2b.purchasing.domain;

import lombok.Data;

@Data
public class ExcelProdsInfo {
   private Long id;
   private double categoryId;
   private double subCategoryId;
   private double vendorInfo;
   private String prodName;
   private double weight;
   private double money;
   private String isFly;
   private String isSea;
   private String specInfo ;
   private double countryId;
   private double prodId;
}
