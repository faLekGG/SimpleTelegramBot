package com.goloveyko.services;

import com.goloveyko.domain.YandexResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TranslateTweet {
    private static final String Url = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private static final String key = "?key=trnsl.1.1.20190322T073255Z.d5f639e9b9a82087.07874466a2bc772dcc8924e24299959b52a1a429";

    private RestTemplate restTemplate;

    public TranslateTweet(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String translateTweetToRussian(String textOfTweet){
        String lang = "&lang=en-ru";
        //String text = "&text=" + textOfTweet;
        //String resourceUrl = Url + key + lang + text;
        //YandexResponse yandexResponse = restTemplate.getForObject(resourceUrl, YandexResponse.class);

        String resourceUrl = Url + key + lang;
        StringBuilder completeTweet = new StringBuilder();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("text", textOfTweet);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        YandexResponse yandexResponse = restTemplate.postForObject(resourceUrl, request, YandexResponse.class);

        List<String> tweetPieces = yandexResponse.getText();
        for(String tweet : tweetPieces){
            completeTweet.append(tweet).append(" ");
        }

        return String.valueOf(completeTweet);
    }
}
