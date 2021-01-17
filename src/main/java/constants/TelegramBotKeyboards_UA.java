package constants;

import models.users.conditions.BotKeyboardQueryStates;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TelegramBotKeyboards_UA {

    public static InlineKeyboardMarkup getKeyboardForStartMessage() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_QUICK_PRINT.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_QUICK_PRINT.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_CALC_PRODUCT.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_CALC_PRODUCT.toString()));

        return new InlineKeyboardMarkup().setKeyboard(getKeyboard(buttons.size(), buttons));
    }

    public static InlineKeyboardMarkup getKeyboardForQuickPrint() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_A4_BW.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_A4_BW.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_A4_CL.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_A4_CL.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_A3_BW.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_A3_BW.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(BotKeyboardQueryStates.KEY_A3_CL.getValue())
                .setCallbackData(BotKeyboardQueryStates.KEY_A3_CL.toString()));

        return new InlineKeyboardMarkup().setKeyboard(getKeyboard(buttons.size(), buttons));
    }


    private static List<List<InlineKeyboardButton>> getKeyboard(int rows, List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> fullKeyboard = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<InlineKeyboardButton> buttonColumns = new ArrayList<>();
            buttonColumns.add(buttons.get(i));
            fullKeyboard.add(buttonColumns);
        }
        return fullKeyboard;
    }
}