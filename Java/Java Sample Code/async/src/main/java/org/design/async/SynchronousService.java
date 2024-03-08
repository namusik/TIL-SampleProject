package org.design.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SynchronousService {
    public String executeSync() {
        log.info("동기 작업 시작, 스레드 이름 : {}", Thread.currentThread().getName());

        try {
            /**
             * 블로킹 작업을 시뮬레이션하기 위해 사용됩니다.
             * 실제 애플리케이션에서는 데이터베이스 쿼리, 네트워크 호출 등 실제 블로킹 작업을 수행
             */
            Thread.sleep(5000);
            log.info("동기 작업 종료, 스레드 이름 : {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            /**
             * InterruptedException이 발생하면 현재 스레드의 인터럽트 상태를 설정합니다.
             * 이는 스레드가 중단되어야 함을 나타낸다.
             */
            Thread.currentThread().interrupt();
            log.error("동기 작업 중 인터럽트 발생");
        }
        return "Completed Synchronous Task";
    }

    public void additionalWork() {
        log.info("추가 작업 수행중, 스레드 이름 : {}", Thread.currentThread().getName());
    }
}
