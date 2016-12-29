package com.knalx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new HomelyBot());
        } catch (TelegramApiException e) {
            BotLogger.error("BOT", e);
        }
        log.info("Bot started.");
    }
}
