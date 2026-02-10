package com.sanuth.cryptoflow.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "coingecko.api")
public class CoinGeckoApiConfig {

    private String baseUrl;
    private String marketDataUrl;
    private String apiKey;
    private String coinlist;

    public String getCoinlist() {
        return coinlist;
    }

    public void setCoinlist(String coinlist) {
        this.coinlist = coinlist;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getMarketDataUrl() {
        return marketDataUrl;
    }

    public void setMarketDataUrl(String marketDataUrl) {
        this.marketDataUrl = marketDataUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
