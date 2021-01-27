package contollers;

import contollers.handlers.QuickPrintHandler;
import models.users.TelegramUser;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import static constants.TelegramBotMessages_UA.RUN_BOT;
import static models.users.conditions.UserQueryStates.*;

public class Controller {
    private Update update;
    private TelegramUser user;

    public Controller(TelegramUser user, Update update) {
        this.update = update;
        this.user = user;
        handleUpdateEvent();
    }

    private void handleUpdateEvent() {
        checkForStartCommand();
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                user.setState(UserStates.QUICK_PRINT_DESCRIPTION);
                new QuickPrintHandler(user, update);
            } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                // TODO
                //  1 set user state to CALC_PRODUCT
                //  2 create handler for calculate printing product
            } else {
                // redirect to other state handlers
                new QuickPrintHandler(user, update);
            }
        } else {
            // redirect to other state handlers
            new QuickPrintHandler(user, update);
        }

    }

    private void checkForStartCommand() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(RUN_BOT) || update.getMessage().getText().equals(KEY_CANCEL_ORDER.getValue())) {
                new TelegramMessageFactory().createReplyMessage(UserStates.USER_CONNECTED, update);
            }
        }
    }


}
