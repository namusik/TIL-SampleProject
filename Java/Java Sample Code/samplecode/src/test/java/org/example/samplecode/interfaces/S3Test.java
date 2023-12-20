package org.example.samplecode.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class S3Test {
    @Test
    void s3test() {
        Tesla s3 = new S3();

        // Electronic 메서드 구현
        s3.getElectricityUse();
        // Electronic default method
        s3.printDescription();
        s3.bar();

        // Energy 메서드 구현
        s3.getName();
        // Energy default method
        s3.hello();

        // 추상클래스 구현 메서드
        s3.tesla();
        // 추상클래스 기본 메서드
        s3.dafaultTesla();
    }
}