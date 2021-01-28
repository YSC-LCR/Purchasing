package com.micb2b.purchasing.service;

import java.io.IOException;
import java.text.ParseException;

public interface ProdDataService {
    
    public void findAllData();

    public int saveProsds() throws IOException, ParseException;

    public void calculateOrders() throws IOException, ParseException;

    public void deleteProds();
}
