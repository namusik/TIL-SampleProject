package org.example.thread;


import javax.swing.*;

import static java.lang.Thread.currentThread;

public class ThreadInterrupt {
    public static void main(String[] args) {
        var thread = new Thread(new RunnableImpl());
        thread.start();
        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());

        var input = JOptionPane.showInputDialog("입력");
        thread.interrupt();
        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());


    }

    static class RunnableImpl implements Runnable {
        
        @Override
        public void run() {
            int i = 10;
            while (!currentThread().isInterrupted() && i != 0) {
                System.out.println(i--);
                for (long x = 0; x < 2500000000L; x++);
            }
            System.out.println("카운트 종료");
        }
    }

}
