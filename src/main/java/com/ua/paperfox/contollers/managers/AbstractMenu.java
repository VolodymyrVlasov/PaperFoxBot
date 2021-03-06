package com.ua.paperfox.contollers.managers;

import com.ua.paperfox.TelegramBot;
import com.ua.paperfox.contollers.TelegramMessageFactory;
import com.ua.paperfox.models.shop.Order;
import com.ua.paperfox.models.shop.ShoppingCart;
import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.ua.paperfox.services.mailServices.MailStates;

public abstract class AbstractMenu {

    public TelegramMessageFactory telegramMessageFactory;
    public Update update;
    public TelegramCustomer user;
    public ShoppingCart shoppingCart;

    public AbstractMenu(TelegramCustomer user, Update update) {
        this.update = update;
        this.user = user;
        shoppingCart = user.getShoppingCart();
        telegramMessageFactory = new TelegramMessageFactory();
        handleUpdateEvent();
    }

    public abstract void handleUpdateEvent();

    public void setReplyAndChangeStateTo(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(user, update);
    }

    public void setReply(UserStates newState) {
        TelegramCustomer telegramCustomer = new TelegramCustomer(user.getChatID());
        telegramCustomer.setState(newState);
        telegramMessageFactory.createReplyMessage(telegramCustomer, update);
    }

    public void waitForMultiLoaded() {
        if (!user.isKeyboardSend()) {
            setReply(UserStates.ONE_MORE_FILE);
            user.setKeyboardSend(true);
        }
    }

    public void makeOrder() {
        System.out.println("49(AM) new order " + shoppingCart.getOrderId() + " from " +
                shoppingCart.getCustomer().getFirstName() + " " + shoppingCart.getCustomer().getLastName());
        if (new Order(shoppingCart).handleOrder() == MailStates.OK) {
            user.setState(UserStates.ORDER_COMPLETE);
            if (user.isKeyboardSend()) user.setKeyboardSend(false);
            handleUpdateEvent();
            user.clearShoppingCart();
            TelegramBot.getInstance().setUserState(user.getChatID(), UserStates.USER_CONNECTED);
        } else {
            setReply(UserStates.ERROR);
            makeOrder();
        }
    }

    public void setInvalidInputAndGoToState(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(user, update);
    }
}
