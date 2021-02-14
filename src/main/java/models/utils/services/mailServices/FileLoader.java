package models.utils.services.mailServices;

import constants.config.ConfigData;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class FileLoader {
    private File localFilePath;
    private URL remoteFilePath;
    private String fileName = "";

    public File attachFile(Update update) {
        return getLocalPath(update);
    }

    public File getLocalPath(Update update) {
        int orderNumber = new Random().nextInt(899) + 100;
        String dir = ConfigData.ROOT_PATH +
                GregorianCalendar.getInstance().get(Calendar.YEAR) + "/"
                + getMonth() + "/"
                + GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
                + orderNumber;

        localFilePath = new File(dir);
        if (!localFilePath.exists()) {
            localFilePath.mkdirs();
        }
        downloadFile(getRemoteFilePath(update));
        return localFilePath;
    }

    public URL getRemoteFilePath(Update update) {
        fileName = "/" + update.getMessage().getDocument().getFileName();
        try {
            URL link = new URL("https://api.telegram.org/bot" + ConfigData.TLGM_TOKEN + "/getFile?file_id=" +
                    update.getMessage().getDocument().getFileId());
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONObject json = new JSONObject(jsonObject.get("result").toString());
            remoteFilePath = new URL(
                    "https://api.telegram.org/file/bot" + ConfigData.TLGM_TOKEN + "/" + json.getString("file_path"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteFilePath;
    }

    private void downloadFile(URL url) {
        System.out.println(localFilePath + fileName);
        try {
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(localFilePath + fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMonth() {
        String result;
        switch (GregorianCalendar.getInstance().get(Calendar.MONTH)) {
            case 0 -> result = "01_Январь";
            case 1 -> result = "02_Февраль";
            case 2 -> result = "03_Март";
            case 3 -> result = "04_Апрель";
            case 4 -> result = "05_Май";
            case 5 -> result = "06_Июнь";
            case 6 -> result = "07_Июль";
            case 7 -> result = "08_Август";
            case 8 -> result = "09_Сентябрь";
            case 9 -> result = "10_Октябрь";
            case 10 -> result = "11_Ноябрь";
            default -> result = "12_Декабрь";
        }
        return result;
    }
}

// https://api.telegram.org/bot<<<TOKEN>>>/getFile?file_id=the_file_id/
// https://api.telegram.org/bot<<<TOKEN>>>/getFile?file_id=BQACAgIAAxkBAAIBTl_9lemztusuqza93y3kCpKUka7hAAIUCwACw87oS5OrlMnRvjlrHgQ
// https://api.telegram.org/file/bot<<<TOKEN>>>/documents/file_0.jpg