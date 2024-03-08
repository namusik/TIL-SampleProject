package org.design.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyController {
    private final SynchronousService synchronousService;
    private final AsynchronousService asynchronousService;
    private final BlockingIOService blockingIOService;

    @GetMapping("/sync")
    public String sync() {
        log.info("메인 스레드 계속 실행, 스레드 이름 : {}", Thread.currentThread().getName());
        String syncResult = synchronousService.executeSync();

        log.info("동기 작업 요청 후, 메인 스레드에서 다른 작업 수행중, 스레드 이름 : {}", Thread.currentThread().getName());
        synchronousService.additionalWork();

        log.info("모든 작업 종료, 스레드 이름 : {}", Thread.currentThread().getName());

        return syncResult;
    }

    @GetMapping("/async")
    public CompletableFuture<String> async() {
        log.info("메인 스레드 계속 실행, 스레드 이름 : {}", Thread.currentThread().getName());

        // 비동기 작업 시작
        CompletableFuture<String> asyncResult = asynchronousService.executeAsync();

        // 비동기 작업2 시작
        CompletableFuture<String> asyncResult2 = asynchronousService.executeAsync2();


        // 비동기 작업 진행되는 동안 다른 작업 수행
        log.info("비동기 작업 요청 후, 메인 스레드에서 다른 작업 수행중, 스레드 이름 : {}", Thread.currentThread().getName());
        asynchronousService.additionalWork();

        /**
         * thenApply() 는 CompletableFuture의 실행이 완료되었을 때 호출
         * 비동기 작업의 결과 result를 입력으로 받아 새로운 값을 계산하거나 다른 형태의 결과로 반환할 수 있다.
         * 첫번 째 return은 async() 메소드의 반환 값을 지정하는 것이다.
         * 두번 째 return은 thenApply 메소드에 전달된 람다 함수의 return 값이다.
         */
        asyncResult2.thenApply(result -> {
            log.info("비동기 작업2의 결과, 스레드 이름 : {}", Thread.currentThread().getName());
            return result;
        });

        return asyncResult.thenApply(result -> {
            // 이 작업을 수행하는 스레드 역시 @Async 작업을 수행했던 스레드에서 하고 있음.
            log.info("비동기 작업의 결과, 스레드 이름 : {}", Thread.currentThread().getName());
            return result;
        });
    }

    @GetMapping("/blocking")
    public String blocking() {
        log.info("메인 스레드 계속 실행, 스레드 이름 : {}", Thread.currentThread().getName());

        String response = blockingIOService.blocking();

        blockingIOService.additionalWork();

        return response;
    }

}
