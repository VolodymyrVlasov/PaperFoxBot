package com.ua.paperfox.postgres;

import com.ua.paperfox.constants.config.ConfigData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * jdbc:postgresql://<DB_HOST>:<DB_PORT>/<DB_NAME>?user=<DB_USER)>&password=<DB_PASSWORD>&ssl=true
 * */

public class DBManager {

    public static String getDBurl() {
        StringBuilder sb = new StringBuilder("jdbc:postgresql://");
        sb.append(ConfigData.DB_HOST);
        sb.append(":");
        sb.append(ConfigData.DB_PORT);
        sb.append("/");
        sb.append(ConfigData.DB_NAME);
        sb.append("?user=");
        sb.append(ConfigData.DB_USER);
        sb.append("&password=");
        sb.append(ConfigData.DB_PASSWORD);
        sb.append("&ssl=false");

        return sb.toString();
    }

    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(getDBurl());
    }
}
