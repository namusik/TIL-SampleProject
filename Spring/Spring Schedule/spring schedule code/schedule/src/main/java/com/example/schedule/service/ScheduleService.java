package com.example.schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class ScheduleService {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

//    @Scheduled(fixedDelayString = "${schedule.fixedDelay.milliseconds}")
    public void scheduleFixedDelayTask() throws InterruptedException {
        String name = Thread.currentThread().getName();
        log.info("scheduleFixedDelayTask start :: {}", name);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() throws InterruptedException {
        String name = Thread.currentThread().getName();
        log.info("scheduleFixedRateTask start :: {}", name);
        log.info("threadPoolTaskScheduler pool size: {}", threadPoolTaskScheduler.getPoolSize());
        log.info("threadPoolTaskScheduler active count: {}", threadPoolTaskScheduler.getActiveCount());
        Thread.sleep(5000);
        log.info("scheduleFixedRateTask end :: {}", name);
    }

//    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask2() throws InterruptedException {
        String name = Thread.currentThread().getName();
        log.info("scheduleFixedRateTask2 start :: {}", name);
        log.info("threadPoolTaskScheduler2 pool size: {}", threadPoolTaskScheduler.getPoolSize());
        log.info("threadPoolTaskScheduler2 active count: {}", threadPoolTaskScheduler.getActiveCount());
    }

//    @Scheduled(initialDelay = 20000, fixedRate = 5000)
    public void scheduleInitialDelayTask() {
        log.info("scheduleInitialDelayTask");
    }

//    @Scheduled(cron = "5 * * * * *")
    public void scheduleCronTask() {
        log.info("scheduleCronTask start");
    }
}
