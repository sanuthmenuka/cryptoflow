package com.sanuth.cryptoflow.scheduling;

import com.sanuth.cryptoflow.connector.MarketDataConnector;
import com.sanuth.cryptoflow.model.CoinMarketData;
import com.sanuth.cryptoflow.service.MarketDataService;
import com.sanuth.cryptoflow.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketDataScheduler {

    private final MarketDataConnector marketDataConnector;
    private final RedisService redisService;
    private final StringRedisTemplate stringRedisTemplate;
    private volatile boolean uiLoaded=false;

    public MarketDataScheduler(MarketDataConnector marketDataConnector,RedisService redisService,StringRedisTemplate stringRedisTemplate){
        this.marketDataConnector=marketDataConnector;
        this.redisService=redisService;
        this.stringRedisTemplate=stringRedisTemplate;
    }
    @Scheduled(fixedRate = 60000,initialDelay = 10000)
    public void scheduleData(){
//        if(!uiLoaded){
//            return;
//        }
        System.out.println("Task running");
        List<CoinMarketData> coinMarketData=this.marketDataConnector.getPriceList();
        // call api
        // redisservice.updatelistdata()
        redisService.saveAllPrices(coinMarketData);
        stringRedisTemplate.convertAndSend("crypto:prices:updates", "updated");

    }

    public void notifyUiLoaded(){
        this.uiLoaded=true;
    }
}
