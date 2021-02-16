package contollers.handlers;

import models.bots.CustomTelegramBot;
import models.products.categories.digitalPrints.PlainPrint;
import models.shop.Order;
import models.shop.ShoppingCart;
import models.users.TelegramUser;
import models.users.conditions.UserQueryStates;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import models.utils.services.mailServices.FileLoader;
import models.utils.services.mailServices.MailStates;
import org.telegram.telegrambots.meta.api.objects.Update;

import static models.users.conditions.UserQueryStates.*;

public class QuickPrintHandler {
    private TelegramMessageFactory telegramMessageFactory;
    private Update update;
    private TelegramUser user;
    private ShoppingCart shoppingCart;

    public QuickPrintHandler(TelegramUser user, Update update) {
        this.update = update;
        this.user = user;
        shoppingCart = user.getShoppingCart();
        telegramMessageFactory = new TelegramMessageFactory();
        handleUpdateEvent();
    }

    private void handleUpdateEvent() {
        switch (user.getState()) {
            case QUICK_PRINT_DESCRIPTION -> {
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                user.setState(UserStates.QUICK_PRINT);
                handleUpdateEvent();
            }
            case QUICK_PRINT -> {
                setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                user.setState(UserStates.SELECT_SIZE_COLOR);
            }
            case SELECT_SIZE_COLOR -> {
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        shoppingCart.addItem(new PlainPrint("A4", false));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        shoppingCart.addItem(new PlainPrint("A4", true));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        shoppingCart.addItem(new PlainPrint("A3", false));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        shoppingCart.addItem(new PlainPrint("A3", true));
                        user.setState(UserStates.UPLOADING_FILES);
                    }
                } else setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
            }
            case UPLOADING_FILES -> {
                if (update.hasMessage()) {
                    if (update.getMessage().hasDocument()) {
                        // TODO
                        //  1 file format and mimetype validation
                        //  2 handle if attache more than one file
                        user.getShoppingCart().getLastItem().attachFile(new FileLoader().attachFile(update));
                        waitForMultiLoaded();
                    } else if (update.getMessage().hasPhoto()) {
                        setReply(UserStates.INVALID_FILE);
                    }
                    if (update.getMessage().hasText()) {
                        String query = update.getMessage().getText();
                        if (query.equals(UserQueryStates.KEY_OTHER_PRODUCT.getValue())) {
                            setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                            user.setState(UserStates.SELECT_SIZE_COLOR);
                        } else if (query.equals(KEY_CANCEL_ORDER.getValue())) {
                            user.setState(UserStates.UPLOADING_FILES);
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
                user.setState(UserStates.USER_CONNECTED);
            }
        }
    }

    private void setReplyAndChangeStateTo(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
    }

    private void setReply(UserStates newState) {
        telegramMessageFactory.createReplyMessage(newState, update);
    }

    public void waitForMultiLoaded() {
        if (!user.isKeyboardSend()) {
            setReply(UserStates.ONE_MORE_FILE);
            user.setKeyboardSend(true);
        }
    }

    private void makeOrder() {
        System.out.println(shoppingCart.toString());
        if (new Order(shoppingCart).handleOrder() == MailStates.OK) {
            user.setState(UserStates.ORDER_COMPLETE);
            if (user.isKeyboardSend()) user.setKeyboardSend(false);
            user.clearShoppingCart();
            handleUpdateEvent();
            CustomTelegramBot.getInstance().setUserState(user.getChatID(), UserStates.USER_CONNECTED);
        } else {
            setReply(UserStates.ERROR);
            makeOrder();
        }
    }

    private void setInvalidInputAndGoToState(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOICE, update);
        user.setState(newState);
    }
}
