package contollers.handlers;

import models.users.conditions.UserQueryStates;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import static models.users.conditions.UserQueryStates.*;

public class StateHandler {
    TelegramMessageFactory telegramMessageFactory;
    Update update;
    UserStates userSate;

    public StateHandler(UserStates userState, Update update) {
        this.update = update;
        telegramMessageFactory = new TelegramMessageFactory();
        switch (userState) {
            case USER_CONNECTED:
                if (update.hasMessage() && update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/start")) {
                        setReply(userState);
                    }
                } else if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                        setReply(UserStates.QUICK_PRINT_DESCRIPTION);
                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                        setReply(UserStates.CALC_PRODUCT);
                    }
                } else setInvalidStatus();

                break;
            case QUICK_PRINT_DESCRIPTION:
                setReply(UserStates.QUICK_PRINT);
            case QUICK_PRINT:
                setReply(UserStates.QUICK_PRINT);
                break;
            case SELECTED_SIZE_COLOR:
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        // todo add info and id to order item
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        // todo add info and id to order item
                    }
                    setReply(UserStates.SELECTED_SIZE_COLOR);
                } else setInvalidStatus();
                break;
            case FILE_ADDED:
                if (update.hasMessage() && update.getMessage().hasDocument()) {
                    // todo create class for downloading file
                    // todo attach into current item this file path
                    setReply(UserStates.ONE_MORE_FILE);
                }
                break;
            case ONE_MORE_FILE:
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
                if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().equals(KEY_SEND_QUICK_PRINT_ORDER)){
                        // todo send order to email
                        // todo if email sended succesfuly? set UserSates.ORDER_COMPETE
                    }
                }
                break;
            case ORDER_COMPLETE:
                // todo
                //  if  UserSates.ORDER_COMPETE?
                //  setReply with order confirmation and id
                //  else
                //  set UserSates.ERROR
                break;
            case ERROR:
                // todo if KEY_TRY_AGAIN
                //  setReply(UserStates.QUICK_PRINT_DESCRIPTION)
                break;
            case CALC_PRODUCT:
                break;
        }

    }

    private void setReply(UserStates newState) {
        userSate = newState;
        telegramMessageFactory.createReplyMessage(newState, update);
    }

    private void setInvalidStatus() {
        userSate = UserStates.INVALID_CHOSE;
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOSE, update);
    }
}
