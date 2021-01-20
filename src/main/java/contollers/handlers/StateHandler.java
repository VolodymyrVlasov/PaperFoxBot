package contollers.handlers;

import models.bots.CustomTelegramBot;
import models.users.ChatUser;
import models.users.conditions.UserStates;
import models.utils.TelegramMessageFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
                if (update.hasMessage() && update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/start")) {
                        setReply(user.getState());
                        user.setPassportFields(update);
                    }
                } else if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                        setReply(UserStates.QUICK_PRINT_DESCRIPTION);
                        setReply(UserStates.QUICK_PRINT);
                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                        setReply(UserStates.CALC_PRODUCT);
                        setReply(UserStates.USER_CONNECTED);
                    }
                } else if (!update.hasCallbackQuery() || update.getMessage().hasDocument() || update.getMessage().getText().equals("/start")) {
                    setInvalidChoice(UserStates.USER_CONNECTED);
                }
                break;

            case QUICK_PRINT:
                setReply(UserStates.SELECT_SIZE_COLOR);

                if (!update.hasCallbackQuery() || !update.getMessage().hasDocument()) {
                    setInvalidChoice(UserStates.QUICK_PRINT);
                }
            case SELECT_SIZE_COLOR:
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A4_BW.getValue()));
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A4_CL.getValue()));
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A3_BW.getValue()));
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A3_CL.getValue()));
                    }
                } else if (update.getMessage().getText().equals("/start")) {
                    setReply(UserStates.USER_CONNECTED);
                } else if (update.hasMessage() && update.getMessage().hasDocument()) {
                    // todo add hasPhoto validation and alert message
                    setReply(UserStates.FILE_ADDED);
                } else setInvalidFile(UserStates.SELECT_SIZE_COLOR);
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
                        setReply(UserStates.SELECT_SIZE_COLOR);
                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER)) {
                        setReply(UserStates.SEND_ORDER);
                    }
                } else setInvalidChoice(UserStates.ONE_MORE_FILE);
                break;
            case SEND_ORDER:
                if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().equals(KEY_SEND_QUICK_PRINT_ORDER)) {
                        // todo send order to email
                        // todo if email sended succesfuly? set UserSates.ORDER_COMPETE
                        setReply(UserStates.ORDER_COMPLETE);
                    }
                }
                break;
            case ORDER_COMPLETE:
                // todo
                //  if  UserSates.ORDER_COMPETE?
                //  setReply with order confirmation and id
                //  else
                //  set UserSates.ERROR
                setReply(UserStates.USER_CONNECTED);
                break;
            case ERROR:
                // todo if KEY_TRY_AGAIN
                //  setReply(UserStates.QUICK_PRINT_DESCRIPTION)
        }
    }

    private void setReply(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
        System.out.println("User: id=" + user.getChatID() + " name=" + user.getFirstName() + " " + user.getFamilyName() + " State -> " + userState);
    }

    private void setInvalidChoice(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_CHOICE, update);
        user.setState(newState);
    }

    private void setInvalidFile(UserStates newState) {
        telegramMessageFactory.createReplyMessage(UserStates.INVALID_FILE, update);
        user.setState(newState);
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
