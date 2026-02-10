package com.sanuth.cryptoflow.cache;

import com.sanuth.cryptoflow.controller.PriceSseController;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class PricesSubscriber implements MessageListener {

    private final PriceSseController priceSseController;

    public PricesSubscriber(PriceSseController priceSseController) {
        this.priceSseController = priceSseController;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        priceSseController.sendUpdateEvent();
    }
}
