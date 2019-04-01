package com.goloveyko.utils;

import twitter4j.Status;

public class MessageUtility {

    private MessageUtility() {
    }

    public static boolean isLanguageEn(Status status) {
        return status.getLang().equalsIgnoreCase("en");
    }

    public static boolean isResponseToTweet(Status status) {
        return status.getInReplyToUserId() != -1;
    }
}
