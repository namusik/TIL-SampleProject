package com.example.restclient;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.util.Map;

@SpringBootApplication
public class RestclientApplication {
    @Bean
    ApplicationRunner init(ErApi erApi) {//Bean이 붙은 메서드에서 다른 Bean을 주입받을 때는 @autowired 안 넣어도 됨
        return args -> {
            //RestTemplate 방식
            RestTemplate restTemplate = new RestTemplate();
            //restTemplate은 한번 bean으로 정의해서 클래스 안에서 써도 된다.
            Map<String, Map<String, Double>> forObject = restTemplate.getForObject("https://open.er-api.com/v6/latest", Map.class);
            System.out.println("forObject = " + forObject.get("rates").get("KRW"));

            //WebClient
            WebClient webClient = WebClient.create("https://open.er-api.com");
            Map<String, Map<String, Double>> block = webClient.get().uri("/v6/latest").retrieve().bodyToMono(Map.class).block();
            System.out.println("block = " + block.get("rates").get("KRW"));

            //Http interface
            HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();

            ErApi client = httpServiceProxyFactory.createClient(ErApi.class);
            Map<String, Map<String, Double>> clientLatest = client.getLatest();
            System.out.println("clientLatest = " + clientLatest.get("rates").get("KRW"));

            //Http interface bean
            Map<String, Map<String, Double>> res4 = erApi.getLatest();
            System.out.println("res4 = " + res4.get("rates").get("KRW"));
        };

    }

    @Bean
    ErApi erApi() {
        WebClient webClient = WebClient.create("https://open.er-api.com");
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();

        return httpServiceProxyFactory.createClient(ErApi.class);
    }


    interface ErApi {
        @GetExchange("/v6/latest")
        Map getLatest();
    }



    public static void main(String[] args) {
        SpringApplication.run(RestclientApplication.class, args);
    }

}
