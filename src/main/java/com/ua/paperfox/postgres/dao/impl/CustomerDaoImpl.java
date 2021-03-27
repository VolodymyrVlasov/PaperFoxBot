package com.ua.paperfox.postgres.dao.impl;

import com.ua.paperfox.models.customer.Customer;
import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.postgres.DBManager;
import com.ua.paperfox.postgres.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void createCustomer(Customer customer) throws SQLException {
        Connection dbConn = DBManager.getDBConnection();
        String sql = "INSERT INTO customer(firstname, lastname, phonenumber, email)" +
                "VALUES (" + customer.firstName + "," +
                customer.lastName + "," + customer.phoneNumber + "," + customer.email + ");";
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet operationResult = ps.executeQuery();

        dbConn.close();
    }

    @Override
    public void createTelegramCustomer(TelegramCustomer customer) throws SQLException {
        Connection dbConn = DBManager.getDBConnection();
        String sql = "INSERT INTO customer(firstname, lastname, phonenumber, email) VALUES ('" + customer.firstName + "','" +
                customer.lastName + "','" + customer.phoneNumber + "','" + customer.email + "');";
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ps.execute();

        dbConn.close();

    }
}
