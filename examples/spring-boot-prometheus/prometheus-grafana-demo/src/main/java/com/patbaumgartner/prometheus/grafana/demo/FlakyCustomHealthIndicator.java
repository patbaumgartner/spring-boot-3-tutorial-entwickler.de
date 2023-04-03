package com.patbaumgartner.prometheus.grafana.demo;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FlakyCustomHealthIndicator extends AbstractHealthIndicator {

    private Random random = new Random();

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        switch (random.nextInt(3)) {
            case 1:
                builder.up();
                break;
            case 2:
                builder.down();
                break;
            case 3:
                builder.outOfService();
                break;
            default:
                builder.unknown();
                break;
        }
        builder.withDetail("message", "Hello from FlakyCustomHealthIndicator");
    }
}
