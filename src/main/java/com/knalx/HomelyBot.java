package com.knalx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by knalx on 05.04.16.
 */
public class HomelyBot extends TelegramLongPollingBot {

    Logger log = LoggerFactory.getLogger(this.getClass());
    ConfigReader configReader = new ConfigReader();
    AnswerController answerController;

    public HomelyBot() {
        super();
        answerController = new AnswerController();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText().toLowerCase().replaceAll("[^а-яА-Яa-zA-Z]", "");
            SendMessage sm = new SendMessage();
            sm.setChatId(update.getMessage().getChatId().toString());
            sm.setText(getAnswer(message, update));
            sm.setParseMode(ParseMode.MARKDOWN);
            this.sendMessage(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        log.info(update.toString());
    }

    private void sendCongratsSong(Update update) {
        SendAudio sa = new SendAudio();
        sa.setChatId(update.getMessage().getChatId().toString());
        try {
            super.sendAudio(sa);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendGimnSong(Update update) {
        SendAudio sa = new SendAudio();
        sa.setChatId(update.getMessage().getChatId().toString());
        try {
            super.sendAudio(sa);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private String getAnswer(String message, Update update) {
       return answerController.getAnswer(message, update, this);
    }

    @Override
    public String getBotUsername() {
        String botName = null;
        try {
            botName = configReader.getProperty("bot.name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botName;//"HomelyBot";
    }

    @Override
    public String getBotToken() {
        String token = null;
        try {
            token = configReader.getProperty("bot.token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

}


