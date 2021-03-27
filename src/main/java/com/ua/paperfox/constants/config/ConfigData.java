package com.paperfox.ua.constants.config;

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
    //Path settings
    public static final String ROOT_PATH = "src/main/java/data/temp/";

    //DataBase
    public static final String DB_HOST = System.getenv("DB_HOST");
    public static final String DB_PORT = System.getenv("DB_PORT");
    public static final String DB_NAME = System.getenv("DB_NAME");
    public static final String DB_USER = System.getenv("DB_USER");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");



}
