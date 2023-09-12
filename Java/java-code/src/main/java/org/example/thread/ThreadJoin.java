package org.example.thread;

public class ThreadJoin {
    public static void main(String[] args) {
        ThreadEx gc = new ThreadEx();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;

            //필요한 메모리가 사용할 수 있는 양보다 크거나 전체 메모리의 60%이상을 사용했을 경우 gc를 깨운다.
            if (gc.freeMemory() < requiredMemory || gc.freeMemory() < gc.totalMemory() * 0.4) {
                System.out.println("gc 깨우기");
                gc.interrupt();
                try {
                    System.out.println("gc join");
                    gc.join(100);
                } catch (InterruptedException e) {

                }
            }
            gc.usedMemory += requiredMemory;
            System.out.println("gc.usedMemory = " + gc.usedMemory);
        }
    }

    static class ThreadEx extends Thread {
        final static int MAX_MEMORY = 1000;
        int usedMemory = 0;
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Awaken by interrupt");
                }
                System.out.println("gc 시작");
                gc(); //garbage collection 수행
                System.out.println("gc 끝");
                System.out.println("Garbage Collected. Free Memory : " + freeMemory());
            }
        }

        private void gc() {
            System.out.println("before usedMemory = " + usedMemory);
            usedMemory -= 300;
            if (usedMemory < 0) {
                usedMemory = 0;
            }
            System.out.println("new usedMemory = " + usedMemory);
        }

        public int totalMemory() {
            return MAX_MEMORY;
        }

        public int freeMemory() {
            return MAX_MEMORY - usedMemory;
        }
    }

}
