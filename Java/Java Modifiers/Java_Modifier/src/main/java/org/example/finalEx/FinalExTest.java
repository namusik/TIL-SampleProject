package org.example.finalEx;
class FinalExTest {
    public static void main(String[] args) {
        /**
         * 1. final class를 상속할 수 없다.
         * Cannot inherit from final 'org.example.finalEx.Cat'
         */
//        class ExtendEx extends Cat{}

        /**
         * 2. final 클래스의 field가 불변인 것은 아니다.
         */
        Cat finalEx = new Cat();
        finalEx.setName("aaa");
        System.out.println("finalEx.getName() = " + finalEx.getName());
        finalEx.setName("bbb");
        System.out.println("finalEx.getName() = " + finalEx.getName());

        // ----------------------------------------------------------

        /**
         * final이 붙은 method는 override 안된다.
         * 'sound()' cannot override 'sound()' in 'org.example.finalEx.Dog'; overridden method is final
         */
//        class DogExt extends Dog {
//            @Override
//            public void sound() {
//                System.out.println("푸들들");
//            }
//        }

        //-----------------------------------------------------------

        /**
         * 1. primitive variables
         * Cannot assign a value to final variable 'i'
         */
        final int i = 1;
//        i = 2;

        /**
         * 2. reference variables
         * Cannot assign a value to final variable 'dog'
         */
        final Dog dog = new Dog("seoul");
//        dog = new Dog();

        dog.setName("aaa");

        //-----------------------------------------------------------

        /**
         * 3. final fields
         */
        Dog dog2 = new Dog("seoul");
        Dog dog3 = new Dog("pusan");

        /**
         * static final field , 상수는 모든 인스턴스가 동일한 값을 가진다.
         *
         * final field는 생성자를 통해 초기화되는 경우, 인스턴스들이 서로 다른 값을 가질 수 있다.
         * 물론 한번 초기화된 이후에는 변경이 불가능하다.
         */

        System.out.println("dog2 = " + dog2);
        System.out.println("dog3 = " + dog3);
    }
}