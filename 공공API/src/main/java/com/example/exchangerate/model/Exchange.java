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

    String cur_unit;

    String ttb;

    String tts;

    String deal_bas_r;

    String bkpr;

    String yy_efee_r;

    String ten_dd_efee_r;

    String kftc_bkpr;

    String kftc_deal_bas_r;

    String cur_nm;

    public Exchange(String result, String cur_unit, String ttb, String tts, String deal_bas_r, String bkpr, String yy_efee_r, String ten_dd_efee_r, String kftc_bkpr, String kftc_deal_bas_r, String cur_nm) {
        this.result = result;
        this.cur_unit = cur_unit;
        this.ttb = ttb;
        this.tts = tts;
        this.deal_bas_r = deal_bas_r;
        this.bkpr = bkpr;
        this.yy_efee_r = yy_efee_r;
        this.ten_dd_efee_r = ten_dd_efee_r;
        this.kftc_bkpr = kftc_bkpr;
        this.kftc_deal_bas_r = kftc_deal_bas_r;
        this.cur_nm = cur_nm;
    }
}
