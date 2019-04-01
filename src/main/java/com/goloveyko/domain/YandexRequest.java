package com.goloveyko.domain;

public class YandexRequest {
    private String key;
    private String lang;
    private String text;

    public YandexRequest() {

    }

    public YandexRequest(String key, String lang, String text) {
        this.key = key;
        this.lang = lang;
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
