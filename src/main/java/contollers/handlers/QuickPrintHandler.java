package contollers.handlers;

import models.products.categories.digitalPrints.PlainPrint;
import models.shop.OrderCart;
import models.users.TelegramUser;
import models.users.conditions.UserQueryStates;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import models.utils.services.notifications.FileLoader;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

import static models.users.conditions.UserQueryStates.*;

public class QuickPrintHandler {
    private TelegramMessageFactory telegramMessageFactory;
    private Update update;
    private TelegramUser user;
    private OrderCart orderCart;
    private int itemCount = 0;

    public QuickPrintHandler(TelegramUser user, Update update) {
        this.update = update;
        this.user = user;
        orderCart = user.getOrderCart();
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
//                handleUpdateEvent();
            }
            case SELECT_SIZE_COLOR -> {
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        orderCart.addItem(new PlainPrint("A4", false));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        orderCart.addItem(new PlainPrint("A4", true));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        orderCart.addItem(new PlainPrint("A3", false));
                        user.setState(UserStates.UPLOADING_FILES);
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        orderCart.addItem(new PlainPrint("A3", true));
                        user.setState(UserStates.UPLOADING_FILES);
                    }
                } else setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
            }
            case UPLOADING_FILES -> {
                if (update.hasMessage()) {
                    if (update.getMessage().hasDocument()) {
                        File file = new FileLoader().attachFile(update);
                        // TODO
                        //  1 file format and mimetype validation
                        //  2 handle if attache more than one file
                        user.getOrderCart().getLastItem().attachFile(file);
                        waitForMultiLoaded();
                    } else if (update.getMessage().hasPhoto()) {
                        setInvalidInputAndGoToState(UserStates.UPLOADING_FILES);
                    }
                    if (update.getMessage().hasText()) {
                        String query = update.getMessage().getText();
                        if (query.equals(UserQueryStates.KEY_OTHER_PRODUCT.getValue())) {
                            setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                            user.setState(UserStates.SELECT_SIZE_COLOR);
                        } else if (query.equals(KEY_CANCEL_ORDER.getValue())) {
                            user.setState(UserStates.UPLOADING_FILES);
                            handleUpdateEvent();
                        } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.getValue())) {
                            setReplyAndChangeStateTo(UserStates.SEND_ORDER);
                            handleUpdateEvent();
                        }
                    }
//                    else setInvalidInputAndGoToState(UserStates.ONE_MORE_FILE);
                }
//                else {
//                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
//                }
            }
            case ONE_MORE_FILE -> {
//                if (update.hasMessage() && update.getMessage().hasText()) {
//                    String query = update.getMessage().getText();
//                    if (query.equals(UserQueryStates.KEY_OTHER_PRODUCT.getValue())) {
//                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
//                        user.setState(UserStates.SELECT_SIZE_COLOR);
//                    } else if (query.equals(KEY_MORE_FILES_NEED.getValue())) {
//                        user.setState(UserStates.UPLOADING_FILES);
//                        handleUpdateEvent();
//                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.getValue())) {
//                        setReplyAndChangeStateTo(UserStates.SEND_ORDER);
//                        handleUpdateEvent();
//                    }
//                } else if (update.hasMessage() && update.getMessage().hasDocument()) {
//                    user.setState(UserStates.UPLOADING_FILES);
//                    handleUpdateEvent();
//                } else setInvalidInputAndGoToState(UserStates.ONE_MORE_FILE);
            }
            case SEND_ORDER -> {
                sendOrder();
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
        new Thread(() -> {
            if (!user.isKeyboardSend()) {
                setReply(UserStates.ONE_MORE_FILE);
                user.setKeyboardSend(true);
            }
        }).start();
    }

    private void sendOrder() {
        System.out.println(orderCart.toString());
        // todo send order to email
        //  if email sent successful? set UserSates.ORDER_COMPETE and call  handleUpdateEvent();
        user.setState(UserStates.ORDER_COMPLETE);
        handleUpdateEvent();
    }

    private void setInvalidInputAndGoToState(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOICE, update);
        user.setState(newState);
    }
}
