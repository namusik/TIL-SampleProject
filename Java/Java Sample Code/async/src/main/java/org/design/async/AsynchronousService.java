package org.design.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
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
         */
        //Simulate a non-blocking operation
        return CompletableFuture.supplyAsync(() -> {
            /**
             * 비동기적으로 실행될 작업을 정의. 람다식을 사용한다.
             */
            try {
                /**
                 * 현재 스레드 5초간 일시 중지
                 */
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Completed Asynchronous task";
        });
    }
}
