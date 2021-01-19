package models.utils;

import constants.TelegramBotKeyboards_UA;
import constants.TelegramBotMessages_UA;
import models.bots.CustomTelegramBot;
import models.users.conditions.UserStates;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramMessageFactory {

    public void createReplyMessage(UserStates userState, Update update) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.enableHtml(true);
        message.setChatId(getChatID(update));
        switch (userState) {
            case USER_CONNECTED:
                message.setText(TelegramBotMessages_UA.START_MESSAGE);
                message.setReplyMarkup(TelegramBotKeyboards_UA.getKeyboardForStartMessage());
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case QUICK_PRINT_DESCRIPTION:
                message.setText(TelegramBotMessages_UA.QUICK_PRINT_DESCRIPTION);
                CustomTelegramBot.getInstance().sendMessage(message);
                userState = UserStates.QUICK_PRINT;
                break;
            case QUICK_PRINT:
                message.setText(TelegramBotMessages_UA.QUICK_PRINT);
                message.setReplyMarkup(TelegramBotKeyboards_UA.getKeyboardForQuickPrint());
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case SELECTED_SIZE_COLOR:
                message.setText(TelegramBotMessages_UA.UPLOAD_FILE_DESCRIPTION);
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case FILE_ADDED:
                message.setText("Файл додано");
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case ONE_MORE_FILE:
                break;
            case SEND_ORDER:
                break;
            case ORDER_COMPLETE:
                break;
            case ERROR:
        }
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
