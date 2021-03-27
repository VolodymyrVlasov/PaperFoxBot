package com.paperfox.ua.contollers;

import com.paperfox.ua.contollers.managers.ProductChooserMenu;
import com.paperfox.ua.contollers.managers.QuickPrintMenu;
import javassist.NotFoundException;
import com.paperfox.ua.models.customer.TelegramCustomer;
import com.paperfox.ua.models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.paperfox.ua.constants.messages.ua_UA.TelegramMessages.RUN_BOT;
import static com.paperfox.ua.models.customer.conditions.UserQueryStates.*;

public class MainMenu {
    private Update update;
    private TelegramCustomer user;

    public MainMenu(TelegramCustomer user, Update update) {
        this.update = update;
        this.user = user;
        handleUpdateEvent();
    }

    private void handleUpdateEvent() {
        checkForStartCommand();
        try {
            if (update.hasCallbackQuery()) {
                if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                    user.setState(UserStates.QUICK_PRINT_DESCRIPTION);
                    new QuickPrintMenu(user, update);
                } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                    user.setState(UserStates.CALC_PRODUCT);
                    new ProductChooserMenu(user, update);
                } else {
                    // redirect to other state handlers
                    new QuickPrintMenu(user, update);
                }
            } else {
                // redirect to other state handlers
                new QuickPrintMenu(user, update);
            }
        } catch (NotFoundException e) {
            // todo: send telegram message
        }
    }

    private void checkForStartCommand() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals(RUN_BOT) || update.getMessage().getText().equals(KEY_CANCEL_ORDER.getValue())) {
                user.setState(UserStates.USER_CONNECTED);
                new TelegramMessageFactory().createReplyMessage(user, update);
            }
        }
    }


}
