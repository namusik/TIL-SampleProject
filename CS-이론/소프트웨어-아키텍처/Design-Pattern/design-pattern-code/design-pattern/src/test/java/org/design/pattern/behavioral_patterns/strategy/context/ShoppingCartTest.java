package org.design.pattern.behavioral_patterns.strategy.context;

import org.design.pattern.behavioral_patterns.strategy.concreteStrategies.CreditCardPayment;
import org.design.pattern.behavioral_patterns.strategy.concreteStrategies.PayPalPayment;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

    @Test
    void testCreditCardPayment() {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setPaymentStrategy((new CreditCardPayment("richard", "123412341234")));

        shoppingCart.checkOut(100);
    }

    @Test
    void testPayPalPayment() {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new PayPalPayment("aaa@naver.com"));

        cart.checkOut(100);
    }
}