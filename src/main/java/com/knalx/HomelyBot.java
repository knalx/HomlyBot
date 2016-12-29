package com.knalx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private LinkedList<QuestionItem> questionItems;

    Logger log = LoggerFactory.getLogger(this.getClass());

    ConfigReader configReader = new ConfigReader();

    public HomelyBot() {
        super();
        questionItems = initQuestions();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText().toLowerCase().replaceAll("[^а-яА-Яa-zA-Z]", "");
            SendMessage sm = new SendMessage();
            sm.setChatId(update.getMessage().getChatId().toString());
            sm.setText(getAnswer(message, update));
            this.sendMessage(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        log.info(update.toString());
    }

    private void sendCatPhoto(Update update) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        sendPhoto.setNewPhoto("Kiss.png", configReader.getCatPhotoStream());
        try {
            this.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
        initQuestions();
        String answ = "";
        if (message.equals("лап")) {
            sendCongratsSong(update);
            answ = "Умничка! Ты все угадала!";
        }
        if (message.equals("гимн")) {
            sendGimnSong(update);
            answ = "Положил руку на сердце и встал.";
        }

        if (message.equals("атытожепума")) {
            sendCongratsSong(update);
            answ = "Да нет. У меня еще зубы молочные, и когти молочные, и мертвая мама...";
        }
        if (message.equals("ктолучшевсех")) {
            sendCongratsSong(update);
            answ = "Оля, лучше всех!";
        }
        if (!questionItems.isEmpty() && questionItems.get(0).getQuest().equals(message)) {
            answ = questionItems.get(0).getAnswer();
            questionItems.remove(0);
            if(questionItems.isEmpty()){
                sendCatPhoto(update);
            }
        }
        if (answ.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add("Не понимаю о чем ты");
            list.add("Эээ.... что?");
            list.add("Ты о чем?");
            list.add("Ничего не понял");
            list.add("Ничего не понимаю");
            list.add("Кто здесь?");
            list.add("Ты мне?");

            Random rand = new Random();
            answ = list.get(rand.nextInt(list.size()));
        }
        return answ;

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

    /**
     * Останки его нашли в янтаре,
     * Не так уж и холоно ему в январе
     * Пушизавр
     * <p>
     * <p>
     * <p>
     * Ушастый, пушистый, но он же не кроль,
     * Тебя очень любит это самый король.
     * Кисякинг
     * <p>
     * Этот позывной звучит в ночи,
     * В случае опасности, иль нежности кричи
     * Уськ
     **/

    private LinkedList<QuestionItem> initQuestions() {
        LinkedList<QuestionItem> questionItems = new LinkedList<>();

        questionItems.add(new QuestionItem(
                "привет",
                "Останки его нашли в янтаре \n" +
                        "Не так уж и холоно ему в январе"
        ));
        questionItems.add(new QuestionItem(
                "пушизавр",
                "Этот позывной звучит в ночи, \n" +
                        "В случае опасности, иль нежности кричи"

        ));
        questionItems.add(new QuestionItem(
                "уськ",
                "Ушастый, пушистый, но вовсе не кроль, \n" +
                        "Тебя очень любит это самый король."

        ));
        questionItems.add(new QuestionItem(
                "кисякинг",
                "C новым годом!"
        ));
        return questionItems;
    }
}


