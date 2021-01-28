package com.micb2b.purchasing.utils;//package com.micb2b.purchasing.shoppe.utils;

import com.micb2b.purchasing.domain.ExcelProdsInfo;
import com.micb2b.purchasing.domain.ExcelShoppeOrder;
import com.micb2b.purchasing.domain.ShopeeOrder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiUtil {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("start");
        PoiUtil util = new PoiUtil();
//        util.readShoppeOrdersFile("C:\\takashimaya\\Order.all.20201001_20201031.xls");
//        util,saveSpecAndPrice("C:\\takashimaya\\prods1214.xls");
        System.out.println("end");
    }
    public static List<ExcelProdsInfo> readShoppeProdsInfo(String fileLocation) throws IOException, ParseException {

        List<ExcelProdsInfo> lProds = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            double categoryId = row.getCell(0).getNumericCellValue();
            double subCategoryId = row.getCell(1).getNumericCellValue();
            double vendorInfo = row.getCell(3).getNumericCellValue();
            String prodName = row.getCell(5).getRichStringCellValue().getString();
            double weight = row.getCell(6).getNumericCellValue();
            double money =  row.getCell(7).getNumericCellValue();
            String isFly = row.getCell(8).getRichStringCellValue().getString();
            String isSea = row.getCell(9).getRichStringCellValue().getString();
            String specInfo = row.getCell(10).getRichStringCellValue().getString();
            double countryId = row.getCell(11).getNumericCellValue();
            double prodId = row.getCell(12).getNumericCellValue();

            ExcelProdsInfo prod = new ExcelProdsInfo();

            prod.setCategoryId(categoryId);
            prod.setSubCategoryId(subCategoryId);
            prod.setVendorInfo(vendorInfo);
            prod.setProdName(prodName.trim());
            prod.setWeight(weight);
            prod.setMoney(money);
            prod.setIsFly(isFly.trim());
            prod.setIsSea(isSea.trim());
            prod.setSpecInfo(specInfo.trim());
            prod.setCountryId(countryId);
            prod.setProdId(prodId);
            lProds.add(prod);
        }

        return lProds;
    }

    public static  List<ExcelShoppeOrder> readShoppeOrdersFile(String fileLocation) throws IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<ShopeeOrder> sos = new ArrayList<>();
        List<ExcelShoppeOrder> eSos = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            ExcelShoppeOrder eso = new ExcelShoppeOrder();
            String orderID = row.getCell(0).getRichStringCellValue().getString();
            String orderStatus = row.getCell(1).getRichStringCellValue().getString();
            String createDate = row.getCell(5).getStringCellValue();
            double totalSale  = Double.parseDouble( row.getCell(6).getRichStringCellValue().getString());
            double paymentFee =  Double.parseDouble(row.getCell(18).getRichStringCellValue().getString());
            double giftFee = row.getCell(19).getNumericCellValue();
            double privateFee = row.getCell(20).getNumericCellValue();
            double discountFee = Double.parseDouble(row.getCell(13).getRichStringCellValue().getString());
            double handleFee = Double.parseDouble( row.getCell(16).getRichStringCellValue().getString());
            String specID =String.valueOf((int)(row.getCell(22).getNumericCellValue()));
            double qt  = row.getCell(28).getNumericCellValue();
            double salepPrice  = Double.parseDouble( row.getCell(24).getRichStringCellValue().getString());
            eso.setOrderID(orderID);
            eso.setOrderStatus(orderStatus);
            eso.setCreateDate(createDate);
            eso.setTotalSale(totalSale);
            eso.setPrivateFee(privateFee);
            eso.setDiscountFee(discountFee);
            eso.setHandleFee(handleFee);
            eso.setSpecID(specID);
            eso.setQt(qt);
            eso.setSalepPrice(salepPrice);
            eso.setPaymentFee(paymentFee);
            eso.setGiftFee(giftFee);
//            System.out.println("order status :"+eso.toString());
            eSos.add(eso);
        }
//        System.out.println("excel oreders :"+eSos.size());
        return eSos;
    }

}
