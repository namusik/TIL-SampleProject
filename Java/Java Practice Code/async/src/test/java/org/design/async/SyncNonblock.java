package org.design.async;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class SyncNonblock {

    /**
     * MockWebServer를 사용해서 가상의 HTTP 응답을 내려주자
     */
    private MockWebServer mockWebServer;


    /**
     * BeforeEach로 모든 테스트 전에 실행한다.
     */
    @BeforeEach
    void setUp() throws IOException {
        // MockWebServer 인스턴스 초기화를 해준다.
        mockWebServer = new MockWebServer();
        // start()를 호출해서 가상서버를 시작한다.
        mockWebServer.start();
    }

    /**
     * 각 테스트가 종료할 떄마다 호출
     * mockWebServer를 종료한다.
     * @throws IOException
     */
    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }


    @Test
    void testExternalServiceCall() {
        // 서버가 받게 될 HTTP 요청에 대한 응답을 큐에 추가
        // 큐에 추가된 응답은 들어오는 요청 순서대로 반환된다.
        // MockResponse 객체를 만들고 본문 응답을 적어둔다.
        mockWebServer.enqueue(new MockResponse().setBody("Fake response"));

        // mockWebServer의 포트를 가져와서 요청할 가상의 서버 주소 문자열을 만든다.
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());

        // 위에서 만든 baseUrl을 가진 webclient 인스턴스 생성
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        // 가상서버에 GET 요청을 보냄
        // 응답을 Mono<String>으로 받는다.
        // blocK() 메서드로 Mono가 완료될 떄까지 현재 스레드를 블록하고 완료된 결과를 동기적으로 반환한다.
        String result = webClient.get().uri("/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();


    }


}
