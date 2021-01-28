package com.micb2b.purchasing.service.impl;

import com.micb2b.purchasing.domain.*;
import com.micb2b.purchasing.repository.*;
import com.micb2b.purchasing.service.ProdDataService;
import com.micb2b.purchasing.utils.PoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProdDataServiceImpl implements ProdDataService {

    @Autowired
    BackendProdRepository backendProdRepository;

    @Autowired
    BackendProdSubCategoryRepository backendProdSubCategoryRepository;

    @Autowired
    BackendProdSubCategoryProdRepository backendProdSubCategoryProdRepository;

    @Autowired
    BackendProdsInfoRepository bckendProdsInfoRepository;

    @Autowired
    ShoppeOrderRepository shoppeOrderRepository;

    @Value("${file.prodsInfo}")
    private String prodsInfo;

    @Value("${file.orderInfo}")
    private String ordersInfo;

    @Value("${system.giftswitch}")
    private String giftswitch;

    @Override
    public void findAllData() {
        backendProdRepository.findAll().forEach(backendProd -> {
            System.out.println(backendProd.toString());
        });
    }

    @Override
    public int saveProsds() throws IOException, ParseException {
        //建立商品
        List<ExcelProdsInfo> newnewProds = null;
        List<ExcelProdsInfo> existProds = null;

        List<ExcelProdsInfo> excelProds = PoiUtil.readShoppeProdsInfo(prodsInfo);

        existProds  = excelProds.stream().filter(prods -> !(prods.getProdId() == 0.0))
                .collect(Collectors.toList());

        newnewProds  = excelProds.stream().filter(prods -> (prods.getProdId()== 0.0))
                .collect(Collectors.toList());

        createExistPords(existProds);

        createNewProds(newnewProds);

        return 0;
    }

    @Override
    public void calculateOrders() throws IOException, ParseException {
        List<BackendProdsInfo> listProds = bckendProdsInfoRepository.findAll();

        Map<Long, BackendProdsInfo> mapProds = listProds.stream()
                .collect(Collectors.toMap(BackendProdsInfo::getSpecId, backendProdsInfo -> backendProdsInfo));

        List<ExcelShoppeOrder> excelOrders = PoiUtil.readShoppeOrdersFile(ordersInfo);
        List<ShopeeOrder> result = parseOrders(excelOrders, mapProds);
        shoppeOrderRepository.saveAll(result);
    }

    @Override
    public void deleteProds() {
        backendProdRepository.deleteAll();
        backendProdSubCategoryProdRepository.deleteAll();
    }

    public void createExistPords(List<ExcelProdsInfo> excelProds) {
        List<BackendProd> erpProds = backendProdRepository.findAll();

        Map<Long, BackendProd> mapProds = erpProds.stream()
                .collect(Collectors.toMap(BackendProd::getProdId, backendProds -> backendProds));


        if(null != excelProds){
            // 查詢既有的prod
            for (ExcelProdsInfo excelProd :excelProds) {
                // 將要增加的spec放入

                BackendProd erpProd = mapProds.get((long)excelProd.getProdId());
                Set<BackendProdSpecInfo> erpSpec = erpProd.getBackendProdSpecInfo();

                BackendProdSpecInfo spec = new BackendProdSpecInfo();
                spec.setName(excelProd.getSpecInfo());
                spec.setWeight((int) excelProd.getWeight());
                spec.setCreateBy("admin");
                spec.setCreateDate(new Timestamp(System.currentTimeMillis()));
                spec.setDeleteFlag("N");

                Set<BackendProdPriceInfo> prices = new HashSet<>();
                BackendProdPriceInfo price = new BackendProdPriceInfo();
                price.setPriceLocal((int) excelProd.getMoney());
                price.setCreateBy("admin");
                price.setCreateDate(new Timestamp(System.currentTimeMillis()));
                price.setDeleteFlag("N");
                prices.add(price);

                spec.setBackendProdPriceInfo(prices);
                erpSpec.add(spec);
                erpProd.setBackendProdSpecInfo(erpSpec);

                backendProdRepository.save(erpProd);

            }

        }

    }

    public void createNewProds(List<ExcelProdsInfo> excelProds) throws IOException, ParseException {

        if(null != excelProds){
            for (int i= 0; i< excelProds.size(); i++) {
                log.info("i : {}",i);
                ExcelProdsInfo excelProd = excelProds.get(i);

                String keyProd = excelProd.getCategoryId()
                        + excelProd.getSubCategoryId() + excelProd.getVendorInfo() + excelProd.getProdName();

                //如果下一個還是同一個prod將他放進同一個Set
                BackendProd prod = new BackendProd();
                prod.setName(excelProd.getProdName());
                prod.setVendorId((long) excelProd.getVendorInfo());
                prod.setCanFly(excelProd.getIsFly());
                prod.setCanSea(excelProd.getIsSea());
                prod.setCreateBy("admin");
                prod.setCreateDate(new Timestamp(System.currentTimeMillis()));
                prod.setDeleteFlag("N");
                prod.setCounrtyId((long) excelProd.getCountryId());

                Set<BackendProdSpecInfo> specs = new HashSet<>();
                BackendProdSpecInfo spec = new BackendProdSpecInfo();
                spec.setName(excelProd.getSpecInfo());
                spec.setWeight((int) excelProd.getWeight());
                spec.setCreateBy("admin");
                spec.setCreateDate(new Timestamp(System.currentTimeMillis()));
                spec.setDeleteFlag("N");

                Set<BackendProdPriceInfo> prices = new HashSet<>();
                BackendProdPriceInfo price = new BackendProdPriceInfo();
                price.setPriceLocal((int) excelProd.getMoney());
                price.setCreateBy("admin");
                price.setCreateDate(new Timestamp(System.currentTimeMillis()));
                price.setDeleteFlag("N");
                prices.add(price);
                spec.setBackendProdPriceInfo(prices);
                specs.add(spec);
                //都是i的下一個
                for(int j= i+1; j< excelProds.size(); j++) {
                    log.debug("j : {}",j);
                    ExcelProdsInfo nextExcelProd = excelProds.get(j);

                    String nextKeyProd = nextExcelProd.getCategoryId()
                            + nextExcelProd.getSubCategoryId() + nextExcelProd.getVendorInfo() + nextExcelProd.getProdName();

                    if(keyProd.equals(nextKeyProd)) {
                        log.debug("next same prod ");
                        BackendProdSpecInfo nextSpec = new BackendProdSpecInfo();
                        nextSpec.setName(nextExcelProd.getSpecInfo());
                        nextSpec.setWeight((int) nextExcelProd.getWeight());
                        nextSpec.setCreateBy("admin");
                        nextSpec.setCreateDate(new Timestamp(System.currentTimeMillis()));
                        nextSpec.setDeleteFlag("N");

                        Set<BackendProdPriceInfo> nextPrices = new HashSet<>();
                        BackendProdPriceInfo nextPrice = new BackendProdPriceInfo();
                        nextPrice.setPriceLocal((int) nextExcelProd.getMoney());
                        nextPrice.setCreateBy("admin");
                        nextPrice.setCreateDate(new Timestamp(System.currentTimeMillis()));
                        nextPrice.setDeleteFlag("N");

                        nextPrices.add(nextPrice);
                        nextSpec.setBackendProdPriceInfo(nextPrices);
                        specs.add(nextSpec);

                    } else {
                        log.debug("next non same prod ");
                        break;
                    }
                    i = j;
                }

                prod.setBackendProdSpecInfo(specs);
                BackendProd erpProd = backendProdRepository.save(prod);
                log.info("insert prod : {}",erpProd.getProdId());

                BackendProdSubCategoryProd subProd = new BackendProdSubCategoryProd();
                subProd.setSubCategoryId((long) excelProd.getSubCategoryId());
                subProd.setProdId(erpProd.getProdId());

                BackendProdSubCategoryProd subCategory = backendProdSubCategoryProdRepository.save(subProd);
                log.info("insert subCategory : {}",subCategory.getSeq());

            }
        }
    }

    public List<ShopeeOrder> parseOrders( List<ExcelShoppeOrder> eSos,Map<Long, BackendProdsInfo> mapProds ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        List<ShopeeOrder> sos = new ArrayList<>();

        for (int i = 0; i < eSos.size(); i++) {
            log.info("parse order size : {}",i);
            ExcelShoppeOrder eso = new ExcelShoppeOrder();
            ExcelShoppeOrder excelOrder = eSos.get(i);
            String orderID = excelOrder.getOrderID();

            String orderStatus = excelOrder.getOrderStatus();
            double totalSale  = excelOrder.getTotalSale();
            double privateFee = excelOrder.getPrivateFee();
            double giftFee = excelOrder.getGiftFee();
            double discountFee = excelOrder.getDiscountFee();
            double handleFee = excelOrder.getHandleFee();
            double paymentFee = excelOrder.getPaymentFee();
            String specID = excelOrder.getSpecID();
            double qt  = excelOrder.getQt();
            double salepPrice  = excelOrder.getSalepPrice();

            //訂單沒有完成跳過此單
            if ("完成".equals(orderStatus)) {
                //先讀第一個子商品
                ShopeeOrder so = new ShopeeOrder();
                Set<ShopeeProd> setsp = new HashSet<>();
                double costPool = 0;
                double profitPool = 0;
                ShopeeProd sp = new ShopeeProd();

                BackendProdsInfo erpProdInfo = mapProds.get(Long.valueOf(specID));
                if(null == erpProdInfo) {
                    log.info("miss specId : {} ",specID);
                    log.info("miss orderID : {} ",orderID);
                }

                sp.setProdId(erpProdInfo.getProdId());
                sp.setProdName(erpProdInfo.getProdName());
                sp.setProdSpec(erpProdInfo.getSpecName());
                sp.setCost(erpProdInfo.getCost()*(int) qt);
                sp.setSalepPrice((float) salepPrice);
                sp.setQt((int) qt);

                double prInfo = (erpProdInfo.getCost()*(int) qt);
                costPool+=prInfo;
                setsp.add(sp);
                int sonOrder = 0; //統計主要訂單下有多少子訂單(之後要加回主要以免重複)
                // 相同訂單再開迴圈，爬取訂單資料
                for (int j= 1; j<= eSos.size(); j++) {
                    //如讀不到為最後一列跳過
                    if (eSos.size() > (i + j)) {

                        ExcelShoppeOrder nextExcelOrder = null;
                        String nextOrderID = null;
                        nextExcelOrder = eSos.get(i + j);
                        nextOrderID   = nextExcelOrder.getOrderID();
                        //如讀不同流水號,為不同訂單
                        //如讀到下個是同樣流水號,為同一張訂單
                        if (orderID.equals(nextOrderID)) {
                            log.info("parse same order : {}",orderID);
                            log.info("parse order : {} , next order : {}",i,i+j);
                            //將其他商品放入
                            ShopeeProd ssp = new ShopeeProd();
                            String sameSpecID  = nextExcelOrder.getSpecID();
                            double samesalepPrice = nextExcelOrder.getSalepPrice();
                            double sameQt  = nextExcelOrder.getQt();
                            BackendProdsInfo sameErpProdInfo = mapProds.get(Long.valueOf(sameSpecID));


                            ssp.setProdId(sameErpProdInfo.getProdId());
                            ssp.setProdName(sameErpProdInfo.getProdName());
                            ssp.setProdSpec(sameErpProdInfo.getSpecName());
                            ssp.setQt((int) sameQt);
                            ssp.setCost(sameErpProdInfo.getCost() * (int) sameQt);
                            ssp.setSalepPrice((float) samesalepPrice);

                            double samePrInfo = sameErpProdInfo.getCost() * (int) sameQt;
                            costPool += samePrInfo;
                            setsp.add(ssp);
                            sonOrder++; //同一張訂單數量
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i+=sonOrder;

                //訂單金額超過多少需要贈品費用
                //10月份超過900元就送禮物
                if (giftswitch.equals("Y") && totalSale >=900 ) {
                    giftFee = 16;
                }

                profitPool = totalSale-costPool-(discountFee+handleFee+privateFee+giftFee+paymentFee);

                so.setOrderId(orderID);
                so.setCreateDate(simpleDateFormat.parse(excelOrder.getCreateDate()));
                so.setDiscountFee(discountFee);
                so.setHandleFee(handleFee);
                so.setPrivateFee(privateFee);
                so.setTotalSale(totalSale);
                so.setProfit(profitPool);
                so.setShopeeProds(setsp);
                so.setGiftFee(giftFee);
                so.setTotalCost(costPool);
                so.setPaymentFee(paymentFee);
                sos.add(so);
            }

        }
        log.info("shoppe orders :{}",sos.size());
        return sos;
    }
}
