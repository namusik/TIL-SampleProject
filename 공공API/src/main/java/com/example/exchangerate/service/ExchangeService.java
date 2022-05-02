package com.example.exchangerate.service;

import com.example.exchangerate.model.Exchange;
import com.example.exchangerate.repository.ExchangeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public void getRate() {
        // 인증키 (개인이 받아와야함)
        String key = "8Sqtmy1PUqdAdk8yAG2mrxHYzoyry8N5";

        // 파싱한 데이터를 저장할 변수
        String data = "";

        try {

            URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="
                    + key + "&data=AP01");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            data = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(data);

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject rateObject = (JSONObject) jsonArray.get(i);

                Exchange exchange = gson.fromJson(rateObject.toString(), Exchange.class);

                System.out.println("exchange = " + exchange);

                exchangeRepository.save(exchange);

            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
