package org.design.pattern.creational_patterns.singleton;

import org.design.pattern.creational_patterns.singleton.basic_singleton.Button;
import org.design.pattern.creational_patterns.singleton.basic_singleton.Label;
import org.design.pattern.creational_patterns.singleton.basic_singleton.TextField;
import org.design.pattern.creational_patterns.singleton.basic_singleton.Theme;
import org.junit.jupiter.api.Test;

class SingletonTest {

    @Test
    void testSingleton() {
        Button button = new Button("Submit");
        TextField textField = new TextField("Enter your name");
        Label label = new Label("Username");

        button.display();
        textField.display();
        label.display();

        Theme.getInstance().setThemeColor("dark");

        button.display();
        textField.display();
        label.display();
    }
}