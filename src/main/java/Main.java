import models.bots.CustomTelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import services.mailServices.EmailTemplator;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            EmailTemplator.getInstance();
            telegramBotsApi.registerBot(CustomTelegramBot.getInstance());
        } catch (TelegramApiRequestException | IOException e) {
            e.printStackTrace();
        }
    }
}
