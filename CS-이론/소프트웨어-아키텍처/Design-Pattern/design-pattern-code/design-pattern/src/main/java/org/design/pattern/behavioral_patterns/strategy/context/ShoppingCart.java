package org.design.pattern.behavioral_patterns.strategy.context;

import lombok.Setter;
import org.design.pattern.behavioral_patterns.strategy.strategy_interface.PaymentStrategy;

@Setter
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void checkOut(int amount) {
        paymentStrategy.pay(amount);
    }
}
