import models.bots.CustomTelegramBot;
import models.users.conditions.UserQueryStates;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getenv("TLGM_TOKEN"));

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(CustomTelegramBot.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
