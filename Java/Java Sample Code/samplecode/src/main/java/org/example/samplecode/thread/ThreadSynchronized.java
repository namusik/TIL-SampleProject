package org.example.samplecode.thread;

public class ThreadSynchronized {
    public static void main(String[] args) {
        RunnableSync r = new RunnableSync();

        Thread thread1 = new Thread(r);
        thread1.setName("thread1");

        Thread thread2 = new Thread(r);
        thread2.setName("thread2");

        thread1.start();
        thread2.start();

    }

    static class Account {
        private int balance = 1000;

        public int getBalance() {
            return balance;
        }

        public synchronized void withdraw(int money) {

            if (balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                balance -= money;
            }
        }
    }

    static class RunnableSync implements Runnable {
        Account acc = new Account();

        @Override
        public void run() {

            while (acc.getBalance() > 0) {
                // 100, 200, 300 중에 랜덤으로 출금
                int money = (int) (Math.random() * 3 + 1) * 100;
                acc.withdraw(money);

                System.out.println("acc.getBalance() = " + acc.getBalance());
            }
        }
    }
}
