package constants.messages.ua_UA;

import models.users.conditions.UserQueryStates;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramKeyboards {
    public static InlineKeyboardMarkup getKeyboardForStartMessage() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_QUICK_PRINT.getValue())
                .setCallbackData(UserQueryStates.KEY_QUICK_PRINT.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_CALC_PRODUCT.getValue())
                .setCallbackData(UserQueryStates.KEY_CALC_PRODUCT.toString()));
        return new InlineKeyboardMarkup().setKeyboard(getKeyboard(buttons.size(), buttons));
    }

    public static InlineKeyboardMarkup getKeyboardForQuickPrint() {

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_A4_BW.getValue())
                .setCallbackData(UserQueryStates.KEY_A4_BW.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_A4_CL.getValue())
                .setCallbackData(UserQueryStates.KEY_A4_CL.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_A3_BW.getValue())
                .setCallbackData(UserQueryStates.KEY_A3_BW.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_A3_CL.getValue())
                .setCallbackData(UserQueryStates.KEY_A3_CL.toString()));
        return new InlineKeyboardMarkup().setKeyboard(getKeyboard(buttons.size(), buttons));
    }

    public static ReplyKeyboard getKeyboardForOneMoreFile() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_OTHER_PRODUCT.getValue())
                .setCallbackData(UserQueryStates.KEY_OTHER_PRODUCT.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_SEND_QUICK_PRINT_ORDER.getValue())
                .setCallbackData(UserQueryStates.KEY_SEND_QUICK_PRINT_ORDER.toString()));
        return new InlineKeyboardMarkup().setKeyboard(getKeyboard(buttons.size(), buttons));
    }

    public static ReplyKeyboardMarkup getKeyboardForUploadingFilesState() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(UserQueryStates.KEY_OTHER_PRODUCT.getValue());
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(UserQueryStates.KEY_SEND_QUICK_PRINT_ORDER.getValue());
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(UserQueryStates.KEY_CANCEL_ORDER.getValue());
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }

    public static ReplyKeyboardMarkup getKeyboardForNormalState() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(UserQueryStates.KEY_CANCEL_ORDER.getValue());
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }

    public static InlineKeyboardMarkup getKeyboardForChooseProduct() {

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_STICKERS.getValue())
                .setCallbackData(UserQueryStates.KEY_STICKERS.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_CARDS.getValue())
                .setCallbackData(UserQueryStates.KEY_CARDS.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_BIZ_CARDS.getValue())
                .setCallbackData(UserQueryStates.KEY_BIZ_CARDS.toString()));
        buttons.add(new InlineKeyboardButton()
                .setText(UserQueryStates.KEY_FLYERS.getValue())
                .setCallbackData(UserQueryStates.KEY_FLYERS.toString()));
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