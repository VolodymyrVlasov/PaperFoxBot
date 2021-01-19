package contollers.handlers;

import models.users.conditions.UserQueryStates;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StateHandler {

    public StateHandler(UserStates userState, Update update) {
        TelegramMessageFactory telegramMessageFactory = new TelegramMessageFactory();

        switch (userState) {
            case USER_CONNECTED:
                if ( update.getMessage() != null && update.getMessage().hasText() ) {
                    if (update.getMessage().getText().equals("/start")) {
                        telegramMessageFactory.createReplyMessage(userState, update);
                    }
                } else if (update.getCallbackQuery() != null && update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(UserQueryStates.KEY_QUICK_PRINT.toString())) {
                        userState = UserStates.QUICK_PRINT;
                        telegramMessageFactory.createReplyMessage(userState, update);
                    } else if (update.getCallbackQuery().getData().equals(UserQueryStates.KEY_CALC_PRODUCT.toString())) {
                        userState = UserStates.CALC_PRODUCT;
                        telegramMessageFactory.createReplyMessage(userState, update);
                    }
                } else {
                    //todo send default message
                }
                break;
            case QUICK_PRINT:
                break;
            case SELECTED_SIZE_COLOR:
                break;
            case FILE_ADDED:
                break;
            case SEND_ORDER:
                break;
            case ORDER_COMPLETE:
                break;
            case ERROR:

        }

    }
}
