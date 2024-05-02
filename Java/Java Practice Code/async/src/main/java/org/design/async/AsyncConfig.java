package org.design.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    /**
     * @EnableAsync
     * 애플리케이션 내에서 비동기 처리를 활성화해준다.
     * @Async 어노테니션이 붙은 메소드를 비동기적으로 실행할 수 있도록 해줌.
     */
}
