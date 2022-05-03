package com.example.exchangerate.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String result;

    String curUnit;

    String ttb;

    String tts;

    String dealBasR;

    String bkpr;

    String yyEfeeR;

    String tenDdEfeeR;

    String kftcBkpr;

    String kftcDealBasR;

    String curNm;

    public Exchange(String result, String curUnit, String ttb, String tts, String dealBasR, String bkpr, String yyEfeeR, String tenDdEfeeR, String kftcBkpr, String kftcDealBasR, String curNm) {
        this.result = result;
        this.curUnit = curUnit;
        this.ttb = ttb;
        this.tts = tts;
        this.dealBasR = dealBasR;
        this.bkpr = bkpr;
        this.yyEfeeR = yyEfeeR;
        this.tenDdEfeeR = tenDdEfeeR;
        this.kftcBkpr = kftcBkpr;
        this.kftcDealBasR = kftcDealBasR;
        this.curNm = curNm;
    }

    public Double calculate(Double cur) {

        return cur * Double.parseDouble(this.bkpr.replace(",", ""));
    }
}
