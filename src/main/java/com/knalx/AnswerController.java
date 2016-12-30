package com.knalx;

import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @since 29.12.16
 **/
public class AnswerController {

    private LinkedList<QuestionItem> questionItems;

    public AnswerController() {
        questionItems = initQuestions();
        initQuestions();
    }


    ConfigReader configReader = new ConfigReader();

    public String getAnswer(String message, Update update, TelegramLongPollingBot bot) {

        String answ = "";
        if (message.equals("атытожепума")) {
            answ = "Да нет. У меня еще зубы молочные, и когти молочные, и мертвая мама...";
        }
        if (message.equals("ктолучшевсех")) {
            answ = "Оля, лучше всех!";
        }
        if (!questionItems.isEmpty() && questionItems.get(0).getQuest().equals(message)) {
            answ = questionItems.get(0).getAnswer();
            questionItems.remove(0);
            if (questionItems.isEmpty()) {
                sendCatPhoto(update, bot);
            }
        }
        if (message.equals("reset")) {
            questionItems = initQuestions();
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
            list.add("И тебя с наступающим!");

            Random rand = new Random();
            answ = list.get(rand.nextInt(list.size()));
        }
        return answ;
    }

    private void sendCatPhoto(Update update, TelegramLongPollingBot bot) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        sendPhoto.setNewPhoto("Kiss.png", configReader.getCatPhotoStream());
        try {
            bot.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
                "Привет, я помогу тебе узнать пароль,\n" +
                        "Медвежей лапы я сыграю роль,\n" +
                        "Кончено странно с лапой говорить,\n" +
                        "Но ты прикинь, как мне сложно жить!\n" +
                        "\n" +
                        "Давай скорее к делу перейдем\n" +
                        "Попробуй разгадать этот \"литературный прием\"\n" +
                        "Я бы не стал называть это загадкой,\n" +
                        "У Леши получилось не очень то гладко.\n" +
                        "\n" +
                        "_Останки его нашли в янтаре,_\n" +
                        "_Не так уж и холоно ему в декабре._"));
        questionItems.add(new QuestionItem(
                "пушизавр",
                "Отличное начало, надеюсь вторая загадка тоже не подкачала: \n \n" +
                        "_Этот позывной звучит в ночи,_\n" +
                        "_В случае опасности, иль нежности кричи._"

        ));
        questionItems.add(new QuestionItem(
                "уськ",
                "Последний вопрос, и прекращаем опрос: \n \n"+
                "_Ушастый, пушистый, но вовсе не кроль,_ \n" +
                        "_Тебя очень любит это самый король._"

        ));
        questionItems.add(new QuestionItem(
                "кисякинг",
                "C новым годом тебя, \n" +
                        "Поздравляет лапа любя! \n" +
                        "Вот твой подарочный код: *118* \n" +
                        "Обнимает и целует тебя твой кот!"
        ));
        return questionItems;
    }
}
