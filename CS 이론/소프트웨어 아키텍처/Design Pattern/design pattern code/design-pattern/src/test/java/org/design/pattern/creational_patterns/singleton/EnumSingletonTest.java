package org.design.pattern.creational_patterns.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class EnumSingletonTest {

    @Test
    @DisplayName("Enum 싱글톤 테스트")
    void testEnumSingleton() {
        EnumSingleton instanceOne = EnumSingleton.INSTANCE;
        instanceOne.showMessage();

        // Serialization Test
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enumSingleton.ser"));
            oos.writeObject(instanceOne);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enumSingleton.ser"));
            EnumSingleton instanceTwo = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println("Instance One HashCode: " + instanceOne.hashCode());
            System.out.println("Instance Two HashCode: " + instanceTwo.hashCode());
            System.out.println("Are both instances the same? " + (instanceOne == instanceTwo));

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IO Exception");
        }

        // Reflection Test (This will fail)
        try {
            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            EnumSingleton instanceThree = constructor.newInstance("INSTANCE", 0);
            instanceThree.showMessage();
        } catch (Exception e) {
            System.out.println("Exception occurred during reflection: " + e.getMessage());
        }
    }
}