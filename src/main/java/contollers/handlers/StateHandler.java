package contollers.handlers;

import models.products.categories.digitalPrints.NormalPrint;
import models.shop.OrderCart;
import models.users.ChatUser;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import models.utils.services.notifications.FileLoader;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

import static constants.TelegramBotMessages_UA.*;
import static models.users.conditions.UserQueryStates.*;

public class StateHandler {
    private TelegramMessageFactory telegramMessageFactory;
    private Update update;
    private ChatUser user;
    private OrderCart order;
    private int itemCount = 0;


    public StateHandler(ChatUser user, Update update) {
        this.update = update;
        this.user = user;
        order = user.getOrderCart();
        telegramMessageFactory = new TelegramMessageFactory();
        handleUpdateEvent();
    }

    private void handleUpdateEvent() {
        // todo
        //  if  UserSates.ORDER_COMPETE?
        //  setReply with order confirmation and id
        //  else
        //  set UserSates.ERROR
        // todo if KEY_TRY_AGAIN
        //  setReply(UserStates.QUICK_PRINT_DESCRIPTION)
        switch (user.getState()) {
            case USER_CONNECTED -> {
                checkForStartCommand();
                if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                        user.setState(UserStates.SELECT_SIZE_COLOR);
                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                        setReplyAndChangeStateTo(UserStates.CALC_PRODUCT);
                        setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                    }
                }
            }
            case QUICK_PRINT -> checkForStartCommand();
            case SELECT_SIZE_COLOR -> {
                checkForStartCommand();
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        order.addItem(new NormalPrint("A4", false));
                        System.out.println(order.getCustomer().toString() + " -> " + order.getLastItem().toString());
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        order.addItem(new NormalPrint("A4", true));
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        order.addItem(new NormalPrint("A3", false));
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        order.addItem(new NormalPrint("A3", true));
                        user.setState(UserStates.FILE_ADDED);
                    }
                } else setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
            }
            case FILE_ADDED -> {
                checkForStartCommand();
                if (update.hasMessage() && update.getMessage().hasDocument()) {
                    File file = new FileLoader().attachFile(update);
                    System.out.println("Received: " + file.getName() + " Local storage: " + file.getAbsolutePath());
                    //order.getLastItem().attachDesign(file);
                    // normalPrint.attachDesign(file);
                    // todo create class for downloading file
                    // -> download file and add file_path to item
                    // todo attach into current item this file path
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else {
                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                }
            }

            case ONE_MORE_FILE -> {
                checkForStartCommand();
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_ONE_MORE_FILE.toString())) {
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                        user.setState(UserStates.SELECT_SIZE_COLOR);
                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.toString())) {
                        setReplyAndChangeStateTo(UserStates.SEND_ORDER);
                        sendOrder();
                    }
                } else setInvalidInputAndGoToState(UserStates.ONE_MORE_FILE);
            }

            case SEND_ORDER -> {
                checkForStartCommand();
                setReplyAndChangeStateTo(UserStates.ORDER_COMPLETE);
//                if (update.hasCallbackQuery()) {
//                    if (update.getCallbackQuery().equals(KEY_SEND_QUICK_PRINT_ORDER)) {
//                        // todo send order to email
//                        // todo if email sent successful? set UserSates.ORDER_COMPETE
//                        setReplyAndChangeStateTo(UserStates.ORDER_COMPLETE);
//                        user.setState(UserStates.ORDER_COMPLETE);
//                    }
//                }
            }
            case ORDER_COMPLETE -> {
                checkForStartCommand();
                setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
            }
            case ERROR -> checkForStartCommand();
        }
    }

    private void setReplyAndChangeStateTo(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
    }

    private void sendOrder() {
        System.out.println(order.getCustomer().toString() + " -> " + order.getLastItem().toString());
        user.setState(UserStates.ORDER_COMPLETE);
        handleUpdateEvent();
    }

    private void checkForStartCommand() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(RUN_BOT)) {
                setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
            } else {
                setInvalidInputAndGoToState(UserStates.USER_CONNECTED);
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
