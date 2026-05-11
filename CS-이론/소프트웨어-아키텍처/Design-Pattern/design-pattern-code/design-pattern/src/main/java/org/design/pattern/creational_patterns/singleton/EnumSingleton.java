package org.design.pattern.creational_patterns.singleton;

public enum EnumSingleton {
    INSTANCE; // single instance

    public void showMessage() {
        System.out.println("Hello from EnumSingleton! Instance HashCode: " + this.hashCode());

    }
}
