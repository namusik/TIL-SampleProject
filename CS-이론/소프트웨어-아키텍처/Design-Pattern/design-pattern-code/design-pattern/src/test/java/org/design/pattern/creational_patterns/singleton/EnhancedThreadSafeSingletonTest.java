package org.design.pattern.creational_patterns.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedThreadSafeSingletonTest {

    @Test
    @DisplayName("직렬화 싱글톤 테스트")
    void testSerialization() {
        try {
            EnhancedThreadSafeSingleton instance = EnhancedThreadSafeSingleton.getInstance();
            instance.showMessage();

            // Serialize the singleton instance to a file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
            oos.writeObject(instance);
            oos.close();

            // Deserialize the singleton instance from the file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
            EnhancedThreadSafeSingleton instanceTwo = (EnhancedThreadSafeSingleton) ois.readObject();
            ois.close();

            // Display hash codes to verify if both instances are the same
            System.out.println("Instance One HashCode: " + instance.hashCode());
            System.out.println("Instance Two HashCode: " + instanceTwo.hashCode());

            // Check if both instances are the same
            System.out.println("Are both instances the same? " + (instance == instanceTwo));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("e = " + e);
        }
    }

    /**
     * readResolve() 없을 때
     * EnhancedThreadSafeSingleton instance created.
     * Hello from EnhancedThreadSafeSingleton! Instance HashCode: 573926093
     * Instance One HashCode: 573926093
     * Instance Two HashCode: 524223214
     * Are both instances the same? false
     */

    /**
     * readResolve() 있을 때
     * EnhancedThreadSafeSingleton instance created.
     * Hello from EnhancedThreadSafeSingleton! Instance HashCode: 573926093
     * Instance One HashCode: 573926093
     * Instance Two HashCode: 573926093
     * Are both instances the same? true
     */


    @Test
    @DisplayName("reflection 싱글톤 테스트")
    void testReflection() {
        try {
            EnhancedThreadSafeSingleton instanceOne = EnhancedThreadSafeSingleton.getInstance();
            instanceOne.showMessage();

            // Attempt to create a second instance using reflection
            Constructor<EnhancedThreadSafeSingleton> constructor = EnhancedThreadSafeSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true); // Bypass the private constructor

            System.out.println("Attempting to create a second instance via reflection...");
            EnhancedThreadSafeSingleton instanceTwo = constructor.newInstance();
            instanceTwo.showMessage();

            // Display hash codes to verify if both instances are the same
            System.out.println("Instance One HashCode: " + instanceOne.hashCode());
            System.out.println("Instance Two HashCode: " + instanceTwo.hashCode());

            // Check if both instances are the same
            System.out.println("Are both instances the same? " + (instanceOne == instanceTwo));

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * reflection 방지 코드 없으면
     * EnhancedThreadSafeSingleton instance created.
     * Hello from EnhancedThreadSafeSingleton! Instance HashCode: 534666530
     * Attempting to create a second instance via reflection...
     * EnhancedThreadSafeSingleton instance created.
     * Hello from EnhancedThreadSafeSingleton! Instance HashCode: 782689036
     * Instance One HashCode: 534666530
     * Instance Two HashCode: 782689036
     * Are both instances the same? false
     */

    /**
     * reflection 방지 코드 있으면
     * EnhancedThreadSafeSingleton instance created.
     * Hello from EnhancedThreadSafeSingleton! Instance HashCode: 534666530
     * Attempting to create a second instance via reflection...
     * Exception occurred: null
     */
}