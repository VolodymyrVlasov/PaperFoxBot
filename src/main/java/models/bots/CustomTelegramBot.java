package models.bots;

import constants.wallets.Admin;
import contollers.Controller;
import models.shop.OrderCart;
import models.users.conditions.UserStates;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import models.users.TelegramUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://github.com/SergioViacheslaev/MyWizardTelegramBot

public class CustomTelegramBot extends TelegramLongPollingBot {
    private static CustomTelegramBot instance;
    private final Map<Long, TelegramUser> users = new HashMap<>();


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
            TelegramUser user = new TelegramUser(chatId);
            user.setPassportFields(update);
            users.put(chatId, user);
        }
        if (users.get(chatId).getOrderCart() == null)
            users.get(chatId).setOrderCart(new OrderCart(users.get(chatId).getCustomer()));
        new Controller(users.get(chatId), update);
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
        return Admin.TLGM_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return Admin.TLGM_TOKEN;
    }

    public void setUserState(long userId, UserStates state) {
        users.get(userId).setState(state);
    }
}

