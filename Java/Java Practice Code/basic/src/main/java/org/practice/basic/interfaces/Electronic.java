package org.practice.basic.interfaces;

public interface Electronic extends Energy {
    // Constant variable
    String LED = "LED";

    // Abstract method
    int getElectricityUse();

    // Static method
    static boolean isEnergyEfficient(String electronicType) {
        return electronicType.equals(LED);
    }

    //Default method
    default void printDescription() {
        System.out.println("Electronic Description");
    }

    //Private method
    default void bar() {
        System.out.print("Hello");
        baz();
        staticBaz();
    }

    private void baz() {
        System.out.println(" world!");
    }

    static void buzz() {
        System.out.print("Hello");
        staticBaz();
//        baz();
    }

    private static void staticBaz() {
        System.out.println(" static world!");
    }


}
