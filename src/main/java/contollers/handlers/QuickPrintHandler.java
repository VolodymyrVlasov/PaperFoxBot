package contollers.handlers;

import contollers.Controller;
import models.products.categories.digitalPrints.PlainPrint;
import models.shop.OrderCart;
import models.users.TelegramUser;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import models.utils.services.notifications.FileLoader;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

import static constants.TelegramBotMessages_UA.*;
import static models.users.conditions.UserQueryStates.*;

public class QuickPrintStateHandler {
    private TelegramMessageFactory telegramMessageFactory;
    private Update update;
    private TelegramUser user;
    private OrderCart orderCart;
    private int itemCount = 0;

    public QuickPrintStateHandler(TelegramUser user, Update update) {
        this.update = update;
        this.user = user;
        orderCart = user.getOrderCart();
        telegramMessageFactory = new TelegramMessageFactory();
        handleUpdateEvent();
    }

    private void handleUpdateEvent() {
        switch (user.getState()) {
//            case USER_CONNECTED -> {
//                checkForStartCommand();
//                if (update.hasCallbackQuery()) {
//                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
//                        user.setState(UserStates.QUICK_PRINT);
//                        handleUpdateEvent();
//                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
//                        setReplyAndChangeStateTo(UserStates.CALC_PRODUCT);
//                        user.setState(UserStates.USER_CONNECTED);
//                    }
//                }
//            }
            case QUICK_PRINT_DESCRIPTION -> {
                checkForStartCommand();
                user.setState(UserStates.QUICK_PRINT_DESCRIPTION);
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                user.setState(UserStates.QUICK_PRINT);
                handleUpdateEvent();
            }
            case QUICK_PRINT -> {
                checkForStartCommand();
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                user.setState(UserStates.SELECT_SIZE_COLOR);
//                handleUpdateEvent();
            }

            case SELECT_SIZE_COLOR -> {
                checkForStartCommand();
                user.setState(UserStates.SELECT_SIZE_COLOR);
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                    if (query.equals(KEY_A4_BW.toString()))
                        orderCart.addItem(new PlainPrint("A4", false));
                    else if (query.equals(KEY_A4_CL.toString()))
                        orderCart.addItem(new PlainPrint("A4", true));
                    else if (query.equals(KEY_A3_BW.toString()))
                        orderCart.addItem(new PlainPrint("A3", false));
                    else if (query.equals(KEY_A3_CL.toString()))
                        orderCart.addItem(new PlainPrint("A3", true));
                    user.setState(UserStates.FILE_ADDED);
                } else setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
            }

            case FILE_ADDED -> {
                user.setState(UserStates.FILE_ADDED);
                checkForStartCommand();
                if (update.hasMessage() && update.getMessage().hasDocument()) {
                    File file = new FileLoader().attachFile(update);
                    // TODO
                    //  1 file format and mimetype validation
                    //  2 handle if attache more than one file
//                    System.out.println("Received: " + file.getName() + " Local storage: " + file.getAbsolutePath());
                    user.getOrderCart().getLastItem().attachDesign(file);
                    System.out.println("\n-> ORDER CART AT 87 STROKE \n" + orderCart.toString());
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else {
                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                }
            }

            case ONE_MORE_FILE -> {
                user.setState(UserStates.ONE_MORE_FILE);
                checkForStartCommand();
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_ONE_MORE_FILE.toString())) {
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                        user.setState(UserStates.SELECT_SIZE_COLOR);
                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.toString())) {
                        setReplyAndChangeStateTo(UserStates.SEND_ORDER);
                        user.setState(UserStates.SEND_ORDER);
                        handleUpdateEvent();
                    }
                } else setInvalidInputAndGoToState(UserStates.ONE_MORE_FILE);
            }

            case SEND_ORDER -> {
                user.setState(UserStates.SEND_ORDER);
                checkForStartCommand();
                sendOrder();
            }
            case ORDER_COMPLETE -> {
                checkForStartCommand();
                setReplyAndChangeStateTo(UserStates.ORDER_COMPLETE);
                user.setState(UserStates.USER_CONNECTED);
            }
            case ERROR -> checkForStartCommand();
        }
    }

    private void setReplyAndChangeStateTo(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
    }

    private void sendOrder() {
        System.out.println(orderCart.getCustomer().toString() + " -> " + orderCart.getLastItem().toString());
        // todo send order to email
        //  if email sent successful? set UserSates.ORDER_COMPETE
        user.setState(UserStates.ORDER_COMPLETE);
        handleUpdateEvent();
    }

    private void checkForStartCommand() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(RUN_BOT)) {
                new Controller(user, update);
//                setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
            } else {
                setInvalidInputAndGoToState(UserStates.USER_CONNECTED);
                new Controller(user, update);
            }
        }
    }

    private void setInvalidInputAndGoToState(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOICE, update);
        user.setState(newState);
    }

    private void setInvalidFile(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_FILE, update);
        user.setState(newState);
    }
}
