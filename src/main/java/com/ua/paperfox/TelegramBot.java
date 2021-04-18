package com.ua.paperfox;

import com.ua.paperfox.constants.config.ConfigData;
import com.ua.paperfox.contollers.MainMenu;
import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.models.customer.conditions.UserStates;
import com.ua.paperfox.models.shop.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static TelegramBot instance;
    private final Map<Long, TelegramCustomer> users = new HashMap<>();


    public static TelegramBot getInstance() {
        if (instance == null) instance = new TelegramBot();
        return instance;
    }

    Logger logger = LoggerFactory.getLogger(TelegramBot.class);

//    private String botUsername;
//    private String botToken;

    static {
        ApiContextInitializer.init();
    }

//    private TelegramBot() {
//        this.botToken = "1708930091:AAFnuvy2bKSFJE0DeXHz-GSdeIUzTK3uETY";
//        this.botUsername = "test_paper_fox_bot";
//    }

    @PostConstruct
    public void addBot() throws TelegramApiRequestException {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("hello");
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