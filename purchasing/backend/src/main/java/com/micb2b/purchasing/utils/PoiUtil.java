package com.micb2b.purchasing.utils;

import com.micb2b.purchasing.domain.Prod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Component
public class PoiUtil {

    private static Map<String, Prod> erpProds;

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("start");
        PoiUtil util = new PoiUtil();
//        util.readShoppeOrdersFile("C:\\takashimaya\\Order.all.20201001_20201031.xls");
//        util.readProdsFile("C:\\takashimaya\\prods1214.xls");
        System.out.println("end");
    }
}
