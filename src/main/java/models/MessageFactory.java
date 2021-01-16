package models;

import constants.BotMessages_UA;
import models.bots.CustomTelegramBot;
import models.users.UserStates;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MessageFactory {

    public void createBackMessage(UserStates userState, Update update) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.enableHtml(true);
        message.setChatId(update.getMessage().getChatId());
        switch (userState) {
            case USER_CONNECTED:
                message.setText(BotMessages_UA.START_MESSAGE);
                message.setReplyMarkup(setMessageButton());
                CustomTelegramBot.getInstance().sendMessage(message);
                break;
            case QUICK_PRINT:
                break;
            case SELECTED_SIZE_COLOR:
                break;
            case FILE_ADDED:
                break;
            case SEND_ORDER:
                break;
            case ORDER_COMPLETE:
                break;
            case ERROR:
        }
    }

    private InlineKeyboardMarkup setMessageButton() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        InlineKeyboardButton btn1 = new InlineKeyboardButton();

        btn1.setText("Простий друк (відправити файли)");
        btn1.setCallbackData("простий друк");
        buttons1.add(btn1);

        InlineKeyboardButton btn2 = new InlineKeyboardButton();
        btn2.setText("Розрахувати вартість продукції");
        btn2.setCallbackData("розрахунок");
        buttons2.add(btn2);


        buttons.add(buttons1);
        buttons.add(buttons2);
        buttons.add(buttons3);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
}
