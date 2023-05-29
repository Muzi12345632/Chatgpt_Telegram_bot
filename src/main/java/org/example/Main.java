package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Master_bot bot = new Master_bot();
            botsApi.registerBot(bot);
            bot.SendAnim(5822826384L, "CgACAgIAAxkBAAIBDGR05gWWGTPupT8JUWm6kw5t8dTQAAI-EgACLDlBSNytvXBQPGoJLwQ");
            //bot.SendAnimation(5822826384L, "CgACAgIAAxkBAAIBDGR05gWWGTPupT8JUWm6kw5t8dTQAAI-EgACLDlBSNytvXBQPGoJLwQ");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}