package com.goloveyko.services;

import com.goloveyko.utils.MessageUtility;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.List;

@Service
public class ScheduleTelegramPosting {

    private TelegramBot telegramBot;
    private TwitterClient twitterClient;
    private TranslateTweet translateTweet;

    ScheduleTelegramPosting(TelegramBot telegramBot, TwitterClient twitterClient, TranslateTweet translateTweet) {
        this.telegramBot = telegramBot;
        this.twitterClient = twitterClient;
        this.translateTweet = translateTweet;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void postTweetsToTelegram(){
        List<Status> statuses = twitterClient.getUserTweets(1, 5);

        for(Status status : statuses) {
            constructMessage(status);
        }
    }

    private void constructMessage(Status status) {

        if(!status.isRetweet() && !MessageUtility.isResponseToTweet(status)) {
            String text = status.getText();

            if (MessageUtility.isLanguageEn(status)) {
                telegramBot.sendMessage("<b>" + status.getUser().getName() + "</b> сделал новую публикацию!\n\n"
                        + translateTweet.translateTweetToRussian(text));
            } else {
                telegramBot.sendMessage("<b>" + status.getUser().getName() + "</b> сделал новую публикацию!\n\n"
                        + text);
            }
        } else if(status.isRetweet() && !MessageUtility.isResponseToTweet(status)){
            String authorOfTheTweet = status.getRetweetedStatus().getUser().getScreenName();
            String textOfTheTweet = status.getRetweetedStatus().getText();

            if (MessageUtility.isLanguageEn(status)) {
                telegramBot.sendMessage("<b>" + status.getUser().getName() + "</b> ретвитнул пост от <b>" + authorOfTheTweet + "</b>!\n\n"
                        + translateTweet.translateTweetToRussian(textOfTheTweet));
            } else {
                telegramBot.sendMessage("<b>" + status.getUser().getName() + "</b> ретвитнул пост от <b>" + authorOfTheTweet + "</b>!\n\n"
                        + textOfTheTweet);
            }
        }
    }
}
