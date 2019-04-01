package com.goloveyko.services;

import com.goloveyko.constants.ChannelsId;
import com.goloveyko.constants.TwitterCredentials;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TwitterClient {
    //private static final long twitterListId = 803278267113439232L;

    private Twitter twitter;
    private List<Status> statuses;
    private String userName;
    private Date postingDate;

    TwitterClient() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setTweetModeExtended(true)
                            .setOAuthConsumerKey(TwitterCredentials.consumerKey)
                            .setOAuthConsumerSecret(TwitterCredentials.consumerSecret)
                            .setOAuthAccessToken(TwitterCredentials.accesToken)
                            .setOAuthAccessTokenSecret(TwitterCredentials.acccessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
        statuses = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            postingDate = dateFormat.parse("02/11/2012 23:11:11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Status> getUserTweets(int page, int count) {
        Paging paging = new Paging(page, count);
        statuses.clear();

        try {
            List<Status> statusesTemp;
            statusesTemp = twitter.getUserListStatuses(ChannelsId.twitterList, paging);
            for(Status status : statusesTemp){
                if(postingDate.getTime() < status.getCreatedAt().getTime()){
                    statuses.add(status);
                }
            }

            postingDate = new Date();
            /*Status status = twitter.showStatus(getTheLatestTweetId());
            statuses = twitter.getUserTimeline(userName, paging);
            User user = twitter.showUser(userName);
            id = user.getId();
            System.out.println(user.getScreenName() + " : " + id);
            System.out.println(status.getText() + " " + status.getUser());*/
            /*List<UserList> userList = twitter.getUserLists("VitalyGoloveiko");
            for(UserList userList1 : userList)
            System.out.println(userList1.getId() + " = " + userList1.getName());*/
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return statuses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName != null && !userName.isEmpty()) {
            this.userName = userName;
        } else {
            try {
                this.userName = twitter.verifyCredentials().getScreenName();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        this.userName = userName;
    }

}
