package models.bots;

import constants.config.ConfigData;
import contollers.MainMenu;
import models.shop.ShoppingCart;
import models.customer.conditions.UserStates;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import models.customer.TelegramCustomer;
import postgres.dao.CustomerDao;
import postgres.dao.impl.CustomerDaoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//https://github.com/SergioViacheslaev/MyWizardTelegramBot

public class CustomTelegramBot extends TelegramLongPollingBot {
    private static CustomTelegramBot instance;
    private final Map<Long, TelegramCustomer> users = new HashMap<>();
    private CustomerDao customerDao = new CustomerDaoImpl();

    public static CustomTelegramBot getInstance() {
        if (instance == null) instance = new CustomTelegramBot();
        return instance;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        if (update.hasMessage() && update.getMessage() != null) {
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery() && update.getCallbackQuery() != null) {
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        if (!users.containsKey(chatId)) {
            TelegramCustomer user = new TelegramCustomer(chatId);
            user.setPassportFields(update);

            try {
                customerDao.createTelegramCustomer(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            users.put(chatId, user);
        }
        if (users.get(chatId).getShoppingCart() == null)
            users.get(chatId).setShoppingCart(new ShoppingCart(users.get(chatId).getCustomer()));
        new MainMenu(users.get(chatId), update);
    }

    public synchronized void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return ConfigData.TLGM_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return ConfigData.TLGM_TOKEN;
    }

    public void setUserState(long userId, UserStates state) {
        users.get(userId).setState(state);
    }
}

