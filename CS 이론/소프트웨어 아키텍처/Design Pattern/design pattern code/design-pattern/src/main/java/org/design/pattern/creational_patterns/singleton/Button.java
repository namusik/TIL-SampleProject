package org.design.pattern.creational_patterns.singleton;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Button {
    private String label;

    public void display() {
        String themeColor = Theme.getInstance().getThemeColor();
        System.out.println(themeColor + " - " + label);
    }
}
