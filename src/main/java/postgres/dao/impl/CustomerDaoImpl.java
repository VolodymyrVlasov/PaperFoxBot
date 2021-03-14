package postgres.dao.impl;

import models.customer.Customer;
import models.customer.TelegramCustomer;
import postgres.DBManager;
import postgres.dao.CustomerDao;

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
