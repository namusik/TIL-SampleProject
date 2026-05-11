package org.practice.basic.dataType.string;

public class StringCreate {
    public static void main(String[] args) {
        // 문자열 리터럴
        String s1 = "Hello";

        // new 키워드 사용
        String s2 = new String("Hello");

        // StringBuilder
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        String s5 = sb.toString();

        // StringBuffer
        StringBuffer buffer = new StringBuffer("Hello");
        // 문자열에 " World" 추가
        buffer.append(" World");
        // 특정 위치에 문자열 삽입
        buffer.insert(5, ",");
        // 특정 위치의 문자열 삭제
        buffer.delete(5, 6);
        // 문자열 뒤집기
        buffer.reverse();
        // 문자열의 내용을 String으로 변환
        String result = buffer.toString();

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("sb = " + sb);
        System.out.println("s5 = " + s5);
        System.out.println("buffer = " + buffer);
        System.out.println("result = " + result);

    }
}
