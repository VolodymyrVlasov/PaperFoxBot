package com.paperfox.ua.contollers.managers;

import javassist.NotFoundException;
import com.paperfox.ua.models.products.categories.digitalPrints.PlainPrint;
import com.paperfox.ua.models.customer.TelegramCustomer;
import com.paperfox.ua.models.customer.conditions.UserQueryStates;
import com.paperfox.ua.models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.paperfox.ua.services.mailServices.FileLoader;

import static com.paperfox.ua.models.customer.conditions.UserQueryStates.*;

public class QuickPrintMenu extends AbstractMenu {

    public QuickPrintMenu(TelegramCustomer user, Update update) throws NotFoundException {
        super(user, update);
    }

    @Override
    public void handleUpdateEvent() {
        switch (super.user.getState()) {
            case QUICK_PRINT_DESCRIPTION -> {
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                super.user.setState(UserStates.QUICK_PRINT);
                handleUpdateEvent();
            }
            case QUICK_PRINT -> {
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                super.user.setState(UserStates.SELECT_SIZE_COLOR);
            }
            case SELECT_SIZE_COLOR -> {
                if (super.update.hasCallbackQuery()) {
                    setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                    String query = super.update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        super.shoppingCart.addItem(new PlainPrint(KEY_A4_BW));
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        super.shoppingCart.addItem(new PlainPrint(KEY_A4_CL));
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        super.shoppingCart.addItem(new PlainPrint(KEY_A3_BW));
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        super.shoppingCart.addItem(new PlainPrint(KEY_A3_CL));
                    }
                    super.user.setState(UserStates.UPLOADING_FILES);
                } else {
                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                }
            }
            case UPLOADING_FILES -> {
                if (super.update.hasMessage()) {
                    if (super.update.getMessage().hasDocument()) {
                        // TODO
                        //  1 file format and mimetype validation
                        //  2 handle if attache more than one file
                        super.user.getShoppingCart().getLastItem().attachFile(
                                new FileLoader().attachFile(super.update, super.user.getShoppingCart()));
                        waitForMultiLoaded();
                    } else if (super.update.getMessage().hasPhoto()) {
                        setReply(UserStates.INVALID_FILE);
                    }
                    if (super.update.getMessage().hasText()) {
                        String query = super.update.getMessage().getText();
                        if (query.equals(UserQueryStates.KEY_OTHER_PRODUCT.getValue())) {
                            setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                            super.user.setState(UserStates.SELECT_SIZE_COLOR);
                        } else if (query.equals(KEY_CANCEL_ORDER.getValue())) {
                            super.user.setState(UserStates.UPLOADING_FILES);
                        } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.getValue())) {
                            setReplyAndChangeStateTo(UserStates.SEND_ORDER);
                            handleUpdateEvent();
                        }
                    }
                }
            }
            case SEND_ORDER -> {
                makeOrder();
            }
            case ORDER_COMPLETE -> {
                setReplyAndChangeStateTo(UserStates.ORDER_COMPLETE);
                super.user.setState(UserStates.USER_CONNECTED);
            }
        }
    }

    @Override
    public void makeOrder() {
        super.makeOrder();
    }
}
