package com.example.tddbook.money;

public class Dollar {
    private int amount;
    public Dollar(int value) {
        this.amount = value;
    }

    public int time(int i) {
        return amount*i;
    }
}
