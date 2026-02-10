package com.sanuth.cryptoflow.connector;

import com.sanuth.cryptoflow.config.CoinGeckoApiConfig;
import com.sanuth.cryptoflow.model.CoinMarketData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class MarketDataConnector {

    private final WebClient webClient;
    private final CoinGeckoApiConfig apiConfig;

    private MarketDataConnector( CoinGeckoApiConfig apiConfig){

        this.apiConfig=apiConfig;
        this.webClient = WebClient.builder()
                .baseUrl(apiConfig.getBaseUrl())
                .build();
    }


    public List<CoinMarketData> getPriceList(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiConfig.getMarketDataUrl())
                        .queryParam("symbols", apiConfig.getCoinlist())
                        .queryParam("vs_currency","usd")
                        .build())
                .header("x-cg-demo-api-key", apiConfig.getApiKey())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CoinMarketData>>() {})
                .block();

    }
}
