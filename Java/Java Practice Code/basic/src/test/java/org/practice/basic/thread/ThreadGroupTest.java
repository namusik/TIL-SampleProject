package org.practice.basic.thread;

import org.junit.jupiter.api.Test;

public class ThreadGroupTest {
    @Test
    void threadGroupTest() {
        var main = Thread.currentThread().getThreadGroup();
        var grp1 = new ThreadGroup("Group1");
        var grp2 = new ThreadGroup("Group2");

        var subGrp1 = new ThreadGroup(grp1, "SubGroup1");
        grp1.setMaxPriority(3);

        var r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        };

        new Thread(grp1, r, "th1").start();
        new Thread(subGrp1, r, "th2").start();
        new Thread(grp2, r, "th3").start();

        main.list();


    }
}
