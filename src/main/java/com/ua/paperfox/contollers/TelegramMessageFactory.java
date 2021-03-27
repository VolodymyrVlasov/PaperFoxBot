package com.paperfox.ua.contollers;

import com.paperfox.ua.constants.messages.ua_UA.TelegramKeyboards;
import com.paperfox.ua.constants.messages.ua_UA.TelegramMessages;
import com.paperfox.ua.models.bots.CustomTelegramBot;
import com.paperfox.ua.models.customer.TelegramCustomer;
import com.paperfox.ua.models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class TelegramMessageFactory {

    public void createReplyMessage(TelegramCustomer user, Update update) {
        UserStates userState = user.getState();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
//        message.enableMarkdownV2(true);
        message.enableHtml(true);
        message.setChatId(getChatID(update));
        switch (userState) {
            case USER_CONNECTED -> {
                message.setText(TelegramMessages.START_MESSAGE);
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForStartMessage());
            }
            case QUICK_PRINT_DESCRIPTION -> {
                message.setText(TelegramMessages.QUICK_PRINT_DESCRIPTION);
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForNormalState());
            }
            case QUICK_PRINT -> {
                message.setText(TelegramMessages.QUICK_PRINT);
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForQuickPrint());
            }
            case SELECT_SIZE_COLOR -> message.setText(TelegramMessages.UPLOAD_FILE_DESCRIPTION);
            case UPLOADING_FILES -> message.setText("\uD83D\uDC49 додано файл: + file_name");
            case ONE_MORE_FILE -> {
                message.setText("⚠ Коли завантажаться всі файли ⚠\n" +
                        "\uD83D\uDC47 оберіть дію на клавіатурі \uD83D\uDC47 ");
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForUploadingFilesState());
            }
            case INVALID_CHOICE -> message.setText(TelegramMessages.INVALID_CHOICE);

            case SEND_ORDER -> {
                message.setText("Оформлюю замовлення, зачекайте...");
            }
            case ORDER_COMPLETE -> {
                message.setText("Готово, замовлення #" + user.getShoppingCart().getOrderId());
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForNormalState());
            }
            case ERROR -> message.setText(" \"⚠️ПОМИЛКА!!!\n" +
                    "Вибачте, щось пішло не так." +
                    "Будь ласка, спробуйте ще раз");
            case CHOOSE_PRODUCT-> {
                message.setText("Оберіть категорію продукту:");
                message.setReplyMarkup(TelegramKeyboards.getKeyboardForChooseProduct());

            }
            case INVALID_FILE -> message.setText(TelegramMessages.ADD_FILE);
        }
        CustomTelegramBot.getInstance().sendMessage(message);
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
