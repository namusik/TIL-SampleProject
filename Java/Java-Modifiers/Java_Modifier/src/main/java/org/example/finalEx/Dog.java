package org.example.finalEx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Dog {
    private String name;
    private int age;

    //1. 선언과 동시에 초기화
    static final int HEIGHT = 13;

    //2.static 초기화 블록에서 초기화
    static final int WEIGHT;
    static {
        WEIGHT = 3;
    }

    //1. 선언과 동시에 초기화
    private final String type = "animal";

    //2. instance 초기화 블록에서 초기화
    private final String master;
    {
        master = "aaa";
    }

    //3. 생성자에서 초기화
    private final String address;

    public Dog(String address) {
        this.address = address;
    }

    /**
     * final이 붙은 field는 한번 초기화되었기에 값을 변경 불가.
     * 따라서 setter 불가. getter만 가능.
     * Cannot assign a value to final variable 'type'
     */
//    public int setType(String type) {
//        this.type = type;
//    }

    public final void sound() {
        System.out.println("왈왈");
    }

    public void run(final int speed) {
        /**
         * method의 final argument는 값이 바뀔 수 없다.
         */
//        speed = 3;
    }
}
