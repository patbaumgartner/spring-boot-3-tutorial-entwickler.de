package com.patbaumgartner.prometheus.grafana.demo;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RestController
public class BeerCustomMetricsController {

    // Metrics principle: "never gauge something you can count, never count something you can time".
    // See https://twitter.com/jon_k_schneider/status/1302984122688663554

    // Timers: measures latencies or frequency of events
    private final Timer timer;

    // Gauge: shows the current value of a meter
    private final Gauge gauge;

    // DistributionSummary: provides distribution of events and a simple summary
    private final DistributionSummary distributionSummary;

    // Counter: reports merely a count over a specified property of an application
    private final Counter aleOrderCounter;
    private final Counter lightOrderCounter;

    private final Random random = new Random();
    private final List<Order> orders = new ArrayList<>();


    BeerCustomMetricsController(MeterRegistry meterRegistry) {
        timer = meterRegistry.timer("beer.orderTimings");

        gauge = Gauge.builder("beer.ordersInQueue", orders, Collection::size)
                .description("Number of unserved orders")
                .register(meterRegistry);

        distributionSummary = DistributionSummary
                .builder("beer.prices")
                .description("Oder price summary distribution")
                .baseUnit("EUR")
                .register(meterRegistry);

        lightOrderCounter = meterRegistry.counter("beer.orders", "type", "light"); // 1 - create a counter
        aleOrderCounter = Counter.builder("beer.orders")    // 2 - create a counter using the fluent API
                .tag("type", "ale")
                .description("The number of orders ever placed for Ale beers")
                .register(meterRegistry);
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private long amount;
        private String type;
        private double price;
    }

    private static Order toOrder(Long l) {
        long amount = l % 5;
        String type = l % 2 == 0 ? "ale" : "light";
        double price = Math.random() * 10;
        return new Order(amount, type, price);
    }

    @GetMapping("/orderBeer")
    public void orderBeer() {
        Order order = toOrder(random.nextLong());

        // Measuring how long it takes to order
        timer.record(() -> {
            try {
                orders.add(order);
                // Record the prices
                distributionSummary.record(order.getPrice());
                Thread.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Count the light and ale beers
        if ("light".equals(order.getType())) {
            lightOrderCounter.increment(1.0);
        } else if ("ale".equals(order.getType())) {
            aleOrderCounter.increment();
        }
    }


    @GetMapping("/countsCallsAspect")
    @Counted("my.custom.aspect.counter")
    public void countCallsAspect() throws Exception {
    }

    @GetMapping("/takesTimeAspect")
    @Timed("my.custom.aspect.timer")
    public void takesTimeAspect() throws Exception {
        Thread.sleep(random.nextInt(5));
    }

}
