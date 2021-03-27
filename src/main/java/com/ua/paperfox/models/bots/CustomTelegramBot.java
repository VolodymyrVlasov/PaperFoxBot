package com.ua.paperfox.models.bots;

import com.ua.paperfox.constants.config.ConfigData;
import com.ua.paperfox.contollers.MainMenu;
import com.ua.paperfox.models.shop.ShoppingCart;
import com.ua.paperfox.models.customer.conditions.UserStates;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.postgres.dao.CustomerDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//https://github.com/SergioViacheslaev/MyWizardTelegramBot

public class CustomTelegramBot extends TelegramLongPollingBot {
    private static CustomTelegramBot instance;
    private final Map<Long, TelegramCustomer> users = new HashMap<>();


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

