package org.design.pattern.creational_patterns.singleton.basic_singleton;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextField {
    private String text;

    public void display() {
        String themeColor = Theme.getInstance().getThemeColor();
        System.out.println(themeColor + "-" + text);
    }

}
