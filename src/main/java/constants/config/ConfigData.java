package constants.config;

public class ConfigData {
    //Telegram auth const
    public static final String TLGM_TOKEN = System.getenv("TLGM_TOKEN");
    public static final String TLGM_USER_NAME =System.getenv("TLGM_USER_NAME");
    //Email auth const
    public static final String MAIL_RECIPIENT = System.getenv("MAIL_RECIPIENT");

    public static final String MAIL_SENDER = System.getenv("MAIL_SENDER");
    public static final String MAIL_SERVER_HOST = System.getenv("MAIL_SERVER_HOST");
    public static final String MAIL_SMTP_PORT = System.getenv("MAIL_SMTP_PORT");
    public static final String MAIL_USER = System.getenv("MAIL_USER");
    public static final String MAIL_PASSWORD = System.getenv("MAIL_PASSWORD");

}
