package models.bots;

import constants.Admin;
import constants.TelegramBotMessages_UA;
import contollers.handlers.StateHandler;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import models.users.ChatUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://github.com/SergioViacheslaev/MyWizardTelegramBot

public class CustomTelegramBot extends TelegramLongPollingBot {
    private static CustomTelegramBot instance;
    private Map<Long, ChatUser> users = new HashMap<>();

    private CustomTelegramBot() {
    }

    public static CustomTelegramBot getInstance() {
        if (instance == null) {
            instance = new CustomTelegramBot();
        }
        return instance;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        if (update.hasMessage() && update.getMessage() != null) {
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery() && update.getCallbackQuery() != null) {
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        if (!users.containsKey(chatId)) {
            users.put(chatId, new ChatUser(chatId));
        }
        new StateHandler(users.get(chatId), update);
    }

    public synchronized void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
//            SendMessage sendMsg = new SendMessage();
//            sendMsg.enableMarkdown(true);
//            sendMsg.setChatId(sendMessage.getChatId());
//            sendMsg.setText("");
//            setMainMenuButtons(sendMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Admin.TLGM_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return Admin.TLGM_TOKEN;
    }


    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // парсим некоторые данные о погоде
    public static String getFilePath(String resultJson) {
        String filePath = "";
        System.out.println("109: getFilePath(): " + filePath + " resultJson = " + resultJson);
        JSONObject jsonObject = new JSONObject(resultJson);
        JSONObject json = new JSONObject(jsonObject.get("result").toString());
        filePath = json.getString("file_path");
        System.out.println("\n121 filePath = " + filePath);
        return filePath;
    }

    public synchronized void setMainMenuButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("На початок"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Допомога"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}


//        String message = update.getMessage().getText();
//        if (update.getMessage().hasDocument()) {
//            URL link;
//
//            try {
//                link = new URL("https://api.telegram.org/bot" + Admin.TLGM_TOKEN +
//                        "/getFile?file_id=" + update.getMessage().getDocument().getFileId());
//                new EmailSenderService().sendEmail(new File("https://api.telegram.org/file/bot" +
//                        Admin.TLGM_TOKEN + "/" + getFilePath(parseUrl(link))));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//            //new EmailSenderService().sendEmail(file);
//            // https://api.telegram.org/bot<bot_token>/getFile?file_id=the_file_id
//            //   https://api.telegram.org/bot1570392341:AAEUNtXXA47uPa_K0-WvS6RwWILkjoxlThQ/getFile?file_id=BQACAgIAAxkBAAIBTl_9lemztusuqza93y3kCpKUka7hAAIUCwACw87oS5OrlMnRvjlrHgQ
//            //https://api.telegram.org/file/bot1570392341:AAEUNtXXA47uPa_K0-WvS6RwWILkjoxlThQ/documents/file_0.jpg
//        }
//        switch (message) {
//            case "На початок":
//                sendStartMsg(update.getMessage().getChatId().toString());
//                break;
//            case "/start":
//                sendStartMsg(update.getMessage().getChatId().toString());
//                break;
//            case "Допомога":
//                sendMsg(update.getMessage().getChatId().toString(), "/help");
//                break;
//            default:
//                if (message != null) {
//                    sendMsg(update.getMessage().getChatId().toString(), message);
//                }
//        }

