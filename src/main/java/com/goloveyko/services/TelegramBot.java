package com.goloveyko.services;

import com.goloveyko.constants.ChannelsId;
import com.goloveyko.constants.TelegramCredentials;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
    }

    @Override
    public String getBotUsername() {
        return TelegramCredentials.botUserName;
    }

    @Override
    public String getBotToken() {
        return TelegramCredentials.botToken;
    }

    public void sendMessage(String message_text) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(ChannelsId.telegramChannel)
                //.setParseMode("markdown")
                .setParseMode("html")
                .setText(message_text);

        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
