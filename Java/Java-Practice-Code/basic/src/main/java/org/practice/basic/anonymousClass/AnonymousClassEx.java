package org.practice.basic.anonymousClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnonymousClassEx {
    public static void main(String[] args) {
        Animal animal = new Animal("amy");

        animal.sound();

        Animal dog = new Animal("Romy") {
            @Override
            public void sound() {
                System.out.println("dog");
            }
        };

        dog.sound();

        AnonymousInterface anonymousInterface = new AnonymousInterface() {

            @Override
            public void run() {
                log.info("run");
            }
        };

        anonymousInterface.run();
    }
}
