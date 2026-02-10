package com.sanuth.cryptoflow.service;

import com.sanuth.cryptoflow.connector.MarketDataConnector;
import com.sanuth.cryptoflow.model.CoinMarketData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketDataService {

    private final  MarketDataConnector marketDataConnector;

    public MarketDataService(MarketDataConnector marketDataConnector){
        this.marketDataConnector=marketDataConnector;
    }

    public List<CoinMarketData> getPriceDataList(){
        return this.marketDataConnector.getPriceList();
    }


}