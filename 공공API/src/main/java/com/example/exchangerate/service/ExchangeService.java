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
        String key = "";

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

//            GsonBuilder builder = new GsonBuilder();
//            builder.setPrettyPrinting();
//            Gson gson = builder.create();

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject rateObject = (JSONObject) jsonArray.get(i);

                String result = rateObject.get("result").toString();
                String curUnit = rateObject.get("cur_unit").toString();
                String ttb = rateObject.get("ttb").toString();
                String tts = rateObject.get("tts").toString();
                String dealBasR = rateObject.get("deal_bas_r").toString();
                String bkpr = rateObject.get("bkpr").toString();
                String yyEfeeR = rateObject.get("yy_efee_r").toString();
                String tenDdEfeeR = rateObject.get("ten_dd_efee_r").toString();
                String kftcBkpr = rateObject.get("kftc_bkpr").toString();
                String kftcDealBasR = rateObject.get("kftc_deal_bas_r").toString();
                String curNm = rateObject.get("cur_nm").toString();

                Exchange exchange = new Exchange(result, curUnit, ttb, tts, dealBasR, bkpr, yyEfeeR, tenDdEfeeR, kftcBkpr, kftcDealBasR, curNm);
//                Exchange exchange = gson.fromJson(rateObject.toString(), Exchange.class);

                exchangeRepository.save(exchange);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Double calculate(Double cur, String unit) {
        Exchange exchange = exchangeRepository.findByCurUnit(unit).orElse(null);

        return exchange.calculate(cur);

    }
}
