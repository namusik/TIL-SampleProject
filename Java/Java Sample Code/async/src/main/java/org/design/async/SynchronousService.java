package org.design.async;

import org.springframework.stereotype.Service;

@Service
public class SynchronousService {
    public String executeSync() {
        // simulate a blocking operation
        try {
            /**
             * 블로킹 작업을 시뮬레이션하기 위해 사용됩니다.
             * 실제 애플리케이션에서는 데이터베이스 쿼리, 네트워크 호출 등 실제 블로킹 작업을 수행
             */
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            /**
             * InterruptedException이 발생하면 현재 스레드의 인터럽트 상태를 설정합니다.
             * 이는 스레드가 중단되어야 함을 나타내
             */
            Thread.currentThread().interrupt();
        }
        return "Completed Synchronous Task";
    }
}
