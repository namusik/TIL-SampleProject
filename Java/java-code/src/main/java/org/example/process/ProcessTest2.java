package org.example.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ProcessTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime r = Runtime.getRuntime();

        Process p1 = r.exec("notepad");

        // 3초간 기다림
        p1.waitFor(1, TimeUnit.SECONDS);
        // process kill
        p1.destroy();

        ProcessBuilder pb1 = new ProcessBuilder("notepad", "a.txt");
        Process p2 = pb1.start();
        p2.waitFor(3, TimeUnit.SECONDS);
        p2.destroy();
        
        //ping 명령 실행하고 문자열 결과 리턴
        Process p3 = r.exec("ping 127.0.0.1");
        //inputstream으로 받음
        InputStream inputStream = p3.getInputStream();
        // inputstream을 한 바이트 씩 InputStreamReader로 읽음.
        // 재결합 시켜서 BufferedReader로 넣어줌
        // 한 라인씩 읽으려고.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (String str; (str = bufferedReader.readLine()) != null; ) {
            System.out.println(str);
        }
        System.out.println("p3.exitValue() = " + p3.exitValue());

        Thread.sleep(3000);

        ProcessBuilder pb2 = new ProcessBuilder("ping", "127.0.0.1");
        Process p4 = pb2.start();
        //inputstream으로 받음
        InputStream inputStream2 = p4.getInputStream();
        // inputstream을 한 바이트 씩 InputStreamReader로 읽음.
        // 재결합 시켜서 BufferedReader로 넣어줌
        // 한 라인씩 읽으려고.
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
        for (String str; (str = bufferedReader2.readLine()) != null; ) {
            System.out.println(str);
        }
        System.out.println("p3.exitValue() = " + p4.exitValue());



    }
}
