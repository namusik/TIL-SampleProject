package org.example.samplecode.process;

/**
 * Runtime 클래스 예제
 */
public class ProcessTest1 {
    String[] s = {"Hello", "world"};

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();

        //JVM에서 이용가능한 코어 개수 확인
        System.out.println("cpu core 개수 = " + r.availableProcessors());

        //jvm memory 확인
        System.out.println("r.totalMemory() = " + r.totalMemory());
        System.out.println("r.freeMemory() = " + r.freeMemory());

        for (int i = 0; i <= 100000; i++) {
            // 10만개 객체 생성
            new ProcessTest1();
        }
        System.out.println("객체 생성 이후 --------------");
        System.out.println("r.totalMemory() = " + r.totalMemory());
        System.out.println("r.freeMemory() = " + r.freeMemory());

        //JVM 가비지 컬렉터 실행.
        //호출된다고 바로 참조가 끊어진 객체들을 지우진 않음.
        r.gc();

        System.out.println("GC 이후 --------------");
        System.out.println("r.totalMemory() = " + r.totalMemory());
        System.out.println("r.freeMemory() = " + r.freeMemory());

    }
}
