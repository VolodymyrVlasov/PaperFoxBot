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
                break;
            case QUICK_PRINT:
                message.setText(TelegramBotMessages_UA.QUICK_PRINT);
                message.setReplyMarkup(TelegramBotKeyboards_UA.getKeyboardForQuickPrint());
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case SELECT_SIZE_COLOR:
                message.setText(TelegramBotMessages_UA.UPLOAD_FILE_DESCRIPTION);
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case FILE_ADDED:
                message.setText("\uD83D\uDC49 додано файл: + file_name");
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case ONE_MORE_FILE:
                message.setText("Додати це файл?");
                message.setReplyMarkup(TelegramBotKeyboards_UA.getKeyboardForOneMoreFile());
                break;
            case INVALID_CHOICE:
                message.setText(TelegramBotMessages_UA.INVALID_CHOICE);
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case SEND_ORDER:
                message.setText("Надсилаю замовлення, зачекайте...");
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case ORDER_COMPLETE:
                message.setText("Замовлення оформлене, ідентифікатор 1234");
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case ERROR:
                message.setText(" \"⚠️ПОМИЛКА!!!\n" +
                                "Вибачте, щось пішло не так." +
                                "Будь ласка, спробуйте ще раз");
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case CALC_PRODUCT:
                CustomTelegramBot.getInstance().sendMessage(message.setText(TelegramBotMessages_UA.SECTION_IN_DEVEPMENT));
                break;
            case INVALID_FILE:
                CustomTelegramBot.getInstance().sendMessage(message.setText(TelegramBotMessages_UA.ADD_FILE));
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
