package com.sanuth.cryptoflow.service;

import com.sanuth.cryptoflow.model.CoinMarketData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    // inject the template as ListOperations
    public void saveAllPrices(List<CoinMarketData> prices) {
        redisTemplate.opsForValue().set("crypto:prices:latest", prices);
    }

    public List<CoinMarketData> getAllPrices() {
        Object data = redisTemplate.opsForValue().get("crypto:prices:latest");
        return (List<CoinMarketData>) data;
    }
}
