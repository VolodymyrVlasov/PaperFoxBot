package contollers.handlers;

import models.users.ChatUser;
import models.users.conditions.UserQueryStates;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static models.users.conditions.UserQueryStates.*;

public class StateHandler {
    TelegramMessageFactory telegramMessageFactory;
    Update update;
    UserStates userState;
    ChatUser user;

    public StateHandler(ChatUser user, Update update) {
        this.update = update;
        this.user = user;
        userState = user.getState();
        telegramMessageFactory = new TelegramMessageFactory();
        switch (userState) {
            case USER_CONNECTED:
                System.out.println("case: USER_CONNECTED -> User " + getChatID(update));
                if (update.hasMessage() && update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/start")) {
                        setReply(user.getState());
                    }
                } else if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                        setReply(UserStates.QUICK_PRINT_DESCRIPTION);
                        user.setState(UserStates.QUICK_PRINT);
                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                        setReply(UserStates.CALC_PRODUCT);
                    } else if (update.getCallbackQuery().getData().equals(KEY_A4_CL.toString()) ||
                            update.getCallbackQuery().getData().equals(KEY_A4_BW.toString()) ||
                            update.getCallbackQuery().getData().equals(KEY_A3_CL.toString()) ||
                            update.getCallbackQuery().getData().equals(KEY_A3_BW.toString())) {
                        System.out.println("KEY: " + update.getCallbackQuery().getData());
                        user.setState(UserStates.SELECTED_SIZE_COLOR);
                    }
                } else setInvalidStatus();
                break;
            case QUICK_PRINT_DESCRIPTION:
                System.out.println("case: QUICK_PRINT_DESCRIPTION -> User " + getChatID(update));
                setReply(UserStates.QUICK_PRINT);
            case QUICK_PRINT:
                System.out.println("case: QUICK_PRINT -> User " + getChatID(update));
                setReply(UserStates.QUICK_PRINT);
            case SELECTED_SIZE_COLOR:
                System.out.println("case: SELECTED_SIZE_COLOR -> User " + getChatID(update));
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        System.out.println("--> KEY_A4_BW selected");
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        System.out.println("--> KEY_A4_CL selected");
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        System.out.println("--> KEY_A3_BW selected");
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        System.out.println("--> KEY_A3_CL selected");
                        // todo add info and id to order item
                    }
                    setReply(UserStates.SELECTED_SIZE_COLOR);
                } else setInvalidStatus();
                break;
            case FILE_ADDED:
                System.out.println("case: FILE_ADDED -> User " + getChatID(update));
                if (update.hasMessage() && update.getMessage().hasDocument()) {
                    // todo create class for downloading file
                    // todo attach into current item this file path
                    setReply(UserStates.ONE_MORE_FILE);
                }
                break;
            case ONE_MORE_FILE:
                System.out.println("case: ONE_MORE_FILE -> User " + getChatID(update));
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_ONE_MORE_FILE)) {
                        setReply(UserStates.SELECTED_SIZE_COLOR);
                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER)) {
                        setReply(UserStates.SEND_ORDER);
                    }
                } else setInvalidStatus();
                break;
            case SEND_ORDER:
                System.out.println("case: SEND_ORDER -> User " + getChatID(update));
                if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().equals(KEY_SEND_QUICK_PRINT_ORDER)) {
                        // todo send order to email
                        // todo if email sended succesfuly? set UserSates.ORDER_COMPETE
                    }
                }
                break;
            case ORDER_COMPLETE:
                System.out.println("case: ORDER_COMPLETE -> User " + getChatID(update));
                // todo
                //  if  UserSates.ORDER_COMPETE?
                //  setReply with order confirmation and id
                //  else
                //  set UserSates.ERROR
                break;
            case ERROR:
                System.out.println("case: ERROR -> User " + getChatID(update));
                // todo if KEY_TRY_AGAIN
                //  setReply(UserStates.QUICK_PRINT_DESCRIPTION)
                break;
            case CALC_PRODUCT:
                System.out.println("case: CALC_PRODUCT -> User " + getChatID(update));
                break;
        }

    }

    private void setReply(UserStates newState) {
//        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
        System.out.println("User " + getChatID(update) + " State -> " + userState);
        if (userState.equals(UserStates.QUICK_PRINT_DESCRIPTION)) {
            setReply(UserStates.QUICK_PRINT);
            user.setState(UserStates.SELECTED_SIZE_COLOR);
        }
//        if (userState.equals(UserStates.QUICK_PRINT)){
//            new StateHandler(UserStates.SELECTED_SIZE_COLOR, update);
//        }

    }

    private void setInvalidStatus() {
        user.setState(UserStates.INVALID_CHOSE);
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOSE, update);
    }

    public long getChatID(Update update) {
        long chatId = 0;
        if (update.getMessage() != null && update.hasMessage()) {
            chatId = update.getMessage().getChatId();
        } else if (update.getCallbackQuery() != null && update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        return chatId;
    }
}
