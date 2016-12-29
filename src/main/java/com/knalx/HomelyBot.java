package com.knalx;


import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by knalx on 05.04.16.
 */
public class HomelyBot extends TelegramLongPollingBot {

    private HashMap<String, String> hm;

    ConfigReader configReader = new ConfigReader();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            //super.sendAudio();
            String message = update.getMessage().getText().toLowerCase().replaceAll("[^а-яА-Яa-zA-Z]", "");
            SendMessage sm = new SendMessage();
            sm.setChatId(update.getMessage().getChatId().toString());
            sm.setText(getAnswer(message, update));
            this.sendMessage(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println(update);
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
        init();
        String answ = hm.get(message);
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
        if (answ == null) {
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
        String botName = configReader.getProperty("bot.name");
        return botName;//"HomelyBot";
    }

    @Override
    public String getBotToken() {
        return configReader.getProperty("bot.token");
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


    private void init() {
        hm = new HashMap<String, String>();
        hm.put("привет",
                "Привет, Оля.\n" +
                        "Я ваш домаший житель, \n" +
                        "Загадок и тайн хранитель. \n" +
                        "Загадку ты попробуй разгадать, \n" +
                        "А где искать, попробую я подсказать: \n" +
                        " Не вешай нос, и не чихай,\n" +
                        " Мои друзья, помогут так и знай.");
        hm.put("выдра",
                "В меня не верила ты никогда,\n" +
                        "Ведь новомодный я, на кухне я звезда.");
        hm.put("silenthill",
                "Немножка ада есть во мне,\n" +
                        "Ищи в хранилище, на дне.");
        hm.put("пионы",
                "Нас всего трое, разных но похожих,\n" +
                        "Ищи ответ в украшенном подножье");
        hm.put("холокост",
                "Приду на помощь я в часы душевной муки,\n" +
                        "Да и в обычный день займу я твои руки"
        );
        hm.put("panicatthedisco",
                "И за метафорой скрывается ответ,\n" +
                        "Расстроен, счастлив, иль все бред?"
        );
    }
}


