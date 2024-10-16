package org.design.pattern.behavioral_patterns.template_method;

import org.junit.jupiter.api.Test;

class BeverageTest {
    @Test
    void beverage() {
        Tea tea = new Tea();
        tea.prepareRecipe();

        Coffee coffee = new Coffee();
        coffee.prepareRecipe();
    }
}