package com.goloveyko.domain;

public class YandexPostRq {
    String text;

    public  YandexPostRq() {

    }

    public YandexPostRq(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
