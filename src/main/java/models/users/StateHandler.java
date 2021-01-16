package models.users;

import models.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StateHandler {

    public StateHandler(UserStates userState, Update update) {
        MessageFactory messageFactory = new MessageFactory();

        switch (userState) {
            case USER_CONNECTED:
                if (update.getMessage().hasText() || update.hasCallbackQuery()) {
                    if (update.getMessage().getText().equals("/start")) {
                        messageFactory.createBackMessage(userState, update);
                    } else if (update.getCallbackQuery().equals("Кнопка 1")) {
                        userState = UserStates.QUICK_PRINT;
                        messageFactory.createBackMessage(userState, update);
                    } else if (update.getCallbackQuery().equals("Кнопка 2")) {
                        userState = UserStates.CALC_PRODUCT;
                        messageFactory.createBackMessage(userState, update);
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
