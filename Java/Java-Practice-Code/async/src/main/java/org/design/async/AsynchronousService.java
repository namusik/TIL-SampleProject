package org.design.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsynchronousService {
    @Async
    /**
     * 아래 함수가 비동기적으로 실행되어야함을 나타냄.
     * 이 함수는 호출한 스레드와는 별도의 스레드에서 실행된다.
     */
    public CompletableFuture<String> executeAsync() {
        /**
         * CompletableFuture : Java Future를 확장한 것. 비동기 연산의 결과를 나타냄
         * 비동기 작업이 완료되었을 때의 결과를 얻거나, 작업 완료 후 추가작업을 연쇄적으로 수행할 수 있음
         * 연산이 완료될 때까지 기다리지 않고 즉시 반환된다.
         * 메소드 실행이 끝나면, 'CompletableFuture'는 해당 결과를 갖게 된다.
         */
        log.info("비동기 작업 시작, 스레드 이름 : {}", Thread.currentThread().getName());


        try {
            /**
             * 현재 스레드 5초간 일시 중지
             */
            Thread.sleep(5000);
            log.info("비동기 작업 종료, 스레드 이름 : {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("비동기 작업 중 인터럽트 발생");
        }

        return CompletableFuture.completedFuture("Completed Asynchronous task");
    }

    @Async
    public CompletableFuture<String> executeAsync2() {

        log.info("비동기 작업2 시작, 스레드 이름 : {}", Thread.currentThread().getName());


        try {
            Thread.sleep(9000);
            log.info("비동기 작업2 종료, 스레드 이름 : {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("비동기 작업2 중 인터럽트 발생");
        }

        return CompletableFuture.completedFuture("Completed Asynchronous task2");
    }

    public void additionalWork() {
        log.info("추가 작업 수행중, 스레드 이름 : {}", Thread.currentThread().getName());
    }
}
