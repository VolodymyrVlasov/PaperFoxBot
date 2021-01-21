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
                        setReplyAndChangeStateTo(user.getState());
                        user.setPassportFields(update);
                    } else {
                        setInvalidInputAndGoToState(UserStates.USER_CONNECTED);
                    }
                } else if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().getData().equals(KEY_QUICK_PRINT.toString())) {
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                        user.setState(UserStates.SELECT_SIZE_COLOR);
                        // setReply(UserStates.SELECT_SIZE_COLOR);
                    } else if (update.getCallbackQuery().getData().equals(KEY_CALC_PRODUCT.toString())) {
                        setReplyAndChangeStateTo(UserStates.CALC_PRODUCT);
                        setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                    }
                    // todo make method to check invalid input for each user state
                } else if (!update.hasCallbackQuery() || update.getMessage().hasDocument() || update.getMessage().getText().equals("/start")) {
                    setInvalidInputAndGoToState(UserStates.USER_CONNECTED);
                }
                break;


            case QUICK_PRINT:
                if (!update.hasCallbackQuery() || update.getMessage().hasDocument()) {
                    setInvalidInputAndGoToState(UserStates.QUICK_PRINT);
                    // todo make method to check invalid input for each user state
                } else if (update.getMessage().getText().equals("/start")) {
                    setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                }


            case SELECT_SIZE_COLOR:
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_A4_BW.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A4_BW.getValue()));
                        // todo  -> make new child of PrintingProduct
                        setReplyAndChangeStateTo(UserStates.SELECT_SIZE_COLOR);
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A4_CL.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A4_CL.getValue()));
                        // todo  -> make new child of PrintingProduct
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A3_BW.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A3_BW.getValue()));
                        // todo  -> make new child of PrintingProduct
                        user.setState(UserStates.FILE_ADDED);
                    } else if (query.equals(KEY_A3_CL.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_A3_CL.getValue()));
                        // todo  -> make new child of PrintingProduct
                        user.setState(UserStates.FILE_ADDED);
                    }
                    // fixme make method to check invalid input for each user state
                } else if (update.getMessage().getText().equals("/start")) {
                    setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                } else setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                break;


            case FILE_ADDED:
                System.out.println("case FILE_ADDED");
                if (update.hasMessage() && update.getMessage().hasDocument()) {
                    System.out.println("file received");
                    // todo create class for downloading file
                    // -> download file and add file_path to item
                    // todo attach into current item this file path
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
                    setReplyAndChangeStateTo(UserStates.ONE_MORE_FILE);
                } else {
                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                }
                break;

            case ONE_MORE_FILE:
                if (update.hasCallbackQuery()) {
                    String query = update.getCallbackQuery().getData();
                    if (query.equals(KEY_ONE_MORE_FILE.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_ONE_MORE_FILE.getValue()));
//                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT_DESCRIPTION);
                        setReplyAndChangeStateTo(UserStates.QUICK_PRINT);
                        user.setState(UserStates.SELECT_SIZE_COLOR);
                    } else if (query.equals(KEY_SEND_QUICK_PRINT_ORDER.toString())) {
                        CustomTelegramBot.getInstance().sendMessage(new SendMessage()
                                .setChatId(user.getChatID())
                                .setText("your choice is: " + KEY_SEND_QUICK_PRINT_ORDER.getValue()));
                        setReplyAndChangeStateTo(UserStates.SEND_ORDER);
                    }
                    // todo make method to check invalid input for each user state
                } else setInvalidInputAndGoToState(UserStates.ONE_MORE_FILE);
                break;


            case SEND_ORDER:
                if (update.hasCallbackQuery()) {
                    if (update.getCallbackQuery().equals(KEY_SEND_QUICK_PRINT_ORDER)) {
                        // todo send order to email
                        // todo if email sent successful? set UserSates.ORDER_COMPETE

                        setReplyAndChangeStateTo(UserStates.ORDER_COMPLETE);
                    }
                }
                // todo make method to check invalid input for each user state
                break;


            case ORDER_COMPLETE:
                // todo
                //  if  UserSates.ORDER_COMPETE?
                //  setReply with order confirmation and id
                //  else
                //  set UserSates.ERROR
                setReplyAndChangeStateTo(UserStates.USER_CONNECTED);
                break;


            case ERROR:
                // todo if KEY_TRY_AGAIN
                //  setReply(UserStates.QUICK_PRINT_DESCRIPTION)
        }
    }

    private void setReplyAndChangeStateTo(UserStates newState) {
        user.setState(newState);
        telegramMessageFactory.createReplyMessage(newState, update);
        System.out.println("User: id=" + user.getChatID() + " name=" + user.getFirstName() + " " + user.getFamilyName() + " State -> " + userState);
    }

    private void setReplyWithoutChangeState() {

    }

    private void setInvalidInputAndGoToState(UserStates newState) {
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
