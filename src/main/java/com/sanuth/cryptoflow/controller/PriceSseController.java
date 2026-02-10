package com.sanuth.cryptoflow.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class PriceSseController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/sse/prices")
    public SseEmitter streamPrices() {

        SseEmitter emitter = new SseEmitter(0L); // no timeout
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(e -> emitters.remove(emitter));

        return emitter;
    }

    public void sendUpdateEvent() {
        List<SseEmitter> dead = new ArrayList<>();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("prices_updated")
                        .data("updated"));
            } catch (Exception e) {
                dead.add(emitter);
            }
        }

        emitters.removeAll(dead);
    }
}
