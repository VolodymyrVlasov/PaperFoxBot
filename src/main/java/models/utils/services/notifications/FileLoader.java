package models.utils.services.notifications;

import constants.Admin;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileLoader {
    private File localFilePath;
    private URL remoteFilePath;
    private String fileName = "src/main/java/data/temp/";

    public File attachFile(Update update) {
        return localFilePath = getLocalFilePath(getRemoteFilePath(update), fileName);
    }

    private File getLocalFilePath(URL url, String file) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            localFilePath = new File(fileName);
        }
        return localFilePath;
    }

    public URL getRemoteFilePath(Update update) {
        fileName = fileName + update.getMessage().getDocument().getFileName();
        try {
            URL link = new URL("https://api.telegram.org/bot" + Admin.TLGM_TOKEN + "/getFile?file_id=" +
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
                    "https://api.telegram.org/file/bot" + Admin.TLGM_TOKEN + "/" + json.getString("file_path"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteFilePath;
    }
}

// https://api.telegram.org/bot<bot_token>/getFile?file_id=the_file_id/
// https://api.telegram.org/bot1570392341:AAEUNtXXA47uPa_K0-WvS6RwWILkjoxlThQ/getFile?file_id=BQACAgIAAxkBAAIBTl_9lemztusuqza93y3kCpKUka7hAAIUCwACw87oS5OrlMnRvjlrHgQ
// https://api.telegram.org/file/bot1570392341:AAEUNtXXA47uPa_K0-WvS6RwWILkjoxlThQ/documents/file_0.jpg