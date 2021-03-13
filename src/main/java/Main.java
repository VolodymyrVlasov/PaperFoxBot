
import freemarker.template.Configuration;
import freemarker.template.Version;

import constants.config.ConfigData;

import models.bots.CustomTelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import services.mailServices.EmailTemplator;
import services.mailServices.MailService;

import services.mailServices.LetterTemplates;


public class Main {

    public static void main(String[] args) {

        EmailTemplator.getInstance();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(CustomTelegramBot.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
