package com.sanuth.cryptoflow.scheduling;

import com.sanuth.cryptoflow.connector.MarketDataConnector;
import com.sanuth.cryptoflow.model.CoinMarketData;
import com.sanuth.cryptoflow.service.MarketDataService;
import com.sanuth.cryptoflow.service.RedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketDataScheduler {

    private final MarketDataConnector marketDataConnector;
    private final RedisService redisService;
    private volatile boolean uiLoaded=false;

    public MarketDataScheduler(MarketDataConnector marketDataConnector,RedisService redisService){
        this.marketDataConnector=marketDataConnector;
        this.redisService=redisService;
    }
    @Scheduled(fixedRate = 30000)
    public void scheduleData(){
//        if(!uiLoaded){
//            return;
//        }
        System.out.println("Task running");
        List<CoinMarketData> coinMarketData=this.marketDataConnector.getPriceList();
        // call api
        // redisservice.updatelistdata()
        redisService.saveAllPrices(coinMarketData);
    }

    public void notifyUiLoaded(){
        this.uiLoaded=true;
    }
}
