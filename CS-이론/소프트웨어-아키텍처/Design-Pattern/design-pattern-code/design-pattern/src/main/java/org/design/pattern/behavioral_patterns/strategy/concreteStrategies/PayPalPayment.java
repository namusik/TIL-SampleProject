package org.design.pattern.behavioral_patterns.strategy.concreteStrategies;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.design.pattern.behavioral_patterns.strategy.strategy_interface.PaymentStrategy;

@Slf4j
@AllArgsConstructor
public class PayPalPayment implements PaymentStrategy {

    private String email;

    @Override
    public void pay(int amount) {
        log.info("PayPal paid amount {}", amount);
    }
}
