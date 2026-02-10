package com.sanuth.cryptoflow.controller;

import com.sanuth.cryptoflow.model.CoinMarketData;
import com.sanuth.cryptoflow.service.MarketDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/live")
public class MarketDataController {

    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService){
        this.marketDataService=marketDataService;
    }

    @GetMapping
    public ResponseEntity<List<CoinMarketData>> getMarketPriceList(){
        List<CoinMarketData> response=this.marketDataService.getPriceDataList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}