import models.bots.CustomTelegramBot;
import models.shop.Order;
import models.shop.ShoppingCart;
import models.users.Customer;
import models.utils.services.mailServices.FileLoader;
import models.utils.services.mailServices.MailService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(CustomTelegramBot.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
